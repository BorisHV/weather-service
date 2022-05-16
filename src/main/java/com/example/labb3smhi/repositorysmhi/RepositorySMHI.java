package com.example.labb3smhi.repositorysmhi;

import com.example.labb3smhi.Handlers.SMHIHandler;
import com.example.labb3smhi.REST.SMHIRestClient;
import com.example.labb3smhi.SMHI.Smhi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositorySMHI {

    SMHIHandler smhiHandler = new SMHIHandler();
    SMHIRestClient smhiRestClient = new SMHIRestClient();
    ObjectMapper objectMapper = new ObjectMapper();
    Smhi smhi;

    {
        convertJsonToJavaSMHI();
    }

    private void convertJsonToJavaSMHI() {
        String jsonStringSmhi = smhiRestClient.getAllWeatherDataSMHI();
        try {
            smhi = objectMapper.readValue(jsonStringSmhi, Smhi.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public List<Double> getTemperatureSMHI(){
        return smhiHandler.findTemperature24HoursFromNow(smhi);
    }
    public List<Double> getPrecipitationSMHI(){
        return smhiHandler.findPrecipitation24HoursFromNow(smhi);
    }
    public List<Double> getWindSpeedSMHI(){
        return smhiHandler.findWindSpeed24HoursFromNow(smhi);
    }
}
