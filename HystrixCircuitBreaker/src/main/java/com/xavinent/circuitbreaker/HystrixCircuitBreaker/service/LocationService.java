package com.xavinent.circuitbreaker.HystrixCircuitBreaker.service;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
/**
 * 
 * @author MD Shahid
 *
 */
@Service
public class LocationService {
	
	private final RestTemplate restTemplate;

	  public LocationService(RestTemplate rest) {
	    this.restTemplate = rest;
	  }

	  @HystrixCommand(fallbackMethod = "recover" )
	  public User readingList() {
	    URI uri = URI.create("http://localhost:1234/getUser/shahid@gmail.com/");
	    User user = this.restTemplate.getForObject(uri, User.class);
	    return user;
	  }

	  public User recover() {
		  User u = new User();
		  u.setRole("ADM");
	    return u;
	  }

}
