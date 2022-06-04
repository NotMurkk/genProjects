package com.techelevator.dao;

import com.techelevator.model.Beer;

import java.util.List;

public interface BeerDao {

    List<Beer> getAll();

    boolean createBeer(Beer newBeer);

    List<Beer> getFavorites(String username);

    boolean deleteBeer(Long beerId);

    boolean favoriteExists(String username, String beerName);

    boolean addFavorite(String username, String beerName);

    boolean removeFavorite(String username, String beerName);
}
