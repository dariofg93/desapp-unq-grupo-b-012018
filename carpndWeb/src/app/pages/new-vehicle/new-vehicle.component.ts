import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from "@angular/router";

import { User } from './../../models/user';
import { Vehicle } from './../../models/vehicle';
import { Car,Scooter } from './../../models/categories';
import { UserService } from './../../services/user/user.service';
import { GenericRestService } from '../../services/generic/generic-rest.service';
import { PATHBACKEND } from '../../../environments/environment';

@Component({
  selector: 'app-new-vehicle',
  templateUrl: './new-vehicle.component.html',
  styleUrls: ['./new-vehicle.component.css'],
  providers: [
    GenericRestService,
    { provide: 'url', useValue: PATHBACKEND },
    { provide: 'endpoint', useValue: 'vehicles' }
  ]
})
export class NewVehicleComponent implements OnInit {

	vehicle = new Vehicle();
	profile: User;

	categories = [
		new Car(),
		new Scooter()
	];

  constructor(
    private usersService: UserService,
    private vehicleService: GenericRestService<Vehicle>,
  	private router: Router,
  	private location: Location,
  ) {}

  ngOnInit() {
    this.usersService.read(JSON.parse(localStorage.getItem('id'))).subscribe(
      data => this.profile = data.body
    );
  }

  return() {
    this.location.back();
  }

  saveVehicle(form) {
    if (this.profile.myVehicles) {
      this.profile.myVehicles.push(this.vehicle);
      console.log(this.profile.myVehicles);
    }else{
      this.profile.myVehicles = [this.vehicle];
      console.log("ver aca" ,this.profile.myVehicles);
    }

    //this.vehicle.owner = this.profile;
    //this.vehicleService.create(this.vehicle);

    this.usersService.update(this.profile.id,this.profile).subscribe(
      data => console.log(data)
    );
    console.log(this.vehicleService);
    console.log(this.usersService);
    
    this.router.navigate(['']);
  }
}
