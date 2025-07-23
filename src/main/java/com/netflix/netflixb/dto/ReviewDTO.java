package com.netflix.netflixb.dto;

public class ReviewDTO {
    private String comment;
    private Integer rating;

    // Getter ve Setter
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
}
