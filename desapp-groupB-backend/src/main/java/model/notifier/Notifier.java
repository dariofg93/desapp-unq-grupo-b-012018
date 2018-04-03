package model.notifier;

import model.booking.BookingRequest;
import model.builders.MailCarpndBuilder;
import model.email.MailBody;
import model.email.MailCarpnd;
import model.publication.Publication;
import model.user.User;
import org.joda.time.DateTime;

import java.util.List;

public class Notifier {

    private MailCarpnd mailBuilded;

    public void notifyRequestByMail(User anyUser, BookingRequest anyRequest) {
        User requester = anyRequest.getRequester();
        String fullName = requester.getFirstName() + " " + requester.getLastName();

        this.prepareMailCarpndWithSubject(
                anyRequest,
                "Reservation request of " + fullName);

        anyUser.getEmail().addMailCarpnd(this.mailBuilded);
        this.mailBuilded = null;
    }

    public void notifyAceptByMail(BookingRequest anyRequest) {
        anyRequest.setAcepted();

        this.prepareMailCarpndWithSubject(
                anyRequest,
                "They have accepted your reservation!");

        anyRequest.getRequester().getEmail().addMailCarpnd(this.mailBuilded);
        this.mailBuilded = null;
    }

    public void notifyRejectByMail(BookingRequest anyRequest) {
        anyRequest.setRejected();

        this.prepareMailCarpndWithSubject(
                anyRequest,
                "Your reservation has been rejected");

        anyRequest.getRequester().getEmail().addMailCarpnd(this.mailBuilded);
        this.mailBuilded = null;
    }

    public void notifyRetreatBuyerByMail(BookingRequest anyRequest, Publication anyPublication) {
        this.prepareMailCarpndWithSubject(
                anyRequest,
                "Confirmed the transaction of your publication by " + anyRequest.getTotalHours() + " hours!");

        anyPublication.getUser().getEmail().addMailCarpnd(this.mailBuilded);
        this.mailBuilded = null;
    }

    public void notifyRetreatSellerByMail(BookingRequest anyRequest) {

        this.prepareMailCarpndWithSubject(
                anyRequest,
                "Your " + anyRequest.getTotalHours() + " hours reservation was confirmed by the seller!");

        anyRequest.getRequester().getEmail().addMailCarpnd(this.mailBuilded);
        this.mailBuilded = null;
    }

    public void notifyReturnBuyerByMail(BookingRequest anyRequest, Publication anyPublication) {
        this.prepareMailCarpndWithSubject(
                anyRequest,
                "Reservation completed for " + anyRequest.getTotalHours() + " hours confirmed by the buyer!");

        anyPublication.getUser().getEmail().addMailCarpnd(this.mailBuilded);
        this.mailBuilded = null;
    }

    public void notifyReturnSellerByMail(BookingRequest anyRequest) {
        this.prepareMailCarpndWithSubject(
                anyRequest,
                "Reservation completed for " + anyRequest.getTotalHours() + " hours confirmed by the seller!");

        anyRequest.getRequester().getEmail().addMailCarpnd(this.mailBuilded);
        this.mailBuilded = null;
    }

    public void sendMovementsOfTheMonthToUsers(List<User> manyUsers) {
        for(User user: manyUsers){
            this.prepareMailCarpndWithSubject(
                    user.getMovementsOfMonth(),
                    "Movements of the month of " + DateTime.now().monthOfYear().getAsText());

            user.getEmail().addMailCarpnd(this.mailBuilded);
            user.getMovementsOfMonth().cleanHistory();
            this.mailBuilded = null;
        }
    }

    private void prepareMailCarpndWithSubject(MailBody mailBody, String subject) {
        this.mailBuilded = new MailCarpndBuilder().createMail()
                .withSubject(subject)
                .withBody(mailBody)
                .build();
    }
}
