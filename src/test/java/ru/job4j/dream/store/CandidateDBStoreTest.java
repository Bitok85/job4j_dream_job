package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.AfterClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.job4j.dream.Main;
import ru.job4j.dream.model.Candidate;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CandidateDBStoreTest {

    private static BasicDataSource pool = new Main().loadPool();

    @AfterEach
    public void wipeTable() throws SQLException {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("DELETE FROM CANDIDATE")
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
        CandidateDBStore store = new CandidateDBStore(pool);
        Candidate candidate = new Candidate(0, "Ivan Ivanov", "Some text", LocalDateTime.now());
        store.add(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName()).isEqualTo(candidate.getName());
    }

    @Test
    public void whenAddPostAndFindByIdGeneratedIdMustBeTheSame() {
        CandidateDBStore store = new CandidateDBStore(pool);
        Candidate candidate = store.add(new Candidate(0, "Ivan Ivanov", "Some text", LocalDateTime.now()));
        assertThat(store.findById(candidate.getId())).isEqualTo(candidate);
    }

    @Test
    public void whenUpdatePost() {
        CandidateDBStore store = new CandidateDBStore(pool);
        Candidate candidate = new Candidate(0, "Ivan Ivanov", "Some text", LocalDateTime.now());
        store.add(candidate);
        assertThat(store.update(new Candidate(candidate.getId(), candidate.getName(), "Changed field", candidate.getCreated()))).isTrue();
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getDescription()).isEqualTo("Changed field");
    }

    @Test
    public void whenFindAll() {
        CandidateDBStore store = new CandidateDBStore(pool);
        store.add(new Candidate(0, "Ivan Ivanov", "some text", LocalDateTime.now()));
        store.add(new Candidate(1, "Petr Petrov", "some text", LocalDateTime.now()));
        store.add(new Candidate(2, "Sidr Sidorov", "some text", LocalDateTime.now()));
        List<Candidate> rslCandidates = store.findAll();
        assertThat(rslCandidates.size()).isEqualTo(3);
        assertThat(rslCandidates.get(1).getName()).isEqualTo("Petr Petrov");
    }

}