package com.ShivnexEngineering.journalApp.service;

import com.ShivnexEngineering.journalApp.externalApi.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class WeatherService {

    @Value("${apiKey}")
    private String apiKey;

    private static final String finalApi = "https://api.weatherstack.com/current?access_key=apiKey&query=city";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse weatherReport(String city){

        String url = finalApi.replace("apiKey", apiKey).replace("city", city);

        /*

        Example of [Post Request] to (external API) :-

            String stringBody = "{\n" +
                    "    \"userName\" : \"Shiwank Kumar\",\n" +
                    "    \"password\" : \"ShiwankKumar123456\"\n" +
                    "}";

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("key","value");

            HttpEntity<String> httpEntity = new HttpEntity<>(stringBody, httpHeaders);

            ResponseEntity<WeatherResponse> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, WeatherResponse.class);

            return response.getBody();


         */

        ResponseEntity<WeatherResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, WeatherResponse.class);

        return response.getBody();
    }

}
