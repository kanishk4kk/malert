package com.sysquare.malert.core.service;

import java.util.Date;
import java.util.List;

import com.sysquare.malert.core.util.OrderBy;
import com.sysquare.malert.model.domain.User;
import com.sysquare.malert.model.domain.VisitObservation;

public interface VisitObservationService {
    public void addVisitObservation(VisitObservation visitObservation);
    public List<VisitObservation> listVisitObservation();
    public void removeVisitObservation(Integer id);
    public List<VisitObservation> findByWorkshop(Integer workshopId, List<String> groupBy, List<OrderBy> orderBy);
    List<VisitObservation> findByWorkshopVisitDateAndStatus(Integer workshopId, Date visitDate, String status);
    void saveVisitObservation(List<VisitObservation> visitObservations);
    void update(VisitObservation visitObservation);
    void markAsImplemented(Integer visitObservationId);
    VisitObservation findById(Integer visitObservationId);
    public List<VisitObservation> findByStatus(String status);
    public List<VisitObservation> findByWorkshopAndStatus(Integer workshopId, String status, boolean isSent);
    public void updateVisitObservation(List<VisitObservation> visitObservations);
    public List<VisitObservation> findAllPendingVOAfterTarget(User user);
}
