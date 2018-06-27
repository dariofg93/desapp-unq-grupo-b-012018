export class BookingState {
	concretType: string;
	description: string
	id: number;

	constructor(concretType: string){
		this.concretType = concretType;
	}
}