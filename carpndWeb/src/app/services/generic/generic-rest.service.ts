import { ResponseEntity } from './../../models/response-entity';
import { Optional,Injectable,Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient,HttpHeaders } from '@angular/common/http';


@Injectable()
export class GenericRestService<T> {
constructor(
    private http: HttpClient,
    @Inject('url') @Optional() public url?: string,
    @Inject('endpoint') @Optional() public endpoint?: string
) {}

  public create(item: T): Observable<ResponseEntity<T>> {
    return this.http
      .post<ResponseEntity<T>>(`${this.url}/${this.endpoint}`, item);
  }

  public update(primaryKey: any, item: T): Observable<ResponseEntity<T>> {
    return this.http
      .put<ResponseEntity<T>>(`${this.url}/${this.endpoint}/${primaryKey}`,item);
  }

	read(primaryKey: any): Observable<ResponseEntity<T>> {
    return this.http
      .get<ResponseEntity<T>>(`${this.url}/${this.endpoint}/${primaryKey}`);
  }

  list(): Observable<ResponseEntity<T[]>> {
    return this.http
      .get<ResponseEntity<T[]>>(`${this.url}/${this.endpoint}/`);
  }

  delete(id: number) {
    return this.http
      .delete(`${this.url}/${this.endpoint}/${id}`);
  }
}