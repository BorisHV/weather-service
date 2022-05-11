package com.example.labb3smhi.JsonHandler;

import com.example.labb3smhi.SMHI.Smhi;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class JsonHandler {

    public List<Double> findTemperature24HoursFromNow(Smhi smhi) {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeFormatted = ZonedDateTime.now().minusSeconds(ZonedDateTime.now().getSecond()).minusMinutes(ZonedDateTime.now().getMinute()).plusHours(24).format(FORMATTER);

        int indexI = 0;
        int indexJ = 0;

        for (int i = 0; i < 76; i++) {
            String replaceValidTime = smhi.getTimeSeries().get(i).getValidTime().replace("T", " ")
                    .replace("Z", "");
            if (replaceValidTime.contentEquals(timeFormatted)) {
                indexI = i;
                for (int j = 0; j < 18; j++) {
                    if (smhi.getTimeSeries().get(indexI).getParameters().get(j).getName().equals("t")) {
                        indexJ = j;

                        return smhi.getTimeSeries().get(indexI).getParameters().get(indexJ).getValues();
                    }
                }
            }
        }
        return null;
    }
}

