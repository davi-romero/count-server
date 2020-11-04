package prep.count.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import prep.count.dto.RequestCount;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class CountAggregatorServiceBean implements CountAggregatorService {

	@Autowired
	private CountMessageService countMessageService;
	
	private Map<Long, Integer> counter = new ConcurrentHashMap<>();
	
	@Override
	public void aggregate(long requestId) {
		int count = counter.getOrDefault(requestId, 0) + 1;
		counter.put(requestId, count);
	}
	
	@Scheduled(fixedDelay = 5000)
	public void save() {
		if (counter.isEmpty()) {
	    	return;
	    }
		Map<Long, Integer> aux = new HashMap<>(this.counter);
		this.counter.clear();	    
	    aux.keySet().stream().forEach(p -> {
	    	System.out.println("sending: " + p + "(" + aux.get(p) + ")");
	    	countMessageService.sendSaveMessage(new RequestCount(p, aux.get(p)));
	    });
	}

}
