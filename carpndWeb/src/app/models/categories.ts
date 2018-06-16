export abstract class Category {
	public abstract getName(): string;
}

export class Car extends Category {
	
	public getName(): string{
		return "Car";
	}
}

export class Scooter extends Category {
	
	public getName(): string{
		return "Scooter";
	}
}
