import { HistoryRecord } from './../models/history-record';

export class MovementsOfMonth {
	id: number
	history: HistoryRecord[];

	constructor(){
		this.history = [];
	}
}
