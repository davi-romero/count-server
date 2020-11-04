package prep.count.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import prep.count.service.CountMessageService;
import prep.count.service.CountService;

@RestController
@RequestMapping("/count")
public class CountRestController {
	
	@Autowired
	private CountService countService;
	
	@Autowired
	private CountMessageService countMessageService;
	
	@PostMapping("/{requestId}")
	@ResponseStatus(value = HttpStatus.OK)
	public void increment(@PathVariable(required = true) long requestId) {
		countMessageService.sendAggregateMessage(requestId);
	}
	
	@GetMapping("/{requestId}")
	public int getRequestCount(@PathVariable(required = true) long requestId) {
		return countService.getRequestCount(requestId);
	}

}
