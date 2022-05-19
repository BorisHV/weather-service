package com.example.labb3smhi.controllers;

import com.example.labb3smhi.repositorysmhi.RepositorySMHI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class SMHIController {

    @Autowired
    private RepositorySMHI repositorySMHI;

    public LocalDateTime getDateAndTime(){

        LocalDateTime localDateTime = LocalDateTime.now().plusHours(24).minusSeconds(LocalDateTime.now()
                .getSecond()).minusNanos(LocalDateTime.now().getNano());
        return localDateTime;
    }

    @GetMapping("/smhi")
    public String getAllWeatherSMHI(Model model) {
        LocalDateTime localDateTime = getDateAndTime();
        List<Double> temperatureRepository = repositorySMHI.getTemperatureSMHI();
        List<Double> precipitationSMHI = repositorySMHI.getPrecipitationSMHI();
        List<Double> windSpeedSMHI = repositorySMHI.getWindSpeedSMHI();

        model.addAttribute("temperatureListSMHI", temperatureRepository);
        model.addAttribute("precipitationListSMHI", precipitationSMHI);
        model.addAttribute("windSpeedListSMHI", windSpeedSMHI);
        model.addAttribute("localDateTimeSmhi", localDateTime);

        return "index";
    }
}
