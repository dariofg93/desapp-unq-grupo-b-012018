import { Component } from '@angular/core';
import { AuthService } from './services/auth/auth.service';
import { setTheme } from 'ngx-bootstrap/utils';

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
    setTheme('bs4');
  }

  ngOnInit() {}
}