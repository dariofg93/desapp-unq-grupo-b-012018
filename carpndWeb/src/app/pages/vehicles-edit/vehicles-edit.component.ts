import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router,ActivatedRoute } from "@angular/router";

import { PATHBACKEND } from './../../../environments/environment';
import { Vehicle } from './../../models/vehicle'
import { GenericRestService } from './../../services/generic/generic-rest.service';
import { Car,Scooter } from './../../models/categories';


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
  categories = [
		new Car(),
		new Scooter()
	];

  return;

  constructor(
  	private vehiclesService: GenericRestService<Vehicle>,
  	private router: Router,
  	private activatedRoute: ActivatedRoute,
  	private location: Location,
  ) {}

  ngOnInit() {
  	this.vehiclesService.read(this.activatedRoute.snapshot.params.id).subscribe(
      data => this.vehicle = data.body
    );
  }
  saveVehicle(form):void {
    this.vehiclesService.update(this.vehicle.id, this.vehicle);
    this.router.navigate(['']);
  }
}
