package com.gymworkout.example.ws;

import com.gymworkout.example.model.dto.ExerciseDTO;

import javax.jws.WebService;

/**
 * @author pfranca
 * Created by pfranca on 9/11/2015.
 */
@WebService
public interface IWorkoutService {
    boolean add(ExerciseDTO exerciseDTO);

}
