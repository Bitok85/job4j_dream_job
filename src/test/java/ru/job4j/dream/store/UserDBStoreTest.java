package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import ru.job4j.dream.Main;
import ru.job4j.dream.model.User;

import static org.assertj.core.api.Assertions.assertThat;

class UserDBStoreTest {

    private static BasicDataSource pool = new Main().loadPool();

    /**
    @Ignore
    @Test
    public void whenAddUser() {
        UserDBStore store = new UserDBStore(pool);
        User user = new User("123@gmail.com", "123");
        assertThat(store.add(user).get()).isEqualTo(user);
        assertThat(store.add(user).get()).isEqualTo(user);
    }
    */
}