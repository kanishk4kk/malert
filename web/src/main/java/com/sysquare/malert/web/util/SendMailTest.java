package com.sysquare.malert.web.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTest {
    
    private static final String username = "malerts.wms@gmail.com";
    private static final String from = "malerts.wms@gmail.com";
    private static final String cc = "deepak.sharma2@maruti.co.in";
    private static final String password = "maruti970";
    private static final String smtpAddress = "smtp.gmail.com";
    private static final String smtpPort = "587";

//    private static final String username = "javamailapi@gmail.com";
//    private static final String from = "javamailapi@gmail.com";
//	private static final String cc = "pr12gold@gmail.com";
//	private static final String password = "password142536";
//	private static final String smtpAddress = "smtp.gmail.com";
//	private static final String smtpPort = "587";
	
	private static final String mailDebug = "true";
	
	private static Properties props = new Properties();
	static {
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", smtpAddress);
	    props.put("mail.smtp.port", smtpPort);
	    props.put("mail.debug", mailDebug);
	}
	
	private static Authenticator authenticator = new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    };
	
    public static void main(String[] args) throws AddressException, MessagingException {

        sendTestMail(new Date());
    }
    
    public static void sendTestMail(Date today) throws AddressException, MessagingException {
        String to = "deepak.sharma2@maruti.co.in";
        Session session = Session.getInstance(props, authenticator);
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
        message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc));
        message.setSubject("Pending Jobs for : 'Workshop Name' as of "+ today);
        StringBuilder mailBody = new StringBuilder();
        
        mailBody.append("Dear Sir,<br><br>");
        mailBody.append("The following are the observations made during our recent visits, which are pending as on date: " + today);
        mailBody.append("<br><br>");
        mailBody.append("<table class='data'>");
        mailBody.append("<thead>");       
        mailBody.append("<tr>");
        mailBody.append("<th style='font-weight: bold;background-color: #5C82FF;color: white;'>Observation</th>");
        mailBody.append("<th style='font-weight: bold;background-color: #5C82FF;color: white;'>Action Planed</th>");
        mailBody.append("<th style='font-weight: bold;background-color: #5C82FF;color: white;'>Target Date</th>");
        mailBody.append("</tr>");
        mailBody.append("</thead>");
        mailBody.append("<tbody>");
        mailBody.append("<tr>");
        mailBody.append("<td style='border-collapse: collapse; border: 1px solid #aaa; margin: 2px; padding: 2px; vertical-align: top;'>visitObservation.getObservation()</td>");
        mailBody.append("<td style='border-collapse: collapse; border: 1px solid #aaa; margin: 2px; padding: 2px; vertical-align: top;'>visitObservation.getActionPlan()</td>");
        mailBody.append("<td style='border-collapse: collapse; border: 1px solid #aaa; margin: 2px; padding: 2px; vertical-align: top; width: 80px;'>CalendarUtil.getStandardFormatDate(visitObservation.getTargetDate())</td>");
        mailBody.append("</tr>");
        mailBody.append("</tbody>");    
        mailBody.append("</table>");
        
        mailBody.append("<br><br>");
        mailBody.append("May kindly reply on action taken.");

        message.setText(mailBody.toString(), "UTF-8", "html");
        message.setDisposition( Part.INLINE );
        Transport.send(message);
        System.out.println("\n\n\n\nMail sent to : " +to+ " \n\n");

    }
}