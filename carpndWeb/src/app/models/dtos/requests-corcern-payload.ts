export class RequestsCorcernPayload {

	userId: number;
	requestId: number;

	constructor(userId: number, requestId: number){
		this.userId = userId;
		this.requestId = requestId;
	}
}
