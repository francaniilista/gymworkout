package com.gymworkout.example.model.dto;

import com.gymworkout.example.model.Exercise;

import java.util.List;

/**
 * Created by pfranca on 5/3/2016.
 */
public class WorkoutDTO {
    private String id;
    private String name;
    private List<Exercise> exercises;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return "WorkoutDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", exercises=" + exercises +
                '}';
    }
}
