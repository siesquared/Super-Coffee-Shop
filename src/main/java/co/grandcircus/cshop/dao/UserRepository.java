package main.java.co.grandcircus.cshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.coffeeshop.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsernameAndPassword(String username, String password);
}

