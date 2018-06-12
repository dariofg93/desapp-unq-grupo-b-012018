import { Vehicle } from './../models/vehicle'
import { BookingRequest } from './../models/booking-request';
import { City } from './../models/city';
import { GeographicZoneDescription } from './../models/geographic-zone-description';

export class Publication {
	id: number;
  pricePerHour: number; 
  phone: number; 
  fromDate: Date;
  toDate: Date;
  requests: BookingRequest;
  publishedVehicle: Vehicle;
  city: City;
  pickUpZone: GeographicZoneDescription;
  dropZone: GeographicZoneDescription;
}