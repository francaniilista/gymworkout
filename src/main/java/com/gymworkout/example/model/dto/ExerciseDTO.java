package com.gymworkout.example.model.dto;

import com.gymworkout.example.model.Name;

import java.util.Calendar;

/**
 * @author pfranca
 * Created by pfranca on 9/11/2015.
 */
public class ExerciseDTO {
    private String name;
    private int serie;
    private int reps;
    private int weight;
    private Calendar date;

    public String getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name.getName();
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
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
}
