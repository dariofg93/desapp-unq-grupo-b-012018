import { Component, OnInit } from '@angular/core';
import { Publication } from '../../models/publication';
import { UserService } from '../../services/user/user.service';
import { Router } from '@angular/router';
import { GenericRestService } from '../../services/generic/generic-rest.service';
import { Vehicle } from '../../models/vehicle';
import { User } from '../../models/user';
import { City } from '../../models/city';

@Component({
  selector: 'app-new-publication',
  templateUrl: './new-publication.component.html',
  styleUrls: ['./new-publication.component.css']
})
export class NewPublicationComponent implements OnInit {

  publication = new Publication();
  profile: User;

  cities = [
		new City("Wilde"),
    new City("Bernal"),
    new City("La Plata"),
	];


  constructor(
    private usersService: UserService,
    private publicationsService:  GenericRestService<Publication>,
    private vehiclesService: GenericRestService<Vehicle>,
  	private router: Router,

  ) { }

  ngOnInit() {
    this.usersService.read(JSON.parse(localStorage.getItem('id'))).subscribe(
      data => this.profile = data.body
   );
  }

  today(){
    return Date.now().toLocaleString;
  }

}



