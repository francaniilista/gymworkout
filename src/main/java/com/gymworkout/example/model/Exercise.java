package com.gymworkout.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author pfranca
 * Created by pfranca on 9/11/2015.
 */
@Document(collection = "exercises")
public final class Exercise {
    @Id
    private String id;
    private String name;
    private int serie;
    private int reps;
    private int weight;
    private long date;

    //This constructor is only used by mongodb
    public Exercise() {}

    private Exercise(final Name name, final int weight, final long date) {
        this.name = name.getName();
        if (weight == 0) {
            throw new IllegalStateException("Weight cannot be zero.");
        }
        this.weight = weight;

        if (date == 0l) {
            throw new IllegalStateException("Date cannot be null.");
        }
        this.date = date;
    }

    public String getId() {
        return id;
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

    public long getDate() {
        return date;
    }

    public static Builder getBuilder(Name name, int weight, long date){
        return new Builder(name, weight, date);
    }

    public static class Builder {
        private Exercise built;

        public Builder(Name name, int weight, long date) {
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
        if (date != exercise.date) return false;
        if (id != null ? !id.equals(exercise.id) : exercise.id != null) return false;
        return name.equals(exercise.name);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + name.hashCode();
        result = 31 * result + serie;
        result = 31 * result + reps;
        result = 31 * result + weight;
        result = 31 * result + (int) (date ^ (date >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Exercise{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", serie=").append(serie);
        sb.append(", reps=").append(reps);
        sb.append(", weight=").append(weight);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
