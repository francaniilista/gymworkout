package com.gymworkout.example.controller;

import com.gymworkout.example.exception.ResourceNotFoundException;
import com.gymworkout.example.model.Exercise;
import com.gymworkout.example.model.dto.ExerciseDTO;
import com.gymworkout.example.model.dto.error.ErrorDetail;
import com.gymworkout.example.service.ExerciseService;
import com.gymworkout.example.service.NotFoundException;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Created by pfranca on 9/30/2015.
 */
@Controller("exerciseController")
@RequestMapping(value = "/exercise")
@Api(value = "exercise", description = "Exercise API")
public class ExerciseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseController.class);

    @Autowired
    private ExerciseService service;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves all the exercises", response = Exercise.class, responseContainer = "List")
    public ResponseEntity<Iterable<Exercise>> findAll() {
        LOGGER.info("Searching all exercises information");
        Iterable<Exercise> exercises = service.findAll();
        return new ResponseEntity<Iterable<Exercise>>(exercises, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves an exercise associated with the id", response = Exercise.class)
    public ResponseEntity<Exercise> get(@PathVariable("id") final String id) throws ResourceNotFoundException {
        LOGGER.info("Getting exercise with id: " + id);
        Exercise exercise = service.findOne(id);
        return new ResponseEntity<Exercise>(exercise, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Creates a new exercise", notes="The newly created id will be sent in the location response header", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code=201, message="Exercise created successfully", response=Void.class),
            @ApiResponse(code=500, message="Error creating exercise", response=ErrorDetail.class)
    })
    public ResponseEntity<Void> create(@Valid @RequestBody ExerciseDTO dto) {
        LOGGER.info("Adding new exercise with information: {}", dto);
        Exercise added = service.add(dto);
        LOGGER.info("Added exercise with information: {}", added);

        //Set the location header for newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newTrainingUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(added.getId())
                .toUri();
        responseHeaders.setLocation(newTrainingUri);

        return new ResponseEntity<Void>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "Updates an exercise", response = Void.class)
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody ExerciseDTO dto) throws ResourceNotFoundException {
        LOGGER.info("Updating exercise with information: {}", dto);
        Exercise updated = service.update(dto);
        LOGGER.info("Updated exercise with information: {}", updated);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Removes an exercise", response = Void.class)
    public ResponseEntity<Void> remove(@PathVariable String id) throws ResourceNotFoundException {
        LOGGER.info("Deleting exercise with id: {}", id);
        service.remove(id);
        LOGGER.info("Deleted exercise with id: {}", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}