package com.netflix.netflixb.dto;

import java.time.LocalDate;

public class ContentResponseDTO {
    private Long id;
    private String title;
    private String genre;
    private LocalDate releaseDate;
    private String actors;
    private String director;
    private String description;
    private double imdbRating;

    // Boş constructor
    public ContentResponseDTO() {}

    // Getter ve Setter'lar
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public String getActors() { return actors; }
    public void setActors(String actors) { this.actors = actors; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getImdbRating() { return imdbRating; }
    public void setImdbRating(double imdbRating) { this.imdbRating = imdbRating; }
}
