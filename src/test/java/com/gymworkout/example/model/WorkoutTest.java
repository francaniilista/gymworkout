package com.gymworkout.example.model;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by pfranca on 4/29/2016.
 */
public class WorkoutTest {
    @Test(expected = IllegalStateException.class)
    public void testWorkoutWithNullName() {
        System.out.println("Building a Workout with name: null.");
        String name = null;
        assertEquals(null, name);
        Workout workout = Workout.getBuilder(name, buildSampleExercise()).build();
    }


    @Test(expected = IllegalStateException.class)
    public void testWorkoutWithoutExercise() {
        System.out.println("Building a Workout with no exercise.");
        Workout workout = Workout.getBuilder("Workout A", (List<Exercise>)null).build();
    }

    @Test(expected = IllegalStateException.class)
    public void testWorkoutWithoutDate() {
        System.out.println("Building a Workout with no date.");
        Workout workout = Workout.getBuilder("Workout A", 0L, Arrays.asList(buildSampleExercise())).build();
    }

    @Test
    public void testSampleWorkout() {
        System.out.println("Building a sample workout.");
        long date = Calendar.getInstance().getTimeInMillis();
        List<Exercise> exercises = new ArrayList<Exercise>();

        exercises.add(buildSampleExercise());
        exercises.add(buildSampleExercise(Name.DEADLIFT, 100, date));
        exercises.add(buildSampleExercise(Name.BENCHPRESS, 70, date));

        System.out.println(exercises);
        String workoutName = "Workout A";
        Workout workout = Workout.getBuilder(workoutName, date, exercises).build();
        assertEquals(3, workout.getExercises().size());
        assertEquals(workoutName, workout.getName());
        System.out.println(workout);

    }

    private Exercise buildSampleExercise() {
        return Exercise.getBuilder(Name.BARBELL, 100, Calendar.getInstance().getTimeInMillis())
                .reps(10)
                .series(2)
                .build();
    }

    private Exercise buildSampleExercise(final Name name , final int weight, final long date) {
        return Exercise.getBuilder(name, 100, Calendar.getInstance().getTimeInMillis())
                .reps(10)
                .series(2)
                .build();
    }
}
