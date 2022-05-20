package com.example.dao;

import com.example.model.Beer;

import java.util.List;

public interface BeerDao {

    List<Beer> getAll();

    boolean createBeer(Beer newBeer);

    List<Beer> getFavorites(String username);

    boolean addFavorite(String username, String beerName);
}

