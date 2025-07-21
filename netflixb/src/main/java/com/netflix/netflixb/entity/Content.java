package com.netflix.netflixb.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "contents")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String genre;

    private LocalDate releaseDate;

    private String actors;

    private String director;

    @Column(length = 1000)
    private String description;

    private Double imdbRating;

    // Getter ve Setter metodları
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

    public Double getImdbRating() { return imdbRating; }
    public void setImdbRating(Double imdbRating) { this.imdbRating = imdbRating; }
}
