package com.example.labb3smhi;

import com.example.labb3smhi.MET.Met;
import com.example.labb3smhi.Handlers.METHandler;
import com.example.labb3smhi.REST.METRestClient;
import com.example.labb3smhi.REST.SMHIRestClient;
import com.example.labb3smhi.Handlers.SMHIHandler;
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

        SMHIHandler smhiHandler = new SMHIHandler();
        SMHIRestClient smhiRestClient = new SMHIRestClient();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStringSmhi = smhiRestClient.getAllWeatherDataSMHI();
        Smhi smhi = objectMapper.readValue(jsonStringSmhi, Smhi.class);

        System.out.println(smhiHandler.findTemperature24HoursFromNow(smhi));

        //1. Hämta data = klart.
        //2. Definiera var lagringen sker, lista eller db.
        //3. Med db behöver vi en dao/repo, nu ska vi spara datan i dao:n

        //Där vi har smhi-instansen, det är vår dao.

//
//
//        METHandler metHandler = new METHandler();
//
//        SMHIHandler smhiHandler = new SMHIHandler();
//
//        System.out.println(metHandler.findTemperature24HoursFromNow(met));
//
//        System.out.println(metHandler.findPrecipitationAmount24HoursFromNow(met));
//
//        System.out.println(metHandler.findWindSpeed24HoursFromNow(met));
//
//        System.out.println("smhi starts here");
//
//        System.out.println(smhiHandler.findWindSpeed24HoursFromNow(smhi));
//
//        System.out.println(smhiHandler.findPrecipitation24HoursFromNow(smhi));
//
//        System.out.println(smhiHandler.findTemperature24HoursFromNow(smhi));

    }
}
