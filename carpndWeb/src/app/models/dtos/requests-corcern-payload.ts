export class RequestsCorcernPayload {

	userId: number;
	requestId: number;
	scoreValue: number;

	constructor(userId: number, requestId: number, scoreValue: number){
		this.userId = userId;
		this.requestId = requestId;
		this.scoreValue = scoreValue;
	}


}
