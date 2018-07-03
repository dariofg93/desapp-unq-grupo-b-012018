import { Component, OnInit, OnChanges, Input, SimpleChanges } from '@angular/core';
import { ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {} from '@types/googlemaps';

import { Publication } from './../../../models/publication';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit,OnChanges {

  @ViewChild('gmap') gmapElement: any;
  @Input('puntos') points: Publication[];
  @Input('view') isViewed: boolean;
  @Input('rout') router: Router;
  map: google.maps.Map;
  printPoints: boolean = false;


  ngOnInit() {
    /*------------------------ Init Map -----------------------------------*/
    var markerTouches = 0;
    var pickUpMarker = null;
    var pickDownMarker = null;

    var mapProp = {
      center: new google.maps.LatLng(-34.70655181101466, -58.27852249145508),
      zoom: 10,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    this.map = new google.maps.Map(this.gmapElement.nativeElement, mapProp);
  }

  ngOnChanges(changes: SimpleChanges): void{
    if(this.map && changes.points.currentValue.length > 0 && !this.printPoints) {
      console.log(changes);
      changes.points.currentValue.forEach(point => this.addPoint(point,this.map, this.isViewed, this.router));
      this.printPoints = true; 
    }
  }

  addPoint(point: Publication, mapChange: google.maps.Map, isViewed: boolean, router: Router){
    var markerUp = new google.maps.Marker({
      position: new google.maps.LatLng(point.pickUpZone.latitud, point.pickUpZone.longitud),
      map: mapChange,
      title: 'Pick up'
    });

    if (isViewed) {
      var markerDown = new google.maps.Marker({
        position: new google.maps.LatLng(point.dropZone.latitud, point.dropZone.longitud),
        map: mapChange,
        title: 'Pick Down'
      });
    }else{
      markerUp.addListener('click', function() {
        router.navigate(['/publications/' + point.id]);
      });
    }
  }
}
