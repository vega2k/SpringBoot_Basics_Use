package com.vega2k.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.vega2k.service.UserService;

/*
 * MockMvc를 생성하려면 @SpringBootTest의 인자에 WebEnvironment.MOCK 써주고,
 * @AutoConfigureMockMvc 선언하면 된다.
 * 
 * WebMvcTest는 Security 설정이 되어 있는 상태에서는 테스트가 잘 이루어지지 않는다. 
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebMvcTest(UserController.class)
//@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	UserService mockUserService;

	
	@Test @Ignore
	public void hello() throws Exception {
		when(mockUserService.getName()).thenReturn("vega2k");

		mockMvc.perform(get("/hello"))
			   .andExpect(status().isOk())
			   .andExpect(content().string("hello vega2k"))
			   .andDo(print());
	}
	
	@Test @Ignore
	public void createUser_JSON() throws Exception {
		String userJson = "{\"username\":\"vega2k\",\"password\":\"123\"}";
		mockMvc.perform(post("/users/create")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(userJson)) //여기까지가 요청, andExpect 부터가 응답을 받는 부분
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.username", is(equalTo("vega2k"))))
			.andExpect(jsonPath("$.password", is(equalTo("123"))))
			.andDo(print());		       
	}
	
	
	//요청은 json으로 응답은 xml로
	@Test
	public void createUser_XML() throws Exception {
		String userJson = "{\"username\":\"vega2k\",\"password\":\"123\"}";
		mockMvc.perform(post("/users/create")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_XML)
				.content(userJson))
			.andExpect(status().isOk())
			.andExpect(xpath("/User/username").string("vega2k"))
			.andExpect(xpath("/User/password").string("123"))
			.andDo(print());
		       
	}
}
