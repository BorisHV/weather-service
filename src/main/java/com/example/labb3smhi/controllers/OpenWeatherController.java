package com.example.labb3smhi.controllers;

import com.example.labb3smhi.Handlers.OpenWeatherHandler;
import com.example.labb3smhi.openWeatherRepository.RepositoryOpenWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OpenWeatherController {

    @Autowired
    private RepositoryOpenWeather repositoryOpenWeather;

    @GetMapping("/open")
    public String getAllWeatherMET(Model model) {

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

        return "index";
    }
}
