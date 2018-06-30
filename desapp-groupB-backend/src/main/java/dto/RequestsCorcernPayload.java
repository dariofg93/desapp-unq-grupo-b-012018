package dto;

public class RequestsCorcernPayload {
	
	private Integer userId;
	private Integer requestId;

	public Integer getUserId() {
		return this.userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getRequestId() {
		return this.requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
}
