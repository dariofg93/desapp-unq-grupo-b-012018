import { RequestsCorcernPayload } from './../../models/dtos/requests-corcern-payload';

export class FinishBySeller extends RequestsCorcernPayload{
	
	constructor(userId: number, requestId: number, scoreValue: number){
		super(userId,requestId, scoreValue);
	}
}
