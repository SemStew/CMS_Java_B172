package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.RestaurantRecord;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.RestaurantService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class EmailController {
    private RestaurantRecord restaurantRecord;
    private final HtmlEmail email = new HtmlEmail();

    public EmailController(){
        RestaurantService restaurantService = new RestaurantService();
        this.restaurantRecord = restaurantService.GetInstance();
        ConnectEmail();
    }

    private void ConnectEmail(){
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setSSLOnConnect(true);
        email.setAuthentication(restaurantRecord.getEmail(), restaurantRecord.getEmailPassword());
    }

    public String AcceptMessage(String emailTo, int id, String keyword){
        try {
            email.setFrom(restaurantRecord.getEmail());
            email.addTo(emailTo);
            email.setSubject("Acceptance");
            email.setHtmlMsg(keyword +" number " + Integer.toString(id) + " has been accepted");

            email.send();
        } catch (EmailException exception){
            return exception.getMessage();
        }

        return "Accepted";
    }

    public String DeclineMessage(String emailTo, int id, String keyword){
        try {
            email.setFrom(restaurantRecord.getEmail());
            email.addTo(emailTo);
            email.setSubject("Declined");
            email.setHtmlMsg(keyword + " number " + Integer.toString(id) + " has been declined");

            email.send();
        } catch (EmailException exception){
            return exception.getMessage();
        }

        return "Declined";
    }

    public String DeleteMessage(String emailTo, int id,String keyword){
        try {
            email.setFrom(restaurantRecord.getEmail());
            email.addTo(emailTo);
            email.setSubject("Deleted");
            email.setHtmlMsg(keyword + " number " + Integer.toString(id) + " has been deleted");

            email.send();
        } catch (EmailException exception){
            return exception.getMessage();
        }

        return "Deleted";
    }
}
