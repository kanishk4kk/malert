package com.sysquare.malert.web.scheduler;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sysquare.malert.web.util.Backup_Restore_Utility;

@Component
public class BackupScheduerJob {
    
    Logger log = Logger.getLogger(getClass());
	
    /*public static void main(String[] args) {
        MailScheduerJob job = new MailScheduerJob();
    }*/
    
    public void execute() {
        try {
        	log.info("Start backup.");
        	Backup_Restore_Utility bru = new Backup_Restore_Utility(Backup_Restore_Utility.BACKUP, null);
            bru.call();//execute(Backup_Restore_Utility.BACKUP, "ab.sql");
            log.info("End backup.");
		}catch (Exception ex) {
			ex.printStackTrace(); 
		}
    }
}   
