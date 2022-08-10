package com.example.F1Standings2022.Standings.controller;


import com.example.F1Standings2022.Standings.entity.Team;
import com.example.F1Standings2022.Standings.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/constructors")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) { this.teamService = teamService;}

    @GetMapping("/data")
    public List<Team> getTeams() { return teamService.getTeams(); }

    @GetMapping("/text")
    public String teamDisplay() {
        List<Team> teams = teamService.getTeams();

        StringBuffer teamString = new StringBuffer();

        for (int i=0; i<teams.size(); i++) {
            teamString.append(teams.get(i).toString());
            teamString.append("<br />");
        }

        return teamString.toString();
    }

}


