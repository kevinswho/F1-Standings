package com.example.F1Standings2022.Standings.service;

import com.example.F1Standings2022.Standings.dao.DriverDao;
import com.example.F1Standings2022.Standings.dao.TeamDao;
import com.example.F1Standings2022.Standings.entity.Driver;
import com.example.F1Standings2022.Standings.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private DriverService driverService;

    public String addTeam(String name) {

        int points = 0;
        List<Driver> teamDrivers = new ArrayList<Driver>();

        List<Driver> drivers = driverService.getDrivers();
        for (int i=0; i<drivers.size(); i++){
            if (!teamDrivers.contains(drivers.get(i))){
                teamDrivers.add(drivers.get(i));
                points += drivers.get(i).getPoints();
            }
        }

        Team curr = new Team (name, points, teamDrivers);

        if (!teamDao.getTeams().contains(curr)) {
            teamDao.addTeam(curr);
            return "Team " + name + " added";
        } else {
            return "Team " + name + " already in database";
        }

    }

    public String addTeams(List<Team> teams){
        teamDao.addTeams(teams);
        return "2022 F1 constructors added";
    }

    public List<Team> getTeams() {
        return teamDao.getTeams();
    }

}
