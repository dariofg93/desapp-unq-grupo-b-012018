import { Component, AfterViewChecked } from '@angular/core';
import { ASSETS } from './../../variables/variables'

import { User } from './../../models/user'
import { Vehicle } from './../../models/vehicle'
import { UserService } from './../../services/user/user.service';
import { GenericRestService } from './../../services/generic/generic-rest.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements AfterViewChecked {

  profile: any = null;

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
    private vehiclesService: GenericRestService<Vehicle>
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

  pathImage(vehicle: Vehicle): string {
    return ASSETS + vehicle.pictures[0];
  }

  deleteVehicle(id: number): void {
    this.vehiclesService.delete(id).subscribe(
      data => console.log(data.body)
    );
  }
}
