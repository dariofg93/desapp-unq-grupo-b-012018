import { Component, OnInit,Input } from '@angular/core';
import { ViewChild } from '@angular/core';
import {} from '@types/googlemaps';

import { GeographicZoneDescription } from './../../../models/geographic-zone-description';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {

  @ViewChild('gmap') gmapElement: any;
  @Input() points: GeographicZoneDescription[];

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
    }
  }
}
