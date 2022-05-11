package com.example.labb3smhi;

import com.example.labb3smhi.MET.Met;
import com.example.labb3smhi.METHandler.METHandler;
import com.example.labb3smhi.REST.METRestClient;
import com.example.labb3smhi.REST.SMHIRestClient;
import com.example.labb3smhi.SMHIHandler.SMHIHandler;
import com.example.labb3smhi.SMHI.Smhi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;

@SpringBootApplication
public class Labb3SmhiApplication {

    public static void main(String[] args) throws JsonProcessingException, ParseException {
        SpringApplication.run(Labb3SmhiApplication.class, args);

        METRestClient metRestClient = new METRestClient();
        SMHIRestClient smhiRestClient = new SMHIRestClient();

        String jsonString = metRestClient.getAllWeatherDataMET();
        ObjectMapper objectMapper = new ObjectMapper();
        Met met = objectMapper.readValue(jsonString, Met.class);

        String jsonStringSmhi = smhiRestClient.getAllWeatherDataSMHI();

        Smhi smhi = objectMapper.readValue(jsonStringSmhi, Smhi.class);


        METHandler metHandler = new METHandler();

        SMHIHandler smhiHandler = new SMHIHandler();

        System.out.println(metHandler.findTemperature24HoursFromNow(met));

        System.out.println(metHandler.findPrecipitationAmount24HoursFromNow(met));

        System.out.println(metHandler.findWindSpeed24HoursFromNow(met));

        System.out.println(smhiHandler.findWindSpeed24HoursFromNow(smhi));


//        System.out.println(METHandler.findTemperature24HoursFromNow(met));
//
//        System.out.println(METHandler.findPrecipitation24HoursFromNow(smhi));
//
//        System.out.println(METHandler.findThunderProbability24HoursFromNow(smhi));


//        SMHIRestClient smhiRestClient = new SMHIRestClient();
//
//        String jsonString = smhiRestClient.getAllWeatherDataSMHI();
//        ObjectMapper objectMapper = new ObjectMapper();
//        Smhi smhi = objectMapper.readValue(jsonString, Smhi.class);
//
//        SMHIHandler SMHIHandler = new SMHIHandler();
//
//        System.out.println(SMHIHandler.findTemperature24HoursFromNow(smhi));
//
//        System.out.println(SMHIHandler.findPrecipitation24HoursFromNow(smhi));
//
//        System.out.println(SMHIHandler.findThunderProbability24HoursFromNow(smhi));
    }
}
