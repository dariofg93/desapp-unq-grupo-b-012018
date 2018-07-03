import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs/Observable";
import { ActivatedRoute,Router } from "@angular/router";
import { OrderPipe } from 'ngx-order-pipe';

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

  reverse: boolean = false;
  order: string = 'pricePerHour'
	publications: Publication[];
  currentP = 1;
  categoryFilter;
  categories = [
    new Category("Car"),
    new Category("Scooter")
  ];

  constructor(
    private publicationsService: GenericRestService<Publication>, 
    private route: ActivatedRoute,
    private router: Router,
    private orderPipe: OrderPipe
  ) {
    this.publications = [] 
  }

  ngOnInit() {
    this.publicationsService.list()
      .subscribe(
        data => this.publications = data.body
      );
  }

  getRouter(){
    return this.router;
  }

  hasPublications(){
    return this.publications;
  }

  setOrder(value: string) {
    if (this.order === value) {
      this.reverse = !this.reverse;
    }

    this.order = value;
  }

}
