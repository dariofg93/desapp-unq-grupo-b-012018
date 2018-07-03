import { Component, OnInit } from '@angular/core';
import { Publication } from '../../models/publication';
import { Location } from '@angular/common';
import { UserService } from '../../services/user/user.service';
import { Router } from '@angular/router';
import { GenericRestService } from '../../services/generic/generic-rest.service';
import { Vehicle } from '../../models/vehicle';
import { User } from '../../models/user';
import { City } from '../../models/city';
import { GeographicZoneDescription } from '../../models/geographic-zone-description';
import { PublicationService } from '../../services/publication/publication.service';

@Component({
  selector: 'app-new-publication',
  templateUrl: './new-publication.component.html',
  styleUrls: ['./new-publication.component.css']
})
export class NewPublicationComponent implements OnInit {

  publication = new Publication();
  profile: User;
  errors;

  constructor(
    private usersService: UserService,
    private publicationsService: PublicationService,
    private vehiclesService: GenericRestService<Vehicle>,
    private router: Router,
    private location: Location,
  ) { }

  ngOnInit() {
    this.usersService.read(JSON.parse(localStorage.getItem('id'))).subscribe(
      data => this.profile = data.body
    );
  }

  return() {
    this.location.back();
  }


  pickUpZones(): GeographicZoneDescription[] {
    return this.profile.myPublications.map(function (p) { return p.pickUpZone })
  }

  savePublication(form) {
    var pickUp = JSON.parse(localStorage.getItem('PickUp'));
    var pickDown = JSON.parse(localStorage.getItem('PickDown'));

    this.publication.user = this.profile;
    if(pickUp === null || pickDown === null){
      alert("Debe seleccionar Zona de retiro y zona de deposito del vehiculo")
    }
    this.publication.pickUpZone = new GeographicZoneDescription(pickUp.lat,pickUp.lng);
    this.publication.dropZone = new GeographicZoneDescription(pickDown.lat,pickDown.lng);

    localStorage.removeItem('PickUp'); localStorage.removeItem('PickDown');

    console.log(this.publication.fromDate, this.publication.toDate)
    if (this.publication.fromDate > this.publication.toDate) {
      alert('La fecha desde no puede ser mayor a la fecha hasta')
    } else {
    
      this.publication.user = this.profile;
      console.log(this.publication)
      this.publicationsService.create(this.publication).subscribe(
        data => this.errors = (data.body),
        error => {
          this.errors = error;
        },
        () => {
          console.log(this.errors)
          if (this.errors.error) {
            this.errors = JSON.parse(this.errors);
            if (this.errors.error) {
              alert(this.errors.error)
            }
          } else {
            this.router.navigate(['/publications']);
          }
        }
      );
    }
  }
}



