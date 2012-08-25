package com.sysquare.malert.web.scheduler;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sysquare.malert.core.service.VisitObservationService;
import com.sysquare.malert.core.service.WorkshopService;
import com.sysquare.malert.core.util.CalendarUtil;
import com.sysquare.malert.model.domain.VisitObservation;
import com.sysquare.malert.model.domain.Workshop;

@Component
public class MailScheduerJob {
    
    Logger log = Logger.getLogger(getClass());
	
    @Autowired
    private VisitObservationService observationService;
    @Autowired
    private WorkshopService workshopService;
    
    private static final String username = "malerts.wms@gmail.com";
    private static final String fromName = "WMS Alerts";
    private static final String from = "malerts.wms@gmail.com";
    private static final String cc = "rsajith.kumar@maruti.co.in";
    private static final String password = "maruti970";
    private static final String smtpAddress = "smtp.gmail.com";
    private static final String smtpPort = "587";

	private static final String STATUS_PENDING = "pending";
	
	private static final String mailDebug = "false";
	
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
	
    /*public static void main(String[] args) {
        MailScheduerJob job = new MailScheduerJob();
    }*/
    
    public void execute() {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        log.info(CalendarUtil.getStandardFormatDate(today));
        List<Workshop> allWorkshops = workshopService.listWorkshop(Boolean.FALSE);
        try {
            boolean isMondayToday = cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
            for (Workshop workshop : allWorkshops) {
                List<VisitObservation> pendingVisitObservations = observationService.findByWorkshopAndStatus(workshop.getId(), STATUS_PENDING, false);
                sendMail(workshop, pendingVisitObservations, today, false);
                if(isMondayToday) {
                    pendingVisitObservations = observationService.findByWorkshopAndStatus(workshop.getId(), STATUS_PENDING, true);
                    sendMail(workshop, pendingVisitObservations, today, true);
                }
            }
		}catch (Exception ex) {
			ex.printStackTrace(); 
		}
    }
    
    private void sendMail(Workshop workshop, List<VisitObservation> pendingVisitObservations, Date today, boolean isMondayReminder) throws AddressException, MessagingException, UnsupportedEncodingException {
        if(pendingVisitObservations != null && !pendingVisitObservations.isEmpty()) {
            Session session = Session.getInstance(props, authenticator);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from, fromName));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(workshop.getTsmEmail()));
            message.setRecipients(Message.RecipientType.CC,InternetAddress.parse(cc));
            message.setSubject((isMondayReminder ? "Re:" : "" ) + "Pending action points for : "+workshop.getName() +","+workshop.getLocation()+" ("+workshop.getCode()+") as of "+ CalendarUtil.getStandardFormatDate(today));
            StringBuilder mailBody = new StringBuilder();
            
            mailBody.append("Dear Sir,<br><br>");
            mailBody.append("The following are the observations made during our recent visits to your "+workshop.getName() +","+workshop.getLocation()+" (<b>"+workshop.getCode()+"</b>), which are pending as on date: " + CalendarUtil.getStandardFormatDate(today));
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
            for (VisitObservation visitObservation : pendingVisitObservations) {
                mailBody.append("<tr>");
                mailBody.append("<td style='border-collapse: collapse; border: 1px solid #aaa; margin: 2px; padding: 2px; vertical-align: top;'>"+visitObservation.getObservation() +"</td>");
                mailBody.append("<td style='border-collapse: collapse; border: 1px solid #aaa; margin: 2px; padding: 2px; vertical-align: top;'>"+visitObservation.getActionPlan() +"</td>");
                mailBody.append("<td style='border-collapse: collapse; border: 1px solid #aaa; margin: 2px; padding: 2px; vertical-align: top; white-space:nowrap !important;'>"+CalendarUtil.getStandardFormatDate(visitObservation.getTargetDate()) +"</td>");
                mailBody.append("</tr>");
                visitObservation.setSent(true);
            }
            mailBody.append("</tbody>");    
            mailBody.append("</table>");
            
            mailBody.append("<br><br>");
            mailBody.append("May kindly reply on action taken.");

            message.setText(mailBody.toString(), "UTF-8", "html");
            message.setDisposition( Part.INLINE );
            Transport.send(message);
            observationService.updateVisitObservation(pendingVisitObservations);
            log.info("\n\n\n\nMail sent to : " + workshop.getTsmEmail() + "\n\n");
        }
    }
}