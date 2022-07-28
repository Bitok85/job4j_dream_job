package ru.job4j.dream.model;

import java.util.Objects;

public class Candidate {

    private String name;
    private String surname;
    private String handle;


    public Candidate(String name, String surname, String handle) {
        this.name = name;
        this.surname = surname;
        this.handle = handle;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getHandle() {
        return handle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return Objects.equals(name, candidate.name) && Objects.equals(surname, candidate.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
