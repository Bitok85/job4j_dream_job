package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.CandidateDBStore;
import ru.job4j.dream.store.CandidateStore;

import java.util.Collection;

@Service
@ThreadSafe
public class CandidateService {

    private final CandidateDBStore store;

    public CandidateService(CandidateDBStore candidateStore) {
        this.store = candidateStore;
    }

    public Collection<Candidate> findAll() {
        return store.findAll();
    }

    public void add(Candidate candidate) {
        store.add(candidate);
    }

    public Candidate findById(int id) {
        return store.findById(id);
    }

    public void update(Candidate candidate) {
        store.update(candidate);
    }
}
