package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.Candidate;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CandidateDBStore {

    private static final Logger LOG = LogManager.getLogger(CandidateDBStore.class.getName());
    private final BasicDataSource pool;

    public CandidateDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Candidate> findAll() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM candidate")
        ) {
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    candidates.add(new Candidate(resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getTimestamp("created").toLocalDateTime()));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return candidates;
    }

    public Candidate add(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps
                     = cn.prepareStatement("INSERT INTO candidate(name, description, created, visiblea, photo) VALUES(?, ?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(candidate.getCreated()));
            ps.setBoolean(4, candidate.isVisible());
            ps.setBytes(5, candidate.getPhoto());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return candidate;
    }

    public boolean update(Candidate candidate) {
        boolean result = false;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =
                     cn.prepareStatement("UPDATE candidate SET name = ?, description = ?, created = ?, visiblea = ?, photo = ? where id = ?")
        ) {
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(candidate.getCreated()));
            ps.setBoolean(4, candidate.isVisible());
            ps.setBytes(5, candidate.getPhoto());
            ps.setInt(6, candidate.getId());
            result = ps.executeUpdate() > 0;
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return result;
    }

    public Candidate findById(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM candidate WHERE id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Candidate(
                            it.getInt("id"),
                            it.getString("name"),
                            it.getString("description"),
                            it.getTimestamp("created").toLocalDateTime()
                    );
                }
            }
        } catch (Exception e) {
            LOG.error("Exception", e);
        }
        return null;
    }

}
