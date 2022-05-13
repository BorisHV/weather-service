package com.example.labb3smhi.controllers;

import com.example.labb3smhi.repositorymet.RepositoryMET;
import com.example.labb3smhi.repositorysmhi.RepositorySMHI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BestWeatherController {

    private List<String> bestWeather = new ArrayList<>();;

    private RepositorySMHI repositorySMHI;
    private RepositoryMET repositoryMET;

    @Autowired
    public BestWeatherController(RepositorySMHI repositorySMHI, RepositoryMET repositoryMET) {
        this.repositorySMHI = repositorySMHI;
        this.repositoryMET = repositoryMET;
    }

    //    @Autowired
//    RepositorySMHI repositorySMHI;
//
//    @Autowired
//    RepositoryMET repositoryMET;

    //Fixa equal-utskriften, vad händer om alla är equal? Vilken ska då vinna?
    //Högst temperatur vinner alltid om regn och vind är samma. Sedan fortsätt välj prio.

    public String lowestPrecipitation() {
        if (repositorySMHI.getPrecipitationSMHI().get(0) > Double.parseDouble(repositoryMET.getPrecipitationMET().toString())) {
            return "Met";
        } else if (repositorySMHI.getPrecipitationSMHI().get(0) < Double.parseDouble(repositoryMET.getPrecipitationMET().toString())) {
            return "Smhi";
        }
        return "equal";
    }

    public String lowestWindSpeed() {
        if (repositorySMHI.getWindSpeedSMHI().get(0) > repositoryMET.getWindSpeedMET()) {
            return "Met";
        } else if (repositorySMHI.getWindSpeedSMHI().get(0) < repositoryMET.getWindSpeedMET()) {
            return "Smhi";
        }
        return "equal";
    }

    public String highestTemperature() {
        if (repositorySMHI.getTemperatureSMHI().get(0) > repositoryMET.getTemperatureMET()) {
            return "Smhi";
        } else if (repositorySMHI.getWindSpeedSMHI().get(0) < repositoryMET.getWindSpeedMET()) {
            return "Met";
        }
        return "equal";
    }
    public String bestWeather(){

        bestWeather.add(lowestPrecipitation());
        bestWeather.add(lowestWindSpeed());
        bestWeather.add(highestTemperature());
        for (String s : bestWeather) {
            int smhiCounter = 0;
            int metCounter = 0;
            if(s.equals("Smhi")){
                smhiCounter++;
            }
            else if(s.equals("Met")){
                metCounter++;
            }

            if(smhiCounter > metCounter){
                return "Smhi";
            }
            else if(smhiCounter < metCounter){
                return "Met";
            }
            else{
                return "equal";
            }
        }
        return null;
    }


}
