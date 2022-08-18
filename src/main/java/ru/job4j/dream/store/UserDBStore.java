package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;

@Repository
public class UserDBStore {

    private static final Logger LOG = LoggerFactory.getLogger(UserDBStore.class.getName());
    private final BasicDataSource pool;

    public UserDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public Optional<User> add(User user) {
        Optional<User> result = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO users(email, password) VALUES(?, ?)")
        ) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            if (ps.executeUpdate() > 0) {
                return Optional.of(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
