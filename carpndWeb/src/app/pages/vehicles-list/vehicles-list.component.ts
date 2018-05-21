import { Vehicle } from './../../models/vehicle'
import { GenericRestService } from './../../services/generic/generic-rest.service';
import { PATHBACKEND } from './../../../environments/environment';

import { Observable } from "rxjs/Observable";
import { ActivatedRoute,Router } from "@angular/router";
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-vehicles-list',
  templateUrl: './vehicles-list.component.html',
  styleUrls: ['./vehicles-list.component.css'],
  providers: [
    GenericRestService,
    { provide: 'url', useValue: PATHBACKEND },
    { provide: 'endpoint', useValue: 'vehicles' }
  ]
})
export class VehiclesListComponent implements OnInit {
  vehicles: Array<Vehicle>;

  constructor(
    private vehiclesService: GenericRestService<Vehicle>, 
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    this.getVehicles();
  }

  getVehicles(): void {
	  this.vehiclesService.list()
      .subscribe(
        data => this.vehicles = data.body
      );
	}
}
