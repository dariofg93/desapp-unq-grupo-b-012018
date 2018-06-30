import { Publication } from './../../models/publication';
import { BookingRequest } from './../../models/booking-request';

export class RentVehicle {
	publication: Publication;
	request: BookingRequest;

	constructor(publication: Publication, request: BookingRequest){
		this.publication = publication;
		this.request = request;
	}
}
