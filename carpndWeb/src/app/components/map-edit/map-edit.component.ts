import { Component, OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import {} from '@types/googlemaps';

import { GeographicZoneDescription } from './../../models/geographic-zone-description';

@Component({
  selector: 'app-map-edit',
  templateUrl: './map-edit.component.html',
  styleUrls: ['./map-edit.component.css']
})
export class MapEditComponent implements OnInit {

  @ViewChild('gmap') gmapElement: any;
  map: google.maps.Map;

  ngOnInit() {
    /*------------------------ Init Map -----------------------------------*/
    var markerTouches: number = 0;
    var pickUpMarker: google.maps.Marker = null;
    var pickDownMarker: google.maps.Marker = null;

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

        localStorage.setItem('PickUp', JSON.stringify(pickUpMarker.getPosition()))

        if(pickDownMarker != null) 
          pickDownMarker.setMap(null);
      }else{
        pickDownMarker = new google.maps.Marker({
          position: latLng,
          map: mapChanged,
          title: 'Pick down'
        });

        localStorage.setItem('PickDown', JSON.stringify(pickDownMarker.getPosition()))
      }

      markerTouches++;
    }
  }
}