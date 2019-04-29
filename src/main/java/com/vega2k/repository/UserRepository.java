package com.vega2k.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vega2k.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByName(String name);
}
