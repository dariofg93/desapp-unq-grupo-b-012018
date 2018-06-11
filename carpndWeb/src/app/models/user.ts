import { Vehicle } from './../models/vehicle'
import { Publication } from './../models/publication';
import { GeographicZoneDescription } from './../models/geographic-zone-description';
import { MovementsOfMonth } from './../models/movements-of-month';
import { CreditsAccount } from './../models/credits-account';
import { Email } from './../models/email';

export class User {
	id: number;
  address: GeographicZoneDescription;
  email: Email;
  creditsAccount: CreditsAccount;
  myPublications: Publication[];
  movementsOfMonth: MovementsOfMonth;
  //scoreManager: ScoreManager;
  cuil: number;
  first_name: string;
  last_name: string;
  myVehicles: Vehicle[]

  constructor( email: string){
  	this.email = new Email(email);
  }

  public addVehicle(vehicle: Vehicle){
  	if (this.myVehicles == null) 
  		this.myVehicles = [vehicle];
  	else
  		this.myVehicles.push(vehicle);
  }
}