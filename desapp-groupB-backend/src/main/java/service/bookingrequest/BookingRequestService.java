package service.bookingrequest;

import org.springframework.transaction.annotation.Transactional;

import dto.RequestsCorcernPayload;
import model.booking.BookingRequest;
import model.exceptions.NoAceptedException;
import model.exceptions.RequestNoExistException;
import model.publication.Publication;
import model.score.Score;
import model.user.User;
import persistence.generic.GenericService;
import service.publication.PublicationService;
import service.user.UserService;

public class BookingRequestService extends GenericService<BookingRequest> {

	private static final long serialVersionUID = -2932116622242535843L;

	public BookingRequest searchById(Long id) {
		return this.getRepository().findById(id);
	}

	public void updateById(Long id, BookingRequest updatedPublication) {
		updatedPublication.setId(id);
		this.getRepository().update(updatedPublication);
	}
	
	@Transactional
	public void aceptRequest(Long userId, Long requestId, UserService userService, PublicationService publicationService)  {
		BookingRequest request = this.searchById(requestId);
		User user = userService.searchById(userId);
		user.setMyPublications(
				publicationService.selectByFunction((publication) -> publication.getUser().getId() == userId));
		request.acept();
		try {
			user.aceptRequest(request);}
		catch (RequestNoExistException e) {}
		
		this.updateById(requestId, request);
	}

	@Transactional
	public void rejectRequest(Long userId, Long requestId, UserService userService, PublicationService publicationService) {
		BookingRequest request = this.searchById(requestId);
		User user = userService.searchById(userId);
		user.rejectRequest(request);
		
		this.updateById(requestId, request);
	}

	@Transactional
	public void initBySeller(Long userId, Long requestId, UserService userService,PublicationService publicationService) {
		BookingRequest request = this.searchById(requestId);
		User user = userService.searchById(userId);
		user.setMyPublications(
				publicationService.selectByFunction((publication) -> publication.getUser().getId() == userId));
		
		try {
			user.confirmVehicleRetreatSeller(request);
		} catch (NoAceptedException e) {}
		this.updateById(requestId, request);
		
	}
	@Transactional
	public void finishBySeller(RequestsCorcernPayload dto, UserService userService, PublicationService publicationService) {
		BookingRequest request = this.searchById(dto.getRequestId());
		User user = userService.searchById(dto.getUserId());
		user.setMyPublications(
				publicationService.selectByFunction((publication) -> publication.getUser().getId() == dto.getUserId()));
		
		try {
			user.confirmVehicleReturnSeller(request, this.createScoreWith(dto.getScoreValue()));
		} catch (NoAceptedException e) {}
		
		this.updateById(dto.getRequestId(), request);
		userService.updateById(dto.getUserId(), user);
	}

	private Score createScoreWith(Double scoreValue) {
		Score score = new Score();
		score.setValue(scoreValue);
		
		return score;
	}
	@Transactional
	public void finishByBuyer(RequestsCorcernPayload requestPayload, UserService userService,
			PublicationService publicationService) {
		
		BookingRequest request = this.searchById(requestPayload.getRequestId());
		User user = userService.searchById(requestPayload.getUserId());
		user.setMyPublications(
				publicationService.selectByFunction((publication) -> publication.getUser().getId() == requestPayload.getUserId()));
		
		try {
			user.confirmVehicleReturnBuyer(request, this.createScoreWith(requestPayload.getScoreValue()), new Score());
		} catch (NoAceptedException e) {}
		
		this.updateById(requestPayload.getRequestId(), request);
		userService.updateById(requestPayload.getUserId(), user);
		
	}
	@Transactional
	public void initByBuyer(RequestsCorcernPayload requestPayload, UserService userService,
			PublicationService publicationService) {
		
		BookingRequest request = this.searchById(requestPayload.getRequestId());
		User user = userService.searchById(requestPayload.getUserId());
		user.setMyPublications(
				publicationService.selectByFunction((publication) -> publication.getUser().getId() == requestPayload.getUserId()));
		
		Publication post = publicationService.selectByFunction((publication) -> publication.containsRequest(request)).get(0);
		
		try {
			user.confirmVehicleRetreatBuyer(request, post);
		} catch (NoAceptedException e) {}
		this.updateById(requestPayload.getRequestId(), request);
	}	
}
