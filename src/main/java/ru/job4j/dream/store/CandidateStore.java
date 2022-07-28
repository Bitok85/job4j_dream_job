package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CandidateStore {

    private static final CandidateStore INST = new CandidateStore();
    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private CandidateStore() {
        candidates.put(1, new Candidate("Ivan", "Ivanov", "HH"));
        candidates.put(2, new Candidate("Petr", "Sidorov", "LinkedIn"));
        candidates.put(3, new Candidate("Pavel", "Morozov", "Facebook"));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
