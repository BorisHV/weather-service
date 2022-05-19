package com.example.labb3smhi.controllers;

import com.example.labb3smhi.openWeatherRepository.RepositoryOpenWeather;
import com.example.labb3smhi.repositorymet.RepositoryMET;
import com.example.labb3smhi.repositorysmhi.RepositorySMHI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class BestWeatherController {

    private int smhiCounter = 0;
    private int metCounter = 0;
    private int equalCounter = 0;
    private int rainOnSmhiAndOpenWeatherNoRainOnMetCounter = 0;
    private int rainOnMetAndOpenWeatherNoRainOnSmhiCounter = 0;
    private int highestTempSmhiCounter = 0;
    private int highestTempMetCounter = 0;
    private int highestTempOpenWeatherCounter = 0;

    private List<Double> temperatureListMet;
    private List<Integer> precipitationListMet;
    private List<Double> windSpeedListMet;

    private List<String> bestWeather = new ArrayList<>();

    private RepositorySMHI repositorySMHI;
    private RepositoryMET repositoryMET;
    private SMHIController smhiController;
    private METController metController;
    private RepositoryOpenWeather repositoryOpenWeather;
    private OpenWeatherController openWeatherController;



    @Autowired
    public BestWeatherController(RepositorySMHI repositorySMHI, RepositoryMET repositoryMET,
                                 SMHIController smhiController, METController metController,
                                 RepositoryOpenWeather repositoryOpenWeather,
                                 OpenWeatherController openWeatherController) {
        this.repositorySMHI = repositorySMHI;
        this.repositoryMET = repositoryMET;
        this.smhiController = smhiController;
        this.metController = metController;
        this.openWeatherController = openWeatherController;
        this.repositoryOpenWeather = repositoryOpenWeather;
    }

    public LocalDateTime getDateAndTime(){

       LocalDateTime localDateTime = LocalDateTime.now().plusHours(24).minusSeconds(LocalDateTime.now()
                .getSecond()).minusNanos(LocalDateTime.now().getNano());
       return localDateTime;
    }

    public String lowestPrecipitation() {

        if (repositorySMHI.getPrecipitationSMHI().get(0) > Double.parseDouble(
                repositoryMET.getPrecipitationMET().toString())
                && repositoryOpenWeather.getPrecipitationOpenWeather() > Double.parseDouble(
                repositoryMET.getPrecipitationMET().toString())) {
            return "lowestPrecipitationMet";
        } else if (repositorySMHI.getPrecipitationSMHI().get(0) < Double.parseDouble(
                repositoryMET.getPrecipitationMET().toString())
                && repositoryOpenWeather.getPrecipitationOpenWeather() > repositorySMHI.getPrecipitationSMHI().get(0)) {
            return "lowestPrecipitationSmhi";
        } else if (repositoryOpenWeather.getPrecipitationOpenWeather() < repositorySMHI.getPrecipitationSMHI().get(0)
                && repositoryOpenWeather.getPrecipitationOpenWeather() < repositoryMET.getPrecipitationMET()) {
            return "lowestPrecipitationOpenWeather";
        }
        return "equal";
    }

    public String lowestWindSpeed() {
        if (repositorySMHI.getWindSpeedSMHI().get(0) > repositoryMET.getWindSpeedMET()
                && repositoryOpenWeather.getWindSpeedOpenWeather() > repositoryMET.getWindSpeedMET()) {
            return "Met";
        } else if (repositorySMHI.getWindSpeedSMHI().get(0) < repositoryMET.getWindSpeedMET()
                && repositoryOpenWeather.getWindSpeedOpenWeather() < repositorySMHI.getWindSpeedSMHI().get(0)) {
            return "Smhi";
        } else if (repositorySMHI.getWindSpeedSMHI().get(0) > repositoryMET.getWindSpeedMET()
                && repositoryOpenWeather.getWindSpeedOpenWeather() < repositorySMHI.getWindSpeedSMHI().get(0)) {
            return "OpenWeather";
        }
        return "equal";
    }

    public String highestTemperature() {
        if (repositorySMHI.getTemperatureSMHI().get(0) > repositoryMET.getTemperatureMET()) {
            return "highestTempSmhi";
        } else if (repositorySMHI.getTemperatureSMHI().get(0) < repositoryMET.getTemperatureMET()) {
            return "highestTempMet";
        } else if (repositorySMHI.getTemperatureSMHI().get(0) > repositoryMET.getTemperatureMET()
                && repositoryOpenWeather.getTemperatureOpenWeather() > repositorySMHI.getTemperatureSMHI().get(0)) {
            return "highestTempOpenWeather";
        }
        return "equal";
    }

    public String precipitationOnTwoServicesAndNotTheThird() {
        if (repositorySMHI.getPrecipitationSMHI().get(0) > 0 && Double.parseDouble(
                repositoryMET.getPrecipitationMET().toString()) == 0
                && repositoryOpenWeather.getPrecipitationOpenWeather() > 0) {
            return "rainOnSmhiAndOpenWeatherNoRainOnMet";
        } else if (repositorySMHI.getPrecipitationSMHI().get(0) == 0 && Double.parseDouble(
                repositoryMET.getPrecipitationMET().toString()) > 0
                && repositoryOpenWeather.getPrecipitationOpenWeather() > 0) {
            return "rainOnMetAndOpenWeatherNoRainOnSmhi";
        } else if (repositorySMHI.getPrecipitationSMHI().get(0) > 0 && Double.parseDouble(
                repositoryMET.getPrecipitationMET().toString()) > 0
                && repositoryOpenWeather.getPrecipitationOpenWeather() == 0) {
            return "rainOnMetAndSmhiNoRainOnOpenWeather";
        }
        return "";
    }

    public void bestWeather() {

        bestWeather.add(highestTemperature());
        bestWeather.add(precipitationOnTwoServicesAndNotTheThird());
        bestWeather.add(lowestPrecipitation());
        bestWeather.add(lowestWindSpeed());

        for (String weatherString : bestWeather) {

            if (weatherString.equals("rainOnSmhiAndOpenWeatherNoRainOnMet")) {
                rainOnSmhiAndOpenWeatherNoRainOnMetCounter++;
            } else if (weatherString.equals("rainOnMetAndOpenWeatherNoRainOnSmhi")) {
                rainOnMetAndOpenWeatherNoRainOnSmhiCounter++;
            } else if (weatherString.equals("highestTempSmhi")) {
                highestTempSmhiCounter++;
            } else if (weatherString.equals("highestTempMet")) {
                highestTempMetCounter++;
            } else if (weatherString.equals("highestTempOpenWeather")) {
                highestTempOpenWeatherCounter++;
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
        if (rainOnSmhiAndOpenWeatherNoRainOnMetCounter > 0) {
            getAllWeatherMet(model);
        } else if (rainOnMetAndOpenWeatherNoRainOnSmhiCounter > 0) {
            getAllWeatherSmhi(model);
        } else if (highestTempSmhiCounter > 0) {
            getAllWeatherSmhi(model);
        } else if (highestTempMetCounter > 0) {
            getAllWeatherMet(model);
        } else if (highestTempOpenWeatherCounter > 0) {
            getAllWeatherOpenWeather(model);
        } else if (smhiCounter > metCounter) {
            getAllWeatherSmhi(model);
        } else if (smhiCounter < metCounter) {
            getAllWeatherMet(model);
        } else {
            getAllWeatherSmhi(model);
        }
        return "index";
    }

    private String getAllWeatherOpenWeather(Model model) {
        LocalDateTime localDateAndTime = getDateAndTime();
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
        model.addAttribute("localDateTimeOpenWeather", localDateAndTime);
        return "index";
    }

    private String getAllWeatherSmhi(Model model) {
        LocalDateTime localDateAndTime = getDateAndTime();
        List<Double> temperatureRepository = repositorySMHI.getTemperatureSMHI();
        List<Double> precipitationSMHI = repositorySMHI.getPrecipitationSMHI();
        List<Double> windSpeedSMHI = repositorySMHI.getWindSpeedSMHI();

        model.addAttribute("temperatureListSMHI", temperatureRepository);
        model.addAttribute("precipitationListSMHI", precipitationSMHI);
        model.addAttribute("windSpeedListSMHI", windSpeedSMHI);
        model.addAttribute("localDateTimeSmhi", localDateAndTime);
        return "index";
    }

    private String getAllWeatherMet(Model model) {
        LocalDateTime localDateAndTime = getDateAndTime();
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
        model.addAttribute("localDateTimeMet", localDateAndTime);

        return "index";
    }
}
