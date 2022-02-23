package com.paymybuddy.testing.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.paymybuddy.model.User;
import com.paymybuddy.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
public class UserTests {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository repo;

	// test methods go below

	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("ravikumar@gmail.com");
		user.setPassword("ravi2020");
		user.setFirstName("Ravi");
		user.setLastName("Kumar");

		User savedUser = repo.save(user);

		User existUser = entityManager.find(User.class, savedUser.getUserId());

		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
	}

	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setEmail("ravikumar@gmail.com");
		user.setPassword("ravi2020");
		user.setFirstName("Ravi");
		user.setLastName("Kumar");

		User savedUser = repo.save(user);

		savedUser.setEmail("ravikumar@gmail.fr");

		User savedUser1 = repo.save(savedUser);

		User existUser1 = entityManager.find(User.class, savedUser1.getUserId());

		assertThat(existUser1.getEmail()).isEqualTo("ravikumar@gmail.fr");
	}

	@Test
	public void testDeleteUser() {
		User user = new User();
		user.setEmail("ravikumar@gmail.com");
		user.setPassword("ravi2020");
		user.setFirstName("Ravi");
		user.setLastName("Kumar");

		User savedUser = repo.save(user);
		repo.delete(savedUser);
		User existUser = entityManager.find(User.class, savedUser.getUserId());

		assertThat(existUser).isNull();
	}
}
