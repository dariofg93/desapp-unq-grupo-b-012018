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

    public void notifyRequestByMail(User anyUser, BookingRequest anyRequest) {
        User requester = anyRequest.getRequester();
        String fullName = requester.getFirstName() + " " + requester.getLastName();

        MailCarpnd anyMail = this.prepareMailCarpndWithSubject(
                anyRequest,
                "Reservation request of " + fullName);

        anyUser.getEmail().addMailCarpnd(anyMail);
    }

    public void notifyAceptByMail(BookingRequest anyRequest) {
        anyRequest.setAcepted();

        MailCarpnd anyMail = this.prepareMailCarpndWithSubject(
                anyRequest,
                "They have accepted your reservation!");

        anyRequest.getRequester().getEmail().addMailCarpnd(anyMail);
    }

    public void notifyRejectByMail(BookingRequest anyRequest) {
        anyRequest.setRejected();

        MailCarpnd anyMail = this.prepareMailCarpndWithSubject(
                anyRequest,
                "Your reservation has been rejected");

        anyRequest.getRequester().getEmail().addMailCarpnd(anyMail);
    }

    public void notifyRetreatBuyerByMail(BookingRequest anyRequest, Publication anyPublication) {
        MailCarpnd anyMail = this.prepareMailCarpndWithSubject(
                anyRequest,
                "Confirmed the transaction of your publication by " + anyRequest.getTotalHours() + " hours!");

        anyPublication.getUser().getEmail().addMailCarpnd(anyMail);
    }

    public void notifyRetreatSellerByMail(BookingRequest anyRequest) {

        MailCarpnd anyMail = this.prepareMailCarpndWithSubject(
                anyRequest,
                "Your " + anyRequest.getTotalHours() + " hours reservation was confirmed by the seller!");

        anyRequest.getRequester().getEmail().addMailCarpnd(anyMail);
    }

    public void notifyReturnBuyerByMail(BookingRequest anyRequest, Publication anyPublication) {
        MailCarpnd anyMail = this.prepareMailCarpndWithSubject(
                anyRequest,
                "Reservation completed for " + anyRequest.getTotalHours() + " hours confirmed by the buyer!");

        anyPublication.getUser().getEmail().addMailCarpnd(anyMail);
    }

    public void notifyReturnSellerByMail(BookingRequest anyRequest) {
        MailCarpnd anyMail = this.prepareMailCarpndWithSubject(
                anyRequest,
                "Reservation completed for " + anyRequest.getTotalHours() + " hours confirmed by the seller!");

        anyRequest.getRequester().getEmail().addMailCarpnd(anyMail);
    }

    public void sendMovementsOfTheMonthToUsers(List<User> manyUsers) {
        for(User user: manyUsers){
            MailCarpnd anyMail = this.prepareMailCarpndWithSubject(
                    user.getMovementsOfMonth(),
                    "Movements of the month of " + DateTime.now().monthOfYear().getAsText());

            user.getEmail().addMailCarpnd(anyMail);
            user.getMovementsOfMonth().cleanHistory();
        }
    }

    private MailCarpnd prepareMailCarpndWithSubject(MailBody mailBody, String subject) {
        return new MailCarpndBuilder().createMail()
                .withSubject(subject)
                .withRequest(mailBody)
                .build();
    }
}
