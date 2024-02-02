package com.rest.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.example.demo.models.User;

public interface UserRepo extends JpaRepository<User, Long>{

}
