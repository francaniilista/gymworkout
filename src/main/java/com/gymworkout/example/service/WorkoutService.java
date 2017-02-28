package com.gymworkout.example.service;

import com.gymworkout.example.model.Workout;
import com.gymworkout.example.model.Name;
import com.gymworkout.example.model.Workout;
import com.gymworkout.example.model.dto.WorkoutDTO;
import com.gymworkout.example.model.dto.WorkoutDTO;
import com.gymworkout.example.repository.WorkoutRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pfranca on 5/3/2016.
 */
@Service
public class WorkoutService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkoutService.class);

    private WorkoutRepository repository;

    public Workout add(WorkoutDTO added) {
        LOGGER.info("Adding new Workout with information {}", added);

        Workout built = buildWorkout(added);

        return repository.save(built);
    }

    public Workout update(WorkoutDTO updated) throws NotFoundException {
        LOGGER.info("Updating workout with information: {}", updated);

        Workout found  = repository.findOne(updated.getId());

        if (found == null) {
            LOGGER.info("No workout found with id: {}", updated.getId());
            throw new NotFoundException("No workout found with id: " + updated.getId());
        }

        repository.save(found);

        return found;
    }

    public List<Workout> findAll() {
        LOGGER.info("Finding all workouts");
        return repository.findAll();
    }

    public Workout findOne(String id) throws NotFoundException {
        LOGGER.info("Finding workout by id: {}", id);

        Workout found = repository.findOne(id);

        if (found == null) {
            LOGGER.info("No workout found with id: {}", id);
            throw new NotFoundException("No workout found with id: " + id);
        }

        LOGGER.info("Found workout: " + found);

        return found;
    }

    public boolean remove(String id) throws NotFoundException {
        LOGGER.info("Deleting workout by id: {}", id);

        Workout deleted = this.findOne(id);
        repository.delete(deleted);

        LOGGER.info("Deleted workout: {}", deleted);

        return true;
    }

    private Workout buildWorkout(WorkoutDTO built) {
        return Workout.getBuilder(built.getName(), built.getExercises()).build();
    }
    @Autowired
    public void setRepository(WorkoutRepository repository) {
        this.repository = repository;
    }
}