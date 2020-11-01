package prep.count.service;

public interface CountService {

	void increment(long requestId);
	
	int getRequestCount(long requestId);
	
	void aggregateCount(long requestId, int maxResults);
	
}
