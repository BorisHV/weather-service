package com.example.labb3smhi.REST;

import com.example.labb3smhi.MET.Met;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class METRestClient {

    private ObjectMapper objectMapper = new ObjectMapper();
    private METRestClient metRestClient = new METRestClient();
    String uri = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=59.3110&lon=18.0300";

    public String getAllWeatherDataMET() throws JsonProcessingException {
        getMETJsonData();
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
    public void getMETJsonData() throws JsonProcessingException {
        String jsonString = metRestClient.getAllWeatherDataMET();
        Met met = objectMapper.readValue(jsonString, Met.class);
    }
}
