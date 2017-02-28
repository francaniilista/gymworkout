package com.gymworkout.example.controller;

import com.gymworkout.example.model.ErrorInfo;
import com.gymworkout.example.model.Workout;
import com.gymworkout.example.model.Name;
import com.gymworkout.example.model.dto.WorkoutDTO;
import com.gymworkout.example.service.WorkoutService;
import com.gymworkout.example.service.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

/**
 * Created by pfranca on 5/3/2016.
 */
@Controller("workoutController")
@RequestMapping(value = "/workout")
public class WorkoutController {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkoutController.class);

    @Autowired
    private WorkoutService service;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Workout> findAll() {
        LOGGER.info("Searching all workouts information");
        return service.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public Workout get(@PathVariable("id") final String id) throws NotFoundException {
        LOGGER.info("Getting workout with id: " + id);
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public Workout create(@RequestBody WorkoutDTO dto) {
        LOGGER.info("Adding new workout with information: {}", dto);
        Workout added = service.add(dto);
        LOGGER.info("Added workout with information: {}", added);
        return added;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody WorkoutDTO dto) throws NotFoundException {
        LOGGER.info("Updating workout with information: {}", dto);
        Workout updated = service.update(dto);
        LOGGER.info("Updated workout with information: {}", updated);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable String id) throws NotFoundException {
        LOGGER.info("Deleting workout with id: {}", id);
        service.remove(id);
        LOGGER.info("Deleted workout with id: {}", id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ErrorInfo handleNotFoundRequest(HttpServletRequest req, Exception ex) {
        ErrorInfo error = new ErrorInfo(req.getRequestURL().toString(), ex);
        LOGGER.info("Returning error info with information: {}", error);
        return error;
    }
}