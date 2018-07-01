import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute } from "@angular/router";

import { Vehicle } from './../../models/vehicle'
import { Publication } from './../../models/publication';
import { BookingState } from './../../models/booking-state';
import { BookingRequest } from './../../models/booking-request';
import { UserService } from './../../services/user/user.service';
import { DateService } from './../../services/date/date.service';
import { PublicationService } from './../../services/publication/publication.service';

@Component({
  selector: 'app-publication-details',
  templateUrl: './publication-details.component.html',
  styleUrls: ['./publication-details.component.css']
})
export class PublicationDetailsComponent implements OnInit {

	publication: Publication = null;
  request: BookingRequest = new BookingRequest();

  constructor(
  	private activatedRoute: ActivatedRoute,
  	private location: Location,
    private usersService: UserService,
    private publicationsService: PublicationService,
    private dateService: DateService
  ) {
    this.usersService.read(JSON.parse(localStorage.getItem('id'))).subscribe(
      data => this.request.requester = data.body
    );
  }

  ngOnInit() {
  	this.publicationsService.read(this.activatedRoute.snapshot.params.id).subscribe(
      data => this.publication = data.body
    );
  }

  hasPublication(): boolean {
    return this.publication != null;
  }

  hasImages(vehicle: Vehicle): boolean {
    return vehicle? vehicle.pictures? vehicle.pictures.length > 0:
                                       false:
                    false;
  }

  hasRequest(): boolean {
    return this.publication? 
        this.publication.requests && this.publication.requests.length > 0:
        false;
  }

  isMyPublication() {
    return this.publication.user.id == JSON.parse(localStorage.getItem('id'));
  }

  executeRequest(form): void {
    this.request.requester.myVehicles = [];
    this.request.requester.myPublications = [];
    this.request.reservationDateTime = new Date();
    this.request.state = new BookingState("AWA");

    this.publication.requests.push(this.request)

    this.publicationsService.rentVehicle(this.publication,this.request).subscribe(
      data => console.log(data)
    );
  }

  return() {
    this.location.back();
  }
}
