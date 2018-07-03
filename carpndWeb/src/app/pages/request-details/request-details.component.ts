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
  scoreValue: number;
	
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
    console.log(this.request.requester)
    return this.request.requester.id == this.user.id;
  }

  requestToMyPublicationIsAWA(): boolean {
    return  this.isMyPublication() && this.request.state.concretType == "AWA";
  }

  requestToMyPublicationIsAPPButNotInit(): boolean {
    return  this.isMyPublication() && this.request.state.concretType == "APP" && 
                                      this.request.status.confirmRetreatSeller == false && 
                                      this.request.status.confirmReturnSeller == false;
  }

  requestToMyPublicationIsAPPAndWantFinish(): boolean {
    return  this.isMyPublication() && this.request.state.concretType == "APP" && 
                                      this.request.status.confirmRetreatSeller == true && 
                                      this.request.status.confirmReturnSeller == false;
  }

  myRequestWasAceptedButNotInit(): boolean {
    return  this.isMyRequest() && this.request.state.concretType == "APP" && 
                                  this.request.status.confirmRetreatBuyer == false &&
                                  this.request.status.confirmReturnBuyer == false;
  }

  myRequestIsAPPAndWantFinish(): boolean {
    return  this.isMyRequest() && this.request.state.concretType == "APP" && 
                                  this.request.status.confirmRetreatSeller == true && 
                                  this.request.status.confirmReturnBuyer == false;
  }

  requestWithOutActions(): boolean {
    return  !this.requestToMyPublicationIsAWA() &&
            !this.requestToMyPublicationIsAPPButNotInit() && 
            !this.requestToMyPublicationIsAPPAndWantFinish() &&
            !this.myRequestWasAceptedButNotInit() && 
            !this.myRequestIsAPPAndWantFinish();
  }

  isMyPublicationOrMyRequestAndApp(): boolean {
  console.log((this.isMyPublication() && this.request.state.concretType == "APP" || this.isMyRequest() && this.request.state.concretType == "APP"))
    return  (this.isMyPublication() && this.request.state.concretType == "APP" || this.isMyRequest() && this.request.state.concretType == "APP")
  }
  aceptRequest(): void {
    this.request.state.concretType = "APP"
    this.request.state.description = "Approved"
    this.requestsService.aceptRequest(this.user.id,this.request.id).subscribe(
      data => this.request = data.body
    );
  }

  rejectRequest(): void {
    this.request.state.concretType = "REJ"
    this.request.state.description = "Rejected"
    this.requestsService.rejectRequest(this.user.id,this.request.id).subscribe(
      data =>this.request = data.body
    );
  }

  initBySeller(): void {
    this.request.reservationDateTime = new Date();
    this.request.status.confirmRetreatSeller = true;
    this.requestsService.initBySeller(this.user.id,this.request.id).subscribe(
      data => this.request = data.body
    );
  }

  finishBYSeller(): void {
    this.request.hoursOfTheReservation = Math.round(Math.abs(new Date().getTime() - new Date(this.request.reservationDateTime).getTime()) / 36e5);
    this.request.status.confirmReturnSeller = true;
    this.requestsService.finishBYSeller(this.user.id,this.request.id, this.scoreValue).subscribe(
      data => this.request = data.body
    );
  }

  initByBuyer(): void {
    this.request.reservationDateTime = new Date();
    this.request.status.confirmRetreatBuyer = true;
    this.requestsService.initByBuyer(this.user.id,this.request.id).subscribe(
      data => {this.request = data.body, console.log(data.body)}
    );
  }

  finishByBuyer(): void {
    this.request.hoursOfTheReservation = Math.round(Math.abs(new Date().getTime() - new Date(this.request.reservationDateTime).getTime()) / 36e5);
    this.request.status.confirmReturnBuyer = true;
    this.requestsService.finishByBuyer(this.user.id,this.request.id, this.scoreValue).subscribe(
      data =>  {this.request = data.body, console.log(data.body)}
    );
  }

  return() {
    this.location.back();
  }
}
