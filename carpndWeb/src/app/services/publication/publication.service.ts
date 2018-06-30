import { Injectable,Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ResponseEntity } from './../../models/response-entity';
import { RentVehicle } from './../../models/dtos/rent-vehicle';
import { BookingRequest } from './../../models/booking-request';
import { GenericRestService } from './../../services/generic/generic-rest.service';
import { PATHBACKEND } from './../../../environments/environment';
import { Publication } from '../../models/publication';

@Injectable()
export class PublicationService extends GenericRestService<Publication>{

  constructor(
    protected http: HttpClient
  ){
    super(http,PATHBACKEND,'publications')
  }

  public rentVehicle(publication: Publication, request: BookingRequest): Observable<ResponseEntity<Publication>> {
    return this.http
      .put<ResponseEntity<Publication>>(`${this.url}/${this.endpoint}/rentVehicle`,new RentVehicle(publication, request));
  }
}
