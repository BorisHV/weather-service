package com.example.labb3smhi.controllers;

import com.example.labb3smhi.REST.SMHIRestClient;
import com.example.labb3smhi.repositorymet.RepositoryMET;
import com.example.labb3smhi.repositorysmhi.RepositorySMHI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BestWeatherController {

    @Autowired
    RepositorySMHI repositorySMHI;

    @Autowired
    RepositoryMET repositoryMET;

    public String bestWeather(){
       if(repositorySMHI.getPrecipitationSMHI().get(0) > repositoryMET.getPrecipitationMET()) {
           return "Met";
       }
       else if(repositorySMHI.getPrecipitationSMHI().get(0) < repositoryMET.getPrecipitationMET()){
           return "Smhi";
       }
       return null;
    }
}
