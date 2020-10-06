package com.example.demodeploy.service;


import com.example.demodeploy.model.Category;

public interface ICategoryService {
    Iterable<Category> findAll();

    Category findById(long id);

    void save(Category model);

    void remove(Category id);
}
