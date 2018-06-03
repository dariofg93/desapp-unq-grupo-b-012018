import { Component, OnInit } from '@angular/core';

import { AuthService } from './../../services/auth/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  profile: any;

  constructor(public auth: AuthService) {}

  ngOnInit() {
    this.auth.getProfile((err, profile) => {
      this.profile = profile;
    });
  }
}
