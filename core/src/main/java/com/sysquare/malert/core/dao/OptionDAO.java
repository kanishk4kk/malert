package com.sysquare.malert.core.dao;

import com.sysquare.malert.model.domain.AppOption;

public interface OptionDAO {
	
	public void addOption(AppOption option);
	public AppOption findByName(String name);
    public void updateOption(AppOption option);
}
