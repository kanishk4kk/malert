package com.sysquare.malert.core.dao;

import java.util.List;

import com.sysquare.malert.model.domain.Workshop;

public interface WorkshopDAO {
	
	public void addWorkshop(Workshop workshop);
	public List<Workshop> listWorkshop(boolean showInactive);
	public void removeWorkshop(Integer id);
    public Workshop findById(Integer id);
    public void updateWorkshop(Workshop workshop);
    public Workshop findByCode(String code, Integer excludeId);
}
