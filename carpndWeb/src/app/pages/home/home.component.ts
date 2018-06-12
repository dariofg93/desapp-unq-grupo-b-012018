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

  hasProfile(): boolean {
    return this.profile;
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
