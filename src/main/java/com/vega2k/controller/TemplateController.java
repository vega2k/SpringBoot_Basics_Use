package com.vega2k.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.vega2k.exception.CustomException;

@Controller
public class TemplateController {
	
	@GetMapping("/leaf")
	public String leaf(Model model) {
		model.addAttribute("name","vega2k");
		return "leaf";
	}
	
	@GetMapping("/custom")
	public String custom(Model model) {
		throw new CustomException("E888", "This is custom message");
	}
	
	@GetMapping("/generic")
	public String generic(Model model) throws Exception{
		throw new Exception();
	}
	
	@ExceptionHandler(CustomException.class)
	public ModelAndView handleCustomException(CustomException ex) {
		ModelAndView model = new ModelAndView("error/generic_error");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());
		return model;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView model = new ModelAndView("error/generic_error");
		model.addObject("errMsg", "this is Exception.class");
		return model;
	}
	
	
	@GetMapping("/hellopage")
	public String hello() {
		return "hellopage";
	}
	
	@GetMapping("/mypage")
	public String mypage() {
		return "mypage";
	}
	
}
