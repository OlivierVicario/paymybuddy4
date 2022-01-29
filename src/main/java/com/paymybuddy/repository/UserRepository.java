package com.paymybuddy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.paymybuddy.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);
	
	public User findByFirstName(String firstName);
	
	@Query("SELECT u FROM User u WHERE u.firstName = ?1 AND u.lastName = ?2")
    public User findByFullName(String firstName, String LastName);
}
