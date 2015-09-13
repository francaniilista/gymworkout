package com.gymworkout.example.ws;

import com.gymworkout.example.model.dto.ExerciseDTO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author pfranca
 * Created by pfranca on 9/11/2015.
 */
@Service(value = "workoutService")
public class WorkoutServiceImpl implements IWorkoutService {
    private static final Logger logger = Logger.getLogger(WorkoutServiceImpl.class);

    @Override
    public boolean add(ExerciseDTO exerciseDTO) {
        logger.info("called add method");
        return true;
    }
}


