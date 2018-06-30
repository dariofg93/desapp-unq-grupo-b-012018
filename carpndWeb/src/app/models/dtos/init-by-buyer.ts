import { RequestsCorcernPayload } from './../../models/dtos/requests-corcern-payload';

export class InitByBuyer extends RequestsCorcernPayload{
	
	constructor(userId: number, requestId: number){
		super(userId,requestId);
	}
}
