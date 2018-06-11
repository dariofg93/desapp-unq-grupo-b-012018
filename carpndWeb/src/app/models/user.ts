import { Vehicle } from './../models/vehicle'

export class User {
	id_user: number;
  first_name: string;
  last_name: string;
  cuil: number;
  movementsOfMonth: string;
  email: string;
  myVehicles: Vehicle[]

  constructor( email: string){
  	this.email = email;
  }

  public addVehicle(vehicle: Vehicle){
  	if (this.myVehicles == null) 
  		this.myVehicles = [vehicle];
  	else
  		this.myVehicles.push(vehicle);
  }
}