package com.sysquare.malert.web.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sysquare.malert.model.domain.VisitObservation;

public class VisitObservationsVO {
    private Integer workshopId;
    
    private Date visitDate;
    private List<VisitObservation> visitObservations;
    private String act;
    
    public VisitObservationsVO() {
        visitObservations = new ArrayList<VisitObservation>();
    }

    public Integer getWorkshopId() {
        return workshopId;
    }
    public void setWorkshopId(Integer workshopId) {
        this.workshopId = workshopId;
    }
    public Date getVisitDate() {
        return visitDate;
    }
    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
    public List<VisitObservation> getVisitObservations() {
        return visitObservations;
    }
    public void setVisitObservations(List<VisitObservation> visitObservations) {
        this.visitObservations = visitObservations;
    }
    public String getAct() {
        return act;
    }
    public void setAct(String act) {
        this.act = act;
    }
}
