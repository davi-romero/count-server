package prep.count.service;

public interface CountService {

	void increment(long requestId);
	
	void increment(long requestId, int count);
	
	int getRequestCount(long requestId);
	
}
