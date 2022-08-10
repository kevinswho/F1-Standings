package com.example.F1Standings2022.Standings.entity;

//import com.example.F1Standings2022.Standings.repository.DriverRepository;
import com.example.F1Standings2022.Standings.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

@Configuration
public class DriverConfig {

    private DriverService driverService;

    @Autowired
    public DriverConfig(DriverService driverservice){
        this.driverService = driverservice;
    }

    private static List<Driver> getDriversList()
    {
        final String driversURL = "http://ergast.com/api/f1/2022/11/driverStandings.json";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(driversURL, String.class);

        JSONObject obj = new JSONObject(result);
        JSONArray driverStandings = obj.getJSONObject("MRData")
                .getJSONObject("StandingsTable")
                .getJSONArray("StandingsLists")
                .getJSONObject(0)
                .getJSONArray("DriverStandings");

        /*JSONObject max = driverStandings.getJSONObject(0); max is first object; current top driver as of 7/24
        String maxName = max.getJSONObject("Driver").get("driverId").toString();
        System.out.println(maxName);*/

        List<Driver> drivers = new ArrayList<>();

        for(int i=0; i<driverStandings.length(); i++) {

            String driverFirstName = driverStandings.getJSONObject(i).getJSONObject("Driver").get("givenName").toString();
            String driverLastName = driverStandings.getJSONObject(i).getJSONObject("Driver").get("familyName").toString();

            int driverNumber = valueOf(driverStandings.getJSONObject(i).getJSONObject("Driver").get("permanentNumber").toString());
            String driverName = driverFirstName + " " + driverLastName;
            String driverConstructor = driverStandings.getJSONObject(i).getJSONArray("Constructors").getJSONObject(0).get("name").toString();
            int driverPoints = valueOf(driverStandings.getJSONObject(i).get("points").toString());

            Driver curr = new Driver(driverNumber, driverName, driverConstructor, driverPoints);
            drivers.add(curr);
            //System.out.println(curr.toString() + "added");
        }
        //System.out.println(drivers);
        return drivers;
    }

    @Bean
    CommandLineRunner commandLineRunner() {

        return args -> {
            List<Driver> pullDrivers = getDriversList();
            driverService.addDrivers(pullDrivers);
        };
    }

}
