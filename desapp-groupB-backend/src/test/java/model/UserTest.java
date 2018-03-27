package model;

import junit.framework.TestCase;
import model.booking.BookingRequest;
import model.builders.UserBuilder;
import model.creditsAccount.CreditsAccount;
import model.email.Email;
import model.exceptions.BannedException;
import model.exceptions.NotEnoughCreditsException;
import model.exceptions.RequestNoExistException;
import model.notifier.Notifier;
import model.publication.Publication;
import model.score.ScoreManager;
import model.user.User;
import model.website.WebSite;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class UserTest extends TestCase {

    @Rule
    private ExpectedException thrown= ExpectedException.none();

    private UserBuilder userBuilder;

    private Email anyEmailMock;
    private WebSite anyWebSiteMock;
    private Notifier anyNotifierMock;
    private Publication anyPublicationMock;
    private ScoreManager anyScoreManagerMock;
    private CreditsAccount anyCreditsAccountMock;
    private BookingRequest anyBookingRequestMock;

    @Before
	public void setUp() {
        this.userBuilder = new UserBuilder();

        this.anyEmailMock = mock(Email.class);
        this.anyWebSiteMock = mock(WebSite.class);
        this.anyNotifierMock = mock(Notifier.class);
        this.anyPublicationMock = mock(Publication.class);
        this.anyScoreManagerMock = mock(ScoreManager.class);
        this.anyCreditsAccountMock = mock(CreditsAccount.class);
        this.anyBookingRequestMock = mock(BookingRequest.class);
	}

    public void testPublishSuccessfully() throws BannedException {
	    User anyUser = userBuilder.createUser()
                .withPublications(new ArrayList<>())
                .withScoreManager(anyScoreManagerMock)
                .build();

        when(anyScoreManagerMock.minimumScoreAccepted()).thenReturn(4.0);
        when(anyScoreManagerMock.averageScore()).thenReturn(4.5);

        anyUser.publish(anyPublicationMock);

        assertEquals(anyUser.numberOfPublications(),1,0);
    }
/*
    public void testPublishWithFailure() throws BannedException{
        User anyUser = userBuilder.createUser()
                .withPublications(new ArrayList<>())
                .withScoreManager(anyScoreManagerMock)
                .build();

        when(anyScoreManagerMock.minimumScoreAccepted()).thenReturn(4.0);
        when(anyScoreManagerMock.averageScore()).thenReturn(3.5);

        thrown.expect(BannedException.class);
        anyUser.publish(anyPublicationMock);
    }
*/

    public void testDepositCredits(){
        User anyUser = userBuilder.createUser()
                .withCreditsAccount(anyCreditsAccountMock)
                .build();

        when(anyCreditsAccountMock.getAmount()).thenReturn(25.0);

        anyUser.depositCredits(25.0);

        assertEquals(anyUser.availableCredits(),25.0);
    }

    public void testRetireCreditsWithSufficientSalary() throws NotEnoughCreditsException {
        User anyUser = userBuilder.createUser()
                .withCreditsAccount(anyCreditsAccountMock)
                .build();

        anyUser.retireCredits(20.0);

        verify(anyCreditsAccountMock).sustractCredits(20.0);
    }

    public void testRentVehicle() throws BannedException {
        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .withScoreManager(anyScoreManagerMock)
                .build();

        when(anyScoreManagerMock.minimumScoreAccepted()).thenReturn(4.0);
        when(anyScoreManagerMock.averageScore()).thenReturn(4.5);
        when(anyWebSiteMock.getNotifier()).thenReturn(anyNotifierMock);
        when(anyPublicationMock.getEmail()).thenReturn(anyEmailMock);

        anyUser.rentVehicle(anyPublicationMock,anyBookingRequestMock);

        verify(anyPublicationMock).addBookingRequest(anyBookingRequestMock);
        verify(anyNotifierMock).notifyRequestByMail(anyEmailMock,anyBookingRequestMock);
    }
/*
    public void testRentVehicle() throws BannedException {
        User anyUser = userBuilder.createUser()
                .withScoreManager(anyScoreManagerMock)
                .build();

        when(anyScoreManagerMock.minimumScoreAccepted()).thenReturn(4.0);
        when(anyScoreManagerMock.averageScore()).thenReturn(0.5);

        thrown.expect(BannedException.class);
        anyUser.rentVehicle(anyPublicationMock,anyBookingRequestMock);
    }
*/
    public void testAceptRequest() throws NotEnoughCreditsException, RequestNoExistException {
        User anyUserMock = mock(User.class);
        CreditsAccount anotherCreditsMock = mock(CreditsAccount.class);

        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .withPublications(Collections.singletonList(anyPublicationMock))
                .withCreditsAccount(anyCreditsAccountMock)
                .build();

        when(anyUserMock.getCreditsAccount()).thenReturn(anotherCreditsMock);
        when(anyPublicationMock.getPrice()).thenReturn(250.0);
        when(anyPublicationMock.containsRequest(anyBookingRequestMock)).thenReturn(true);
        when(anyBookingRequestMock.getRequester()).thenReturn(anyUserMock);
        when(anyWebSiteMock.getNotifier()).thenReturn(anyNotifierMock);
        when(anyBookingRequestMock.onTheSameDatesAs(anyBookingRequestMock)).thenReturn(false);

        anyUser.aceptRequest(anyBookingRequestMock);

        verify(anyNotifierMock).notifyAceptByMail(anyBookingRequestMock);
        verify(anyCreditsAccountMock).addCredits(250.0);
        verify(anotherCreditsMock).sustractCredits(250.0);
    }
/*
    public void testAceptRequestFailed() throws NotEnoughCreditsException, RequestNoExistException {

        User anyUser = userBuilder.createUser()
                .withPublications(Collections.singletonList(anyPublicationMock))
                .build();

        when(anyPublicationMock.containsRequest(anyBookingRequestMock)).thenReturn(false);

        thrown.expect(RequestNoExistException.class);
        anyUser.aceptRequest(anyBookingRequestMock);
    }
*/
    public void testRejectRequest(){
        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .build();

        when(anyWebSiteMock.getNotifier()).thenReturn(anyNotifierMock);

        anyUser.rejectRequest(anyBookingRequestMock);

        verify(anyNotifierMock).notifyRejectByMail(anyBookingRequestMock);
    }
}
