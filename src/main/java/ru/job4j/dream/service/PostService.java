package ru.job4j.dream.service;

import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PostStore;

import java.util.Collection;

public class PostService {

    private static final PostService INST = new PostService();

    public static PostService instOf() {
        return INST;
    }

    private final PostStore store = PostStore.instOf();

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
