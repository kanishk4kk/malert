package com.sysquare.malert.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface ApplicationConstants {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss_a");
    SimpleDateFormat dateFormatDD_MM_YYYY = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat dateFormatYYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat dateFormatMedium = DateFormat.getDateInstance(DateFormat.MEDIUM);
    String DB_NAME = "malert";
    String DB_BF_NAME = dateFormat.format(new Date()) + "_"+DB_NAME+".sql";
    String USR = "root";
    String PSW = "root";//"prsyskan";
    String DB_BF_LOCATION = "/home/pradeep/Desktop/";
    String WINDOWS_DB_BF_LOCATION = "d:\\";
    String DB_BD_NAME = "malert_data_backup/";
    boolean IS_LINUX_PLATEFORM = false;
    boolean IS_WINDOWS_PLATEFORM = !IS_LINUX_PLATEFORM;
    String NEW_LINE = System.getProperty("line.separator");
    boolean MAC_VALIDATION = false;
}