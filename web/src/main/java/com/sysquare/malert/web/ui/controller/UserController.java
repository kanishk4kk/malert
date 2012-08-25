package com.sysquare.malert.web.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sysquare.malert.core.service.UserService;
import com.sysquare.malert.model.domain.User;
import com.sysquare.malert.web.util.WebUtil;

@Controller
public class UserController {
    public static final String MODEL_KEY = "user";

    @Autowired
    private UserService userService;

    @ModelAttribute(MODEL_KEY)
    public User populateCommandObject(ModelMap map, @RequestParam(required = false) Integer id) {
        if(id != null) {
            User user = userService.findById(id);
            return user;
        } else {
            return new User();
        }
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public void getAddUser(@ModelAttribute(MODEL_KEY) User user) {

    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String postAddUser(@ModelAttribute(MODEL_KEY) User user, BindingResult result, HttpServletRequest request) {
        User adminUser = WebUtil.getLoginUser(request);
        if(adminUser.isAdminUser()) {
            validate(user, user.getWarnings());
            if(result.hasErrors()) {
                WebUtil.setErrors(result, user.getWarnings());
            }
            if(user.getWarnings().isEmpty()) {
                user.setUpdatedBy(WebUtil.getLoginUser(request));
                userService.addUser(user);
                return "redirect:/resetPasswords.html";
            }
        }
        return null;
    }
    
    @RequestMapping(value = "/editUser", method = RequestMethod.GET)
    public User getEditUser(HttpServletRequest request) {
        return WebUtil.getLoginUser(request);
    }
    
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public String postEditUser(@ModelAttribute(MODEL_KEY) User user, BindingResult result, HttpServletRequest request) {
        validate(user, user.getWarnings());
        if(result.hasErrors()) {
            WebUtil.setErrors(result, user.getWarnings());
        }
        if(user.getWarnings().isEmpty()) {
            user.setUpdatedBy(WebUtil.getLoginUser(request));
            userService.updateUser(user);
            return "redirect:/home.html";
        }
        return null;
    }
    
    @RequestMapping(value = "/resetPasswords", method = RequestMethod.GET)
    public void resetPasswords(ModelMap map, @RequestParam(value="userId", required=false) Integer userId, HttpServletRequest request) {
        User adminUser = WebUtil.getLoginUser(request);
        if(adminUser.isAdminUser()) {
            List<User> allUser = new ArrayList<User>();
            List<User> allUserResult = userService.listUser(adminUser);
            if(allUserResult != null) {
                for (User user : allUserResult) {
                    if(user.isAdminUser()) {
                        continue;
                    }
                    allUser.add(user);    
                }
            }
            User user = userService.findById(userId);
            map.put("User", user);
            map.put("Users", allUser);
        }
    }
    
    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public String resetPasswords(@RequestParam(value="userId", required=true) Integer userId, HttpServletRequest request) {
        User adminUser = WebUtil.getLoginUser(request);
        if(adminUser.isAdminUser()) {
            User user = userService.findById(userId);
            if(user != null) {
                user.setPassword("password");
                userService.updateUser(user);
                return "redirect:/resetPasswords.html?userId="+userId;
            }
        }
        return null;
    }
    
    private void validate(User user, List<String> warnings) {
        
        if(user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            warnings.add("Please enter email.");
        } else if(!WebUtil.isValidEmail(user.getEmail().trim())) { 
            warnings.add("Please enter valid email.");
        } else if(userService.isUserExistWithEmail(user.getEmail(), user.getId())) {
            warnings.add("User already exists with the same email address.");
        }
        if(user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            warnings.add("Please enter password.");
        }
        if(user.getFname() == null || user.getFname().trim().isEmpty()) {
            warnings.add("Please enter first name.");
        }
        if(user.getLname() == null || user.getLname().trim().isEmpty()) {
            warnings.add("Please enter last name.");
        }
    }
}
