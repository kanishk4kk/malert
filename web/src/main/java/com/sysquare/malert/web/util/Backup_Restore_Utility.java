/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sysquare.malert.web.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import com.sysquare.malert.core.util.ApplicationConstants;

/**
 * 
 * @author Pradeep
 */
public class Backup_Restore_Utility implements Callable<String> {

    private static Logger log = Logger.getLogger(Backup_Restore_Utility.class);
    public static String BACKUP = "backup";
    public static String RESTORE = "restore";
    private String action;
    private String fileName;

    public Backup_Restore_Utility(String action, String fileName) {
        this.action = action;
        this.fileName = fileName;
    }

    @Override
    public String call() throws Exception {

        String result = null;

        if (this.action.equals(Backup_Restore_Utility.BACKUP)) {

            result = this.backup();

        } else if (this.action.equals(Backup_Restore_Utility.RESTORE) && this.fileName != null) {

            result = this.restore(fileName);

        }
        log.info("Backup_Restore_Utility " + action + " done.");
        return result;
    }

    private synchronized String backup() throws IOException {

        return execute(Backup_Restore_Utility.BACKUP, null);
    }

    private synchronized String restore(String fileName) throws IOException {

        return execute(Backup_Restore_Utility.RESTORE, fileName);
    }

    public String execute(String action, String fileName) throws IOException {
        String result = null;
        try {
            if (action != null) {
                if (action.equals(Backup_Restore_Utility.BACKUP)) {
                    doBackup();
                } else if (action.equals(Backup_Restore_Utility.RESTORE)) {
                    //doRestore();
                }
            }
        } catch (Exception e) {
            log.error("Error!", e);
        }
        return result;
    }

    public void doBackup() {
        Runtime rt = Runtime.getRuntime();
        Process proc = null;
        StringBuilder stringBuilder = new StringBuilder();

        StringBuilder mysqldumpCmd = new StringBuilder();

        mysqldumpCmd.append("mysqldump");
        mysqldumpCmd.append(" --user=" + ApplicationConstants.USR);
        mysqldumpCmd.append(" --password=" + ApplicationConstants.PSW);
        mysqldumpCmd.append(" --lock-all-tables");
        mysqldumpCmd.append(" --opt ");
        mysqldumpCmd.append(ApplicationConstants.DB_NAME);

        log.info(mysqldumpCmd);

        stringBuilder.append(mysqldumpCmd);
        stringBuilder.append(" > ");

        StringBuilder dbBfAbsPath = new StringBuilder();
        dbBfAbsPath.append(ApplicationConstants.IS_WINDOWS_PLATEFORM ? ApplicationConstants.WINDOWS_DB_BF_LOCATION : ApplicationConstants.DB_BF_LOCATION);	
        dbBfAbsPath.append(ApplicationConstants.DB_BD_NAME);
        dbBfAbsPath.append(ApplicationConstants.DB_BF_NAME);

        log.info(dbBfAbsPath);

        stringBuilder.append(dbBfAbsPath);

        File backDir = new File((ApplicationConstants.IS_WINDOWS_PLATEFORM ? ApplicationConstants.WINDOWS_DB_BF_LOCATION : ApplicationConstants.DB_BF_LOCATION) + ApplicationConstants.DB_BD_NAME);

        if (!backDir.exists()) {

            backDir.mkdir();
        }
        try {

            if (ApplicationConstants.IS_WINDOWS_PLATEFORM) {
                String cmpstr = stringBuilder.toString();
                // log.info(cmpstr);
                proc = rt.exec(new String[] { "cmd", "/k", cmpstr }, null, backDir);
            } else {
                proc = rt.exec(mysqldumpCmd.toString());
            }

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            // read the output from the command
            StringBuilder outputStr = new StringBuilder();
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                outputStr.append(s).append(ApplicationConstants.NEW_LINE);
            }

            StringBuilder errorStr = new StringBuilder();
            errorStr.append("Here is the standard error of the command (if any):\n");
            // read any errors from the attempted command
            while ((s = stdError.readLine()) != null) {
                errorStr.append(s);
            }
            log.error(errorStr.toString());

            File file = new File(dbBfAbsPath.toString());

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsolutePath());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(outputStr.toString());
            bw.close();
        } catch (Exception e) {
            log.error("Error!", e);
        }

    }

    // TODO not tested
    public void doRestore() {
        try {

            Runtime rt = Runtime.getRuntime();
            Process proc = null;
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("type \"" + fileName);
            stringBuilder.append("\" | mysql ");
            stringBuilder.append("-u" + ApplicationConstants.USR);
            stringBuilder.append(" -p" + ApplicationConstants.PSW);
            stringBuilder.append(" " + ApplicationConstants.DB_NAME);

            File backDir = new File(ApplicationConstants.DB_BF_LOCATION);

            if (!backDir.exists()) {
                backDir.mkdir();
            }

            String cmpstr = stringBuilder.toString();
            log.info(cmpstr);

            proc = rt.exec(new String[] { "cmd", "/k", cmpstr }, null, backDir);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            // read the output from the command
            StringBuilder outputStr = new StringBuilder();
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                outputStr.append(s);
            }

            StringBuilder errorStr = new StringBuilder();
            errorStr.append("Here is the standard error of the command (if any):\n");
            // read any errors from the attempted command 
            while ((s = stdError.readLine()) != null) { 
                errorStr.append(s);
            }
            log.error(errorStr.toString());
        } catch (Exception e) {
            log.error("Error!", e);
        }
    }

    public static void main(String[] args) throws IOException {

        Backup_Restore_Utility bru = new Backup_Restore_Utility(Backup_Restore_Utility.BACKUP, null);

        bru.execute(Backup_Restore_Utility.BACKUP, "ab.sql");

        // JFileChooser jfc = new JFileChooser(".");
        //
        // jfc.showOpenDialog(null);
        //
        // String f = jfc.getSelectedFile().getAbsolutePath();
        // // String f =
        // "C:/Documents and Settings/Pradeep/My Documents/NetBeansProjects/BulkMailer/data/15-11-2008_06-45-15_PM_bulk_mailer_db.sql";
        // //jfc.getSelectedFile().getAbsolutePath();
        //
        // bru.execute(Backup_Restore_Utility.RESTORE, f);
    }
}
/*



 */