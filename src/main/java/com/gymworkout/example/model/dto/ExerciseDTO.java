package com.gymworkout.example.model.dto;

import com.gymworkout.example.util.CalendarUtil;

import java.util.Calendar;

/**
 * @author pfranca
 * Created by pfranca on 9/11/2015.
 */
public class ExerciseDTO {
    private String id;
    private String name;
    private int series;
    private int reps;
    private int weight;
    private Calendar date;

    public ExerciseDTO() {}

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
        return "ExerciseDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", series=" + series +
                ", reps=" + reps +
                ", weight=" + weight +
                ", date=" + CalendarUtil.getDefaultFormattedDate(date) +
                '}';
    }
}
