package prep.count.service;

public interface CountService {

	void increment(long requestId);
	
	int getRequestCount(long requestId);
	
}
