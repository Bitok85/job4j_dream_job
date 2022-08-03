package ru.job4j.dream.service;

import net.jcip.annotations.GuardedBy;
import org.springframework.stereotype.Service;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PostStore;

import java.util.Collection;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class PostService {

    @GuardedBy("this")
    private volatile PostStore store;

    private final ReentrantLock reentrantLock = new ReentrantLock(true);

    public PostService(PostStore store) {
        this.store = store;
    }

    public Collection<Post> findAll() {
        return store.findAll();
    }

    public void add(Post post) {
        store.add(post);
    }

    public Post findById(int id) {
        return store.findById(id);
    }

    public void update(Post post) {
        store.update(post);
    }
}
