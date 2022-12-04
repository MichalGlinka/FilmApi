package com.example.filmapi.data;

import com.example.filmapi.model.Film;
import org.springframework.data.repository.CrudRepository;

public class FilmRepository implements CrudRepository<Integer,Film> {
}
