package com.example.labb3smhi;

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
//        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Smhi smhi = objectMapper.readValue(jsonString, Smhi.class);

//        System.out.println(smhiRestClient.getAllWeatherData());

        int indexI = 0;
        int indexJ = 0;

//        String input = LocalDateTime.now().toString();

        //"2022-05-11 10:00:00Z"

        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeFormatted = ZonedDateTime.now().minusSeconds(ZonedDateTime.now().getSecond()).minusMinutes(ZonedDateTime.now().getMinute()).plusHours(24).format(FORMATTER);


//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMDDThhmmssZ");
//
//        LocalDateTime localDateTime = LocalDateTime.parse(input,formatter);
//
//        ZonedDateTime zoneDateTime = localDateTime.atZone(ZoneId.of("GMT+"));
//
//        System.out.println(zoneDateTime);
//
//        ZonedDateTime gmtDateTime = zoneDateTime.withZoneSameInstant(ZoneOffset.of("GMT+"));
//
//        System.out.println(gmtDateTime);


//        System.out.println(smhi.getTimeSeries().get(0).getValidTime().replace("T", " "));

        for (int i = 0; i < 76; i++) {
            String replaceValidTime = smhi.getTimeSeries().get(i).getValidTime().replace("T", " ")
                    .replace("Z", "");
            if (replaceValidTime.contentEquals(timeFormatted)) {
                indexI = i;
                for (int j = 0; j < 18; j++) {
                    if (smhi.getTimeSeries().get(indexI).getParameters().get(j).getName().equals("t")) {
                        indexJ = j;
                        System.out.println(smhi.getTimeSeries().get(indexI).getParameters().get(indexJ).getValues());
                    }
                }
            }
        }
    }
}
