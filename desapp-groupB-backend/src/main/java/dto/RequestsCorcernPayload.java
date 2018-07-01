package dto;

public class RequestsCorcernPayload {
	
	private Long  userId;
	private Long requestId;

	public Long  getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getRequestId() {
		return this.requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
}
