package com.HospitalManagementSystem.hospital_management_system.services;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebHookService {

	private final RestTemplate restTemplate;
	
	public WebHookService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	public void sendWebHook(String url, Map<String,Object> payload) {
		restTemplate.postForObject(url,payload,String.class);
	}
	
}
