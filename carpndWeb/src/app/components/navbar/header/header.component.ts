import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from './../../../services/auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  languages;
  selectedItem;
  i18nValue: string;
  profile: any;

  constructor(
    private router: Router,
    private auth: AuthService
  ) { }
  
  ngOnInit() {
    this.languages = [
      {i18n: 'en-US', name: 'English'},
      {i18n: 'es-AR', name: 'Spanish'}
    ];
    this.auth.getProfile((err, profile) => {
      this.profile = profile;
    });
  }

  changeLanguage(): void{
    if(this.i18nValue != undefined){
      localStorage.setItem('language', this.i18nValue);
      location.reload();
    }
  }

  setSelectedItem(newItem) {
    this.selectedItem = newItem;
  }
}
