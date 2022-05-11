package com.example.labb3smhi;

import com.example.labb3smhi.JsonHandler.JsonHandler;
import com.example.labb3smhi.REST.SMHIRestClient;
import com.example.labb3smhi.SMHI.Smhi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class Labb3SmhiApplication {

    public static void main(String[] args) throws JsonProcessingException, ParseException {
        SpringApplication.run(Labb3SmhiApplication.class, args);

        SMHIRestClient smhiRestClient = new SMHIRestClient();

        String jsonString = smhiRestClient.getAllWeatherData();
        ObjectMapper objectMapper = new ObjectMapper();
        Smhi smhi = objectMapper.readValue(jsonString, Smhi.class);

        JsonHandler jsonHandler = new JsonHandler();

        System.out.println(jsonHandler.findTemperature24HoursFromNow(smhi));
    }


}
