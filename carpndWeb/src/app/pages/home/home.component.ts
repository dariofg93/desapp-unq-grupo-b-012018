import { Inject, Component, AfterViewChecked, LOCALE_ID } from '@angular/core';
import { ASSETS } from './../../variables/variables'
import { registerLocaleData } from '@angular/common'
import localeEs from '@angular/common/locales/es';

import { PATHBACKEND } from './../../../environments/environment';
import { User } from './../../models/user';
import { Vehicle } from './../../models/vehicle';
import { Publication } from './../../models/publication';
import { BookingRequest } from './../../models/booking-request';
import { DateService } from './../../services/date/date.service';
import { UserService } from './../../services/user/user.service';
import { GenericRestService } from './../../services/generic/generic-rest.service';
import { Router } from '@angular/router';

registerLocaleData(localeEs, 'es-AR');

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [
    GenericRestService,
    { provide: 'url', useValue: PATHBACKEND },
    { provide: 'endpoint', useValue: 'vehicles' }
  ]
})
export class HomeComponent implements AfterViewChecked {

  profile: User = null;
  currentP = 1;
  setted = false;
  errors;

  constructor(
    private usersService: UserService,
    private vehiclesService: GenericRestService<Vehicle>,
    private dateService: DateService,
    private router: Router/*,
    @Inject(LOCALE_ID) private _locale: string*/
  ) {}

  ngAfterViewChecked(){
    if (JSON.parse(localStorage.getItem('id')) && this.profile == null && !this.setted) {

      this.usersService.read(JSON.parse(localStorage.getItem('id'))).subscribe(
        data => this.profile = data.body
      );

      this.setted = true;
    }
  }

  hasImages(vehicle: Vehicle): boolean {
    return vehicle.pictures? vehicle.pictures.length > 0: false;
  }

  hasProfile(): boolean {
    return this.profile != null;
  }

  hasMovements(): boolean {
    return  this.profile.movementsOfMonth != null && 
            this.profile.movementsOfMonth.history != null &&
            this.profile.movementsOfMonth.history.length > 0;
  }

  pathImage(vehicle: Vehicle): string {
    return vehicle.pictures[0];
  }

  deleteVehicle(id: number): void {
    this.vehiclesService.delete(id).subscribe(
      data => this.errors = (data.body),
      error => {
        this.errors = error;
      },
        () => {
          this.errors =  JSON.parse( this.errors);
          if(this.errors.error){
            alert(this.errors.error)
          }else{
            window.location.reload();
          } 
      }
      
    );
  }

  requestOfPublications(): BookingRequest[]{
    var requests: BookingRequest[][] = this.profile.myPublications? 
      this.profile.myPublications.map(function(p){ return p.requests }): 
      null;

    return !requests? 
      null: 
      requests.length > 1?
        requests.reduce(function(a,b) {
          return a.concat(b);
        }): 
        requests[0];
  }
}
