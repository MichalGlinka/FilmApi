package com.example.filmapi.controll;

import com.example.filmapi.model.Film;
import com.example.filmapi.model.Status;
import com.example.filmapi.services.DataReciver;
import com.example.filmapi.services.FilmRepoService;
import com.example.filmapi.services.JsonHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/addFavorite")
    public String addToFavorite(@RequestParam String title){
        Status status = new Status();
     /**  if(repository.read(title) != null){
            status.setMessage("Film już jest w ulubionych");
        }else {
       **/     repository.save(handler.createFilm(reciver.sendRequest(DataReciver.OMDBAPI_URL, new String[]{DataReciver.OMDBAPI_KEY, "t=" + title})));
            status.setMessage("Film został dodany do ulubionych");
       // }
        return status.toString();
    }

    @GetMapping("/film")
    public String getFilmByTitle(@RequestParam String title){
        return handler.createFilm(reciver.sendRequest(DataReciver.OMDBAPI_URL, new String[]{DataReciver.OMDBAPI_KEY,"t=" + title})).toString();
    }
}
