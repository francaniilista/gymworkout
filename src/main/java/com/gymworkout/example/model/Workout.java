package com.gymworkout.example.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by pfranca on 10/21/2015.
 */
public class Workout {
    private String name;
    private List<Exercise> exercises;
    private Calendar date;

    public Workout(String name, Calendar date, Exercise... exercises) {
        if (name == null || name.equals("")) {
            throw new IllegalStateException("Workout has not name.");
        }
        this.name = name;

        if (date == null) {
            throw new IllegalStateException("Date is null.");
        }

        this.date = date;

        if (exercises.length == 0) {
            throw new IllegalStateException("Workout has not exercise.");
        }

        for (Exercise exer : exercises) {
            this.getExercises().add(exer);
        }
    }

    public Workout(String name, List<Exercise> exercises) {
        if (name == null || name.equals("")) {
            throw new IllegalStateException("Workout has not name.");
        }
        this.name = name;

        this.date = Calendar.getInstance();

        if (exercises.size() == 0) {
            throw new IllegalStateException("Workout has not exercise.");
        }
        this.setExercises(exercises);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Exercise> getExercises() {
        if (this.exercises == null) {
            this.exercises = new ArrayList<Exercise>();
        }
        return exercises;
    }

    private void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Workout workout = (Workout) o;

        if (!name.equals(workout.name)) return false;
        if (!exercises.equals(workout.exercises)) return false;
        return date.equals(workout.date);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + exercises.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Workout{");
        sb.append("name='").append(name).append('\'');
        sb.append(", exercises=").append(exercises);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}