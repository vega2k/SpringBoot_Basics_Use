package com.vega2k.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vega2k.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	//Account findByUsername(String username);
	Optional<Account> findByUsername(String username);
}
