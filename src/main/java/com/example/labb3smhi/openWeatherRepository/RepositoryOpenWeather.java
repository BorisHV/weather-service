package com.example.labb3smhi.openWeatherRepository;

import com.example.labb3smhi.Handlers.OpenWeatherHandler;
import com.example.labb3smhi.REST.OpenWeatherRestClient;
import com.example.labb3smhi.openweather.OpenWeather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;


@Repository
public class RepositoryOpenWeather {


    OpenWeatherHandler openWeatherHandler = new OpenWeatherHandler();
    OpenWeatherRestClient openWeatherRestClient = new OpenWeatherRestClient();
    ObjectMapper objectMapper = new ObjectMapper();
    OpenWeather openWeather;

    {
        convertJsonToJavaOpenWeather();
    }

    private void convertJsonToJavaOpenWeather() {
        try {
            String jsonStringOpenWeather = openWeatherRestClient.getAllWeatherDataOpenWeather();
            openWeather = objectMapper.readValue(jsonStringOpenWeather, OpenWeather.class);
            System.out.println(jsonStringOpenWeather);
            System.out.println("Convert ");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    public Double getTemperatureOpenWeather() {
        return openWeatherHandler.findTemperature24HoursFromNow(openWeather);
    }

    public Long getPrecipitationOpenWeather() {
        return openWeatherHandler.findPrecipitationAmount24HoursFromNow(openWeather);
    }

    public Double getWindSpeedOpenWeather() {
        return openWeatherHandler.findWindSpeed24HoursFromNow(openWeather);
    }
}


