package com.gymworkout.example.model;

import com.gymworkout.example.util.CalendarUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by pfranca on 10/21/2015.
 */
@Document(collection = "workout")
public class Workout {
    @Id
    private String id;
    private String name;
    @DBRef
    private List<Exercise> exercises;

    public Workout() {}

    private Workout(final String name, final long date, final List<Exercise> exercises) {
        if (name == null || name.equals("")) {
            throw new IllegalStateException("Workout has no name.");
        }
        this.name = name;

        if (date == 0L) {
            throw new IllegalStateException("Workout has no date.");
        }

        if (exercises == null || exercises.size() == 0) {
            throw new IllegalStateException("Workout has no exercise.");
        }

        for (Exercise exer : exercises) {
            this.getExercises().add(exer);
        }
    }

    private Workout(String name, Exercise... exercises) {
        if (name == null || name.equals("")) {
            throw new IllegalStateException("Workout has not name.");
        }
        this.name = name;

        if (exercises == null || exercises.length == 0) {
            throw new IllegalStateException("Workout has not exercise.");
        }

        for (Exercise exer : exercises) {
            this.getExercises().add(exer);
        }
    }

    private Workout(String name, List<Exercise> exercises) {
        if (name == null || name.equals("")) {
            throw new IllegalStateException("Workout has not name.");
        }
        this.name = name;

        if (exercises == null || exercises.size() == 0) {
            throw new IllegalStateException("Workout has not exercise.");
        }
        this.exercises = exercises;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Exercise> getExercises() {
        if (this.exercises == null) {
            this.exercises = new ArrayList<Exercise>();
        }
        return exercises;
    }

    public static Builder getBuilder(final String name, final long date, List<Exercise> exercises) {
        return new Builder(name, date, exercises);
    }

    public static Builder getBuilder(final String name, Exercise... exercises) {
        return new Builder(name, exercises);
    }

    public static Builder getBuilder(final String name, List<Exercise> exercises) {
        return new Builder(name, exercises);
    }
    public static class Builder {
        private Workout built;

        public Builder(String name, long date, List<Exercise> exercise) {
            this.built = new Workout(name, date, exercise);
        }

        public Builder(String name, Exercise... exercise) {
            this.built = new Workout(name, exercise);
        }

        public Builder(String name, List<Exercise> exercise) {
            this.built = new Workout(name, exercise);
        }

        public Builder id(String id) {
            this.built.id = id;
            return this;
        }

        public Workout build() {
            return built;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Workout workout = (Workout) o;

        if (id != null ? !id.equals(workout.id) : workout.id != null) return false;
        return !(name != null ? !name.equals(workout.name) : workout.name != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "exercises=" + exercises +
                '}';
    }
}