package model.user;

import junit.framework.TestCase;
import model.booking.BookingRequest;
import model.bookingState.Approved;
import model.bookingState.BookingState;
import model.builders.UserBuilder;
import model.creditsAccount.CreditsAccount;
import model.exceptions.BannedException;
import model.exceptions.NoAceptedException;
import model.exceptions.NotEnoughCreditsException;
import model.exceptions.RequestNoExistException;
import model.filter.ByCategory;
import model.filter.QuestFilter;
import model.notifier.Notifier;
import model.publication.Publication;
import model.score.OfBuyer;
import model.score.OfSeller;
import model.score.OfVehicle;
import model.score.ScoreManager;
import model.user.User;
import model.website.WebSite;
import org.joda.time.DateTime;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class UserTest extends TestCase {

    @Rule
    private ExpectedException thrown= ExpectedException.none();

    private UserBuilder userBuilder;

    private QuestFilter anyFilterMock;
    private WebSite anyWebSiteMock;
    private Notifier anyNotifierMock;
    private OfBuyer anyBuyerScoreMock;
    private OfSeller anySellerScoreMock;
    private OfVehicle anyVehicleScoreMock;
    private Publication anyPublicationMock;
    private ScoreManager anyScoreManagerMock;
    private BookingState anyBookingStateMock;
    private CreditsAccount anyCreditsAccountMock;
    private BookingRequest anyBookingRequestMock;

    @Before
	public void setUp() throws Exception {
        super.setUp();
        this.userBuilder = new UserBuilder();

        this.anyFilterMock = mock(ByCategory.class);
        this.anyWebSiteMock = mock(WebSite.class);
        this.anyNotifierMock = mock(Notifier.class);
        this.anyBuyerScoreMock = mock(OfBuyer.class);
        this.anySellerScoreMock = mock(OfSeller.class);
        this.anyVehicleScoreMock = mock(OfVehicle.class);
        this.anyBookingStateMock = mock(Approved.class);
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
        when(anyPublicationMock.getUser()).thenReturn(anyUser);

        anyUser.rentVehicle(anyPublicationMock,anyBookingRequestMock);

        verify(anyPublicationMock).addBookingRequest(anyBookingRequestMock);
        verify(anyNotifierMock).notifyRequestByMail(anyUser,anyBookingRequestMock);
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
    public void testAceptRequest() throws RequestNoExistException {
        User anyUserMock = mock(User.class);
        CreditsAccount anotherCreditsMock = mock(CreditsAccount.class);

        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .withPublications(Collections.singletonList(anyPublicationMock))
                .withCreditsAccount(anyCreditsAccountMock)
                .build();

        when(anyUserMock.getCreditsAccount()).thenReturn(anotherCreditsMock);
        when(anyPublicationMock.containsRequest(anyBookingRequestMock)).thenReturn(true);
        when(anyBookingRequestMock.getRequester()).thenReturn(anyUserMock);
        when(anyWebSiteMock.getNotifier()).thenReturn(anyNotifierMock);

        anyUser.aceptRequest(anyBookingRequestMock);

        verify(anyNotifierMock).notifyAceptByMail(anyBookingRequestMock);
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

    public void testSearchVehicle(){
        List<Publication> publications = Collections.singletonList(anyPublicationMock);
        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .build();

        when(anyWebSiteMock.publications()).thenReturn(publications);

        anyUser.searchVehicles(anyFilterMock);

        verify(anyFilterMock).filterAndOrder(publications);
    }

    public void testDeleteExpiredPublications(){
        Publication anyPublicationMock2 = mock(Publication.class);
        Publication anyPublicationMock3 = mock(Publication.class);

        List<Publication> manyPublications = Arrays.asList(
                anyPublicationMock,
                anyPublicationMock2,
                anyPublicationMock3);

        User anyUser = userBuilder.createUser()
                .withPublications(manyPublications)
                .build();

        when(anyPublicationMock.isExpired()).thenReturn(true);
        when(anyPublicationMock2.isExpired()).thenReturn(false);
        when(anyPublicationMock3.isExpired()).thenReturn(false);

        anyUser.deleteExpiredPublications();

        assertEquals(anyUser.numberOfPublications(),2,0);
        assertTrue(anyUser.getMyPublications().contains(anyPublicationMock2));
        assertTrue(anyUser.getMyPublications().contains(anyPublicationMock3));
    }

    public void testConfirmRequestBuyerWith2PartsAcepted() throws NoAceptedException, NotEnoughCreditsException {
        User anyUserMock = mock(User.class);
        CreditsAccount anotherCreditsMock = mock(CreditsAccount.class);
        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .withPublications(Collections.singletonList(anyPublicationMock))
                .withCreditsAccount(anyCreditsAccountMock)
                .build();

        this.prepareTestConfirmRequest(anyUserMock,anotherCreditsMock,true);

        anyUser.confirmVehicleRetreatBuyer(anyBookingRequestMock);

        verify(anyBookingRequestMock).setStateOfVehicleRetreatBuyer(true);
        verify(anyNotifierMock).notifyRetreatBuyerByMail(anyBookingRequestMock,anyPublicationMock);
        this.verifyTestConfirmRequest(anotherCreditsMock,anyCreditsAccountMock);
    }

    public void testConfirmRequestBuyerWithOut2PartsAcepted() throws NoAceptedException, NotEnoughCreditsException {
        User anyUserMock = mock(User.class);
        CreditsAccount anotherCreditsMock = mock(CreditsAccount.class);
        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .withPublications(Collections.singletonList(anyPublicationMock))
                .withCreditsAccount(anyCreditsAccountMock)
                .build();

        this.prepareTestConfirmRequest(anyUserMock,anotherCreditsMock,false);

        anyUser.confirmVehicleRetreatBuyer(anyBookingRequestMock);

        verify(anyBookingRequestMock).setStateOfVehicleRetreatBuyer(true);
        verify(anyNotifierMock).notifyRetreatBuyerByMail(anyBookingRequestMock,anyPublicationMock);
        verify(anyBookingRequestMock).setStateOfVehicleRetreatBuyer(false);
        verify(anyBookingRequestMock).setStateOfVehicleRetreatSeller(false);
    }

    public void testConfirmRequestSellerWith2PartsAcepted() throws NoAceptedException, NotEnoughCreditsException {
        User anyUserMock = mock(User.class);
        CreditsAccount anotherCreditsMock = mock(CreditsAccount.class);
        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .withPublications(Collections.singletonList(anyPublicationMock))
                .withCreditsAccount(anyCreditsAccountMock)
                .build();

        this.prepareTestConfirmRequest(anyUserMock,anotherCreditsMock,true);

        anyUser.confirmVehicleRetreatSeller(anyBookingRequestMock);

        verify(anyBookingRequestMock).setStateOfVehicleRetreatSeller(true);
        verify(anyNotifierMock).notifyRetreatSellerByMail(anyBookingRequestMock);
        this.verifyTestConfirmRequest(anyCreditsAccountMock,anotherCreditsMock);
    }

    public void testConfirmRequestSellerWithOut2PartsAcepted() throws NoAceptedException, NotEnoughCreditsException {
        User anyUserMock = mock(User.class);
        CreditsAccount anotherCreditsMock = mock(CreditsAccount.class);
        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .withPublications(Collections.singletonList(anyPublicationMock))
                .withCreditsAccount(anyCreditsAccountMock)
                .build();

        this.prepareTestConfirmRequest(anyUserMock,anotherCreditsMock,false);

        anyUser.confirmVehicleRetreatSeller(anyBookingRequestMock);

        verify(anyBookingRequestMock).setStateOfVehicleRetreatSeller(true);
        verify(anyNotifierMock).notifyRetreatSellerByMail(anyBookingRequestMock);
        this.verifyTestConfirmRequest(anyCreditsAccountMock,anotherCreditsMock);
    }

    public void testConfirmVehicleReturnBuyerWith2PartsAcepted() throws NoAceptedException {
        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .withScoreManager(anyScoreManagerMock)
                .withPublications(Collections.singletonList(anyPublicationMock))
                .build();

        when(anyPublicationMock.getUser()).thenReturn(anyUser);
        when(anyBookingRequestMock.getDateTimeOfReservation()).thenReturn(DateTime.now().minusHours(5));
        this.prepareTestConfirmReturn(true);

        anyUser.confirmVehicleReturnBuyer(anyBookingRequestMock, anyVehicleScoreMock, anySellerScoreMock);

        verify(anyPublicationMock).setCurrentAprovedRequest(null);
        verify(anyBookingRequestMock).setHoursOfTheReservation(5);

        verify(anyBookingRequestMock).setStateOfVehicleReturnBuyer(true);
        verify(anyNotifierMock).notifyReturnBuyerByMail(anyBookingRequestMock,anyPublicationMock);
        verify(anyScoreManagerMock).addScore(anySellerScoreMock);
        verify(anyScoreManagerMock).addScore(anyVehicleScoreMock);
    }

    public void testConfirmVehicleReturnSellerWithOut2PartsAcepted() throws NoAceptedException {
        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .withScoreManager(anyScoreManagerMock)
                .withPublications(Collections.singletonList(anyPublicationMock))
                .build();

        when(anyBookingRequestMock.getRequester()).thenReturn(anyUser);
        this.prepareTestConfirmReturn(false);

        anyUser.confirmVehicleReturnSeller(anyBookingRequestMock,anyBuyerScoreMock);

        verify(anyBookingRequestMock).setStateOfVehicleReturnSeller(true);
        verify(anyNotifierMock).notifyReturnSellerByMail(anyBookingRequestMock);
        verify(anyScoreManagerMock).addScore(anyBuyerScoreMock);
    }

/** ---------------------------------------- Privates(no tests) ------------------------------------------------- */
    private void prepareTestConfirmRequest(User anyUserMock, CreditsAccount anotherCreditsMock, Boolean confirmParts)
            throws NoAceptedException {
        when(anyPublicationMock.containsRequest(anyBookingRequestMock)).thenReturn(true);
        when(anyPublicationMock.getPricePerHour()).thenReturn(20.0);
        when(anyBookingRequestMock.getTotalHours()).thenReturn(5);
        when(anyBookingRequestMock.getRequester()).thenReturn(anyUserMock);
        when(anyUserMock.getCreditsAccount()).thenReturn(anotherCreditsMock);
        when(anyBookingRequestMock.getState()).thenReturn(anyBookingStateMock);
        when(anyBookingStateMock.getConfirmRetreatBuyer()).thenReturn(confirmParts);
        when(anyBookingStateMock.getConfirmRetreatSeller()).thenReturn(confirmParts);
        when(anyWebSiteMock.getNotifier()).thenReturn(anyNotifierMock);
    }

    private void verifyTestConfirmRequest(CreditsAccount beneficiaryAccount, CreditsAccount damagedAccount)
            throws NotEnoughCreditsException {
        verify(anyPublicationMock).setCurrentAprovedRequest(anyBookingRequestMock);
        verify(beneficiaryAccount).addCredits(100.0);
        verify(damagedAccount).sustractCredits(100.0);
    }

    private void prepareTestConfirmReturn(Boolean returnParts) throws NoAceptedException {
        when(anyPublicationMock.containsRequest(anyBookingRequestMock)).thenReturn(true);
        when(anyWebSiteMock.getNotifier()).thenReturn(anyNotifierMock);
        when(anyBookingRequestMock.getState()).thenReturn(anyBookingStateMock);
        when(anyBookingStateMock.getConfirmReturnBuyer()).thenReturn(returnParts);
        when(anyBookingStateMock.getConfirmReturnSeller()).thenReturn(returnParts);
    }
}
