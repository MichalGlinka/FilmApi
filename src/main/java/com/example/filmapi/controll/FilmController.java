package com.example.filmapi.controll;

import com.example.filmapi.model.Film;
import com.example.filmapi.model.Status;
import com.example.filmapi.services.DataReciver;
import com.example.filmapi.services.FilmRepoService;
import com.example.filmapi.services.JsonHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilmController {
    JsonHandler handler;
    DataReciver reciver;
    FilmRepoService repository;


    public FilmController(JsonHandler handler, DataReciver reciver,FilmRepoService repository) {
        this.handler = handler;
        this.reciver = reciver;
        this.repository = repository;
    }

    @PutMapping("/addFavorite")
    public ResponseEntity<Status> addToFavorite(@RequestParam String title){
        Status status = new Status();
       if(repository.read(title) != null){
            status.setMessage("Film już jest w ulubionych");
            return ResponseEntity.status(403).body(status);
        }else {
            repository.save(handler.createFilm(reciver.sendRequest(DataReciver.OMDBAPI_URL, new String[]{DataReciver.OMDBAPI_KEY, "t=" + title})));
            status.setMessage("Film został dodany do ulubionych");
            return ResponseEntity.ok(status);
        }
    }

    @GetMapping("/film")
    public ResponseEntity<Film> getFilmByTitle(@RequestParam String title){
        Film film =  handler.createFilm(reciver.sendRequest(DataReciver.OMDBAPI_URL, new String[]{DataReciver.OMDBAPI_KEY,"t=" + title}));
        if (film.getTitle() == null) {
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(film);
        }
    }

    @GetMapping("/favorites")
    public ResponseEntity<Film[]> getAllFavoriteFilms(){
        Film [] films = repository.getFilms();
        if (films.length == 0){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok(films);
        }
    }
}
