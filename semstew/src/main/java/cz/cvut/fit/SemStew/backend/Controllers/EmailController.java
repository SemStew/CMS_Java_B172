package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.RestaurantRecord;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.RestaurantService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
public class EmailController {
    /**
     * Restaurant record for email parameters
     */
    private RestaurantRecord restaurantRecord;
    /**
     * Email
     */
    private final HtmlEmail email = new HtmlEmail();

    /**
     * EmailController constructor
     *
     * Use {@link #EmailController()} to create EmailController class and for connecting email
     */
    public EmailController(){
        RestaurantService restaurantService = new RestaurantService();
        this.restaurantRecord = restaurantService.GetInstance();
        ConnectEmail();
    }

    /**
     * Connecting email
     *
     * Use {@link #ConnectEmail()} to connect email
     */
    private void ConnectEmail(){
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setSSLOnConnect(true);
        email.setAuthentication(restaurantRecord.getEmail(), restaurantRecord.getEmailPassword());
    }

    /**
     * Send acceptance message
     *
     * Use {@link #AcceptMessage(String emailTo, int id, String keyword)} to send acceptance message to given email with given keyword
     *
     * @param emailTo recipient email address
     * @param id item id
     * @param keyword used keyword
     * @return Accepted if all went fine, else exception message
     */
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

    /**
     * Send decline message
     *
     * Use {@link #DeclineMessage(String emailTo, int id, String keyword)} to send declination message to given email with given keyword
     *
     * @param emailTo recipient email address
     * @param id item id
     * @param keyword used keyword
     * @return Declined if all went fine, else exception message
     */
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

    /**
     * Send delete message
     *
     * Use {@link #DeclineMessage(String emailTo, int id, String keyword)} to send deletion message to given email with given keyword
     *
     * @param emailTo recipient email address
     * @param id item id
     * @param keyword used keyword
     * @return Deleted if all went fine, else exception message
     */
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
