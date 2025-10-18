package com.WebHook.web_hookDemo;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebHookController {

	@PostMapping("/webhook")
	public ResponseEntity<String> handleWebHookMethod(@RequestBody(required=false) Map<String,Object> payload){
		
		System.out.println("Webhook recieved : " + payload);
		
		// Process the Login you want to perform after webHook received.
		
		return ResponseEntity.ok("Webhook recieved and Processed");
				
	}
	
}
