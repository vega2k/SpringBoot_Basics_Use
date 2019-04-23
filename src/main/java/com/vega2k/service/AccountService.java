package com.vega2k.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vega2k.entity.Account;
import com.vega2k.repository.AccountRepository;

@Service
public class AccountService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public Account createAccount(String username, String password) {
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(passwordEncoder.encode(password));
		//account.setPassword(password);
		return accountRepository.save(account);
	}
	
	@Override
	//login 할때 사용자가 입력한 정보가 유효한지를 체크한다.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> byUsername = accountRepository.findByUsername(username);
		Account account = byUsername.orElseThrow(() -> new UsernameNotFoundException(username));
		return new User(account.getUsername(), 
				account.getPassword(), authorities());
	}

	/*
	 * 세번째 인자
	 * USER라는 ROLE을 가진 사용자이다 라고 설정하는 부분
	 */
	private Collection<? extends GrantedAuthority> authorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	}
	
	
	@PostConstruct
	public void init() {
		Optional<Account> byUsername = accountRepository.findByUsername("vega2k");
		if(!byUsername.isPresent()) {
			Account newAccount = this.createAccount("vega2k", "1234");
			System.out.println(newAccount);
		}		
	}
}
