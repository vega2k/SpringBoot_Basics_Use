package com.vega2k.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vega2k.entity.User;
import com.vega2k.entity.Users;
import com.vega2k.repository.UserRepository;
import com.vega2k.service.UserService;

//import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
/*
 * Spring MVC 설정을 개발자가 하지 않아도 내부에 
 * spring-boot-autoconfigure.jar 파일의 META-INF 디렉토리 내에
 * spring.factories의 
 * org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
 * WebMvc와 관련된 자동 설정 클래스가 적용되기 때문이다.
 * 
 * spring.factories 내에서 f3으로 이동이 않되므로 
 * import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
 * 를 한 후에 f3 눌러서 source를 확인한다.
 */
@RestController
public class UserRestController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService sampleService;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello " + sampleService.getName() ;
	}
	
	@PostMapping("/users/create")
	public User create(@RequestBody User user) {
		return user;
	}
	
	@RequestMapping(value="/users/{id}")
	public User getUser(@PathVariable Long id) {
		return userRepository.findById(id).get();
	}
	
	@RequestMapping(value="/users", produces = { "application/json" })
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@RequestMapping(value="/users2", produces = { "application/xml" })
	public List<User> getUsers2() {
		return userRepository.findAll();
	}

	@RequestMapping(value="/usersxml", produces = { "application/xml","text/xml" })
	public Users getUsersXml() {
		Users users = new Users();
		users.setUsers(userRepository.findAll());
		return users;
	}
}
