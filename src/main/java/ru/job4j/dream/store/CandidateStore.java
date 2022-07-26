package ru.job4j.dream.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class CandidateStore {

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private final AtomicInteger key;
    private CandidateStore() {
        candidates.put(1, new Candidate(1, "Ivanov Ivan", "some text", LocalDateTime.now()));
        candidates.put(2, new Candidate(2, "Sidorov Petr", "some text", LocalDateTime.now()));
        candidates.put(3, new Candidate(3, "Morozov Pavel", "some text", LocalDateTime.now()));
        key = new AtomicInteger(candidates.size());
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }

    public void add(Candidate candidate) {
        candidate.setId(key.incrementAndGet());
        candidate.setCreated(LocalDateTime.now());
        candidates.put(candidate.getId(), candidate);
    }

    public Candidate findById(int id) {
        return candidates.get(id);
    }

    public void update(Candidate candidate) {
        candidate.setCreated(candidates.get(candidate.getId()).getCreated());
        candidates.replace(candidate.getId(), candidate);
    }
}
