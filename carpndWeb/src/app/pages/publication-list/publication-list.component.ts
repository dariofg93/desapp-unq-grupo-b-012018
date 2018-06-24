import { Component, OnInit } from '@angular/core';

import { User } from './../../models/user';
import { Vehicle } from './../../models/vehicle'
import { Publication } from './../../models/publication';
import { UserService } from './../../services/user/user.service';

@Component({
  selector: 'app-publication-list',
  templateUrl: './publication-list.component.html',
  styleUrls: ['./publication-list.component.css']
})
export class PublicationListComponent implements OnInit {

	profile: User;
	publication: Publication = null;
  currentP = 1;

  constructor(
    private usersService: UserService
  ) {}

  ngOnInit() {
  	this.usersService.read(JSON.parse(localStorage.getItem('id'))).subscribe(
      data => this.profile = data.body
    );
  }

  public publicationSelected(): boolean {
  	return this.publication != null; 
  }

  public seePublication(publication: Publication): void {
  	this.publication = publication; 
  }

  public hasImages(vehicle: Vehicle): boolean {
    return vehicle.pictures? vehicle.pictures.length > 0: false;
  }

  public showDate(date: number): Date{
    return new Date(date);
  }
}
