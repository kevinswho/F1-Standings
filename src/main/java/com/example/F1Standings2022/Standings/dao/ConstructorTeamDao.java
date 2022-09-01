package com.example.F1Standings2022.Standings.dao;

import com.example.F1Standings2022.Standings.entity.ConstructorTeam;
import com.example.F1Standings2022.Standings.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class ConstructorTeamDao extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;

    public void addConstructorTeam(ConstructorTeam constructorTeam) {

        StringBuffer driverNames = new StringBuffer();

        List<Driver> teamDrivers = constructorTeam.getDriversList();

        for (Driver teamDriver : teamDrivers) {
            driverNames.append(teamDriver.getName());
            driverNames.append(", ");
        }

        driverNames.setLength(driverNames.length() - 2);

        int update = getJdbcTemplate().update("INSERT INTO constructors_table(name,drivers,points,driver_points_difference) VALUES(?,?,?,?)",
                constructorTeam.getName(), constructorTeam.getDrivers(),
                constructorTeam.getPoints(), constructorTeam.getDriverPointsDifference());


        //System.out.println("got here");
        if (update > 0)
            System.out.println("Constructor " + constructorTeam.getName() + " with drivers " + driverNames.toString() +
                    " has been added to the constructors table.");
    }

    public void addConstructorTeams(List<ConstructorTeam> constructorTeams) {
        for (ConstructorTeam constructorTeam : constructorTeams) {
            addConstructorTeam(constructorTeam);
        }
    }

    public List<ConstructorTeam> getConstructorTeams() {
        String SQL = "SELECT * FROM constructors_table";
        return getJdbcTemplate().query(SQL, new ConstructorTeamRowMapper());
    }

    @PostConstruct
    public void  init() { setDataSource(dataSource); }

}
