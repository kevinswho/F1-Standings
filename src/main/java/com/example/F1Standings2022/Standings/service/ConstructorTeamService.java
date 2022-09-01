package com.example.F1Standings2022.Standings.service;

import com.example.F1Standings2022.Standings.dao.ConstructorTeamDao;
import com.example.F1Standings2022.Standings.dao.DriverDao;
import com.example.F1Standings2022.Standings.entity.ConstructorTeam;
import com.example.F1Standings2022.Standings.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConstructorTeamService {

    @Autowired
    private ConstructorTeamDao constructorTeamDao;

    @Autowired
    private DriverDao driverDao;

    public String addConstructorTeams(List<ConstructorTeam> constructorTeams) {
        constructorTeamDao.addConstructorTeams(constructorTeams);
        return "2022 F1 constructors added";
    }

    public List<Driver> getDriversList() {
        return driverDao.getDrivers();
    }

    public List<ConstructorTeam> getConstructorTeams() { return constructorTeamDao.getConstructorTeams(); }

}
