package com.example.labb3smhi.controllers;

import com.example.labb3smhi.Handlers.OpenWeatherHandler;
import com.example.labb3smhi.openWeatherRepository.RepositoryOpenWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OpenWeatherController {

    @Autowired
    private RepositoryOpenWeather repositoryOpenWeather;

    public LocalDateTime getDateAndTime(){

        LocalDateTime localDateTime = LocalDateTime.now().plusHours(24).minusSeconds(LocalDateTime.now()
                .getSecond()).minusNanos(LocalDateTime.now().getNano());
        return localDateTime;
    }

    @GetMapping("/open")
    public String getAllWeatherOpenWeather(Model model) {
        LocalDateTime localDateTime = getDateAndTime();
        Double temperatureOpenWeather = repositoryOpenWeather.getTemperatureOpenWeather();
        List<Double> temperatureListOpenWeather = new ArrayList<>();
        temperatureListOpenWeather.add(temperatureOpenWeather);

        Long precipitationOpenWeather = repositoryOpenWeather.getPrecipitationOpenWeather();
        List<Long> precipitationListOpenWeather = new ArrayList<>();
        precipitationListOpenWeather.add(precipitationOpenWeather);

        Double windSpeedOpenWeather = repositoryOpenWeather.getWindSpeedOpenWeather();
        List<Double> windSpeedListOpenWeather = new ArrayList<>();
        windSpeedListOpenWeather.add(windSpeedOpenWeather);

        model.addAttribute("temperatureListOpenWeather", temperatureListOpenWeather);
        model.addAttribute("precipitationListOpenWeather", precipitationListOpenWeather);
        model.addAttribute("windSpeedListOpenWeather", windSpeedListOpenWeather);
        model.addAttribute("localDateTimeOpenWeather", localDateTime);

        return "index";
    }
}
