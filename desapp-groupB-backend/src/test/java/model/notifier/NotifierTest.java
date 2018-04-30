package model.notifier;

import junit.framework.TestCase;
import model.booking.BookingRequest;
import model.email.Email;
import model.movements.MovementsOfMonth;
import model.user.User;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class NotifierTest extends TestCase {

    private Notifier notifier;

    private User userMock;
    private User requesterMock;
    private Email userEmailMock;
    private Email requesterEmailMock;
    private BookingRequest anyRequestMock;

    @Before
	public void setUp() throws Exception {
        super.setUp();
        this.notifier = new Notifier();

        this.userMock = mock(User.class);
        this.requesterMock = mock(User.class);
        this.userEmailMock = mock(Email.class);
        this.requesterEmailMock = mock(Email.class);
        this.anyRequestMock = mock(BookingRequest.class);
	}

    public void testNotifyRequestByMail(){
        when(anyRequestMock.getTotalHours()).thenReturn(32);
        when(anyRequestMock.getRequester()).thenReturn(requesterMock);
        when(requesterMock.getFirstName()).thenReturn("Papa");
        when(requesterMock.getLastName()).thenReturn("Noel");

        when(userEmailMock.getAccount()).thenReturn("dariofg93@gmail.com");
        when(userMock.getEmail()).thenReturn(userEmailMock);

        notifier.notifyRequestByMail(userMock,anyRequestMock);

        verify(userEmailMock).addMailCarpnd(notifier.getMailBuilded());
    }

    public void testNotifyAceptByMail(){
        when(requesterMock.getEmail()).thenReturn(requesterEmailMock);
        when(anyRequestMock.getRequester()).thenReturn(requesterMock);
        when(anyRequestMock.getTotalHours()).thenReturn(50);

        when(userMock.getFirstName()).thenReturn("Hada");
        when(userMock.getLastName()).thenReturn("Madrina");
        when(userEmailMock.getAccount()).thenReturn("dariofg93@gmail.com");
        when(userMock.getEmail()).thenReturn(userEmailMock);

        notifier.notifyAceptByMail(userMock,anyRequestMock);

        verify(requesterEmailMock).addMailCarpnd(notifier.getMailBuilded());
        verify(anyRequestMock).setAcepted();
    }

    public void testNotifyRejectByMail(){
        when(requesterMock.getEmail()).thenReturn(requesterEmailMock);
        when(anyRequestMock.getRequester()).thenReturn(requesterMock);
        when(anyRequestMock.getTotalHours()).thenReturn(25);

        when(userMock.getFirstName()).thenReturn("Pepe");
        when(userMock.getLastName()).thenReturn("Grillo");
        when(userEmailMock.getAccount()).thenReturn("dariofg93@gmail.com");
        when(userMock.getEmail()).thenReturn(userEmailMock);

        notifier.notifyRejectByMail(userMock,anyRequestMock);

        verify(requesterEmailMock).addMailCarpnd(notifier.getMailBuilded());
        verify(anyRequestMock).setRejected();
    }

    public void testNotifyRetreatBuyerByMail(){
        when(anyRequestMock.getTotalHours()).thenReturn(10);

        when(userMock.getFirstName()).thenReturn("Dario");
        when(userEmailMock.getAccount()).thenReturn("dariofg93@gmail.com");
        when(userMock.getEmail()).thenReturn(userEmailMock);

        notifier.notifyRetreatBuyerByMail(userMock,anyRequestMock);

        verify(userEmailMock).addMailCarpnd(notifier.getMailBuilded());
    }

    public void testNotifyRetreatSellerByMail(){
        when(anyRequestMock.getRequester()).thenReturn(requesterMock);
        when(anyRequestMock.getTotalHours()).thenReturn(16);

        when(requesterMock.getFirstName()).thenReturn("Dario");
        when(requesterEmailMock.getAccount()).thenReturn("dariofg93@gmail.com");
        when(requesterMock.getEmail()).thenReturn(requesterEmailMock);

        notifier.notifyRetreatSellerByMail(anyRequestMock);

        verify(requesterEmailMock).addMailCarpnd(notifier.getMailBuilded());
    }

    public void testNotifyReturnBuyerByMail(){
        when(anyRequestMock.getTotalHours()).thenReturn(6);
        when(userEmailMock.getAccount()).thenReturn("dariofg93@gmail.com");
        when(userMock.getEmail()).thenReturn(userEmailMock);

        notifier.notifyReturnBuyerByMail(userMock,anyRequestMock);

        verify(userEmailMock).addMailCarpnd(notifier.getMailBuilded());
    }

    public void testNotifyReturnSellerByMail(){
        when(anyRequestMock.getRequester()).thenReturn(requesterMock);
        when(anyRequestMock.getTotalHours()).thenReturn(1);

        when(requesterEmailMock.getAccount()).thenReturn("dariofg93@gmail.com");
        when(requesterMock.getEmail()).thenReturn(requesterEmailMock);

        notifier.notifyReturnSellerByMail(anyRequestMock);

        verify(requesterEmailMock).addMailCarpnd(notifier.getMailBuilded());
    }

    public void testSendMovementsOfTheMonthToUsers(){
        MovementsOfMonth movements = mock(MovementsOfMonth.class);

        List users = new ArrayList();
            users.add(userMock);
            users.add(requesterMock);

        when(userEmailMock.getAccount()).thenReturn("dariofg93@gmail.com");
        when(userMock.getEmail()).thenReturn(userEmailMock);
        when(movements.getHistory()).thenReturn("Pocos movimientos\n\t en el mes");
        when(userMock.getMovementsOfMonth()).thenReturn(movements);

        when(requesterEmailMock.getAccount()).thenReturn("fabri1108@gmail.com");
        when(requesterMock.getEmail()).thenReturn(requesterEmailMock);
        when(movements.getHistory()).thenReturn("Se envian\n\t los mails\n\t de carpnd!");
        when(requesterMock.getMovementsOfMonth()).thenReturn(movements);

        notifier.sendMovementsOfTheMonthToUsers(users);

        verify(requesterEmailMock).addMailCarpnd(notifier.getMailBuilded());
        verify(movements, times(2)).cleanHistory();
    }
}
