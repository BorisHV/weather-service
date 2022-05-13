package com.example.labb3smhi.REST;


import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


public class SMHIRestClient {

    String uri = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json";

    public String getAllWeatherDataSMHI(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> weather = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);

        if(weather.getStatusCode() != HttpStatus.OK){
            throw new RuntimeException("getAllWeatherDataSMHI failed");
        }
        return weather.getBody().toString();
    }

}
