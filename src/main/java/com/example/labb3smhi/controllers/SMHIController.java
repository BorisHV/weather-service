package com.example.labb3smhi.controllers;

import com.example.labb3smhi.repositorysmhi.RepositorySMHI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SMHIController {

    @Autowired
    private RepositorySMHI repositorySMHI;

//    @Autowired
//    private SMHIRestClient smhiRestClient;

    @GetMapping("/smhi2")
    public String getAllWeatherSMHI(Model model) {

        //String allWeatherData = smhiRestClient.getAllWeatherDataSMHI();

        List<Double> temperatureRepository = repositorySMHI.getTemperatureSMHI();
        List<Double> precipitationSMHI = repositorySMHI.getPrecipitationSMHI();
        List<Double> windSpeedSMHI = repositorySMHI.getWindSpeedSMHI();

        model.addAttribute("temperatureListSMHI", temperatureRepository);
        model.addAttribute("precipitationListSMHI", precipitationSMHI);
        model.addAttribute("windSpeedListSMHI", windSpeedSMHI);

        return "index";
    }
}
