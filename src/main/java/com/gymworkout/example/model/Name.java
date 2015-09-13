package com.gymworkout.example.model;

/**
 * Created by pfranca on 9/11/2015.
 */
public enum Name {
    DEADLIFT("deadlift"), SQUAT("squat"), DUMBELL_PRESSES("dumbell presses");
    private final String name;

    Name(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
