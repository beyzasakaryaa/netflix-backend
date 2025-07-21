package com.netflix.netflixb.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class ContentRequestDTO {

    @NotBlank(message = "Başlık alanı boş bırakılamaz.")
    @Size(max = 255, message = "Başlık en fazla 255 karakter olabilir.")
    private String title;

    @NotBlank(message = "Tür alanı boş bırakılamaz.")
    @Size(max = 100, message = "Tür en fazla 100 karakter olabilir.")
    private String genre;

    @NotNull(message = "Yayın tarihi zorunludur.")
    @PastOrPresent(message = "Yayın tarihi bugünden ileri bir tarih olamaz.")
    private LocalDate releaseDate;

    @NotBlank(message = "Oyuncular alanı boş bırakılamaz.")
    @Size(max = 500, message = "Oyuncular en fazla 500 karakter olabilir.")
    private String actors;

    @NotBlank(message = "Yönetmen alanı boş bırakılamaz.")
    @Size(max = 255, message = "Yönetmen adı en fazla 255 karakter olabilir.")
    private String director;

    @NotBlank(message = "Açıklama alanı boş bırakılamaz.")
    @Size(max = 1000, message = "Açıklama en fazla 1000 karakter olabilir.")
    private String description;

    @PositiveOrZero(message = "IMDb puanı 0 veya daha büyük olmalıdır.")
    @DecimalMax(value = "10.0", inclusive = true, message = "IMDb puanı en fazla 10.0 olabilir.")
    private double imdbRating;

    // --- Getter ve Setter Metotları ---

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }
}
