package com.example.labb3smhi.weatherservice;

import com.example.labb3smhi.REST.SMHIRestClient;
import com.example.labb3smhi.SMHI.Smhi;
import com.example.labb3smhi.Handlers.SMHIHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SMHIController {

    @Autowired
    private SMHIHandler smhiHandler;

//    @Autowired
//    private SMHIRestClient smhiRestClient;

    @GetMapping("/smhi2")
    public String getAllWeatherSMHI(Smhi smhi, Model model) {

        //String allWeatherData = smhiRestClient.getAllWeatherDataSMHI();
        List<Double> temperatureSMHI = smhiHandler.findTemperature24HoursFromNow(smhi);
//        List<Double> precipitationSMHI = smhiHandler.findPrecipitation24HoursFromNow(smhi);
//        List<Double> windSpeedSMHI = smhiHandler.findWindSpeed24HoursFromNow(smhi);
        model.addAttribute("temperatureSMHI", temperatureSMHI.get(0));
//        model.addAttribute("precipitationSMHI", precipitationSMHI);
//        model.addAttribute("windSpeedSMHI", windSpeedSMHI);
        return "index";
    }
}
