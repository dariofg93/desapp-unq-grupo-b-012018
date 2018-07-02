import { RequestsCorcernPayload } from './../../models/dtos/requests-corcern-payload';

export class InitBySeller extends RequestsCorcernPayload{
	
	constructor(userId: number, requestId: number){
		super(userId,requestId, 0);
	}
}
