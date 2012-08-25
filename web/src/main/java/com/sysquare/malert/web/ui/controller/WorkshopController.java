package com.sysquare.malert.web.ui.controller;

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

import com.sysquare.malert.core.service.WorkshopService;
import com.sysquare.malert.model.domain.Workshop;
import com.sysquare.malert.web.util.WebUtil;

@Controller
public class WorkshopController {

    public static final String MODEL_KEY = "workshop";
    @Autowired
    private WorkshopService workshopService;
    
    @ModelAttribute(MODEL_KEY)
    public Workshop populateCommandObject(ModelMap map, @RequestParam(required = false) Integer id) {
        if(id != null) {
            Workshop Workshop = workshopService.findById(id);
            return Workshop;
        } else {
            return new Workshop();
        }
    }

    @RequestMapping(value = "/addWorkshop", method = RequestMethod.GET)
    public void getAddWorkshop(@ModelAttribute(MODEL_KEY) Workshop workshop) {

    }

    @RequestMapping(value = "/addWorkshop", method = RequestMethod.POST)
    public String postAddWorkshop(@ModelAttribute(MODEL_KEY) Workshop workshop, BindingResult result, HttpServletRequest request) {
        validate(workshop, workshop.getWarnings());
        if(result.hasErrors()) {
            WebUtil.setErrors(result, workshop.getWarnings());
        }
        if(workshop.getWarnings().isEmpty()) {
            workshop.setUpdatedBy(WebUtil.getLoginUser(request));
            workshopService.addWorkshop(workshop);            
            return "redirect:/listWorkshop.html";
        }
        return null;
    }
    
    @RequestMapping(value = "/editWorkshop", method = RequestMethod.GET)
    public void getEditWorkshop(@RequestParam(required=true,value="id") Integer id) {
    }
    
    @RequestMapping(value = "/editWorkshop", method = RequestMethod.POST)
    public String postEditWorkshop(@ModelAttribute(MODEL_KEY) Workshop workshop, BindingResult result, HttpServletRequest request) {
        validate(workshop, workshop.getWarnings());
        if(result.hasErrors()) {
            WebUtil.setErrors(result, workshop.getWarnings());
        }
        if(workshop.getWarnings().isEmpty()) {
            workshop.setUpdatedBy(WebUtil.getLoginUser(request));
            workshopService.updateWorkshop(workshop);
            return "redirect:/listWorkshop.html";
        }
        return null;
    }

    @RequestMapping(value = "/deactivateWorkshop", method = RequestMethod.GET)
    public String deactivateWorkshop(@RequestParam(required=true,value="id") Integer id, HttpServletRequest request) {
        Workshop workshop = workshopService.findById(id); 
        workshop.setUpdatedBy(WebUtil.getLoginUser(request));
        workshop.setActive(false);
        workshopService.updateWorkshop(workshop);
        return "redirect:/listWorkshop.html";
    }
    
    @RequestMapping(value = "/activateWorkshop", method = RequestMethod.GET)
    public String activateWorkshop(@RequestParam(required=true,value="id") Integer id, HttpServletRequest request) {
        Workshop workshop = workshopService.findById(id); 
        workshop.setUpdatedBy(WebUtil.getLoginUser(request));
        workshop.setActive(true);
        workshopService.updateWorkshop(workshop);
        return "redirect:/listWorkshop.html";
    }
    
    @RequestMapping(value = "/listWorkshop", method = RequestMethod.GET)
    public String listWorkshop() {
        return "redirect:/home.html";
    }
    
    private void validate(Workshop workshop, List<String> warnings) {
        if(workshop.getName() == null || workshop.getName().trim().isEmpty()) {
            warnings.add("Please enter name.");
        }
        if(workshop.getLocation() == null || workshop.getLocation().trim().isEmpty()) {
            warnings.add("Please enter location.");
        }
        if(workshop.getCode() == null || workshop.getCode().trim().isEmpty()) {
            warnings.add("Please enter code.");
        } else {
            if(null != workshopService.findByCode(workshop.getCode(), workshop.getId())) {
                warnings.add("Workshop alrady exist with code:" + workshop.getCode().trim()); 
            }
        }
        if(workshop.getTsmName() == null || workshop.getTsmName().trim().isEmpty()) {
            warnings.add("Please enter Tsm Name.");
        }
        if(workshop.getTsmEmail() == null || workshop.getTsmEmail().trim().isEmpty()) {
            warnings.add("Please enter email.");
        } else if(!WebUtil.isValidEmail(workshop.getTsmEmail().trim())) { 
            warnings.add("Please enter valid email.");
        }
    }
}
