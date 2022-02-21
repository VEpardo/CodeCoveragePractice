package com.codecoverage.practice.dao;

import java.util.List;

import com.codecoverage.practice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	List<User> findByAddress(String address);

}
