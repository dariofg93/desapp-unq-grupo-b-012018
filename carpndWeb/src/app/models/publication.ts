import { Vehicle } from './../models/vehicle'
import { BookingRequest } from './../models/booking-request';
import { City } from './../models/city';
import { GeographicZoneDescription } from './../models/geographic-zone-description';
import { User } from './user';

export class Publication {
	id: number;
  pricePerHour: number; 
  phone: number; 
  fromDate: Date;
  toDate: Date;
  requests: BookingRequest[];
  publishedVehicle: Vehicle;
  city: City;
  pickUpZone: GeographicZoneDescription;
  dropZone: GeographicZoneDescription;
  user: User;

  constructor(){
    this.requests = [];
    this.dropZone = new GeographicZoneDescription();
    this.pickUpZone = new GeographicZoneDescription();
    this.publishedVehicle = new Vehicle();
    this.city = new City("");
  }
}
