import { Category } from './../models/categories';
import { User } from './user';

export class Vehicle {
	id: number;
  description: string;
  passengerCapacity: number;
  category: Category;
  pictures: string[];
  owner: User;

  constructor(){
  	this.pictures = [];
  }
}