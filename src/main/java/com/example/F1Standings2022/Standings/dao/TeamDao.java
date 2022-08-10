package com.example.F1Standings2022.Standings.dao;

import com.example.F1Standings2022.Standings.entity.Team;
import com.example.F1Standings2022.Standings.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("teams")
public class TeamDao {

    List<Team> DB = new ArrayList<>();

    public String addTeam(Team team) {
        DB.add(team);
        return team.getName() + " added to teams database";
    }

    public String addTeams(List<Team> teams) {
        DB.addAll(teams);
        return "2022 F1 constructors added";
    }

    public List<Team> getTeams() {
        return DB;
    }

}
