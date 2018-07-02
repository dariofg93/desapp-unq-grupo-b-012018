package model.user;

import model.booking.BookingRequest;
import model.bookingstate.Approved;
import model.bookingstate.BookingState;
import model.builders.UserBuilder;
import model.creditsaccount.CreditsAccount;
import model.exceptions.BannedException;
import model.exceptions.NoAceptedException;
import model.exceptions.RequestNoExistException;
import model.filter.FilterByCategory;
import model.filter.QuestFilter;
import model.notifier.Notifier;
import model.publication.Publication;
import model.score.Score;
import model.score.ScoreManager;
import model.website.WebSite;
import org.joda.time.DateTime;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class UserTest {

    private UserBuilder userBuilder;

    private QuestFilter anyFilterMock;
    private WebSite anyWebSiteMock;
    private Notifier anyNotifierMock;
    private Score anyBuyerScoreMock;
    private Score anySellerScoreMock;
    private Score anyVehicleScoreMock;
    private Publication anyPublicationMock;
    private ScoreManager anyScoreManagerMock;
    private BookingState anyBookingStateMock;
    private CreditsAccount anyCreditsAccountMock;
    private BookingRequest anyBookingRequestMock;

    @Before
	public void setUp() {
        this.userBuilder = new UserBuilder();
        

        this.anyFilterMock = mock(FilterByCategory.class);
        this.anyWebSiteMock = mock(WebSite.class);
        this.anyNotifierMock = mock(Notifier.class);
        this.anyBuyerScoreMock = mock(Score.class);
        this.anySellerScoreMock = mock(Score.class);
        this.anyVehicleScoreMock = mock(Score.class);
        this.anyBookingStateMock = mock(Approved.class);
        this.anyPublicationMock = mock(Publication.class);
        this.anyScoreManagerMock = mock(ScoreManager.class);
        this.anyCreditsAccountMock = mock(CreditsAccount.class);
        this.anyBookingRequestMock = mock(BookingRequest.class);
	}
    
    @Test
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

    @Test(expected = BannedException.class)
    public void testPublishWithFailure() throws BannedException{
        User anyUser = userBuilder.createUser()
                .withPublications(new ArrayList<>())
                .withScoreManager(anyScoreManagerMock)
                .build();

        when(anyScoreManagerMock.minimumScoreAccepted()).thenReturn(4.0);
        when(anyScoreManagerMock.averageScore()).thenReturn(3.5);

        anyUser.publish(anyPublicationMock);
    }

    @Test
    public void testDepositCredits(){
        User anyUser = userBuilder.createUser()
                .withCreditsAccount(anyCreditsAccountMock)
                .build();

        when(anyCreditsAccountMock.getAmount()).thenReturn(25.0);

        anyUser.depositCredits(25.0);

        assertEquals(anyUser.availableCredits(), new Double(25.0));
    }
    
    @Test
    public void testRetireCreditsWithSufficientSalary() {
        User anyUser = userBuilder.createUser()
                .withCreditsAccount(anyCreditsAccountMock)
                .build();

        anyUser.retireCredits(20.0);

        verify(anyCreditsAccountMock).sustractCredits(20.0);
    }
    
    @Test
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
 
    }

  //  @Test(expected = BannedException.class)
    public void testFailRentVehicle() throws BannedException {
        User anyUser = userBuilder.createUser()
                .withScoreManager(anyScoreManagerMock)
                .build();

        when(anyScoreManagerMock.minimumScoreAccepted()).thenReturn(4.0);
        when(anyScoreManagerMock.averageScore()).thenReturn(0.5);

        anyUser.rentVehicle(anyPublicationMock,anyBookingRequestMock);
    }

    @Test
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

        verify(anyNotifierMock).notifyAceptByMail(anyUser,anyBookingRequestMock);
    }
    
    @Test(expected = RequestNoExistException.class)
    public void testAceptRequestFailed() throws  RequestNoExistException {

        User anyUser = userBuilder.createUser()
                .withPublications(Collections.singletonList(anyPublicationMock))
                .build();

        when(anyPublicationMock.containsRequest(anyBookingRequestMock)).thenReturn(false);

        anyUser.aceptRequest(anyBookingRequestMock);
    }

    @Test
    public void testRejectRequest(){
        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .build();

        when(anyWebSiteMock.getNotifier()).thenReturn(anyNotifierMock);

        anyUser.rejectRequest(anyBookingRequestMock);

        verify(anyNotifierMock).notifyRejectByMail(anyUser,anyBookingRequestMock);
    }

    @Test
    public void testSearchVehicle(){
        List<Publication> publications = Collections.singletonList(anyPublicationMock);
        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .build();

        when(anyWebSiteMock.publications()).thenReturn(publications);

        anyUser.searchVehicles(anyFilterMock);

        verify(anyFilterMock).filterAndOrder(publications);
    }

    @Test
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

        when(anyPublicationMock.expired()).thenReturn(true);
        when(anyPublicationMock2.expired()).thenReturn(false);
        when(anyPublicationMock3.expired()).thenReturn(false);

        anyUser.deleteExpiredPublications();

        assertEquals(anyUser.numberOfPublications(),2,0);
        assertTrue(anyUser.getMyPublications().contains(anyPublicationMock2));
        assertTrue(anyUser.getMyPublications().contains(anyPublicationMock3));
    }
    
    @Test
    public void testConfirmRequestBuyerWith2PartsAcepted() throws NoAceptedException {
        User anyUserMock = mock(User.class);
        CreditsAccount anotherCreditsMock = mock(CreditsAccount.class);
        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .withPublications(Collections.singletonList(anyPublicationMock))
                .withCreditsAccount(anyCreditsAccountMock)
                .build();

        this.prepareTestConfirmRequest(anyUser,anyUserMock,anotherCreditsMock,true);

        anyUser.confirmVehicleRetreatBuyer(anyBookingRequestMock);

        verify(anyBookingRequestMock).setStateOfVehicleRetreatBuyer(true);
        verify(anyNotifierMock).notifyRetreatBuyerByMail(anyUser,anyBookingRequestMock);
        this.verifyTestConfirmRequest(anotherCreditsMock,anyCreditsAccountMock);
    }

    @Test
    public void testConfirmRequestBuyerWithOut2PartsAcepted() throws NoAceptedException {
        User anyUserMock = mock(User.class);
        CreditsAccount anotherCreditsMock = mock(CreditsAccount.class);
        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .withPublications(Collections.singletonList(anyPublicationMock))
                .withCreditsAccount(anyCreditsAccountMock)
                .build();

        this.prepareTestConfirmRequest(anyUser,anyUserMock,anotherCreditsMock,false);

        anyUser.confirmVehicleRetreatBuyer(anyBookingRequestMock);

        verify(anyBookingRequestMock).setStateOfVehicleRetreatBuyer(true);
        verify(anyNotifierMock).notifyRetreatBuyerByMail(anyUser,anyBookingRequestMock);
        verify(anyBookingRequestMock).setStateOfVehicleRetreatBuyer(false);
        verify(anyBookingRequestMock).setStateOfVehicleRetreatSeller(false);
    }

    @Test
    public void testConfirmRequestSellerWith2PartsAcepted() throws NoAceptedException {
        User anyUserMock = mock(User.class);
        CreditsAccount anotherCreditsMock = mock(CreditsAccount.class);
        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .withPublications(Collections.singletonList(anyPublicationMock))
                .withCreditsAccount(anyCreditsAccountMock)
                .build();

        this.prepareTestConfirmRequest(anyUser,anyUserMock,anotherCreditsMock,true);

        anyUser.confirmVehicleRetreatSeller(anyBookingRequestMock);

        verify(anyBookingRequestMock).setStateOfVehicleRetreatSeller(true);
        verify(anyNotifierMock).notifyRetreatSellerByMail(anyBookingRequestMock);
        this.verifyTestConfirmRequest(anyCreditsAccountMock,anotherCreditsMock);
    }

    @Test
    public void testConfirmRequestSellerWithOut2PartsAcepted() throws NoAceptedException {
        User anyUserMock = mock(User.class);
        CreditsAccount anotherCreditsMock = mock(CreditsAccount.class);
        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .withPublications(Collections.singletonList(anyPublicationMock))
                .withCreditsAccount(anyCreditsAccountMock)
                .build();

        this.prepareTestConfirmRequest(anyUser,anyUserMock,anotherCreditsMock,false);

        anyUser.confirmVehicleRetreatSeller(anyBookingRequestMock);

        verify(anyBookingRequestMock).setStateOfVehicleRetreatSeller(true);
        verify(anyNotifierMock).notifyRetreatSellerByMail(anyBookingRequestMock);
        this.verifyTestConfirmRequest(anyCreditsAccountMock,anotherCreditsMock);
    }

    @Test
    public void testConfirmVehicleReturnBuyerWith2PartsAcepted() throws NoAceptedException {
        User anyUser = userBuilder.createUser()
                .withWebSite(anyWebSiteMock)
                .withScoreManager(anyScoreManagerMock)
                .withPublications(Collections.singletonList(anyPublicationMock))
                .build();

        when(anyPublicationMock.getUser()).thenReturn(anyUser);
        when(anyBookingRequestMock.getReservationDateTime()).thenReturn(DateTime.now().minusHours(5));
        this.prepareTestConfirmReturn(true);

        anyUser.confirmVehicleReturnBuyer(anyBookingRequestMock, anyVehicleScoreMock, anySellerScoreMock);

        verify(anyBookingRequestMock).setHoursOfTheReservation(5);

        verify(anyBookingRequestMock).setStateOfVehicleReturnBuyer(true);
        verify(anyNotifierMock).notifyReturnBuyerByMail(anyUser,anyBookingRequestMock);
        verify(anyScoreManagerMock).addScore(anySellerScoreMock);
        verify(anyScoreManagerMock).addScore(anyVehicleScoreMock);
    }

    @Test
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
    private void prepareTestConfirmRequest(User anyPublisher, User anyUserMock, CreditsAccount anotherCreditsMock, Boolean confirmParts)
            throws NoAceptedException {
        when(anyPublicationMock.containsRequest(anyBookingRequestMock)).thenReturn(true);
        when(anyPublicationMock.getPricePerHour()).thenReturn(20.0);
        when(anyPublicationMock.getUser()).thenReturn(anyPublisher);
        when(anyBookingRequestMock.getTotalHours()).thenReturn(5);
        when(anyBookingRequestMock.getRequester()).thenReturn(anyUserMock);
        when(anyUserMock.getCreditsAccount()).thenReturn(anotherCreditsMock);
        when(anyBookingRequestMock.getState()).thenReturn(anyBookingStateMock);
        when(anyBookingStateMock.getConfirmRetreatBuyer()).thenReturn(confirmParts);
        when(anyBookingStateMock.getConfirmRetreatSeller()).thenReturn(confirmParts);
        when(anyWebSiteMock.getNotifier()).thenReturn(anyNotifierMock);
    }

    private void verifyTestConfirmRequest(CreditsAccount beneficiaryAccount, CreditsAccount damagedAccount) {
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
