package com.gymworkout.example.repository;

import com.gymworkout.example.model.Exercise;
import com.gymworkout.example.model.Name;
import org.junit.runner.RunWith;
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
    public static void main(String[] args) {
        ApplicationContext ctx = new GenericXmlApplicationContext("classpath:application-context-test.xml");
        Exercise exercise = Exercise.getBuilder(Name.BARBELL, 40, Calendar.getInstance().getTimeInMillis())
                .reps(9)
                .serie(1)
                .build();

        ExerciseRepository repository = (ExerciseRepository) ctx.getBean("exerciseRepository");
        System.out.println(repository.save(exercise));
        System.out.println(repository.find());

    }
}
