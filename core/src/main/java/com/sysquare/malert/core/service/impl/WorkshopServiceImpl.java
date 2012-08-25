package com.sysquare.malert.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sysquare.malert.core.dao.WorkshopDAO;
import com.sysquare.malert.core.service.WorkshopService;
import com.sysquare.malert.model.domain.Workshop;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class WorkshopServiceImpl implements WorkshopService {

    @Autowired
    private WorkshopDAO workshopDAO;
    
    @Override
    public Workshop findById(Integer id) {
        return workshopDAO.findById(id);
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addWorkshop(Workshop workshop) {
        workshopDAO.addWorkshop(workshop);
    }

    public List<Workshop> listWorkshop(boolean showInactive) {

        return workshopDAO.listWorkshop(showInactive);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void removeWorkshop(Integer id) {
        workshopDAO.removeWorkshop(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateWorkshop(Workshop workshop) {
        workshopDAO.updateWorkshop(workshop);        
    }

    @Override
    public Workshop findByCode(String code, Integer excludeId) {
        return workshopDAO.findByCode(code, excludeId);
    }
}
