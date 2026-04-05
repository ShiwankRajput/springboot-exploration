package com.ShivnexEngineering.journalApp.externalApi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class WeatherResponse {

    private Current current;

    @Getter
    @Setter
    @ToString
    public static class Current{
        private int temperature;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;

        @JsonProperty("wind_speed")
        private int windSpeed;

        private int humidity;

        private int feelslike;

        @JsonProperty("is_day")
        private String isDay;
    }

}



