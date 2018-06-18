import { Inject, Component, AfterViewChecked, LOCALE_ID } from '@angular/core';
import { ASSETS } from './../../variables/variables'
import { DatePipe,registerLocaleData } from '@angular/common'
import localeEs from '@angular/common/locales/es';

import { User } from './../../models/user'
import { Vehicle } from './../../models/vehicle'
import { Publication } from './../../models/publication';
import { BookingRequest } from './../../models/booking-request';
import { UserService } from './../../services/user/user.service';
import { GenericRestService } from './../../services/generic/generic-rest.service';

registerLocaleData(localeEs, 'es-AR');

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [
    DatePipe
  ]
})
export class HomeComponent implements AfterViewChecked {

  profile: User = null;
  currentP = 1;

  constructor(
    private usersService: UserService,
    private vehiclesService: GenericRestService<Vehicle>,
    private datepipe: DatePipe,
    @Inject(LOCALE_ID) private _locale: string
  ) {}

  ngAfterViewChecked(){
    if (JSON.parse(localStorage.getItem('id')) && this.profile == null) {

      this.usersService.read(JSON.parse(localStorage.getItem('id'))).subscribe(
        data => this.profile = data.body
      );
    }
  }

  hasImages(vehicle: Vehicle): boolean {
    return vehicle.pictures? vehicle.pictures.length > 0: false;
  }

  hasProfile(): boolean {
    return this.profile != null;
  }

  pathImage(vehicle: Vehicle): string {
    return ASSETS + vehicle.pictures[0];
  }

  deleteVehicle(id: number): void {
    console.log(this.profile);
    this.vehiclesService.delete(id).subscribe(
      data => console.log(data.body)
    );
  }

  getLocale_id(){
    return this._locale;
  }

  showDate(date: number): Date{
    return new Date(date);
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
