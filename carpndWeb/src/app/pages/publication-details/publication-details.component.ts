import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { ActivatedRoute } from "@angular/router";

import { Vehicle } from './../../models/vehicle'
import { Publication } from './../../models/publication';
import { DateService } from './../../services/date/date.service';
import { PublicationService } from './../../services/publication/publication.service';

@Component({
  selector: 'app-publication-details',
  templateUrl: './publication-details.component.html',
  styleUrls: ['./publication-details.component.css']
})
export class PublicationDetailsComponent implements OnInit {

	publication: Publication = null;

  constructor(
  	private activatedRoute: ActivatedRoute,
  	private location: Location,
    private publicationsService: PublicationService,
    private dateService: DateService
  ) {}

  ngOnInit() {
  	this.publicationsService.read(this.activatedRoute.snapshot.params.id).subscribe(
      data => this.publication = data.body
    );
  }

  hasPublication(): boolean {
    return this.publication != null;
  }

  hasImages(vehicle: Vehicle): boolean {
    return vehicle.pictures? vehicle.pictures.length > 0: false;
  }

  request() {
    console.log(this.publication);
  }

  return() {
    this.location.back();
  }
}
