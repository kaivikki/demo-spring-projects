package com.kaivikki.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaivikki.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
