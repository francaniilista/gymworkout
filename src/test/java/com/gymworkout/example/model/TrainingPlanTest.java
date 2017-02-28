package com.gymworkout.example.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by pfranca on 5/3/2016.
 */
public class TrainingPlanTest {
    public static void main(String[] args) {
        TrainingPlan plan = new TrainingPlan();
        long today = Calendar.getInstance().getTimeInMillis();
        long tomorrow = Calendar.getInstance().getTimeInMillis();

        List<Exercise> exercisesA = new ArrayList<Exercise>();

        exercisesA.add(buildSampleExercise(Name.DEADLIFT, 200, today));
        exercisesA.add(buildSampleExercise(Name.SQUAT, 120, today));

        Workout workoutA = Workout.getBuilder("Workout A", today, exercisesA).build();

        List<Exercise> exercisesB = new ArrayList<Exercise>();

        exercisesB.add(buildSampleExercise(Name.BARBELL, 40, today));
        exercisesB.add(buildSampleExercise(Name.BENCHPRESS, 100, today));

        Workout workoutB = Workout.getBuilder("Workout B", tomorrow, exercisesB).build();

        plan.add(workoutA);
        plan.add(workoutB);

        System.out.println(plan);
    }

    private static Exercise buildSampleExercise(final Name name , final int weight, final long date) {
        return Exercise.getBuilder(name, 100, Calendar.getInstance().getTimeInMillis())
                .reps(10)
                .series(2)
                .build();
    }
}