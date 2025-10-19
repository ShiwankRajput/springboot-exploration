package com.SmartEmailAssistant.AI_Email_Assistant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class EmailGeneratorService {
	
	private final WebClient webClient;
	private final String apiKey;

	public EmailGeneratorService(WebClient.Builder webClientBuilder,
			@Value("${gemini.api.url}") String baseURL,
			@Value("${gemini.api.apiKey}") String geminiApiKey) {
		
		this.apiKey = geminiApiKey;
		this.webClient = webClientBuilder.baseUrl(baseURL).build();
		
	}

	public String generateEmailReply(EmailRequest emailRequest) {
		
		// build prompt
		String prompt = buildPrompt(emailRequest);
		
		// prepare raw JSON body
		String requestBody = String.format("""
				
				{
						"contents": [
						    {
						      "parts": [
						        {
						          "text": "%s"
						        }
						      ]
						    }
						  ]
				}
				
				""", prompt);
		
		// send request : Use Web client Spring and Spring Reactive web dependency
		String response = webClient.post()
				.uri(uriBuilder -> uriBuilder
						.path("/v1beta/models/gemini-2.5-flash:generateContent")
						.build())
				.header("x-goog-api-key", apiKey)
				.header("Content-Type", "application/json")
				.bodyValue(requestBody)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		// extract response
		return extractResponseContext(response);
		
	}

	private String buildPrompt(EmailRequest emailRequest) {
		
		StringBuilder prompt = new StringBuilder();
		
		prompt.append("Generate a Proffesional email reply for the following email : ");
		
		if(emailRequest.getTone() != null && !emailRequest.getTone().isEmpty()) {
			// Use a Casual Tone
			prompt.append("Use a ").append(emailRequest.getTone()).append(" tone.");
		}
		
		prompt.append("Original Email : \n").append(emailRequest.getEmailContent());
		
		return prompt.toString();
	}
	
	private String extractResponseContext(String response) {
		
		// response will be in JSON format so use ObjectMapper for java Object
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode root = mapper.readTree(response);
			return root.path("candidates")
					.get(0)
					.path("content")
					.path("parts")
					.get(0)
					.path("text")
					.asText();
					
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
}
