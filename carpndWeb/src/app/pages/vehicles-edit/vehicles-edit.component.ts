import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router, ActivatedRoute } from "@angular/router";

import { PATHBACKEND } from './../../../environments/environment';
import { Vehicle } from './../../models/vehicle'
import { GenericRestService } from './../../services/generic/generic-rest.service';
import { Category } from './../../models/categories';
import { UserService } from '../../services/user/user.service';
import { User } from '../../models/user';


@Component({
  selector: 'app-vehicles-edit',
  templateUrl: './vehicles-edit.component.html',
  styleUrls: ['./vehicles-edit.component.css'],
  providers: [
    GenericRestService,
    { provide: 'url', useValue: PATHBACKEND },
    { provide: 'endpoint', useValue: 'vehicles' }
  ]
})
export class VehiclesEditComponent implements OnInit {

  vehicle: Vehicle;
  newPicturePath: String;
  profile: User;
  
  categories = [
    new Category("Car"),
    new Category("Scooter")
  ];

  return;

  constructor(
    private vehiclesService: GenericRestService<Vehicle>,
    private router: Router,
    private usersService: UserService,
    private activatedRoute: ActivatedRoute,
    private location: Location,
  ) { }

  ngOnInit() {
    this.usersService.read(JSON.parse(localStorage.getItem('id'))).subscribe(
      data => this.profile = data.body
    );

    this.vehiclesService.read(this.activatedRoute.snapshot.params.id).subscribe(
      data => this.vehicle = data.body
    );
  }
  saveVehicle(form): void {
    this.vehicle.owner = this.profile
    this.vehiclesService.update(this.vehicle.id, this.vehicle).subscribe();
    this.router.navigate(['']);
  }


  addPicture(newPicturePath) {
    this.vehicle.pictures.push(newPicturePath);
  }
  revomePicture(newPicturePath) {
    var i = this.vehicle.pictures.indexOf(newPicturePath);
    if (i != -1) {
      this.vehicle.pictures.splice(i, 1);
    }
  }
}
