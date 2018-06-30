import { Injectable,Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ResponseEntity } from './../../models/response-entity';
import { BookingRequest } from './../../models/booking-request';
import { AceptRequest } from './../../models/dtos/acept-request';
import { RejectRequest } from './../../models/dtos/reject-request';
import { InitBySeller } from './../../models/dtos/init-by-seller';
import { FinishBySeller } from './../../models/dtos/finish-by-seller';
import { InitByBuyer } from './../../models/dtos/init-by-buyer';
import { FinishByBuyer } from './../../models/dtos/finish-by-buyer';
import { GenericRestService } from './../../services/generic/generic-rest.service';
import { PATHBACKEND } from './../../../environments/environment';

@Injectable()
export class BookingRequestService extends GenericRestService<BookingRequest>{

	constructor(
    protected http: HttpClient
  ){
    super(http,PATHBACKEND,'requests')
  }

  public aceptRequest(userId: number, requestId: number): Observable<ResponseEntity<BookingRequest>> {
    return this.http
      .put<ResponseEntity<BookingRequest>>(`${this.url}/${this.endpoint}/aceptRequest/`, new AceptRequest(userId,requestId));
  }

  public rejectRequest(userId: number, requestId: number): Observable<ResponseEntity<BookingRequest>> {
    return this.http
      .put<ResponseEntity<BookingRequest>>(`${this.url}/${this.endpoint}/rejectRequest/`, new RejectRequest(userId,requestId));
  }

  public initBySeller(userId: number, requestId: number): Observable<ResponseEntity<BookingRequest>> {
    return this.http
      .put<ResponseEntity<BookingRequest>>(`${this.url}/${this.endpoint}/initBySeller/`, new InitBySeller(userId,requestId));
  }

  public finishBYSeller(userId: number, requestId: number): Observable<ResponseEntity<BookingRequest>> {
    return this.http
      .put<ResponseEntity<BookingRequest>>(`${this.url}/${this.endpoint}/finishBYSeller/`, new FinishBySeller(userId,requestId));
  }

  public initByBuyer(userId: number, requestId: number): Observable<ResponseEntity<BookingRequest>> {
    return this.http
      .put<ResponseEntity<BookingRequest>>(`${this.url}/${this.endpoint}/initByBuyer/`, new InitByBuyer(userId,requestId));
  }

  public finishByBuyer(userId: number, requestId: number): Observable<ResponseEntity<BookingRequest>> {
    return this.http
      .put<ResponseEntity<BookingRequest>>(`${this.url}/${this.endpoint}/finishByBuyer/`, new FinishByBuyer(userId,requestId));
  }
}
