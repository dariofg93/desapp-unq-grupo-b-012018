import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

import { AuthService } from './../../../services/auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  languages;
  profile: any;

  constructor(
    public auth: AuthService,
    private translate: TranslateService
  ) {
    var language = localStorage.getItem('language');
    translate.setDefaultLang(language? language: 'en-US');
  }
  
  ngOnInit() {
    this.languages = [
      {i18n: 'en-US', name: 'English'},
      {i18n: 'es-AR', name: 'Spanish'}
    ];
    this.auth.getProfile((err, profile) => {
      this.profile = profile;
    });
  }

  changeLanguage(i18n: string): void{
    if(i18n != undefined){
      localStorage.setItem('language', i18n);
      this.translate.use(i18n);
      //location.reload();
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

  switchLanguage(language: string) {
    this.translate.use(language);
  }
}
