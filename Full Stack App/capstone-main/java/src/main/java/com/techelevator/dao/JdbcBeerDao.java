package com.techelevator.dao;

import com.techelevator.model.Beer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcBeerDao implements BeerDao{

    private final JdbcTemplate jdbcTemplate;
    private final JdbcBreweryDao breweryDao;

    public JdbcBeerDao(JdbcTemplate jdbcTemplate, JdbcBreweryDao breweryDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.breweryDao = breweryDao;
    }

    @Override
    public List<Beer> getAll() {
        List<Beer> beers = new ArrayList<>();
        String sql = "SELECT * FROM beer;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            beers.add(mapRowToBeer(results));
        }
        return beers;
    }

    @Override
    public boolean createBeer(Beer newBeer) {
        String sql = "INSERT INTO beer (brewery_id, name, type, abv, description) " +
                "VALUES(?, ?, ?, ?, ?);";
        int rowsUpdated = jdbcTemplate.update(sql, newBeer.getBreweryId(), newBeer.getName(), newBeer.getType(), newBeer.getAbv(), newBeer.getDescription());
        return rowsUpdated == 1;
    }



    @Override
    public List<Beer> getFavorites(String username) {
        List<Beer> favorites = new ArrayList<>();
        String sql = "SELECT * FROM beer " +
                "JOIN favorite_beer ON beer.beer_id = favorite_beer.beer_id " +
                "JOIN users ON favorite_beer.user_id = users.user_id " +
                "WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        while (results.next()) {
            favorites.add(mapRowToBeer(results));
        }
        return favorites;
    }

    @Override
    public boolean deleteBeer(Long beerId) {
        String sql = "DELETE FROM beer WHERE beer_id = ?;";
        int rowsDeleted = jdbcTemplate.update(sql, beerId);
        return rowsDeleted == 1;
    }

    @Override
    public boolean favoriteExists(String username, String beerName) {
        for (Beer beer: this.getFavorites(username)) {
            if (beer.getName().equalsIgnoreCase(beerName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addFavorite(String username, String beerName) {
        String sql = "INSERT INTO favorite_beer (user_id, beer_id) " +
                "VALUES (" +
                "(SELECT user_id FROM users WHERE username = ?), " +
                "(SELECT beer_id FROM beer WHERE name = ?)" +
                ");";
        int rowsUpdated = jdbcTemplate.update(sql, username, beerName);
        return rowsUpdated == 1;
    }

    @Override
    public boolean removeFavorite(String username, String beerName) {
        String sql = "DELETE FROM favorite_beer WHERE " +
                "user_id = (SELECT user_id FROM users WHERE username = ?) AND " +
                "beer_id = (SELECT beer_id FROM beer WHERE name = ?);";
        int rowsDeleted = jdbcTemplate.update(sql, username, beerName);
        return rowsDeleted == 1;
    }

    private Beer mapRowToBeer(SqlRowSet rowSet) {
        Beer beer = new Beer();
        beer.setBeerId(rowSet.getLong("beer_id"));
        beer.setBreweryId(rowSet.getLong("brewery_id"));
        beer.setBreweryName(breweryDao.getNameById(beer.getBreweryId()));
        beer.setName(rowSet.getString("name"));
        beer.setType(rowSet.getString("type"));
        beer.setAbv(rowSet.getDouble("abv"));
        beer.setDescription(rowSet.getString("description"));
        return beer;
    }
}
