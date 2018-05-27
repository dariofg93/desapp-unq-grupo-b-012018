import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  languages;
  selectedItem;
  i18nValue: string;

  constructor(
  	private router: Router
  ) { }

  ngOnInit() {
    this.languages = [
      {i18n: 'en-US', name: 'English'},
      {i18n: 'es-AR', name: 'Spanish'}
    ]
  }

  changeLanguage(): void{
    if(this.i18nValue != undefined){
    	this.router.navigateByUrl(`${window.location.pathname}?language=${this.i18nValue}`);
    	location.reload();
    }
  }

  setSelectedItem(newItem) {
    this.selectedItem = newItem;
  }
}
