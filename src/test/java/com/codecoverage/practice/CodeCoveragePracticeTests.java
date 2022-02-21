package com.codecoverage.practice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.codecoverage.practice.model.User;
import com.codecoverage.practice.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.codecoverage.practice.dao.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeCoveragePracticeTests {

	@Test
	public void main() {
		CodeCoveragePractice.main(new String[] {});
	}

	@Test
	public void contextLoads() {
	}
	@Autowired
	private UserService service;

	@MockBean
	private UserRepository repository;



	@Test
	public void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new User(16, "Ana", 21, "USA"), new User(22, "Lily", 25, "UK")).collect(Collectors.toList()));
		assertEquals(2, service.getUsers().size());
	}

	@Test
	public void getUserbyAddressTest() {
		String address = "Bangalore";
		when(repository.findByAddress(address))
				.thenReturn(Stream.of(new User(12, "Montse", 24, "Mexico")).collect(Collectors.toList()));
		assertEquals(1, service.getUserbyAddress(address).size());
	}

	@Test
	public void saveUserTest() {
		User user = new User(19, "Alex", 33, "Canada");
		when(repository.save(user)).thenReturn(user);
		Assert.assertEquals(user, service.addUser(user));
	}

	@Test
	public void deleteUserTest() {
		User user = new User(12, "Gaby", 25, "Mexico");
		service.deleteUser(user);
		verify(repository, times(1)).delete(user);
	}

}
