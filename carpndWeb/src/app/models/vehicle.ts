import { Category } from './../models/categories';

export class Vehicle {
	id: number;
  description: string;
  passengerCapacity: number;
  category: Category;
  pictures: string[];

  constructor(){
  	this.pictures = [];
  }
}