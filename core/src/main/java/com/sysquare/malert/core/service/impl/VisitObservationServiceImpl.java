package com.sysquare.malert.core.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sysquare.malert.core.dao.VisitObservationDAO;
import com.sysquare.malert.core.service.VisitObservationService;
import com.sysquare.malert.core.util.OrderBy;
import com.sysquare.malert.model.domain.User;
import com.sysquare.malert.model.domain.VisitObservation;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class VisitObservationServiceImpl implements VisitObservationService {

    @Autowired
    private VisitObservationDAO visitObservationDAO;
    
    @Override
    public VisitObservation findById(Integer visitObservationId) {
        return visitObservationDAO.findById(visitObservationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void markAsImplemented(Integer visitObservationId) {
        VisitObservation visitObservation = findById(visitObservationId);
        visitObservation.setStatus("implemented");
        update(visitObservation);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void update(VisitObservation visitObservation) {
        visitObservationDAO.update(visitObservation);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addVisitObservation(VisitObservation visitObservation) {
        visitObservationDAO.addVisitObservation(visitObservation);
    }

    @Override
    public List<VisitObservation> listVisitObservation() {
        return visitObservationDAO.listVisitObservation();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeVisitObservation(Integer id) {
        visitObservationDAO.removeVisitObservation(id);
    }

    @Override
    public List<VisitObservation> findByWorkshop(Integer workshopId, List<String> groupBy, List<OrderBy> orderBy) {
        return visitObservationDAO.findByWorkshop(workshopId, groupBy, orderBy);
    }
    
    @Override
    public List<VisitObservation> findByWorkshopVisitDateAndStatus(Integer workshopId, Date visitDate, String status) {
        return visitObservationDAO.findByWorkshopVisitDateAndStatus(workshopId, visitDate, status);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void saveVisitObservation(List<VisitObservation> visitObservations) {
        visitObservationDAO.saveVisitObservations(visitObservations);
    }

	@Override
	public List<VisitObservation> findByStatus(String status) {
		 return visitObservationDAO.findByStatus(status);
	}
	
	@Override
	public List<VisitObservation> findByWorkshopAndStatus(Integer workshopId, String status, boolean isSent) {
		 return visitObservationDAO.findByWorkshopAndStatus(workshopId, status, isSent);
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateVisitObservation(List<VisitObservation> visitObservations) {
        visitObservationDAO.updateVisitObservation(visitObservations);
    }

    @Override
    public List<VisitObservation> findAllPendingVOAfterTarget(User user) {
        return visitObservationDAO.findAllPendingVOAfterTarget(user);
    }
}
