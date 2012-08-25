package com.sysquare.malert.core.dao;

import java.util.Date;
import java.util.List;

import com.sysquare.malert.core.util.OrderBy;
import com.sysquare.malert.model.domain.User;
import com.sysquare.malert.model.domain.VisitObservation;

public interface VisitObservationDAO {
	
	public void addVisitObservation(VisitObservation visitObservation);
	public List<VisitObservation> listVisitObservation();
	public void removeVisitObservation(Integer id);
    public List<VisitObservation> findByWorkshop(Integer workshopId, List<String> groupBy, List<OrderBy> orderBy);
    public List<VisitObservation> findByWorkshopVisitDateAndStatus(Integer workshopId, Date visitDate, String status);
    public void saveVisitObservations(List<VisitObservation> visitObservations);
    public VisitObservation findById(Integer visitObservationId);
    public void update(VisitObservation visitObservation);
    public List<VisitObservation> findByStatus(String status);
    public List<VisitObservation> findByWorkshopAndStatus(Integer workshopId, String status, boolean isSent);
    public void updateVisitObservation(List<VisitObservation> visitObservations);
    public List<VisitObservation> findAllPendingVOAfterTarget(User user);
}
