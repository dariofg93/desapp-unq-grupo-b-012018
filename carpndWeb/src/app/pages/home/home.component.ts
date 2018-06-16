import { Component, AfterViewChecked } from '@angular/core';
import { ASSETS } from './../../variables/variables'
import { DatePipe } from '@angular/common'

import { User } from './../../models/user'
import { Vehicle } from './../../models/vehicle'
import { Publication } from './../../models/publication';
import { BookingRequest } from './../../models/booking-request';
import { UserService } from './../../services/user/user.service';
import { GenericRestService } from './../../services/generic/generic-rest.service';

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

  requests = [
    {
      "id_request": 1,
      "state": "Awaiting approbal",
      "requester": {
        "id": 1,
        "first_name": "Dario",
        "last_name": "Gutierrez",
        "cuil": 20379538860,
        "movementsOfMonth": "",
        "email": "dariofg93@gmail.com"
      },
      "totalHours": 15,
      "dateTimeOfReservation": null,
      "hoursOfTheReservation": null
    },
    {
      "id_request": 2,
      "state": "Approved",
      "requester": {
        "id": 2,
        "first_name": "Fabri",
        "last_name": "Britez",
        "cuil": 561516516,
        "movementsOfMonth": "Muchos",
        "email": "fabri011@gmail.com"
      },
      "totalHours": 100,
      "dateTimeOfReservation": null,
      "hoursOfTheReservation": null
    }
  ]

  myVehicles = [
    {
      "id": 1,
      "description": "Un auto muy veloz!",
      "passengerCapacity": 4,
      "category": "Car",
      "pictures": [
        "maclaren_hermoso.png"
      ]
    },
    {
      "id": 2,
      "description": "La moto definitiva, con esta podes ir desde La Plata hasta Bariloche en un dia!",
      "passengerCapacity": 2,
      "category": "Scooter",
      "pictures": []
    }
  ]

  constructor(
    private usersService: UserService,
    private vehiclesService: GenericRestService<Vehicle>,
    private datepipe: DatePipe
  ) {}

  ngAfterViewChecked(){
    if (JSON.parse(localStorage.getItem('id')) && this.profile == null) {
      this.usersService.read(JSON.parse(localStorage.getItem('id'))).subscribe(
        data => this.profile = data.body
      );
    }
  }

  hasImages(vehicle: Vehicle): boolean {
    return vehicle.pictures.length > 0;
  }

  hasProfile(): boolean {
    return this.profile != null;
  }

  pathImage(vehicle: Vehicle): string {
    return ASSETS + vehicle.pictures[0];
  }

  deleteVehicle(id: number): void {
    this.vehiclesService.delete(id).subscribe(
      data => console.log(data.body)
    );
  }

  showDate(date: number): string{
    return this.datepipe.transform(new Date(date), 'd-MMM-y HH:m:s');
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
