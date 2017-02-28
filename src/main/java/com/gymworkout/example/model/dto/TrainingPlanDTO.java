package com.gymworkout.example.model.dto;

import com.gymworkout.example.model.Workout;

import java.util.List;

/**
 * Created by pfranca on 5/5/2016.
 */
public class TrainingPlanDTO {
    private String id;
    private String name;
    private List<Workout> workouts;

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

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    @Override
    public String toString() {
        return "TrainingPlanDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", workouts=" + workouts +
                '}';
    }
}
