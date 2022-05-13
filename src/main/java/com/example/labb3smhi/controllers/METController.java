package com.example.labb3smhi.controllers;

import com.example.labb3smhi.repositorymet.RepositoryMET;
import com.example.labb3smhi.repositorysmhi.RepositorySMHI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class METController {

    private List<Double> temperatureListMet;
    private List<Integer> precipitationListMet;
    private List<Double> windSpeedListMet;

    @Autowired
    private RepositoryMET repositoryMET;

    @GetMapping("/met")
    public String getAllWeatherSMHI(Model model) {

        //String allWeatherData = smhiRestClient.getAllWeatherDataSMHI();

        Double temperatureRepositoryMET = repositoryMET.getTemperatureMET();
        temperatureListMet = new ArrayList<>();
        temperatureListMet.add(temperatureRepositoryMET);

        Integer precipitationMET = repositoryMET.getPrecipitationMET();
        precipitationListMet = new ArrayList<>();
        precipitationListMet.add(precipitationMET);

        Double windSpeedMET = repositoryMET.getWindSpeedMET();
        windSpeedListMet = new ArrayList<>();
        windSpeedListMet.add(windSpeedMET);

        model.addAttribute("temperatureListMET", temperatureRepositoryMET);
        model.addAttribute("precipitationListMET", precipitationMET);
        model.addAttribute("windSpeedListMET", windSpeedMET);

        return "index";
    }
}
