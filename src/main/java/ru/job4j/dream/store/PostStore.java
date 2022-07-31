package ru.job4j.dream.store;

import ru.job4j.dream.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private static final PostStore INST = new PostStore();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final AtomicInteger key = new AtomicInteger(0);

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "some text", LocalDateTime.now()));
        posts.put(2, new Post(2, "Middle Java Job", "some text", LocalDateTime.now()));
        posts.put(3, new Post(3, "Senior Java Job", "some text", LocalDateTime.now()));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        key.set(posts.size());
        post.setId(key.incrementAndGet());
        post.setCreated(LocalDateTime.now());
        posts.put(post.getId(), post);
    }

}
