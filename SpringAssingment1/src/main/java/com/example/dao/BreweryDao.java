package com.example.dao;

import com.example.model.Brewery;

import java.util.List;

public interface BreweryDao {

    List<Brewery> getAll();

    boolean create(Brewery newBrewery);

    List<Brewery> getFavorites(String username);

    boolean addFavorite(String breweryName, String username);
}

