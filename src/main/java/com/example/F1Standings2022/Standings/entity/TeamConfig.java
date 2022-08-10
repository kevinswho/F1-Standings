package com.example.F1Standings2022.Standings.entity;

import com.example.F1Standings2022.Standings.service.DriverService;
import com.example.F1Standings2022.Standings.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class TeamConfig {

    private TeamService teamService;
    private DriverService driverService;

    @Autowired
    public TeamConfig(TeamService teamService, DriverService driverService){
        this.teamService = teamService;
        this.driverService = driverService;
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            List<Driver> pullDrivers = driverService.getDrivers();
            for (int i=0; i<pullDrivers.size(); i++){
                teamService.addTeam(pullDrivers.get(i).getTeam());
            }
        };
    }

}
