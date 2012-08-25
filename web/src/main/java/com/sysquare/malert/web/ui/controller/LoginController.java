package com.sysquare.malert.web.ui.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sysquare.malert.core.service.UserService;
import com.sysquare.malert.core.util.ApplicationConstants;
import com.sysquare.malert.core.util.GetMac;
import com.sysquare.malert.model.domain.User;

@Controller
public class LoginController {
    
    private static final Logger log = Logger.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	
	/*@Autowired
    private OptionService optionService;*/

	@RequestMapping("/login")
	public void listUsers(Map<String, Object> map) {
	}
	
	@RequestMapping("/appExpire")
    public void appExpire() {
    }
	
	@RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
	    request.getSession().invalidate();
	    return "redirect:/login.html";
    }
	@RequestMapping(value = "/inValidMachine", method = RequestMethod.GET)
    public void inValidMachine() {
	    System.out.println("test");
    }

	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginValidate(@RequestParam(required=true,value="j_username") String j_username, @RequestParam(required=true,value="j_password") String j_password, HttpServletRequest request) {
		System.out.println("Login:"+j_username);
		try {
			if(ApplicationConstants.MAC_VALIDATION && !GetMac.isValidMachine()) {
				log.error("redirect:/inValidMachine.html");
				return "redirect:/inValidMachine.html";
			}
		} catch (Exception e) {
			log.error("not a valid machine", e);
			return "redirect:/inValidMachine.html";
		}
	    log.info("User logged in :" + j_username);
	    User user = userService.findByEmailAndPassword(j_username, j_password);
	    if(user == null) {
	        return "redirect:/login.html?invalidLogin=Y";
	    } else {
	        request.getSession().setAttribute("loggedInUser", user);
	        return "redirect:/home.html";
	    }
	}
}
