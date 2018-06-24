import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from "@angular/router";

import { User } from './../../models/user';
import { Vehicle } from './../../models/vehicle';
import { Category } from './../../models/categories';
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
		new Category("Car"),
		new Category("Scooter")
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
    this.vehicle.category = new Category(this.vehicle.category);
    if (this.profile.myVehicles) {
      this.profile.myVehicles.push(this.vehicle);
    }else{
      this.profile.myVehicles = [this.vehicle];
    }
    this.usersService.update(this.profile.id,this.profile).subscribe();
    this.router.navigate(['']);
  }
}
