import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs/Observable";
import { ActivatedRoute,Router } from "@angular/router";

import { GenericRestService } from './../../services/generic/generic-rest.service';
import { PATHBACKEND } from './../../../environments/environment';
import { Vehicle } from './../../models/vehicle'


@Component({
  selector: 'app-search-vehicle',
  templateUrl: './search-vehicle.component.html',
  styleUrls: ['./search-vehicle.component.css'],
  providers: [
    GenericRestService,
    { provide: 'url', useValue: PATHBACKEND },
    { provide: 'endpoint', useValue: 'vehicles' }
  ]
})
export class SearchVehicleComponent implements OnInit {
	vehicles: Array<Vehicle>;
  currentP = 1;

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
