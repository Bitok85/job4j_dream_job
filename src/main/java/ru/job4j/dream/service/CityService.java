package ru.job4j.dream.service;

import org.springframework.stereotype.Service;
import ru.job4j.dream.model.City;
import ru.job4j.dream.store.CityStore;

import java.util.List;


@Service
public class CityService {

    private final CityStore cityStore;

    public CityService(CityStore cityStore) {
        this.cityStore = cityStore;
    }

    public List<City> getAllCities() {
        return cityStore.findAll();
    }

    public City findById(int id) {
        return cityStore.findById(id);
    }
}
