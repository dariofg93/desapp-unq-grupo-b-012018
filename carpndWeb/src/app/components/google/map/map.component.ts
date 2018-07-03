import { Component, OnInit, OnChanges, Input, SimpleChanges } from '@angular/core';
import { ViewChild } from '@angular/core';
import {} from '@types/googlemaps';

import { GeographicZoneDescription } from './../../../models/geographic-zone-description';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit,OnChanges {

  @ViewChild('gmap') gmapElement: any;
  @Input('puntos') points: GeographicZoneDescription[];
  @Input('view') isViewed: boolean;
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
    /*------------------------ -------- -----------------------------------*/


    this.map.addListener('click', function(ev) {
      console.log("gato");
      placeMarker(ev.latLng, this.map);
    });

    function placeMarker(latLng, mapChanged) {
      if(markerTouches%2 == 0){
        //if(pickUpMarker != null) 
          //pickUpMarker.setMap(null);

        pickUpMarker = new google.maps.Marker({
          position: latLng,
          map: mapChanged,
          title: 'Pick up'
        });

        //if(pickDownMarker != null) 
          //pickDownMarker.setMap(null);
      }else{
        pickDownMarker = new google.maps.Marker({
          position: latLng,
          map: mapChanged,
          title: 'Pick down'
        });
      }

      markerTouches++;
    }
  }

  ngOnChanges(changes: SimpleChanges): void{
    if(this.map && changes.points.currentValue.length > 0 && !this.printPoints) {
      changes.points.currentValue.forEach(point => this.addPoint(point,this.map, this.isViewed));
      this.printPoints = true; 
    }
  }

  addPoint(point: GeographicZoneDescription, mapChange: google.maps.Map, isViewed: boolean){
    var pickUpMarker = new google.maps.Marker({
      position: new google.maps.LatLng(point.latitud, point.longitud),
      map: mapChange,
      title: 'Pick up'
    });
  }
}
