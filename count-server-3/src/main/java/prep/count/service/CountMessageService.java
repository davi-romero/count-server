package prep.count.service;

import prep.count.dto.RequestCount;

public interface CountMessageService {
	
	void sendAggregateMessage(long requestId);
	
	void sendSaveMessage(RequestCount requestCount);

}
