import { Injectable,Component } from '@angular/core';

@Injectable()
export class AlertService {

  alert = { 
    isVisible : false,
    message : ''
  };

  constructor() { }

  show(message){
    console.log('is visible');
    this.alert.isVisible = true;
  }

  hide(){
    this.alert.isVisible = false;
  }

}