package com.sysquare.malert.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sysquare.malert.core.dao.OptionDAO;
import com.sysquare.malert.core.service.OptionService;
import com.sysquare.malert.model.domain.AppOption;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class OptionServiceImpl implements OptionService {

	@Autowired
	private OptionDAO optionDAO;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addOption(AppOption option) {
	    optionDAO.addOption(option);
	}

	@Override
	public AppOption findByName(String name) {
	    return optionDAO.findByName(name);
	}
	
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateOption(AppOption option) {
        optionDAO.updateOption(option);
    }
}
