import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";

import { PATHBACKEND } from './../../../environments/environment';
import { BookingRequest } from './../../models/booking-request';
import { GenericRestService } from './../../services/generic/generic-rest.service';

@Component({
  selector: 'app-see-request',
  templateUrl: './see-request.component.html',
  styleUrls: ['./see-request.component.css'],
  providers: [
    GenericRestService,
    { provide: 'url', useValue: PATHBACKEND },
    { provide: 'endpoint', useValue: 'requests' }
  ]
})
export class SeeRequestComponent implements OnInit {

	request: BookingRequest;

  constructor(
    private requestsService: GenericRestService<BookingRequest>,
  	private activatedRoute: ActivatedRoute,
  ) {}

  ngOnInit() {
  	this.requestsService.read(this.activatedRoute.snapshot.params.id).subscribe(
      data => this.request = data.body
    );
  }

}
