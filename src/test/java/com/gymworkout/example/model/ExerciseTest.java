package com.gymworkout.example.model;

import org.junit.Test;

import java.util.Calendar;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author pfranca
 * Created by pfranca on 9/12/2015.
 */
public class ExerciseTest {
    @Test
    public void testValidInstance() {
        Exercise exercise = Exercise.getBuilder(Name.DEADLIFT, 120, Calendar.getInstance().getTimeInMillis()).build();
        System.out.println("Testing valid instance of Exercise with information: " + exercise);
        assertNotNull(exercise);
    }

    @Test(expected = IllegalStateException.class)
    public void testWeightNotZero() {
        Exercise exercise = Exercise.getBuilder(Name.DEADLIFT, 0, Calendar.getInstance().getTimeInMillis()).build();
        assertNotNull(exercise);
        assertEquals(0, exercise.getWeight());
    }

    @Test
    public void testImmutableDate() {
        Calendar now = Calendar.getInstance();
        Calendar now2 = (Calendar) now.clone();
        Exercise exercise = Exercise.getBuilder(Name.DEADLIFT, 120, now.getTimeInMillis()).build();
        assertEquals(now.getTimeInMillis(), exercise.getDate());

        assertEquals(now2.getTimeInMillis(), exercise.getDate());
    }

    @Test(expected = IllegalStateException.class)
    public void testDateNotNull() {
        Exercise exercise = Exercise.getBuilder(Name.DEADLIFT, 120, 0L).build();

    }
}