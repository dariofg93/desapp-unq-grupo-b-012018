import { AUTHCALLBACKURL } from './../../../environments/environment';
import { UserService } from './../../services/user/user.service';
import { User } from './../../models/user'

import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { filter } from 'rxjs/operators';
import * as auth0 from 'auth0-js';

(window as any).global = window;

@Injectable()
export class AuthService {

  profile: User

  auth0 = new auth0.WebAuth({
    clientID: 'JZ5acUq2z40GWlsUFckSq8QL9U1UUfpl',
    domain: 'carpnd-b.auth0.com',
    responseType: 'token id_token',
    audience: 'https://carpnd-b.auth0.com/userinfo',
    redirectUri: AUTHCALLBACKURL,
    scope: 'openid profile'
  });

  userProfile: any;

  constructor(
    public router: Router,
    private usersService: UserService
  ) {}

  public login(): void {
    this.auth0.authorize();
  }

  public handleAuthentication(): void {
    this.auth0.parseHash((err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken) {
        window.location.hash = '';
        this.setSession(authResult);
        this.router.navigate(['/']);
      } else if (err) {
        //this.router.navigate(['/']);
        //console.log(err);
        //alert(`Error: ${err.error}. Check the console for further details.`);
      }
    });
  }

  private setSession(authResult): void {
    // Set the time that the access token will expire at
    const expiresAt = JSON.stringify((authResult.expiresIn * 1000) + new Date().getTime());
    localStorage.setItem('access_token', authResult.accessToken);
    localStorage.setItem('id_token', authResult.idToken);
    localStorage.setItem('expires_at', expiresAt);

    this.getProfile((err, profile) => {
      this.usersService.selectByEmail(profile.nickname + "@gmail.com").subscribe(
        data => data.statusCode == "OK"? 
                  localStorage.setItem('id', JSON.stringify(data.body.id)):
                  this.usersService.create(new User(profile.nickname + "@gmail.com")).subscribe(
                    createdUser => localStorage.setItem('id', JSON.stringify(createdUser.body.id))
                  ),
        error => console.log(error)
      );
    });
  }

  public logout(): void {
    // Remove tokens and expiry time from localStorage
    localStorage.removeItem('access_token');
    localStorage.removeItem('id_token');
    localStorage.removeItem('expires_at');
    localStorage.removeItem('navbar_item');
    localStorage.removeItem('id');
    // Go back to the home route
    this.router.navigate(['login']);
  }

  public isAuthenticated(): boolean {
    // Check whether the current time is past the
    // access token's expiry time
    const expiresAt = JSON.parse(localStorage.getItem('expires_at'));
    return new Date().getTime() < expiresAt;
  }

  public getProfile(cb): void {
    const accessToken = localStorage.getItem('access_token');
    if (!accessToken) {
      throw new Error('Access Token must exist to fetch profile');
    }

    this.auth0.client.userInfo(accessToken, (err, profile) => {
      if (profile) {
        this.profile = profile;
      }
      cb(err, profile);
    });
  }

}