package com.example.labb3smhi.weatherservice;

import com.example.labb3smhi.Handlers.METHandler;
import com.example.labb3smhi.MET.Met;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class METController {

    @Autowired
    private METHandler metHandler;

    @GetMapping("/met")
    public String getAllWeatherSMHI(Met met, Model model){
        Double temperatureMET = metHandler.findTemperature24HoursFromNow(met);
        Integer precipitationMET = metHandler.findPrecipitationAmount24HoursFromNow(met);
        Double windSpeedMET = metHandler.findWindSpeed24HoursFromNow(met);
        model.addAttribute("temperatureMET", temperatureMET);
        model.addAttribute("precipitationMET", precipitationMET);
        model.addAttribute("windSpeedMET", windSpeedMET);
        return "index";
    }
}
