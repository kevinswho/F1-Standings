package com.example.F1Standings2022.Standings.entity;

//import com.example.F1Standings2022.Standings.repository.DriverRepository;
import com.example.F1Standings2022.Standings.service.ConstructorTeamService;
import com.example.F1Standings2022.Standings.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.valueOf;

@Configuration
public class DriverConfig {

    private DriverService driverService;
    private ConstructorTeamService constructorTeamService;

    @Autowired
    public DriverConfig(DriverService driverservice,ConstructorTeamService constructorTeamService){
        this.driverService = driverservice;
        this.constructorTeamService = constructorTeamService;
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

    public boolean driverProcessed(List<ConstructorTeam> constructorTeams, Driver driver) {
        for (int i=0; i<constructorTeams.size(); i++) {
            if (constructorTeams.get(i).containsDriver(driver)) {
                return true;
            }
        }

        return false;
    }

    public boolean teamAdded(List<ConstructorTeam> constructorTeams, String team) {
        for (int i=0; i<constructorTeams.size(); i++) {
            if (constructorTeams.get(i).getName().equals(team)) {
                return true;
            }
        }

        return false;
    }

    public List<ConstructorTeam> getConstructorTeamList() {
        List<Driver> driverList = getDriversList();
        //System.out.println(driverList);
        //System.out.println(getDriversList());
        List<ConstructorTeam> constructorTeams = new ArrayList<>();

        for (Driver driver : driverList) { //add the teams and their drivers
            if (!teamAdded(constructorTeams,driver.getTeam())){ //if the drivers team isn't on the list
                ArrayList<Driver> newTeamDrivers = new ArrayList<>();
                newTeamDrivers.add(driver);
                ConstructorTeam newTeam = new ConstructorTeam(driver.getTeam(),newTeamDrivers,driver.getPoints(),0);
                constructorTeams.add(newTeam);
            } else { //if the drivers team is already on the list
                int idx = 0;
                while (idx < constructorTeams.size()) {
                    if (constructorTeams.get(idx).getName().equals(driver.getTeam())) {
                        List<Driver> teamsDrivers = constructorTeams.get(idx).getDriversList();
                        teamsDrivers.add(driver);
                        constructorTeams.get(idx).setPoints(constructorTeams.get(idx).getPoints() + driver.getPoints());
                        break;
                    }
                    idx++;
                }
            }
        }

        for (ConstructorTeam team: constructorTeams) {
            List<Driver> currentTeamDrivers = team.getDriversList();
            List<Integer> currentTeamDriverPoints = new ArrayList<Integer>();
            currentTeamDrivers.forEach(x -> currentTeamDriverPoints.add(x.getPoints()));
            Collections.sort(currentTeamDriverPoints);
            int pointsdiff = currentTeamDriverPoints.get(currentTeamDriverPoints.size() - 1) - currentTeamDriverPoints.get(0);
            team.setDriverPointsDifference(pointsdiff);
        }
        //System.out.println(constructorTeams);
        return constructorTeams;
    }

    @Bean
    CommandLineRunner commandLineRunner() {

        return args -> {
            List<Driver> pullDrivers = getDriversList();
            List<ConstructorTeam> pullConstructorTeams = getConstructorTeamList();
            driverService.addDrivers(pullDrivers);
            constructorTeamService.addConstructorTeams(pullConstructorTeams);
        };
    }

}
