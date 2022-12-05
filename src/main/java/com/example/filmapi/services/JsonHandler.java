package com.example.filmapi.services;

import com.example.filmapi.model.Film;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class JsonHandler {
    public Film createFilm(String json) {
        Map<String, String> map = new HashMap<>();
        var wrapper = new Object() {
            String tempKey = null;
        };
        Arrays.stream(json
                        .replace("{", "")
                        .replace("\"", "")
                        .split(","))
                .forEach(x -> {
                    String[] contents = x.split(":");
                    try {
                        map.put(contents[0], contents[1]);
                        wrapper.tempKey = contents[0];
                        if (wrapper.tempKey.equals("Poster")) {
                            map.replace(wrapper.tempKey, map.get(wrapper.tempKey) + "," + contents[2]);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        map.replace(wrapper.tempKey, map.get(wrapper.tempKey) + "," + x);
                    }
                });

        return new Film(map.get("Title"), map.get("Plot"), map.get("Genre"), map.get("Director"), map.get("Poster"));
    }
}
