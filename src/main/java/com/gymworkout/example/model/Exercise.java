package com.gymworkout.example.model;

import java.util.Calendar;

/**
 * @author pfranca
 * Created by pfranca on 9/11/2015.
 */
public final class Exercise {
    private final String name;
    private int serie;
    private int reps;
    private final int weight;
    private final Calendar date;

    private Exercise(final Name name, final int weight, final Calendar date) {
        this.name = name.getName();
        if (weight == 0) {
            throw new IllegalStateException("Weight cannot be zero.");
        }
        this.weight = weight;

        if (date == null) {
            throw new IllegalStateException("Date cannot be null.");
        }
        this.date = date;
    }

    public String getName() { return name; }

    public int getSerie() {
        return serie;
    }

    public int getReps() {
        return reps;
    }

    public int getWeight() {
        return weight;
    }

    public Calendar getDate() {
        return (Calendar) date.clone();
    }

    public static Builder getBuilder(Name name, int weight, Calendar date){
        return new Builder(name, weight, date);
    }

    public static class Builder {
        private Exercise built;

        public Builder(Name name, int weight, Calendar date) {
            this.built = new Exercise(name, weight, date);
        }

        public Builder serie(int serie) {
            this.built.serie = serie;
            return this;
        }

        public Builder reps(int reps) {
            this.built.reps = reps;
            return this;
        }

        public Exercise build() {
            return this.built;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exercise exercise = (Exercise) o;

        if (serie != exercise.serie) return false;
        if (reps != exercise.reps) return false;
        if (weight != exercise.weight) return false;
        if (name != null ? !name.equals(exercise.name) : exercise.name != null) return false;
        return !(date != null ? !date.equals(exercise.date) : exercise.date != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + serie;
        result = 31 * result + reps;
        result = 31 * result + weight;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Exercise{" +
                "name='" + name + '\'' +
                ", serie=" + serie +
                ", reps=" + reps +
                ", weight=" + weight +
                ", date=" + date +
                '}';
    }
}
