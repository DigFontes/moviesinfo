package com.moviesinfo.app.models;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Episode {
    private Integer season;
    private String title;
    private Integer episode;
    private String rating;
    private LocalDate release;

    public Episode(Integer season, EpisodesData episodesData) {
        this.season = season;
        this.title = episodesData.title();
        this.episode = episodesData.episode();
        this.rating = episodesData.rating();
        try {
            this.release = LocalDate.parse(episodesData.release());
        } catch (DateTimeParseException ex) {
            this.release = null;
        }
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    @Override
    public String toString() {
        return "season=" + season +
                ", title='" + title + '\'' +
                ", episode=" + episode +
                ", rating='" + rating + '\'' +
                ", release=" + release ;

    }
}


