import { Injectable,Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User } from './../../models/user'
import { ResponseEntity } from './../../models/response-entity';
import { GenericRestService } from './../../services/generic/generic-rest.service';
import { PATHBACKEND } from './../../../environments/environment';

@Injectable()
export class UserService extends GenericRestService<User>{

  constructor(
    protected http: HttpClient
  ){
    super(http,PATHBACKEND,'users')
  }

  public selectByEmail(email: string): Observable<ResponseEntity<User>> {
    return this.http
      .get<ResponseEntity<User>>(`${this.url}/${this.endpoint}/selectByEmail/${email}`);
  }
}
