package com.sysquare.malert.model.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="visit_observation ")
public class VisitObservation implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Status {
        implemented,pending;
    }
    
    @Id
    @Column(name="id")
    @GeneratedValue
    private Integer id;

    @Column(name="visit_date")
    private Date visitDate;

    @Column(name="observation")
    private String observation;

    @Column(name="action_plan")
    private String actionPlan;

    @Column(name="target_date")
    private Date targetDate;

    @Column(name="update_date")
    private Date updateDate;
    
    @Column(name="status")
    private String status;
    
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;
    
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "visited_by")
    private User visitedBy;
    
    @Column(name="isSent")
    private boolean isSent;

    @Transient
    private List<String> warnings = new ArrayList<String>();

    public User getVisitedBy() {
        return visitedBy;
    }

    public void setVisitedBy(User visitedBy) {
        this.visitedBy = visitedBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation != null ? observation.trim() : observation;
    }

    public String getActionPlan() {
        return actionPlan;
    }

    public void setActionPlan(String actionPlan) {
        this.actionPlan = actionPlan != null ? actionPlan.trim() : actionPlan;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean isSent) {
        this.isSent = isSent;
    }

    public List<String> getWarnings() {
        return warnings;
    }
}
