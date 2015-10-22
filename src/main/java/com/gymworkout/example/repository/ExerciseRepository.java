package com.gymworkout.example.repository;

import com.gymworkout.example.model.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pfranca on 10/21/2015.
 */
@Repository
public class ExerciseRepository implements IRepository<Exercise> {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Exercise save(Exercise exercise) {
        ((MongoOperations)mongoTemplate).save(exercise);
        return exercise;
    }

    @Override
    public Exercise update(Exercise exercise) {
        Query searchExerciseQuery = new Query(Criteria.where("id").is(exercise.getId()));
        Update update = Update.update("id", exercise.getId())
                .set("name", exercise.getName())
                .set("reps", exercise.getReps());

        ((MongoOperations)mongoTemplate).updateFirst(searchExerciseQuery, update, Exercise.class);
        return exercise;
    }

    @Override
    public boolean remove(Exercise exercise) {
        ((MongoOperations)mongoTemplate).remove(exercise);
        return true;
    }

    @Override
    public List<Exercise> find() {
        return ((MongoOperations)mongoTemplate).findAll(Exercise.class);
    }

    @Override
    public Exercise findOne(Exercise exercise) {
        return ((MongoOperations)mongoTemplate).findOne(
                new Query(Criteria.where("id").is(exercise.getId())), Exercise.class);

    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }
}