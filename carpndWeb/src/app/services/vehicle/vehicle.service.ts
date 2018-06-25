import { Injectable,Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ResponseEntity } from './../../models/response-entity';
import { GenericRestService } from './../../services/generic/generic-rest.service';
import { PATHBACKEND } from './../../../environments/environment';
import { Vehicle } from '../../models/vehicle';

@Injectable()
export class VehicleService extends GenericRestService<Vehicle>{

  constructor(
    protected http: HttpClient
  ){
    super(http,PATHBACKEND,'vehicles')
  }

}
