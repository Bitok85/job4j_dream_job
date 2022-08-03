package ru.job4j.dream.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@Repository
@ThreadSafe
public class PostStore {

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final AtomicInteger key = new AtomicInteger(4);
    private final ReentrantLock lock = new ReentrantLock(true);
    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "some text", LocalDateTime.now()));
        posts.put(2, new Post(2, "Middle Java Job", "some text", LocalDateTime.now()));
        posts.put(3, new Post(3, "Senior Java Job", "some text", LocalDateTime.now()));
    }

    public Collection<Post> findAll() {
        lock.lock();
        try {
            return posts.values();
        } finally {
            lock.unlock();
        }

    }

    public void add(Post post) {
        lock.lock();
        try {
            post.setId(key.incrementAndGet());
            post.setCreated(LocalDateTime.now());
            posts.put(post.getId(), post);
        } finally {
            lock.unlock();
        }

    }

    public Post findById(int id) {
        lock.lock();
        try {
            return posts.get(id);
        } finally {
            lock.unlock();
        }

    }

    public void update(Post post) {
        lock.lock();
        try {
            post.setCreated(posts.get(post.getId()).getCreated());
            posts.replace(post.getId(), post);
        } finally {
            lock.unlock();
        }

    }
}
