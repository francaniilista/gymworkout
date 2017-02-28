package com.gymworkout.example.repository;

import com.gymworkout.example.model.TrainingPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by pfranca on 5/3/2016.
 */
public interface TrainingPlanRepository extends MongoRepository<TrainingPlan, String> {

}
