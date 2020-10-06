package com.example.demodeploy.repository;

import com.example.demodeploy.model.Category;
import com.example.demodeploy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface IUserRepository extends PagingAndSortingRepository<User,Long> {
    Page<User> findAllByUsernameContaining(String username, Pageable pageable);

    Page<User> findAllByUsernameContainingAndCategory(String username, Category category, Pageable pageable);

}
