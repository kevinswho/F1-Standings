package com.example.F1Standings2022.Standings.controller;

import com.example.F1Standings2022.Standings.entity.ConstructorTeam;
import com.example.F1Standings2022.Standings.service.ConstructorTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/constructors")
public class ConstructorTeamController {

    private final ConstructorTeamService constructorTeamService;

    @Autowired
    public ConstructorTeamController(ConstructorTeamService constructorTeamService) { this.constructorTeamService = constructorTeamService; }

    @GetMapping("/data")
    public List<ConstructorTeam> getConstructorTeams() {
        return constructorTeamService.getConstructorTeams();
    }

    @GetMapping("/text")
    public String constructorTeamsDisplay() {
        List<ConstructorTeam> constructorTeams = constructorTeamService.getConstructorTeams();

        StringBuffer constructorTeamsString = new StringBuffer();

        for (int i=0; i<constructorTeams.size(); i++) {
            constructorTeamsString.append(constructorTeams.get(i).toString());
            constructorTeamsString.append("<br />");
        }

        return constructorTeamsString.toString();
    }

}
