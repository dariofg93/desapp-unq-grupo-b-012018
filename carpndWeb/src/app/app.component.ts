import { Component } from '@angular/core';
import { AuthService } from './services/auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [
    { provide: 'i18nInstance', useValue: 'en-US' }
  ]
})
export class AppComponent {
	title = 'Carpnd';

	constructor(public auth: AuthService) {
    auth.handleAuthentication();
  }

  ngOnInit() {}
}