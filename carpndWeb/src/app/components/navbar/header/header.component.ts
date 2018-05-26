import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(
  	private router: Router
  ) { }

  ngOnInit() {
  }

  setLanguage(i18nValue: string): void{
  	this.router.navigateByUrl(`${window.location.pathname}?language=${i18nValue}`);
  	location.reload();
  }
}
