package prep.count.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import prep.count.repository.Request;
import prep.count.repository.RequestRepository;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CountServiceBean implements CountService {
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void increment(long requestId) {
		increment(requestId, 1);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void increment(long requestId, int count) {
		Optional<Request> optional = requestRepository.findById(requestId);
		Request request = optional.isPresent() ? optional.get() : new Request(requestId);
		request.increment(count);
		requestRepository.save(request);
	}
	
	public int getRequestCount(long requestId) {
		return requestRepository.getCount(requestId);
	}

}
