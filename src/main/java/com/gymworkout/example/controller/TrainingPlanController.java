package com.gymworkout.example.controller;

import com.gymworkout.example.exception.ResourceNotFoundException;
import com.gymworkout.example.model.TrainingPlan;
import com.gymworkout.example.model.dto.TrainingPlanDTO;
import com.gymworkout.example.model.dto.error.ErrorDetail;
import com.gymworkout.example.service.TrainingPlanService;
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
 * Created by pfranca on 5/5/2016.
 */

@Controller("trainingPlanController")
@RequestMapping(value = "/trainingPlan")
@Api(value = "trainingPlan", description = "Training plan API")
public class TrainingPlanController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingPlanController.class);

    @Autowired
    private TrainingPlanService service;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves all the training plans", response=TrainingPlan.class, responseContainer="List")
    public ResponseEntity<Iterable<TrainingPlan>> findAll() {
        LOGGER.info("Searching all training plans information");
        Iterable<TrainingPlan> trainingPlans = service.findAll();
        return new ResponseEntity<Iterable<TrainingPlan>>(trainingPlans, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Retrieves a training plan associated with the training plan id", response = TrainingPlan.class)
    public ResponseEntity<TrainingPlan> get(@PathVariable("id") final String id) throws ResourceNotFoundException {
        LOGGER.info("Getting training plan with id: " + id);
        TrainingPlan plan = service.findOne(id);
        return new ResponseEntity<TrainingPlan>(plan, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Creates a new training plan", notes="The newly created training plan id will be sent in the location response header", response = Void.class)
    @ApiResponses(value = {
            @ApiResponse(code=201, message="Training plan created successfully", response=Void.class),
            @ApiResponse(code=500, message="Error creating training plan", response=ErrorDetail.class)
    })
    public ResponseEntity<Void> create(@Valid @RequestBody TrainingPlanDTO dto) {
        LOGGER.info("Adding new training plan with information: {}", dto);
        TrainingPlan added = service.add(dto);
        LOGGER.info("Added training plan with information: {}", added);

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
    @ApiOperation(value = "Updates a training plan", response = Void.class)
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody TrainingPlanDTO dto) throws ResourceNotFoundException {
        LOGGER.info("Updating training plan with information: {}", dto);
        TrainingPlan updated = service.update(dto);
        LOGGER.info("Updated training plan with information: {}", updated);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Removes a training plan", response = Void.class)
    public ResponseEntity<Void> remove(@PathVariable String id) throws ResourceNotFoundException {
        LOGGER.info("Deleting training plan with id: {}", id);
        service.remove(id);
        LOGGER.info("Deleted training plan with id: {}", id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}