import { ResponseEntity } from './../../models/response-entity';
import { Optional,Injectable,Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import {RequestOptions, Request, RequestMethod} from '@angular/http';

@Injectable()
/*@Component({
  providers: [
    GenericRestService,
    { provide: 'url', useValue: PATHBACKEND },
    { provide: 'endpoint', useValue: 'users' }
  ]
})*/
export class GenericRestService<T> {

  fileChange(event) {
      const headers = new HttpHeaders({'Content-Type': 'text/uri-list'});
      var asd=new Headers();
      asd.append("Content-Type","multipart/form-data");
      asd.append("Accept","application/json");
      asd.append("Access-Control-Allow-Origin","true");
  }

  constructor(
      protected http: HttpClient,
      @Inject('url') @Optional() protected url?: string,
      @Inject('endpoint') @Optional() protected endpoint?: string
  ) {}

  public create(item: T): Observable<ResponseEntity<T>> {
    const headers = new HttpHeaders({"Content-Type":"multipart/form-data","Accept":"application/json","Access-Control-Allow-Origin":"true"});
    return this.http
      .post<ResponseEntity<T>>(`${this.url}/${this.endpoint}`, item, {headers:headers});
  }

  public update(primaryKey: any, item: T): Observable<ResponseEntity<T>> {
    const headers = new HttpHeaders({"Content-Type":"multipart/form-data","Accept":"application/json","Access-Control-Allow-Origin":"true"});
    return this.http
      .put<ResponseEntity<T>>(`${this.url}/${this.endpoint}/${primaryKey}`,item, {headers:headers});
  }

	public read(primaryKey: any): Observable<ResponseEntity<T>> {
    const headers = new HttpHeaders({"Content-Type":"multipart/form-data","Accept":"application/json","Access-Control-Allow-Origin":"true"});
    return this.http
      .get<ResponseEntity<T>>(`${this.url}/${this.endpoint}/${primaryKey}`, {headers:headers});
  }

  public list(): Observable<ResponseEntity<T[]>> {
    const headers = new HttpHeaders({"Content-Type":"multipart/form-data","Accept":"application/json","Access-Control-Allow-Origin":"true"});
    return this.http
      .get<ResponseEntity<T[]>>(`${this.url}/${this.endpoint}/`, {headers:headers});
  }

  public delete(id: number): Observable<ResponseEntity<String>> {
    const headers = new HttpHeaders({"Content-Type":"multipart/form-data","Accept":"application/json","Access-Control-Allow-Origin":"true"});
    return this.http
      .delete<ResponseEntity<String>>(`${this.url}/${this.endpoint}/${id}`, {headers:headers});
  }
}