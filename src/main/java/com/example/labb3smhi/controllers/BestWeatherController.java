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
public class BestWeatherController {

    private int smhiCounter = 0;
    private int metCounter = 0;
    private int equalCounter = 0;
    private int rainOnSmhiNoRainOnMetCounter = 0;
    private int rainOnMetNoRainOnSmhiCounter = 0;
    private int highestTempSmhiCounter = 0;
    private int highestTempMetCounter = 0;

    private List<Double> temperatureListMet;
    private List<Integer> precipitationListMet;
    private List<Double> windSpeedListMet;

    private List<String> bestWeather = new ArrayList<>();

    private RepositorySMHI repositorySMHI;
    private RepositoryMET repositoryMET;
    private SMHIController smhiController;
    private METController metController;

    @Autowired
    public BestWeatherController(RepositorySMHI repositorySMHI, RepositoryMET repositoryMET,
                                 SMHIController smhiController, METController metController) {
        this.repositorySMHI = repositorySMHI;
        this.repositoryMET = repositoryMET;
        this.smhiController = smhiController;
        this.metController = metController;
    }

//    @Autowired
//    RepositorySMHI repositorySMHI;
//
//    @Autowired
//    RepositoryMET repositoryMET;

    //highestPrecipitation-metoden ska returnera lowestWindSpeed eller nånting annat.

    //Fixa equal-utskriften, vad händer om alla är equal? Vilken ska då vinna?
    //Högst temperatur vinner alltid om regn och vind är samma. Sedan fortsätt välj prio.

    public String lowestPrecipitation() {
        if (repositorySMHI.getPrecipitationSMHI().get(0) > Double.parseDouble(repositoryMET.getPrecipitationMET().toString())) {
            return "lowestPrecipitationMet";
        } else if (repositorySMHI.getPrecipitationSMHI().get(0) < Double.parseDouble(repositoryMET.getPrecipitationMET().toString())) {
            return "lowestPrecipitationSmhi";
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
            return "highestTempSmhi";
        } else if (repositorySMHI.getTemperatureSMHI().get(0) < repositoryMET.getTemperatureMET()) {
            return "highestTempMet";
        }
        return "equal";
    }

    public String precipitationOnOneServiceAndNotTheOther() {
        if (repositorySMHI.getPrecipitationSMHI().get(0) > 0 && Double.parseDouble(repositoryMET.getPrecipitationMET().toString()) == 0) {
            return "rainOnSmhiNoRainOnMet";
        } else if (repositorySMHI.getPrecipitationSMHI().get(0) == 0 && Double.parseDouble(repositoryMET.getPrecipitationMET().toString()) > 0) {
            return "rainOnMetNoRainOnSmhi";
        }
        return "";
    }

        public void bestWeather() {

            bestWeather.add(highestTemperature());
            bestWeather.add(precipitationOnOneServiceAndNotTheOther());
            bestWeather.add(lowestPrecipitation());
            bestWeather.add(lowestWindSpeed());

            for (String weatherString : bestWeather) {

                if (weatherString.equals("rainOnSmhiNoRainOnMet")) {
                    rainOnSmhiNoRainOnMetCounter++;
                } else if (weatherString.equals("rainOnMetNoRainOnSmhi")) {
                    rainOnMetNoRainOnSmhiCounter++;
                } else if (weatherString.equals("highestTempSmhi")) {
                    highestTempSmhiCounter++;
                } else if (weatherString.equals("highestTempMet")) {
                    highestTempMetCounter++;
                } else if (weatherString.equals("Smhi")) {
                    smhiCounter++;
                } else if (weatherString.equals("Met")) {
                    metCounter++;
                } else if (weatherString.equals("equal")) {
                    equalCounter++;
                }
            }
        }

    @GetMapping("/best")
    public String bestWeatherToShow(Model model) {

        bestWeather();
        if (rainOnSmhiNoRainOnMetCounter > 0) {
            getAllWeatherMet(model);
        }

        else if (rainOnMetNoRainOnSmhiCounter > 0) {
            getAllWeatherSmhi(model);
        }

        else if (highestTempSmhiCounter > 0) {
            getAllWeatherSmhi(model);
        }

        else if (highestTempMetCounter > 0) {
            getAllWeatherMet(model);
        }

        else if (smhiCounter > metCounter) {
            getAllWeatherSmhi(model);
        }

        else if (smhiCounter < metCounter) {
            getAllWeatherMet(model);
        } else {
            getAllWeatherSmhi(model);
        }
        return "index";
    }

    private String getAllWeatherSmhi(Model model) {
        List<Double> temperatureRepository = repositorySMHI.getTemperatureSMHI();
        List<Double> precipitationSMHI = repositorySMHI.getPrecipitationSMHI();
        List<Double> windSpeedSMHI = repositorySMHI.getWindSpeedSMHI();

        model.addAttribute("temperatureListSMHI", temperatureRepository);
        model.addAttribute("precipitationListSMHI", precipitationSMHI);
        model.addAttribute("windSpeedListSMHI", windSpeedSMHI);
        return "index";
    }

    private String getAllWeatherMet(Model model) {
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
