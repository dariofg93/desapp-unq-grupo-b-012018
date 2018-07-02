import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs/Observable";
import { ActivatedRoute,Router } from "@angular/router";

import { Category } from './../../models/categories';
import { GenericRestService } from './../../services/generic/generic-rest.service';
import { PATHBACKEND } from './../../../environments/environment';
import { Publication } from './../../models/publication';
import { GeographicZoneDescription } from './../../models/geographic-zone-description';


@Component({
  selector: 'app-search-vehicle',
  templateUrl: './search-vehicle.component.html',
  styleUrls: ['./search-vehicle.component.css'],
  providers: [
    GenericRestService,
    { provide: 'url', useValue: PATHBACKEND },
    { provide: 'endpoint', useValue: 'publications' }
  ]
})
export class SearchVehicleComponent implements OnInit {

  selectCategory;
  changeFilter;
	publications: Publication[];
  currentP = 1;
  categoryFilter = undefined;
  categories = [
    new Category("Car"),
    new Category("Scooter")
  ];

  constructor(
    private publicationsService: GenericRestService<Publication>, 
    private route: ActivatedRoute,
    private router: Router
  ) {

    this.publications = [] 
  }

  ngOnInit() {
    this.publicationsService.list()
      .subscribe(
        data => this.publications = data.body
      );
  }

  pickUpZones(): GeographicZoneDescription[]{
    return this.publications.map(function(p) { return p.pickUpZone })
  }

}
