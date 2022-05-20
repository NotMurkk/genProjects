package com.example.dao;

import com.example.model.Review;

import java.util.List;

public interface ReviewDao {

    List<Review> getAll();

    boolean create (Review newReview);
}
