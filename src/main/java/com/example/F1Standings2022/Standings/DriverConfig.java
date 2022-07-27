package com.example.F1Standings2022.Standings;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

@Configuration
public class DriverConfig {

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
            Driver curr = new Driver(
                    valueOf(driverStandings.getJSONObject(i).getJSONObject("Driver").get("permanentNumber").toString()),
                    driverStandings.getJSONObject(i).getJSONObject("Driver").get("driverId").toString(),
                    driverStandings.getJSONObject(i).getJSONArray("Constructors").toString(),
                    valueOf(driverStandings.getJSONObject(i).get("points").toString())
            );
            drivers.add(curr);
        }
        //System.out.println(drivers);
        return drivers;
    }

    @Bean
    CommandLineRunner commandLineRunner(DriverRepository driverRepository) {

        return args -> {
            driverRepository.saveAll(getDriversList());
        };
    }

}
