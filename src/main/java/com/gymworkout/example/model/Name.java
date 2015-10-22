package com.gymworkout.example.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.IOException;

/**
 * Created by pfranca on 9/11/2015.
 */
@JsonSerialize(using = NameSerializer.class)
public enum Name {
    BARBELL(1, "Barbell"),
    BENCHPRESS(2, "Bench press"),
    DEADLIFT(3, "Deadlift"),
    DUMBELL_PRESSES(4, "Dumbell presses"),
    SQUAT(5, "Squat");

    private final int option;
    private final String name;

    Name(int option, String name) {
        this.option = option;
        this.name = name;
    }

    public int getOption() {
        return option;
    }

    public String getName() {
        return name;
    }

    public static Name getByName(String nameParameter) {
        for (Name name : values()) {
            if (name.getName().equalsIgnoreCase(nameParameter)) {
                return name;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}