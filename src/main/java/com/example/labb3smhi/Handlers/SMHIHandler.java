package com.example.labb3smhi.Handlers;

import com.example.labb3smhi.SMHI.Smhi;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class SMHIHandler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    int sizeOfParametersArrayList = 18;
    int sizeOfTimeSeriesArrayList = 76;
    int indexI = 0;
    int indexJ = 0;
    double lightRain = 18;
    double moderateRain = 19;
    double heavyRain = 20;
    double lightRainInMm = 2.5;
    double moderateRainInMm = 10;
    double heavyRainInMm = 15;
    double noRain = 0;

    List<Double> noRainList;
    List<Double> lightRainInMmList;
    List<Double> moderateRainInMmList;
    List<Double> heavyRainInMmList;


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

        String timeFormatted = ZonedDateTime.now().minusSeconds(ZonedDateTime.now()
                .getSecond()).minusMinutes(ZonedDateTime.now().getMinute()).plusHours(25).format(formatter);

        for (int i = 0; i < smhi.getTimeSeries().size(); i++) {
            String replaceValidTime = smhi.getTimeSeries().get(i).getValidTime().replace("T", " ")
                    .replace("Z", "");
            if (replaceValidTime.contentEquals(timeFormatted)) {
                indexI = i;
                for (int j = 0; j < sizeOfParametersArrayList; j++) {
                    if (smhi.getTimeSeries().get(indexI).getParameters().get(j).getName().equals("Wsymb2")) {
                        indexJ = j;
                        for (int k = 0; k < 2; k++) {
                            if (smhi.getTimeSeries().get(indexI).getParameters().get(indexJ).getValues().get(k) == lightRain) {
                                lightRainInMmList = new ArrayList<>();
                                lightRainInMmList.add(lightRain);
                                return lightRainInMmList;
                            } else if (smhi.getTimeSeries().get(indexI).getParameters().get(indexJ).getValues().get(k) == moderateRain) {
                                moderateRainInMmList = new ArrayList<>();
                                moderateRainInMmList.add(moderateRain);
                                return moderateRainInMmList;
                            } else if (smhi.getTimeSeries().get(indexI).getParameters().get(indexJ).getValues().get(k) == heavyRain) {
                                heavyRainInMmList = new ArrayList<>();
                                heavyRainInMmList.add(heavyRainInMm);
                                return heavyRainInMmList;
                            }
                        }
                    }
                }
            }
        }
        noRainList = new ArrayList<>();
        noRainList.add(noRain);
        return noRainList;
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

