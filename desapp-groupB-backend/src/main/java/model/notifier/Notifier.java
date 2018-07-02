package model.notifier;

import model.booking.BookingRequest;
import model.builders.MailCarpndBuilder;
import model.email.MailBody;
import model.email.MailCarpnd;
import model.email.MailSender;
import model.user.User;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;

public class Notifier implements Serializable{

    private MailCarpnd mailBuilded;
    private MailSender mailSender;
    
    public Notifier() {
    	mailSender = new MailSender();
    	
    }

    public void notifyRequestByMail(User notifier, BookingRequest anyRequest) {
        User requester = anyRequest.getRequester();
        String fullName = requester.getFirstName() + " " + requester.getLastName();
        String subject = "Reservation request of " + fullName;

        mailSender.send(notifier.getEmail().getAccountName(),
            subject,
            "You have a new booking request for " + anyRequest.getTotalHours() + " hours");
        this.prepareMailCarpndWithSubject(anyRequest,subject);

        notifier.getEmail().addMailCarpnd(this.mailBuilded);
    }
    

    public void notifyAceptByMail(User acceptor, BookingRequest anyRequest) {
        anyRequest.acept();
        String fullName = acceptor.getFirstName() + " " + acceptor.getLastName();
        String subject = fullName + " have accepted your reservation!";

        mailSender.send(acceptor.getEmail().getAccountName(),
            subject,
            "You reservation for " + anyRequest.getTotalHours() + " hours has been accepted");
        this.prepareMailCarpndWithSubject(anyRequest,subject);

        anyRequest.getRequester().getEmail().addMailCarpnd(this.mailBuilded);
    }

    public void notifyRejectByMail(User rejector, BookingRequest anyRequest) {
        anyRequest.reject();
        String fullName = rejector.getFirstName() + " " + rejector.getLastName();
        String subject = fullName + " have rejected your reservation!";

        mailSender.send(rejector.getEmail().getAccountName(),
            subject,
            "You reservation for " + anyRequest.getTotalHours() + " hours has been rejected");
        this.prepareMailCarpndWithSubject(anyRequest, subject);

        anyRequest.getRequester().getEmail().addMailCarpnd(this.mailBuilded);
    }

    public void notifyRetreatBuyerByMail(User seller, BookingRequest anyRequest) {
    	
    	System.out.print("________________________________X");
        String endSubject = "! the retreat for " + anyRequest.getTotalHours() + " hours was confirmed!";
        System.out.print("________________________________X2");
        String subject = "Congratulations " + seller.getFirstName() + endSubject;
        System.out.print("________________________________X3");
        mailSender.send(seller.getEmail().getAccountName(),
                subject,
                "The buyer has confirmed the retreat of the vehicle, you can see the details on the site!");
        System.out.print("________________________________X4");
        this.prepareMailCarpndWithSubject(anyRequest,subject);

        System.out.print("________________________________X5");
        seller.getEmail().addMailCarpnd(this.mailBuilded);
    }

    public void notifyRetreatSellerByMail(BookingRequest anyRequest) {
        User buyer = anyRequest.getRequester();
        String endSubject = "! the retreat for " + anyRequest.getTotalHours() + " hours was confirmed!";
        String subject = "Congratulations " + buyer.getFirstName() + endSubject;

        mailSender.send(buyer.getEmail().getAccountName(),
            subject,
            "The seller has confirmed the retreat of the vehicle, you can see the details on the site!");
        this.prepareMailCarpndWithSubject(anyRequest,subject);

        buyer.getEmail().addMailCarpnd(this.mailBuilded);
    }

    public void notifyReturnBuyerByMail(User seller, BookingRequest anyRequest) {
        String subject = "Reservation completed for " + anyRequest.getTotalHours() + " hours confirmed by the buyer!";

        mailSender.send(seller.getEmail().getAccountName(),
            subject,
            "The buyer has confirmed the return of the vehicle, you can see the details on the site!");
        this.prepareMailCarpndWithSubject(anyRequest,subject);

        seller.getEmail().addMailCarpnd(this.mailBuilded);
    }

    public void notifyReturnSellerByMail(BookingRequest anyRequest) {
        User buyer = anyRequest.getRequester();
        String subject = "Reservation completed for " + anyRequest.getTotalHours() + " hours confirmed by the seller!";

        mailSender.send(buyer.getEmail().getAccountName(),
            subject,
            "The seller has confirmed the return of the vehicle, you can see the details on the site!");
        this.prepareMailCarpndWithSubject(anyRequest,subject);

        buyer.getEmail().addMailCarpnd(this.mailBuilded);
    }

    public void sendMovementsOfTheMonthToUsers(List<User> manyUsers) {
        String subject;

        for(User user: manyUsers){
            subject = "Movements of the month of " + DateTime.now().monthOfYear().getAsText();

            mailSender.send(user.getEmail().getAccountName(),subject,user.getMovementsOfMonth().allHistory());
            this.prepareMailCarpndWithSubject(user.getMovementsOfMonth(),subject);

            user.getEmail().addMailCarpnd(this.mailBuilded);
            user.getMovementsOfMonth().cleanHistory();
        }
    }

    private void prepareMailCarpndWithSubject(MailBody mailBody, String subject) {
        this.mailBuilded = new MailCarpndBuilder().createMail()
                .withSubject(subject)
                .withBody(mailBody)
                .build();
    }

    /** Setters and Getters **/

    public MailCarpnd getMailBuilded() {
        return this.mailBuilded;
    }
}
