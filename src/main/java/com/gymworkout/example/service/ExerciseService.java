package com.gymworkout.example.service;

import com.gymworkout.example.exception.ResourceNotFoundException;
import com.gymworkout.example.model.Exercise;
import com.gymworkout.example.model.Name;
import com.gymworkout.example.model.dto.ExerciseDTO;
import com.gymworkout.example.repository.ExerciseRepository;
import com.gymworkout.example.util.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pfranca on 10/22/2015.
 */
@Service
public class ExerciseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExerciseService.class);
    
    private ExerciseRepository repository;

    public Exercise add(ExerciseDTO added) {
        LOGGER.info("Adding new Exercise with information {}", added);
        Exercise built = buildExercise(added);
        return repository.save(built);
    }

    public Exercise update(ExerciseDTO updated) throws ResourceNotFoundException {
        LOGGER.info("Updating exercise with information: {}", updated);
        Exercise found  = repository.findOne(updated.getId());
        if (found == null) {
            LOGGER.info("No exercise found with id: {}", updated.getId());
            throw new ResourceNotFoundException("No exercise found with id: " + updated.getId());
        }
        found.update(updated.getName(), updated.getSeries(), updated.getReps(), updated.getWeight(), updated.getDate().getTimeInMillis());
        repository.save(found);
        return found;
    }

    public List<Exercise> findAll() {
        LOGGER.info("Finding all exercises");
        return repository.findAll();
    }

    public Exercise findOne(String id) throws ResourceNotFoundException {
        LOGGER.info("Finding exercise by id: {}", id);
        Exercise found = repository.findOne(id);
        if (found == null) {
            LOGGER.info("No exercise found with id: {}", id);
            throw new ResourceNotFoundException("No exercise found with id: " + id);
        }
        LOGGER.info("Found exercise: " + found);
        return found;
    }

    public boolean remove(String id) throws ResourceNotFoundException {
        LOGGER.info("Deleting exercise by id: {}", id);
        Exercise deleted = this.findOne(id);
        repository.delete(deleted);
        LOGGER.info("Deleted exercise: {}", deleted);
        return true;
    }

    private Exercise buildExercise(ExerciseDTO built) {
        return Exercise.getBuilder(Name.getByName(built.getName()),built.getWeight(),
                CalendarUtil.cutOffTimeInformation(built.getDate().getTimeInMillis()))
                .id(built.getId())
                .reps(built.getReps())
                .series(built.getSeries())
                .build();
    }

    @Autowired
    public void setRepository(ExerciseRepository repository) {
        this.repository = repository;
    }
}