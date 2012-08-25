package com.sysquare.malert.web.ui.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sysquare.malert.core.service.WorkshopService;
import com.sysquare.malert.model.domain.User;
import com.sysquare.malert.model.domain.Workshop;
import com.sysquare.malert.web.util.WebUtil;

@Controller
public class DashboardController {

    @Autowired
    private WorkshopService workshopService;
    
    @RequestMapping("/home")
    public void listUsers(ModelMap map, HttpServletRequest request) {
        User user = WebUtil.getLoginUser(request);
        List<Workshop> allWorkshops = workshopService.listWorkshop(user.isAdminUser());
        map.put("Workshops", allWorkshops);
    }
}
