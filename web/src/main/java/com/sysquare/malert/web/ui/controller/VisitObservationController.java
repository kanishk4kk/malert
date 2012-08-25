package com.sysquare.malert.web.ui.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sysquare.malert.core.service.VisitObservationService;
import com.sysquare.malert.core.service.WorkshopService;
import com.sysquare.malert.core.util.CalendarUtil;
import com.sysquare.malert.core.util.OrderBy;
import com.sysquare.malert.model.domain.User;
import com.sysquare.malert.model.domain.VisitObservation;
import com.sysquare.malert.model.domain.Workshop;
import com.sysquare.malert.web.util.WebUtil;
import com.sysquare.malert.web.vo.VisitObservationsVO;

@Controller
public class VisitObservationController {

    @Autowired
    private VisitObservationService visitObservationService;
    @Autowired
    private WorkshopService workshopService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        // true passed to CustomDateEditor constructor means convert empty String to null
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/addVisitObservation", method = RequestMethod.GET)
    public void getAddVisitObservation(ModelMap map, @ModelAttribute("visitObservationsVO") VisitObservationsVO visitObservationsVO, @RequestParam(value="workshopId") Integer workshopId) {
        visitObservationsVO.setWorkshopId(visitObservationsVO.getWorkshopId());
        for (int i = 0; i < 2; i++) {
            visitObservationsVO.getVisitObservations().add(new VisitObservation());
        }
        Workshop workshop = workshopService.findById(workshopId);
        map.put("workshop", workshop);
    }
    
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "/addVisitObservation", method = RequestMethod.POST)
    public String addMoreVisitObservation(ModelMap map, @ModelAttribute("visitObservationsVO") VisitObservationsVO visitObservationsVO, 
            BindingResult result, HttpServletRequest request) {
        
        Workshop workshop = workshopService.findById(visitObservationsVO.getWorkshopId());
        visitObservationsVO.setWorkshopId(visitObservationsVO.getWorkshopId());
        
        boolean validationFailed = false;

        if(visitObservationsVO.getVisitDate() == null) {
            if(!result.hasFieldErrors("visitDate")) {
                result.rejectValue("visitDate", "field.not.empty");
            }
            validationFailed =true;
        } else if(visitObservationsVO.getVisitDate().getYear() < 101){
            result.rejectValue("visitDate", "typeMismatch.java.util.Date");
            validationFailed =true;
        }
        int i=0;
        for(VisitObservation visitObservation : visitObservationsVO.getVisitObservations()) {
            if(visitObservation.getTargetDate() != null) {
                if(visitObservation.getObservation()== null || visitObservation.getObservation().trim().isEmpty()) {
                    result.rejectValue("visitObservations["+i+"].observation", "field.not.empty");
                    validationFailed =true;
                }
                if(visitObservation.getActionPlan()== null || visitObservation.getActionPlan().trim().isEmpty()) {
                    result.rejectValue("visitObservations["+i+"].actionPlan", "field.not.empty");
                    validationFailed =true;
                }
                if(visitObservation.getTargetDate().getYear() < 101){
                    result.rejectValue("visitObservations["+i+"].targetDate", "typeMismatch.java.util.Date");
                    validationFailed =true;
                }
            } else if((visitObservation.getTargetDate() == null) &&
                     (visitObservation.getObservation() != null && !visitObservation.getObservation().trim().isEmpty()) ||
                     (visitObservation.getActionPlan() != null && !visitObservation.getActionPlan().trim().isEmpty())
                            ) { 
                if(visitObservation.getObservation()== null || visitObservation.getObservation().trim().isEmpty()) {
                    result.rejectValue("visitObservations["+i+"].observation", "field.not.empty");
                    validationFailed =true;
                }
                if(visitObservation.getActionPlan()== null || visitObservation.getActionPlan().trim().isEmpty()) {
                    result.rejectValue("visitObservations["+i+"].actionPlan", "field.not.empty");
                    validationFailed =true;
                }
                if(!result.hasFieldErrors("visitObservations["+i+"].targetDate")) {
                    result.rejectValue("visitObservations["+i+"].targetDate", "field.not.empty");
                }
                validationFailed =true;
            }
            i++;
        }
        if(!result.hasErrors() && !validationFailed) {
            if(visitObservationsVO.getAct() != null) {
                if("Add More".equals(visitObservationsVO.getAct())) {
                    visitObservationsVO.getVisitObservations().add(new VisitObservation());
                } else if("Save".equals(visitObservationsVO.getAct())) {
                    populateToSave(visitObservationsVO, workshop, WebUtil.getLoginUser(request));
                    visitObservationService.saveVisitObservation(visitObservationsVO.getVisitObservations());
                    return "redirect:/home.html";
                }
            }
        }
        map.put("workshop", workshop);
        return null;
    }
    
    @RequestMapping(value = "/workshopVisitObservations", method = RequestMethod.GET)
    public void getWorkshopVisitObservations(ModelMap map, @RequestParam(value="workshopId") Integer workshopId) {
        List<String> groupBy = Arrays.asList(new String[]{"visitDate", "visitedBy"});
        List<OrderBy> orderBy = Arrays.asList(new OrderBy[]{new OrderBy("visitDate", false), new OrderBy("visitedBy.fname", true), new OrderBy("visitedBy.lname", true)});
        List<VisitObservation> workshopVisitObservations = visitObservationService.findByWorkshop(workshopId, groupBy, orderBy);
        Workshop workshop = workshopService.findById(workshopId);
        map.put("workshop", workshop);
        map.put("WorkshopVisitObservations", workshopVisitObservations);
    }
    
    @RequestMapping(value = "/workshopVisitObservationsByStatus", method = RequestMethod.GET)
    public void workshopVisitObservationsByStatus(ModelMap map, @RequestParam(value="workshopId") Integer workshopId, @RequestParam(value="visitDate", required=false) String visitDateStr, @RequestParam(value="status") String status) {
        
        Date visitDate = null;
        if(visitDateStr != null && !visitDateStr.trim().isEmpty()) {
            try {
                visitDate = CalendarUtil.DATE_FORMAT.parse(visitDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<VisitObservation> workshopVisitObservations = visitObservationService.findByWorkshopVisitDateAndStatus(workshopId, visitDate, status);
        Workshop workshop = workshopService.findById(workshopId);
        map.put("status", status);
        map.put("visitDate", visitDate);
        map.put("workshop", workshop);
        map.put("WorkshopVisitObservations", workshopVisitObservations);
    }
    
    @RequestMapping(value = "/markAsImplemented", method = RequestMethod.GET)
    public String markAsImplemented(ModelMap map, @RequestParam(value="workshopId", required=true) Integer workshopId, @RequestParam(value="voId", required=true) Integer voId) {
        visitObservationService.markAsImplemented(voId);
        return "redirect:/workshopVisitObservationsByStatus.html?workshopId="+workshopId+"&status=pending";
    }
    
    @RequestMapping(value = "/pendingVOAfterTarget", method = RequestMethod.GET)
    public void pendingVOAfterTarget(ModelMap map, HttpServletRequest request) {
        List<VisitObservation> pendingVisitObservations = visitObservationService.findAllPendingVOAfterTarget(WebUtil.getLoginUser(request));
        map.put("WorkshopVisitObservations", pendingVisitObservations);
    }
    
    private void populateToSave(VisitObservationsVO visitObservationsVO, Workshop workshop, User loginUser) {
        List<VisitObservation> visitObservations = visitObservationsVO.getVisitObservations();
        Iterator<VisitObservation> itr = visitObservations.iterator();
        while (itr.hasNext()) {
            VisitObservation visitObservation = itr.next();
            if(visitObservation.getTargetDate() != null) {
                visitObservation.setVisitDate(visitObservationsVO.getVisitDate());
                visitObservation.setUpdateDate(new Date());
                visitObservation.setStatus("pending");
                visitObservation.setWorkshop(workshop);
                visitObservation.setVisitedBy(loginUser);
            } else {
                itr.remove();
            }
        }
    }
    
    
}
