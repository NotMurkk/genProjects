package com.techelevator.model;

import javax.validation.constraints.NotNull;

public class Review {

    private Long review_id;
    @NotNull
    private Long beer_id;
    @NotNull
    private String author;
    @NotNull
    private int rating;
    @NotNull
    private String review;

    public Review() {
    }

    public Review(Long review_id, Long beer_id, String author, int rating, String review) {
        this.review_id = review_id;
        this.beer_id = beer_id;
        this.author = author;
        this.rating = rating;
        this.review = review;
    }

    public Long getReview_id() {
        return review_id;
    }

    public void setReview_id(Long review_id) {
        this.review_id = review_id;
    }

    public Long getBeer_id() {
        return beer_id;
    }

    public void setBeer_id(Long beer_id) {
        this.beer_id = beer_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
