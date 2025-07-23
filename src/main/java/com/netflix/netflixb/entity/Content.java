package com.netflix.netflixb.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contents")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String genre;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(length = 500, nullable = false)
    private String actors;

    @Column(length = 255, nullable = false)
    private String director;

    @Column(length = 1000, nullable = false)
    private String description;

    @Column(nullable = false)
    private double imdbRating;

    // --- Getter ve Setter'lar ---

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
