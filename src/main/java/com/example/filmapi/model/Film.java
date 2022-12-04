package com.example.filmapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    private String genre; //enum?
    private String director;
    private String poster;

    public Film(int id, String title, String description, String gene, String director, String poster) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = gene;
        this.director = director;
        this.poster = poster;
    }

    public Film() {
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}
