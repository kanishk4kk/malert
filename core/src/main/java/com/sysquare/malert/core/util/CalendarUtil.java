package com.sysquare.malert.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
    public static final String STANDARD_DATE_FORMAT = "dd-MMM-yyyy";
    
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(STANDARD_DATE_FORMAT);
    
    public static String getStandardFormatDate(Calendar cal) {
        if(cal == null) {
            return "";
        }
        return getStandardFormatDate(cal.getTime());
    }
    
    public static String getStandardFormatDate(Date date) {
        if(date != null) {
            return DATE_FORMAT.format(date);
        }
        return "";
    }
}
