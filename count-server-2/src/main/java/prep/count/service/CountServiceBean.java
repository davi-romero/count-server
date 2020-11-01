package prep.count.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
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
		requestRepository.save(new Request(requestId));
	}
	
	public int getRequestCount(long requestId) {
		return requestRepository.countByRequestId(requestId);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public void aggregateCount(long requestId, int maxResults) {
		Pageable pageable = PageRequest.of(0, maxResults);
		List<Request> list = requestRepository.findByRequestId(requestId, pageable);
		if (list.isEmpty()) {
			// nothing to aggregate
			return;
		}
		List<Long> ids = list.stream().mapToLong(p -> p.getId()).boxed().collect(Collectors.toList());
		int count = list.stream().mapToInt(p -> p.getCount()).sum();
		System.out.println("aggregateCount [requestId=" + requestId + ", count=" + count + "]");
		requestRepository.delete(ids);
		requestRepository.save(new Request(requestId, count));
	}

}
