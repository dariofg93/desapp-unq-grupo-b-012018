import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from "@angular/router";

import { User } from './../../models/user';
import { Vehicle } from './../../models/vehicle';
import { Car,Scooter } from './../../models/categories';
import { UserService } from './../../services/user/user.service';

@Component({
  selector: 'app-new-vehicle',
  templateUrl: './new-vehicle.component.html',
  styleUrls: ['./new-vehicle.component.css']
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
    }else{
      this.profile.myVehicles = [this.vehicle];
    }
    this.usersService.update(this.profile.id,this.profile).subscribe(
      data => console.log(data)
    );
    this.router.navigate(['']);
  }
}
