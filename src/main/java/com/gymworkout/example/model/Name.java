package com.gymworkout.example.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pfranca on 9/11/2015.
 */
//@JsonSerialize(using = NameSerializer.class)
public enum Name {
    BARBELL("Barbell"),
    BENCHPRESS("Bench press"),
    DEADLIFT("Deadlift"),
    DUMBELL_PRESSES("Dumbell presses"),
    SQUAT("Squat");

    private final String name;

    Name(String name) {
        this.name = name;
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

    public static List<String> getNames() {
        List<String> names = new ArrayList<String>();
        for (Name name : values()) {
            names.add(name.getName());
        }
        return names;
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