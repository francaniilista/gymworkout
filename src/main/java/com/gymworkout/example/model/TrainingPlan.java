package com.gymworkout.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pfranca on 4/29/2016.
 */
@Document(collection = "training_plan")
public class TrainingPlan {
    @Id
    private String id;
    private String name;
    @DBRef
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

    public List<Workout> getWorkouts() {
        if (workouts == null) {
            workouts = new ArrayList<Workout>();
        }
        return workouts;
    }

    public void add(Workout workout) {
        getWorkouts().add(workout);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update(final String name, final List<Workout> workouts) {
        this.name = name;
        this.workouts = workouts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainingPlan plan = (TrainingPlan) o;

        if (name != null ? !name.equals(plan.name) : plan.name != null) return false;
        return !(workouts != null ? !workouts.equals(plan.workouts) : plan.workouts != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (workouts != null ? workouts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TrainingPlan{" +
                "name='" + name + '\'' +
                ", workouts=" + workouts +
                '}';
    }
}