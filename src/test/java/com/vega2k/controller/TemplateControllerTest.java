package com.vega2k.controller;



import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


/* WebMvcTest로 할 수 있지만 security 설정을 하고 난 후에는 SecurityConfig 설정을 적용받아야 하기 때문에
 * SpringBootTest로 선언해서 사용해야 한다.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TemplateController.class)
//@SpringBootTest
//@AutoConfigureMockMvc
public class TemplateControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	//HtmlUnit을 사용하여 html를 좀더 특화시켜서 테스트 하고 싶다면
	@Autowired
	WebClient webClient;
	
	@Test @Ignore
	public void hello() throws Exception {
		//thymeleaf 요청을 확인하는 테스트 메서드
		// 요청 /leaf
		// 응답 name : vega2k
		mockMvc.perform(get("/leaf"))
		   	   .andDo(print())
			   .andExpect(status().isOk())
			   .andExpect(view().name("leaf"))
			   .andExpect(model().attribute("name", "vega2k"));
	}
	
	@Test @Ignore
	public void html() throws Exception {
		HtmlPage page = webClient.getPage("/leaf");
		HtmlHeading1 h1 = page.getFirstByXPath("//h1");
		assertThat(h1.getTextContent()).isEqualToIgnoringCase("vega2k");
	}
	
	@Test
	@WithMockUser
	public void hellopage() throws Exception {
		mockMvc.perform(get("/hellopage")
			   .accept(MediaType.TEXT_HTML))		        
			   .andDo(print())
			   .andExpect(status().isOk())
			   .andExpect(view().name("hellopage"));
	}

	@Test
	public void hellopage_without_user() throws Exception {
		mockMvc.perform(get("/hellopage"))		        
			   .andDo(print())
			   .andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser
	public void mypage() throws Exception {
		mockMvc.perform(get("/mypage"))
			   .andDo(print())
			   .andExpect(status().isOk())
			   .andExpect(view().name("mypage"));
	}
}
