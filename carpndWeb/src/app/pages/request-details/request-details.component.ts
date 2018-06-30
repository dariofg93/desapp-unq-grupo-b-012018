import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute } from "@angular/router";

import { User } from './../../models/user';
import { PATHBACKEND } from './../../../environments/environment';
import { BookingRequest } from './../../models/booking-request';
import { DateService } from './../../services/date/date.service';
import { UserService } from './../../services/user/user.service';
import { BookingRequestService } from './../../services/booking/booking-request.service';

@Component({
  selector: 'app-request-details',
  templateUrl: './request-details.component.html',
  styleUrls: ['./request-details.component.css'],
})
export class RequestDetailsComponent implements OnInit {

  request: BookingRequest = null;
	user: User = null;
	
  constructor(
    private requestsService: BookingRequestService,
    private usersService: UserService,
  	private activatedRoute: ActivatedRoute,
    private location: Location,
    private dateService: DateService
 	) {}

  ngOnInit() {
  	this.requestsService.read(this.activatedRoute.snapshot.params.id).subscribe(
      data => this.request = data.body
    );

    this.usersService.read(JSON.parse(localStorage.getItem('id'))).subscribe(
      data => this.user = data.body
    );
  }

  hasRequest(): boolean {
    return this.request != null;
  }

  hasUser(): boolean {
    return this.user != null;
  }

  isMyPublication(): boolean{
    var requestsListList: BookingRequest[][] = this.user.myPublications? 
      this.user.myPublications.map(function(p){ return p.requests }): 
      null;

    var requests: BookingRequest[] = !requestsListList? 
      []: 
      requestsListList.length > 1?
        requestsListList.reduce(function(a,b) {
          return a.concat(b);
        }): 
        requestsListList[0];

    return requests.map(function(r){ return r.id }).includes(this.request.id);
  }

  isMyRequest(): boolean{
    return this.request.requester.id == this.user.id;
  }

  requestToMyPublicationIsAWA(): boolean {
    return  this.isMyPublication() && this.request.state.concretType == "AWA";
  }

  requestToMyPublicationIsAPPButNotInit(): boolean {
    return  this.isMyPublication() && this.request.state.concretType == "APP" && this.request.reservationDateTime == null;
  }

  requestToMyPublicationIsAPPAndWantFinish(): boolean {
    return  this.isMyPublication() && this.request.state.concretType == "APP" && this.request.reservationDateTime != null;
  }

  myRequestWasAceptedButNotInit(): boolean {
    return  this.isMyRequest() && this.request.state.concretType == "APP" && this.request.reservationDateTime == null;
  }

  myRequestIsAPPAndWantFinish(): boolean {
    return  this.isMyRequest() && this.request.state.concretType == "APP" && this.request.reservationDateTime != null;
  }

  requestWithOutActions(): boolean {
    return (this.request.state.concretType != null || !this.isMyPublication() || !this.isMyRequest()) &&
           (!this.requestToMyPublicationIsAWA() &&
            !this.requestToMyPublicationIsAPPButNotInit() && !this.requestToMyPublicationIsAPPAndWantFinish() &&
            !this.myRequestWasAceptedButNotInit() && !this.myRequestIsAPPAndWantFinish());
  }

  aceptRequest(): void {
    this.request.state.concretType = "APP"
    this.request.state.description = "Approved"
    this.requestsService.aceptRequest(this.user.id,this.request.id).subscribe(
      data => console.log(data)
    );
  }

  rejectRequest(): void {
    this.request.state.concretType = "REJ"
    this.request.state.description = "Rejected"
    this.requestsService.rejectRequest(this.user.id,this.request.id).subscribe(
      data => console.log(data)
    );
  }

  initBySeller(): void {
    this.request.reservationDateTime = new Date();
    this.requestsService.initBySeller(this.user.id,this.request.id).subscribe(
      data => console.log(data)
    );
  }

  finishBYSeller(): void {
    this.request.hoursOfTheReservation = Math.round(Math.abs(new Date().getTime() - new Date(this.request.reservationDateTime).getTime()) / 36e5);
    this.requestsService.finishBYSeller(this.user.id,this.request.id).subscribe(
      data => console.log(data)
    );
  }

  initByBuyer(): void {
    this.request.reservationDateTime = new Date();
    this.requestsService.initByBuyer(this.user.id,this.request.id).subscribe(
      data => console.log(data)
    );
  }

  finishByBuyer(): void {
    this.request.hoursOfTheReservation = Math.round(Math.abs(new Date().getTime() - new Date(this.request.reservationDateTime).getTime()) / 36e5);
    this.requestsService.finishByBuyer(this.user.id,this.request.id).subscribe(
      data => console.log(data)
    );
  }

  return() {
    this.location.back();
  }
}
