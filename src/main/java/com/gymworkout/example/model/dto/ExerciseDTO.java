package com.gymworkout.example.model.dto;

import java.util.Calendar;

/**
 * @author pfranca
 * Created by pfranca on 9/11/2015.
 */
public class ExerciseDTO {
    private String name;
    private int series;
    private int reps;
    private int weight;
    private Calendar date;

    public ExerciseDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExerciseDTO{");
        sb.append("name='").append(name).append('\'');
        sb.append(", series=").append(series);
        sb.append(", reps=").append(reps);
        sb.append(", weight=").append(weight);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
