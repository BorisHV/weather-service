package com.example.labb3smhi.repository;

import com.example.labb3smhi.Handlers.SMHIHandler;
import com.example.labb3smhi.REST.SMHIRestClient;
import com.example.labb3smhi.SMHI.Smhi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Repository {

    SMHIHandler smhiHandler = new SMHIHandler();
    SMHIRestClient smhiRestClient = new SMHIRestClient();
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonStringSmhi = smhiRestClient.getAllWeatherDataSMHI();
    Smhi smhi;

    {
        try {
            smhi = objectMapper.readValue(jsonStringSmhi, Smhi.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public List<Double> getSmhi(){
        return smhiHandler.findTemperature24HoursFromNow(smhi);
    }
}
