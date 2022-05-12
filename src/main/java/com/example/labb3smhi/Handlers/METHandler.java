package com.example.labb3smhi.Handlers;

import com.example.labb3smhi.MET.Met;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class METHandler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    int sizeOfTimeSeriesArrayList = 80;

    public Double findTemperature24HoursFromNow(Met met) {

        String timeFormatted = ZonedDateTime.now()
                .minusSeconds(ZonedDateTime.now().getSecond())
                .minusMinutes(ZonedDateTime.now().getMinute()).plusHours(25).format(formatter);

        for (int i = 0; i < sizeOfTimeSeriesArrayList; i++) {
            String replaceValidTime = met.getProperties().getTimeseries().get(i)
                    .getTime().replace("T", " ")
                    .replace("Z", "");

            if (replaceValidTime.contentEquals(timeFormatted)) {
                return met.getProperties().getTimeseries().get(i).getData()
                        .getInstant().getDetails().getAirTemperature();

            }
        }
        return null;
    }
    public Integer findPrecipitationAmount24HoursFromNow(Met met) {

        String timeFormatted = ZonedDateTime.now()
                .minusSeconds(ZonedDateTime.now().getSecond())
                .minusMinutes(ZonedDateTime.now().getMinute()).plusHours(25).format(formatter);

        for (int i = 0; i < sizeOfTimeSeriesArrayList; i++) {
            String replaceValidTime = met.getProperties().getTimeseries().get(i)
                    .getTime().replace("T", " ")
                    .replace("Z", "");

            if (replaceValidTime.contentEquals(timeFormatted)) {
                return met.getProperties().getTimeseries().get(i).getData()
                        .getNext6Hours().getDetails().getPrecipitationAmount();

            }
        }
        return null;
    }
    public Double findWindSpeed24HoursFromNow(Met met) {

        String timeFormatted = ZonedDateTime.now()
                .minusSeconds(ZonedDateTime.now().getSecond())
                .minusMinutes(ZonedDateTime.now().getMinute()).plusHours(25).format(formatter);

        for (int i = 0; i < sizeOfTimeSeriesArrayList; i++) {
            String replaceValidTime = met.getProperties().getTimeseries().get(i)
                    .getTime().replace("T", " ")
                    .replace("Z", "");

            if (replaceValidTime.contentEquals(timeFormatted)) {
                return met.getProperties().getTimeseries().get(i).getData().getInstant().getDetails().getWindSpeed();

            }
        }
        return null;
    }
}
