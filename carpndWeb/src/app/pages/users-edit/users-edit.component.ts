import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { Router } from "@angular/router";

import { User } from './../../models/user'
import { UserService } from './../../services/user/user.service';

@Component({
  selector: 'app-users-edit',
  templateUrl: './users-edit.component.html',
  styleUrls: ['./users-edit.component.css']
})
export class UsersEditComponent implements OnInit {

	profile: User;

  constructor(
  	private usersService: UserService,
  	private router: Router,
  	private location: Location,
  ) {}

  ngOnInit() {
    this.usersService.read(JSON.parse(localStorage.getItem('id'))).subscribe(
      data => this.profile = data.body
    );
  }

  return() {
    this.location.back();
  }

  saveProfile(form) {
    this.usersService.update(this.profile.id,this.profile);
    this.router.navigate(['']);
  }
}
