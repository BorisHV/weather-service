package com.example.labb3smhi.Handlers;

import com.example.labb3smhi.openweather.OpenWeather;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class OpenWeatherHandler {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public Double findTemperature24HoursFromNow(OpenWeather openWeather) {
        return openWeather.getHourly().get(24).getTemp();
    }

    public Long findPrecipitationAmount24HoursFromNow(OpenWeather openWeather) {

                if (openWeather.getDaily().get(1).getRain() == null) {
                    return 0L;
            }

        return openWeather.getDaily().get(1).getRain();
    }

 public Double findWindSpeed24HoursFromNow(OpenWeather openWeather) {

      return openWeather.getHourly().get(24).getWindSpeed();
    }
}
