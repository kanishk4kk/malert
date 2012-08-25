package com.sysquare.malert.core.service;

import com.sysquare.malert.model.domain.AppOption;

public interface OptionService {
    public void addOption(AppOption option);
    public AppOption findByName(String name);
    public void updateOption(AppOption option);
}
