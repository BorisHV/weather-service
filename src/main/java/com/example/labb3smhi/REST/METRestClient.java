package com.example.labb3smhi.REST;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpHeaders.USER_AGENT;

public class METRestClient {

    String uri = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=59.3110&lon=18.0300";

    public String getAllWeatherDataMET(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        httpHeaders.add("USER-AGENT", "hej");
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> weather = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
        if(weather.getStatusCode() != HttpStatus.OK){
            throw new RuntimeException("getAllWeatherDataSMHI failed");
        }
        return weather.getBody().toString();
    }
}
//RestTemplate rt= new RestTemplateBuilder()
// .defaultHeader(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_VALUE)
// .defaultHeader(HttpHeaders.USER_AGENT, "notJava!")
// .build();