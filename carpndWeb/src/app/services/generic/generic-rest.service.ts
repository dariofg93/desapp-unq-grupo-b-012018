import { ResponseEntity } from './../../models/response-entity';
import { Optional,Injectable,Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { RequestOptions } from '@angular/http';

@Injectable()
/*@Component({
  providers: [
    GenericRestService,
    { provide: 'url', useValue: PATHBACKEND },
    { provide: 'endpoint', useValue: 'users' }
  ]
})*/
export class GenericRestService<T> {

  headers: HttpHeaders;

  constructor(
      protected http: HttpClient,
      @Inject('url') @Optional() protected url?: string,
      @Inject('endpoint') @Optional() protected endpoint?: string
  ) {
    this.headers = new HttpHeaders();
    this.headers.append('Content-Type', 'application/json');
  }

  public create(item: T): Observable<ResponseEntity<T>> {
    return this.http
      .post<ResponseEntity<T>>(`${this.url}/${this.endpoint}/new`, item);
  }

  public update(primaryKey: any, item: T): Observable<ResponseEntity<T>> {
    return this.http
      .put<ResponseEntity<T>>(`${this.url}/${this.endpoint}/${primaryKey}`, item);
  }

	public read(primaryKey: any): Observable<ResponseEntity<T>> {
    return this.http
      .get<ResponseEntity<T>>(`${this.url}/${this.endpoint}/${primaryKey}`);
  }

  public list(): Observable<ResponseEntity<T[]>> {
    return this.http
      .get<ResponseEntity<T[]>>(`${this.url}/${this.endpoint}/`);
  }

  public delete(id: number): Observable<ResponseEntity<String>> {
    return this.http
      .delete<ResponseEntity<String>>(`${this.url}/${this.endpoint}/${id}`);
  }
}
