package com.example.labb3smhi.repositorymet;

import com.example.labb3smhi.Handlers.METHandler;
import com.example.labb3smhi.Handlers.SMHIHandler;
import com.example.labb3smhi.MET.Met;
import com.example.labb3smhi.REST.METRestClient;
import com.example.labb3smhi.REST.SMHIRestClient;
import com.example.labb3smhi.SMHI.Smhi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryMET {

    METHandler metHandler = new METHandler();
    METRestClient metRestClient = new METRestClient();
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonStringMet;
    Met met;
    {
        try {
            jsonStringMet = metRestClient.getAllWeatherDataMET();
            met = objectMapper.readValue(jsonStringMet, Met.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public Double getTemperatureMET(){
        return metHandler.findTemperature24HoursFromNow(met);
    }
    public Integer getPrecipitationMET(){
        return metHandler.findPrecipitationAmount24HoursFromNow(met);
    }
    public Double getWindSpeedMET(){
        return metHandler.findWindSpeed24HoursFromNow(met);
    }
}
