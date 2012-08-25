package com.sysquare.malert.core.util;

public class OrderBy {
    private String property;
    private boolean isAsc;
    public OrderBy(String property, boolean isAsc) {
        super();
        this.property = property;
        this.isAsc = isAsc;
    }
    public String getProperty() {
        return property;
    }
    public boolean isAsc() {
        return isAsc;
    }
}
