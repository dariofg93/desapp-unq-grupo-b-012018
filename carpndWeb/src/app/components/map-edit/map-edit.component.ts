import { Component, OnInit, OnChanges, Input, SimpleChanges } from '@angular/core';
import { ViewChild } from '@angular/core';
import {} from '@types/googlemaps';

import { GeographicZoneDescription } from './../../models/geographic-zone-description';

@Component({
  selector: 'app-map-edit',
  templateUrl: './map-edit.component.html',
  styleUrls: ['./map-edit.component.css']
})
export class MapEditComponent implements OnInit,OnChanges {

  @ViewChild('gmap') gmapElement: any;
  map: google.maps.Map;

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

    var map = new google.maps.Map(this.gmapElement.nativeElement, mapProp);
    /*------------------------ -------- -----------------------------------*/


    map.addListener('click', function(ev) {
      placeMarker(ev.latLng, map);
    });

    function placeMarker(latLng, mapChanged) {
      if(markerTouches%2 == 0){
        if(pickUpMarker != null) 
          pickUpMarker.setMap(null);

        pickUpMarker = new google.maps.Marker({
          position: latLng,
          map: mapChanged,
          title: 'Pick up'
        });

        if(pickDownMarker != null) 
          pickDownMarker.setMap(null);
      }else{
        pickDownMarker = new google.maps.Marker({
          position: latLng,
          map: mapChanged,
          title: 'Pick down'
        });
      }

      markerTouches++;
      this.map = map;
    }
  }

  prints(){
    console.log("mapa",this.map);
  }

  ngOnChanges(changes: SimpleChanges): void{
    console.log(changes);
    console.log(this.map);
  }
}