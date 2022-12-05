package com.example.filmapi.data;

import com.example.filmapi.model.Film;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends CrudRepository<Film,Integer> {
    @Query("SELECT f FROM Film f WHERE f.title = :title")
    Film read(String title);

    @Query("DELETE FROM Film f WHERE f.title = :title")
    void delete(String title);
}
