package com.example.filmapi.services;

import com.example.filmapi.model.Film;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class JsonHandler {
    public Film createFilm(String json){
        Map<String,String> map = new HashMap<>();
        Arrays.stream(json
                        .replace("{","")
                        .replace("\"","")
                        .split(","))
                        .forEach(x -> {
                            String tempKey = null;
                            String [] contents = x.split(":");
                            try {
                                map.put(contents[0], contents[1]);
                                tempKey = contents[0];
                                if (tempKey.equals("Poster")){
                                    map.replace(tempKey,map.get(tempKey) + "," + contents[2]);
                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                map.replace(tempKey,map.get(tempKey) + "," + x);
                            }
                        } );

        return new Film(map.get("Title"),map.get("Plot"),map.get("Genre"),map.get("Director"),map.get("Poster"));
    }
}
