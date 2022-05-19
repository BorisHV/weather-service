package com.example.labb3smhi.controllers;

import com.example.labb3smhi.repositorymet.RepositoryMET;
import com.example.labb3smhi.repositorysmhi.RepositorySMHI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class METController {

    @Autowired
    private RepositoryMET repositoryMET;

    public LocalDateTime getDateAndTime(){

        LocalDateTime localDateTime = LocalDateTime.now().plusHours(24).minusSeconds(LocalDateTime.now()
                .getSecond()).minusNanos(LocalDateTime.now().getNano());
        return localDateTime;
    }

    @GetMapping("/met")
    public String getAllWeatherMET(Model model) {
        LocalDateTime localDateTime = getDateAndTime();
        Double temperatureRepositoryMET = repositoryMET.getTemperatureMET();
        List<Double> temperatureListMet = new ArrayList<>();
        temperatureListMet.add(temperatureRepositoryMET);

        Integer precipitationMET = repositoryMET.getPrecipitationMET();
        List<Integer> precipitationListMet = new ArrayList<>();
        precipitationListMet.add(precipitationMET);

        Double windSpeedMET = repositoryMET.getWindSpeedMET();
        List<Double> windSpeedListMet = new ArrayList<>();
        windSpeedListMet.add(windSpeedMET);

        model.addAttribute("temperatureListMET", temperatureRepositoryMET);
        model.addAttribute("precipitationListMET", precipitationMET);
        model.addAttribute("windSpeedListMET", windSpeedMET);
        model.addAttribute("localDateTimeMet", localDateTime);

        return "index";
    }
}
