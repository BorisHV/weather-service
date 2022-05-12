package com.example.labb3smhi.Handlers;

import com.example.labb3smhi.SMHI.Smhi;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class SMHIHandler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    int sizeOfParametersArrayList = 18;
    int sizeOfTimeSeriesArrayList = 76;
    int indexI = 0;
    int indexJ = 0;

    public List<Double> findTemperature24HoursFromNow(Smhi smhi) {

        String timeFormatted = ZonedDateTime.now().minusSeconds(ZonedDateTime.now()
                .getSecond()).minusMinutes(ZonedDateTime.now().getMinute()).plusHours(25).format(formatter);

        for (int i = 0; i < sizeOfTimeSeriesArrayList; i++) {
            String replaceValidTime = smhi.getTimeSeries().get(i).getValidTime().replace("T", " ")
                    .replace("Z", "");
            if (replaceValidTime.contentEquals(timeFormatted)) {
                indexI = i;
                for (int j = 0; j < sizeOfParametersArrayList; j++) {
                    if (smhi.getTimeSeries().get(indexI).getParameters().get(j).getName().equals("t")) {
                        indexJ = j;

                        return smhi.getTimeSeries().get(indexI).getParameters().get(indexJ).getValues();
                    }
                }
            }
        }
        return null;
    }
    public List<Double> findPrecipitation24HoursFromNow(Smhi smhi) {

        String timeFormatted = ZonedDateTime.now().minusSeconds(ZonedDateTime.now().getSecond()).minusMinutes(ZonedDateTime.now().getMinute()).plusHours(25).format(formatter);

        for (int i = 0; i < sizeOfTimeSeriesArrayList; i++) {
            String replaceValidTime = smhi.getTimeSeries().get(i).getValidTime().replace("T", " ")
                    .replace("Z", "");
            if (replaceValidTime.contentEquals(timeFormatted)) {
                indexI = i;
                for (int j = 0; j < sizeOfParametersArrayList; j++) {
                    if (smhi.getTimeSeries().get(indexI).getParameters().get(j).getName().equals("pcat")) {
                        indexJ = j;

                        return smhi.getTimeSeries().get(indexI).getParameters().get(indexJ).getValues();
                    }
                }
            }
        }
        return null;
    }
    public List<Double> findWindSpeed24HoursFromNow(Smhi smhi) {

        String timeFormatted = ZonedDateTime.now().minusSeconds(ZonedDateTime.now().getSecond())
                .minusMinutes(ZonedDateTime.now().getMinute()).plusHours(25).format(formatter);

        for (int i = 0; i < sizeOfTimeSeriesArrayList; i++) {
            String replaceValidTime = smhi.getTimeSeries().get(i).getValidTime().replace("T", " ")
                    .replace("Z", "");
            if (replaceValidTime.contentEquals(timeFormatted)) {
                indexI = i;
                for (int j = 0; j < sizeOfParametersArrayList; j++) {
                    if (smhi.getTimeSeries().get(indexI).getParameters().get(j).getName().equals("ws")) {
                        indexJ = j;

                        return smhi.getTimeSeries().get(indexI).getParameters().get(indexJ).getValues();
                    }
                }
            }
        }
        return null;
    }
}

