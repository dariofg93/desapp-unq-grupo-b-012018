import { Component } from '@angular/core'; 

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [
    { provide: 'i18nInstance', useValue: 'es-AR' }
  ]
})
export class AppComponent {
	title = 'Carpnd';

  ngOnInit() {}
}