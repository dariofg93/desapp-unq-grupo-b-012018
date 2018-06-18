import { Component, OnInit } from '@angular/core';

import { AuthService } from './../../../services/auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  languages;
  i18nValue: string;
  profile: any;

  constructor(
    public auth: AuthService
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
    localStorage.setItem('navbar_item', newItem);
  }

  selectedItem(): number {
    return JSON.parse(localStorage.getItem('navbar_item'));
  }

  id(): number{
    return JSON.parse(localStorage.getItem('id'));
  }
}
