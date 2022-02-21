package com.codecoverage.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codecoverage.practice.dao.UserRepository;
import com.codecoverage.practice.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	

	public User addUser(User user) {
		return repository.save(user);
	}

	public List<User> getUsers() {
		List<User> users = repository.findAll();
		return users;
	}

	public List<User> getUserbyAddress(String address) {
		return repository.findByAddress(address);
	}

	public void deleteUser(User user) {
		repository.delete(user);
	}
}
