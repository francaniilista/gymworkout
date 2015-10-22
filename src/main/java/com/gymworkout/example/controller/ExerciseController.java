package com.gymworkout.example.controller;

import com.gymworkout.example.model.Exercise;
import com.gymworkout.example.model.Name;
import com.gymworkout.example.model.dto.ExerciseDTO;
import com.gymworkout.example.service.ExerciseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ExerciseService service;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Exercise> findAll() {
        return service.get();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Exercise get(@PathVariable("id") final String id){
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public Exercise create(@RequestBody ExerciseDTO dto) {
        LOGGER.debug("Adding new exercise with information: {}", dto);
        Exercise added = service.add(dto);
        LOGGER.debug("Added exercise with information: {}", added);
        return added;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody ExerciseDTO exercise, @PathVariable String id) {}

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable String id) {
        System.out.println("deleted");
    }

    private Exercise getSampleExercise() {
        return Exercise.getBuilder(Name.BARBELL, 100,Calendar.getInstance().getTimeInMillis())
                .reps(10)
                .serie(2)
                .build();
    }
}
