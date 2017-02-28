package com.gymworkout.example.service;

import com.gymworkout.example.exception.ResourceNotFoundException;
import com.gymworkout.example.model.TrainingPlan;
import com.gymworkout.example.model.dto.TrainingPlanDTO;
import com.gymworkout.example.repository.TrainingPlanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pfranca on 5/5/2016.
 */
@Service
public class TrainingPlanService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrainingPlanService.class);

    private TrainingPlanRepository repository;

    public TrainingPlan add(TrainingPlanDTO added) {
        LOGGER.info("Adding new TrainingPlan with information {}", added);
        TrainingPlan built = buildTrainingPlan(added);
        return repository.save(built);
    }

    public TrainingPlan update(TrainingPlanDTO updated) throws NotFoundException {
        LOGGER.info("Updating training plan with information: {}", updated);
        TrainingPlan found  = repository.findOne(updated.getId());
        if (found == null) {
            LOGGER.info("No training plan found with id: {}", updated.getId());
            throw new ResourceNotFoundException("No training plan found with id: " + updated.getId());
        }
        found.update(updated.getName(), updated.getWorkouts());
        repository.save(found);
        return found;
    }

    public List<TrainingPlan> findAll() {
        LOGGER.info("Finding all training plans");
        return repository.findAll();
    }

    public TrainingPlan findOne(String id) throws NotFoundException {
        LOGGER.info("Finding training plan by id: {}", id);
        TrainingPlan found = repository.findOne(id);
        if (found == null) {
            LOGGER.info("No training plan found with id: {}", id);
            throw new ResourceNotFoundException("No training plan found with id: " + id);
        }
        LOGGER.info("Found training plan: " + found);
        return found;
    }

    public boolean remove(String id) throws NotFoundException {
        LOGGER.info("Deleting training plan by id: {}", id);
        TrainingPlan deleted = this.findOne(id);
        repository.delete(deleted);
        LOGGER.info("Deleted training plan: {}", deleted);
        return true;
    }

    private TrainingPlan buildTrainingPlan(TrainingPlanDTO built) {
        TrainingPlan plan = new TrainingPlan();
        plan.setName(built.getName());

        if (built.getWorkouts() != null && built.getWorkouts().size() > 0) {
            plan.getWorkouts().addAll(built.getWorkouts());
        }

        return plan;
    }

    @Autowired
    public void setRepository(TrainingPlanRepository repository) {
        this.repository = repository;
    }
}