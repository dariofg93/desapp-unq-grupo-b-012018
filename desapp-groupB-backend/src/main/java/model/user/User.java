package model.user;

import model.booking.BookingRequest;
import model.creditsAccount.CreditsAccount;
import model.email.Email;
import model.exceptions.BannedException;
import model.exceptions.NotEnoughCreditsException;
import model.exceptions.RequestNoExistException;
import model.publication.Publication;
import model.score.ScoreManager;
import model.vehicle.Vehicle;
import model.website.WebSite;

import java.util.List;

public class User {
    private List<Publication> myPublications;
    private ScoreManager scoreManager;
    private WebSite webSite;
    private CreditsAccount creditsAccount;
    private Email email;
    private List<Vehicle> myVehicles;
    private String firstName;
    private String lastName;
    private Integer cuil;
    private String addres;

    public void publish(Publication anyPublication) throws BannedException {
        if(!this.isBanned())
            this.myPublications.add(anyPublication);
        else
            throw new BannedException("You can't make publications if you're banned");
    }

    public void rentVehicle(Publication anyPublication, BookingRequest bookingRequest) throws BannedException {
        if(!this.isBanned()){
            anyPublication.addBookingRequest(bookingRequest);
            this.webSite.getNotifier().notifyRequestByMail(anyPublication.getEmail(),bookingRequest);
        }else{
            throw new BannedException("You can't rent a vehicle if you're banned");
        }
    }

    private Boolean isBanned() {
        return this.scoreManager.averageScore() < this.scoreManager.minimumScoreAccepted();
    }

    public Integer numberOfPublications() {
        return this.myPublications.size();
    }

    public void depositCredits(Double anyCredits) {
        this.creditsAccount.addCredits(anyCredits);
    }

    public void retireCredits(Double anyCredits) throws NotEnoughCreditsException {
        this.creditsAccount.sustractCredits(anyCredits);
    }

    public Double availableCredits() {
        return this.creditsAccount.getAmount();
    }

    public void aceptRequest(BookingRequest anyRequest) throws NotEnoughCreditsException, RequestNoExistException {
        Publication anyPublication = this.myPublications.stream()
                .filter(publication -> publication.containsRequest(anyRequest))
                .findFirst()
                .orElse(null);

        if(anyPublication != null) {
            this.webSite.getNotifier().notifyAceptByMail(anyRequest);

            anyPublication.getRequests().stream()
                    .filter(request -> !request.equals(anyRequest) && request.onTheSameDatesAs(anyRequest))
                    .forEach(request -> this.webSite.getNotifier().notifyRejectByMail(request));

            this.creditsAccount.addCredits(anyPublication.getPrice());
            anyRequest.getRequester().getCreditsAccount().sustractCredits(anyPublication.getPrice());
        } else {
            throw new RequestNoExistException();
        }
    }

    public void rejectRequest(BookingRequest anyRequest) {
        this.webSite.getNotifier().notifyRejectByMail(anyRequest);
    }

    /** Setters and Getters **/

    public void setWebSite(WebSite webSite) {
        this.webSite = webSite;
    }

    public void setMyPublications(List<Publication> myPublications) {
        this.myPublications = myPublications;
    }

    public void setScoreManager(ScoreManager scoreManager) {
        this.scoreManager = scoreManager;
    }

    public void setCreditsAccount(CreditsAccount creditsAccount) {
        this.creditsAccount = creditsAccount;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public CreditsAccount getCreditsAccount() {
        return this.creditsAccount;
    }

    public Email getEmail() {
        return this.email;
    }
}
