package com.gymworkout.example.repository;

import java.util.List;

/**
 * Created by pfranca on 10/21/2015.
 */
public interface IRepository<T> {

    public T save(T t);
    public T update(T t);
    public boolean remove(T t);
    public List<T> find();
    public T findOne(T t);
}
