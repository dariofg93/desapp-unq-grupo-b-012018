import { Component, OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import { } from '@types/googlemaps';

@Component({
  selector: 'app-google-map',
  templateUrl: './google-map.component.html',
  styleUrls: ['./google-map.component.css']
})
export class GoogleMapComponent implements OnInit {

  @ViewChild('gmap') gmapElement: any;

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
      this.placeMarker(ev.latLng, map);
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
