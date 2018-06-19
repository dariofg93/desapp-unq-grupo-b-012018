
export abstract class Category {

	name : String;
	public getName(){
		return name;
	}
}

export class Car extends Category {
	
	constructor(){
		super();
		this.name = "Car";
	}

}

export class Scooter extends Category {
		
	constructor(){
		super();
		this.name = "Scooter";
	}
	

}
