package com.techelevator.dao;

import com.techelevator.model.Review;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcReviewDao implements ReviewDao{

    JdbcTemplate jdbcTemplate;

    public JdbcReviewDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Review> getAll() {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM review;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            reviews.add(mapRowToReview(results));
        }
        return reviews;
    }

    @Override
    public boolean create(Review newReview) {
        String sql = "INSERT INTO review (beer_id, author, rating, review) " +
                "VALUES (?, ?, ?, ?);";
        int rowsUpdated = jdbcTemplate.update(sql, newReview.getBeer_id(), newReview.getAuthor(), newReview.getRating(), newReview.getReview());
        return rowsUpdated > 0;
    }

    @Override
    public boolean delete(Long reviewId) {
        String sql = "DELETE FROM review WHERE review_id = ?;";
        int rowsDeleted = jdbcTemplate.update(sql, reviewId);
        return rowsDeleted == 1;
    }

    private Review mapRowToReview(SqlRowSet rowSet) {
        Review review = new Review();
        review.setReview_id(rowSet.getLong("review_id"));
        review.setBeer_id(rowSet.getLong("beer_id"));
        review.setAuthor(rowSet.getString("author"));
        review.setRating(rowSet.getInt("rating"));
        review.setReview(rowSet.getString("review"));
        return review;
    }
}
