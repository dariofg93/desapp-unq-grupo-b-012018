import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute } from "@angular/router";

import { PATHBACKEND } from './../../../environments/environment';
import { BookingRequest } from './../../models/booking-request';
import { DateService } from './../../services/date/date.service';
import { GenericRestService } from './../../services/generic/generic-rest.service';

@Component({
  selector: 'app-request-details',
  templateUrl: './request-details.component.html',
  styleUrls: ['./request-details.component.css'],
  providers: [
    GenericRestService,
    { provide: 'url', useValue: PATHBACKEND },
    { provide: 'endpoint', useValue: 'requests' }
  ]
})
export class RequestDetailsComponent implements OnInit {

	request: BookingRequest = null;
	
  constructor(
    private requestsService: GenericRestService<BookingRequest>,
  	private activatedRoute: ActivatedRoute,
    private location: Location,
    private dateService: DateService
 	) {}

  ngOnInit() {
  	this.requestsService.read(this.activatedRoute.snapshot.params.id).subscribe(
      data => this.request = data.body
    );
  }

  hasRequest(): boolean {
    return this.request != null;
  }

  return() {
    this.location.back();
  }

}
