package model.user;

import model.booking.BookingRequest;
import model.creditsaccount.CreditsAccount;
import model.email.Email;
import model.exceptions.BannedException;
import model.exceptions.NoAceptedException;
import model.exceptions.RequestNoExistException;
import model.filter.QuestFilter;
import model.maps.GeographicZoneDescription;
import model.movements.MovementsOfMonth;
import model.publication.Publication;
import model.score.*;
import model.utils.Entity;
import model.vehicle.Vehicle;
import model.website.WebSite;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.joda.time.DateTime;
import org.joda.time.Hours;

import java.util.List;
import java.util.stream.Collectors;

public class User extends Entity {

	private List<Publication> myPublications;
	private ScoreManager scoreManager;
	@JsonIgnore
	private WebSite webSite;
	private CreditsAccount creditsAccount;
	private Email email;
	private List<Vehicle> myVehicles;
	private String firstName;
	private String lastName;
	private String cuil;
	private GeographicZoneDescription address;
	private MovementsOfMonth movementsOfMonth;

	public User() {
		this.movementsOfMonth = new MovementsOfMonth();
		this.webSite = new WebSite();
	}

	public void publish(Publication anyPublication) throws BannedException {
		if (!this.isBanned()) {
			anyPublication.setUser(this);
			this.myPublications.add(anyPublication);
			this.movementsOfMonth.addToHistory("You have successfully published your vehicle from "
					+ anyPublication.getFromDate() + " to " + anyPublication.getToDate() + "!");
		} else
			throw new BannedException("You can't make publications if you're banned");
	}

	public void rentVehicle(Publication anyPublication, BookingRequest bookingRequest) throws BannedException {
		anyPublication.addBookingRequest(bookingRequest);
		this.movementsOfMonth.addToHistory("You have sent a successful request to rent a vehicle for "
				+ bookingRequest.getTotalHours() + " hours!");
	}

	public Integer numberOfPublications() {
		return this.myPublications.size();
	}

	public void depositCredits(Double anyCredits) {
		this.creditsAccount.addCredits(anyCredits);
	}

	public void retireCredits(Double anyCredits) {
		this.creditsAccount.sustractCredits(anyCredits);
	}

	public Double availableCredits() {
		return this.creditsAccount.getAmount();
	}

	public void aceptRequest(BookingRequest anyRequest) throws RequestNoExistException {
		Publication anyPublication = this.searchPublicationOfRequest(anyRequest);

		if (anyPublication != null) {
			this.webSite.getNotifier().notifyAceptByMail(this, anyRequest);

			anyPublication.allBookingRequest().stream()
					.filter(request -> !request.equals(anyRequest)
							&& anyPublication.remainingTime() < anyRequest.getTotalHours())
					.forEach(request -> this.rejectRequest(request));
			this.movementsOfMonth
					.addToHistory("You have accepted a reservation by " + anyRequest.getTotalHours() + " hours.");
		} else {
			throw new RequestNoExistException();
		}
	}

	public void rejectRequest(BookingRequest anyRequest) {
		anyRequest.reject();
		this.webSite.getNotifier().notifyRejectByMail(this, anyRequest);
		this.movementsOfMonth
				.addToHistory("You have rejected a reservation by " + anyRequest.getTotalHours() + " hours.");
	}

	public List<Publication> searchVehicles(QuestFilter anyFilter) {
		return anyFilter.filterAndOrder(this.webSite.publications());
	}

	public void deleteExpiredPublications() {
		this.myPublications = this.myPublications.stream().filter(publication -> !publication.expired())
				.collect(Collectors.toList());

		this.movementsOfMonth.addToHistory("You have deleted posts that have expired!");
	}

	/**
	 * ############################################# Vehicle removal
	 * ####################################################################
	 */
	/*
	 * Quien retira el vehículo indicará en la aplicación que ya tiene el bien en su
	 * poder. Quien colocó en alquiler el vehículo debe confirmar esta notificación.
	 * El tiempo de alquiler corre a partir de que ambos usuarios cumplieron con su
	 * acción. En caso que el propietario indique que el bien fue retirado y no
	 * exista respuesta de la contraparte dentro de los 30 minutos, la misma se da
	 * por confirmada. En caso que quien alquila el vehículo indique que ya lo tiene
	 * en su poder y no exista respuesta de su contraparte dentro de los 30 minutos,
	 * la misma se da por rechazada.
	 */
	public void confirmVehicleRetreatBuyer(BookingRequest anyRequest, Publication anyPublication)
			throws NoAceptedException {

		anyRequest.setStateOfVehicleRetreatBuyer(true);
		anyRequest.setReservationDateTime(DateTime.now());

		this.webSite.getNotifier().notifyRetreatBuyerByMail(this, anyRequest);

		this.movementsOfMonth.addToHistory("You have confirmed that you have retreat a vehicle for a "
				+ anyRequest.getTotalHours() + " hours reservation.");
		this.executeTransfer(anyRequest, anyRequest.getRequester().getCreditsAccount(), this.creditsAccount,
				anyPublication);
	}

	public void confirmVehicleRetreatSeller(BookingRequest anyRequest) throws NoAceptedException {

		Publication anyPublication = this.searchPublicationOfRequest(anyRequest);
		anyRequest.setStateOfVehicleRetreatSeller(true);
		anyRequest.setReservationDateTime(DateTime.now());

		this.webSite.getNotifier().notifyRetreatSellerByMail(anyRequest);

		this.movementsOfMonth.addToHistory("You have confirmed the retreat of your vehicle for a "
				+ anyRequest.getTotalHours() + " hours reservation.");

		this.executeTransfer(anyRequest, this.creditsAccount, anyRequest.getRequester().getCreditsAccount(),
				anyPublication);
	}

	/**
	 * ----------------------------------------------------------------------------------------------------------------------------------
	 */
	private void cancelTransferByTimeLimit(BookingRequest anyRequest) throws NoAceptedException {
		// if(Pasaron 30 minutos && !anyRequest.getState().getConfirmRetreatSeller()){
		anyRequest.setStateOfVehicleRetreatSeller(false);
		anyRequest.setStateOfVehicleRetreatBuyer(false);
		// }
	}

	private void executeTransferByTimeLimit(BookingRequest anyRequest, CreditsAccount beneficiaryAccount,
			CreditsAccount damagedAccount, Publication anyPublication) {
		// if(Pasaron 30 minutos && !anyRequest.getState().getConfirmRetreatBuyer()){
		this.executeTransfer(anyRequest, beneficiaryAccount, damagedAccount, anyPublication);
		this.movementsOfMonth.addToHistory("The transaction has been canceled!");
		// }
	}

	/**
	 * ----------------------------------------------------------------------------------------------------------------------------------
	 */
	private void executeTransfer(BookingRequest anyRequest, CreditsAccount beneficiaryAccount,
			CreditsAccount damagedAccount, Publication anyPublication) {

		anyRequest.setReservationDateTime(DateTime.now());

		Double totalCharge = anyPublication.getPricePerHour() * anyRequest.getTotalHours();

		this.movementsOfMonth
				.addToHistory("The transaction has been executed. Your account has added " + totalCharge + " credits.");

		beneficiaryAccount.addCredits(totalCharge);
		damagedAccount.sustractCredits(totalCharge);
	}

	/**
	 * ############################################## Vehicle return
	 * ####################################################################
	 */
	/*
	 * Quien devuelve el vehículo indica que ya no posee el mismo y puntúa al
	 * propietario. Quien recibe el vehículo notifica su recepción y puntúa a su
	 * contraparte. El tiempo de alquiler se fija a partir de que ambos usuarios
	 * cumplieron con su acción. Ambos usuarios pueden ingresar comentarios al
	 * momento de puntuar a su contraparte.
	 */

	public void confirmVehicleReturnBuyer(BookingRequest anyRequest, Score scoreOfVehicle, Score scoreOfSeller, Publication anyPublication)
			throws NoAceptedException {

		anyRequest.setStateOfVehicleReturnBuyer(true);

		this.webSite.getNotifier().notifyReturnBuyerByMail(anyPublication.getUser(), anyRequest);

		anyPublication.getUser().addScore(scoreOfSeller);

		this.movementsOfMonth.addToHistory("You have confirmed the return of a vehicle that you had ordered.");

		this.checkFixRentalTime(anyRequest, anyPublication);
	}

	public void confirmVehicleReturnSeller(BookingRequest anyRequest, Score scoreOfBuyer) throws NoAceptedException {

		Publication anyPublication = this.searchPublicationOfRequest(anyRequest);
		anyRequest.setStateOfVehicleReturnSeller(true);
		this.webSite.getNotifier().notifyReturnSellerByMail(anyRequest);

		anyRequest.getRequester().addScore(scoreOfBuyer);

		this.movementsOfMonth.addToHistory("You have confirmed the return of a vehicle that you had rented.");

		this.checkFixRentalTime(anyRequest, anyPublication);
	}

	/**
	 * ----------------------------------------------------------------------------------------------------------------------------------
	 */

	private void checkFixRentalTime(BookingRequest anyRequest, Publication anyPublication) throws NoAceptedException {
		if (anyRequest.getConfirmReturnBuyer() && anyRequest.getConfirmReturnSeller()) {
			Integer hoursOfTheReservation = Hours.hoursBetween(anyRequest.getReservationDateTime(), DateTime.now())
					.getHours();
			anyRequest.setHoursOfTheReservation(hoursOfTheReservation);
		}
	}

	/**
	 * ##################################################################################################################################
	 */

	private Publication searchPublicationOfRequest(BookingRequest anyRequest) {
		return this.myPublications.stream().filter(publication -> publication.containsRequest(anyRequest)).findFirst()
				.orElse(null);
	}

	private Boolean isBanned() {
		return this.scoreManager.averageScore() < this.scoreManager.minimumScoreAccepted();
	}

	private void addScore(Score anyScore) {
		this.scoreManager.addScore(anyScore);
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

	public List<Publication> getMyPublications() {
		return this.myPublications;
	}

	public MovementsOfMonth getMovementsOfMonth() {
		return this.movementsOfMonth;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMovementsOfMonth(MovementsOfMonth movementsOfMonth) {
		this.movementsOfMonth = movementsOfMonth;
	}

	public ScoreManager getScoreManager() {
		return scoreManager;
	}

	public GeographicZoneDescription getAddress() {
		return address;
	}

	public void setAddress(GeographicZoneDescription address) {
		this.address = address;
	}

	public List<Vehicle> getMyVehicles() {
		return myVehicles;
	}

	public void setMyVehicles(List<Vehicle> myVehicles) {
		this.myVehicles = myVehicles;
	}

	@Override
	public boolean equals(Object object) {
		User user = (User) object;

		return (object.getClass().equals(User.class)
				&& user.getEmail().getAccountName() == this.getEmail().getAccountName());
	}

}
