package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.dream.Main;
import ru.job4j.dream.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PostDBStoreTest {

    private static BasicDataSource pool = new Main().loadPool();

    @AfterEach
    public void wipeTable() throws SQLException {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM POST")
        ) {
            ps.execute();
        }
    }

    @AfterClass
    public static void closeCnPool() throws SQLException {
        pool.close();
    }

    @Test
    public void whenCreatePost() {
        PostDBStore store = new PostDBStore(pool);
        Post post = new Post(0, "Java", "Some text", LocalDateTime.now());
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName()).isEqualTo(post.getName());
    }

    @Test
    public void whenAddPostAndFindByIdGeneratedIdMustBeTheSame() {
        PostDBStore store = new PostDBStore(pool);
        Post post = store.add(new Post(0, "Java", "Some text", LocalDateTime.now()));
        assertThat(store.findById(post.getId())).isEqualTo(post);
    }

    @Test
    public void whenUpdatePost() {
        PostDBStore store = new PostDBStore(pool);
        Post post = new Post(0, "Java", "Some text", LocalDateTime.now());
        store.add(post);
        assertThat(store.update(new Post(post.getId(), post.getName(), "Changed field", post.getCreated()))).isTrue();
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getDescription()).isEqualTo("Changed field");
    }

    @Test
    public void whenFindAll() {
        PostDBStore store = new PostDBStore(pool);
        store.add(new Post(0, "Java", "some text", LocalDateTime.now()));
        store.add(new Post(1, "Python", "some text", LocalDateTime.now()));
        store.add(new Post(2, "PHP", "some text", LocalDateTime.now()));
        List<Post> rslPosts = store.findAll();
        assertThat(rslPosts.size()).isEqualTo(3);
        assertThat(rslPosts.get(1).getName()).isEqualTo("Python");
    }

}