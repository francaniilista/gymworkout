package com.gymworkout.example.controller;

import com.gymworkout.example.model.Exercise;
import com.gymworkout.example.model.Name;
import com.gymworkout.example.model.dto.ExerciseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by pfranca on 9/30/2015.
 */
@Controller("exerciseController")
@RequestMapping(value = "/exercise")
public class ExerciseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<ExerciseDTO> findAll() {
        return Arrays.asList(getSampleExercise(),getSampleExercise(),getSampleExercise());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public ExerciseDTO get(@PathVariable("id") final String id){
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public ExerciseDTO create(@RequestBody ExerciseDTO exercise) {
        System.out.println(exercise);
        return exercise;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody ExerciseDTO exercise, @PathVariable String id) {}

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable String id) {}

    private ExerciseDTO getSampleExercise() {
        ExerciseDTO exercise = new ExerciseDTO();
        exercise.setDate(Calendar.getInstance());
        exercise.setName("Barbel");
        exercise.setReps(1);
        exercise.setSeries(1);
        exercise.setWeight(120);
        return exercise;
    }
}
