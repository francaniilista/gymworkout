package com.gymworkout.example.repository;

import com.gymworkout.example.model.Exercise;
import com.gymworkout.example.model.Name;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;

/**
 * Created by pfranca on 10/22/2015.
 */
@ContextConfiguration("classpath:application-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ExerciseRepositoryTest {
    @Autowired
    private ExerciseRepository repository;


    @Test
    public void testInsertDocument() {
        System.out.println("\n\n============== testInsertDocument ==============");
        Exercise exercise = this.buildDefaultExercise();
        System.out.println("Inserting exercise: " + exercise);
        assertNull(exercise.getId());

        exercise = repository.save(exercise);
        System.out.println("Inserted exercise:" + exercise);
        assertNotNull(exercise.getId());
    }

    @Test
    public void testUpdateDocument() {
        System.out.println("\n\n============== testUpdateDocument ==============");
        Exercise exercise = this.buildDefaultExercise();
        assertEquals(Name.BARBELL.getName(), exercise.getName());
        assertEquals(8, exercise.getReps());
        assertEquals(4, exercise.getSeries());

        System.out.println("Saving exercise: " + exercise);
        exercise = repository.save(exercise);
        assertNotNull(exercise.getId());
        System.out.println("Saved exercise with id: " + exercise.getId());

        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(exercise.getDate());

        Exercise exercise1 = this.buildExercise(Name.getByName(exercise.getName()), 60, exercise.getReps(), exercise.getSeries(), date);
        System.out.println("Updating exercise: " + exercise);

        exercise = repository.save(exercise);
        System.out.println("Updated exercise with weight: " + exercise.getWeight());
        assertEquals(60, exercise.getWeight());
        System.out.println("Updated exercise: " + exercise);
    }

    @Test
    public void testDeleteDocument() {
        System.out.println("\n\n============== testDeleteDocument ==============");
        Exercise exercise = this.buildDefaultExercise();
        assertEquals(Name.BARBELL.getName(), exercise.getName());
        assertEquals(8, exercise.getReps());
        assertEquals(4, exercise.getSeries());

        System.out.println("Saving exercise: " + exercise.toString());
        exercise = repository.save(exercise);
        assertNotNull(exercise.getId());
        System.out.println("Saved exercise with id: " + exercise.getId());

        System.out.println("Removing exercise");
        repository.delete(exercise);


    }

    public static void main(String[] args) {
        ApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context-test.xml");
        Exercise exercise = Exercise.getBuilder(Name.BARBELL, 40, Calendar.getInstance().getTimeInMillis())
                .reps(9)
                .series(1)
                .build();

        ExerciseRepository repository = (ExerciseRepository) ctx.getBean("exerciseRepository");
        System.out.println(repository.save(exercise));
        System.out.println(repository.findAll());

    }

    public void setRepository(ExerciseRepository repository) {
        this.repository = repository;
    }

    private Exercise buildExercise(Name name, int weight, int reps, int series, Calendar cal) {
        return Exercise.getBuilder(name, weight, cal.getTimeInMillis())
                .reps(reps)
                .series(series)
                .build();

    }

    private Exercise buildDefaultExercise() {
        return Exercise.getBuilder(Name.BARBELL, 40, Calendar.getInstance().getTimeInMillis())
                .reps(8)
                .series(4)
                .build();
    }
}
