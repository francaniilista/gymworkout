package com.gymworkout.example.service;

import com.gymworkout.example.model.Exercise;
import com.gymworkout.example.model.Name;
import com.gymworkout.example.model.dto.ExerciseDTO;
import com.gymworkout.example.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pfranca on 10/22/2015.
 */
@Service
public class ExerciseService {
    @Autowired
    private ExerciseRepository repository;

    public Exercise add(ExerciseDTO exerciseDTO) {
        return repository.save(setExerciseDTO(exerciseDTO));
    }

    public List<Exercise> get() {
        return repository.find();
    }

    private Exercise setExerciseDTO(ExerciseDTO exerciseDTO) {
        return Exercise.getBuilder(Name.getByName(exerciseDTO.getName()),exerciseDTO.getWeight(),
                exerciseDTO.getDate().getTimeInMillis())
                .reps(exerciseDTO.getReps())
                .serie(exerciseDTO.getSeries())
                .build();
    }
}
