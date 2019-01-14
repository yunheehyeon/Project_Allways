package edu.spring.project.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.spring.project.domain.User;
import edu.spring.project.service.UserPageService;

@Controller
public class UserPageController {
	
	private static Logger logger =
			LoggerFactory.getLogger(UserPageController.class);

	@Autowired
	private UserPageService userPageService;
	
	@RequestMapping(value = "/userPage", method = RequestMethod.GET)
	public String home(int uno, HttpSession session) {
		
		User user = userPageService.check(uno);
		
		session.setAttribute("userInfo", user);
		return "my_page";
	}
	
	
}

