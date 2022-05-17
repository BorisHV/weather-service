package com.example.labb3smhi.REST;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class OpenWeatherRestClient {

    String uri = "https://api.openweathermap.org/data/2.5/onecall?lat=59.3110&lon=18.0300&units=metric&exclude=minutely&appid=12f0b17b028dc893417b2b1b1ad9ac79";

    public String getAllWeatherDataOpenWeather(){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> weather = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);

        if(weather.getStatusCode() != HttpStatus.OK){
            throw new RuntimeException("getAllWeatherDataOpenWeather failed");
        }
        return weather.getBody().toString();
    }
}
