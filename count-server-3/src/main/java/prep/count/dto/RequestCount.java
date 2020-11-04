package prep.count.dto;

import java.io.Serializable;

import prep.count.repository.Request;

public class RequestCount implements Serializable {
	
	private static final long serialVersionUID = -1584931062849661589L;
	
	private long requestId;
	private int count;
	
	public RequestCount() {		
	}
	
	public RequestCount(long requestId, int count) {
		super();
		this.requestId = requestId;
		this.count = count;
	}

	public long getRequestId() {
		return requestId;
	}

	public int getCount() {
		return (int) count;
	}

	@Override
	public String toString() {
		return "RequestCount [requestId=" + requestId + ", count=" + count + "]";
	}

	public Request asRequest() {
		return new Request(requestId, count);
	}

}
