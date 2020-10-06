package com.example.demodeploy.service;


import com.example.demodeploy.model.Category;
import com.example.demodeploy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Iterable<User> findAll();

    User findById(long id);

    void save(User model);

    void remove(User id);

    Page<User> findAllByUserContaining(String firstName, Pageable pageable);

    Page<User> findAllByUsernameContainingAndCategory(String username, Category category, Pageable pageable);

    Iterable<User> findAllByProvince(User user);

}
