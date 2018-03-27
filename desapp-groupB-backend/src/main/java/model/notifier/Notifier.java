package model.notifier;

import model.booking.BookingRequest;
import model.builders.MailCarpndBuilder;
import model.email.Email;
import model.email.MailCarpnd;
import model.user.User;

public class Notifier {

    public void notifyRequestByMail(Email anyEmail, BookingRequest anyRequest) {
        User requester = anyRequest.getRequester();
        String fullName = requester.getFirstName() + " " + requester.getLastName();
        System.out.print("Tienes una nueva solicitud de reserva de: " + fullName);

        MailCarpnd anyMail = new MailCarpndBuilder().createMail()
                .withSubject("Solicitud de reserva de " + fullName)
                .withRequest(anyRequest)
                .build();

        anyEmail.addRequestBooking(anyMail);
    }

    public void notifyAceptByMail(BookingRequest anyRequest) {
        anyRequest.setAcepted();

        MailCarpnd anyMail = new MailCarpndBuilder().createMail()
                .withSubject("Han aceptado tu reserva!")
                .withRequest(anyRequest)
                .build();

        anyRequest.getRequester().getEmail().addRequestBooking(anyMail);
    }

    public void notifyRejectByMail(BookingRequest anyRequest) {
        anyRequest.setRejected();

        MailCarpnd anyMail = new MailCarpndBuilder().createMail()
                .withSubject("Tu reserva ha sido rechazada")
                .withRequest(anyRequest)
                .build();

        anyRequest.getRequester().getEmail().addRequestBooking(anyMail);
    }
}
