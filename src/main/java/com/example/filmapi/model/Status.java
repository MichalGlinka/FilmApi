package com.example.filmapi.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Status {
    private String message;

    public Status(String message) {
        this.message = message;
    }

    public Status() {
    }

    @Override
    public String toString() {
        return "Status{" +
                "message='" + message + '\'' +
                '}';
    }
}
