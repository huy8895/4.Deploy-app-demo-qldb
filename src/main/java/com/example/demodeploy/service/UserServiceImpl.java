package com.example.demodeploy.service;


import com.example.demodeploy.model.Category;
import com.example.demodeploy.model.User;
import com.example.demodeploy.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    IUserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(User model) {
        userRepository.save(model);
    }

    @Override
    public void remove(User id) {
        userRepository.delete(id);
    }

    @Override
    public Page<User> findAllByUserContaining(String username, Pageable pageable) {
        return userRepository.findAllByUsernameContaining(username,pageable);
    }

    @Override
    public Page<User> findAllByUsernameContainingAndCategory(String username, Category category, Pageable pageable) {
        return userRepository.findAllByUsernameContainingAndCategory(username,category,pageable);
    }

    @Override
    public Iterable<User> findAllByProvince(User user) {
        return null;
    }
}
