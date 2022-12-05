package com.example.filmapi.services;

import com.example.filmapi.data.FilmRepository;
import com.example.filmapi.model.Film;
import org.springframework.stereotype.Service;

@Service
public class FilmRepoService {
    FilmRepository filmRepository;

    public FilmRepoService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public void save(Film film){
        filmRepository.save(film);
    }

    public Film read(String title){
        return filmRepository.read(title);
    }

    public void delete(String title){
        filmRepository.delete(title);
    }

    public void delete(Film film){
        filmRepository.delete(film);
    }
}