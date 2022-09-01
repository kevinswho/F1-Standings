package com.example.F1Standings2022.Standings.dao;


import com.example.F1Standings2022.Standings.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DriverDao extends JdbcDaoSupport {

    //List<Driver> DB = new ArrayList<>();

    @Autowired
    private DataSource dataSource;

    public void addDriver(Driver driver) {
        int update = getJdbcTemplate().update("INSERT INTO drivers_table(number,name,team,points) VALUES(?,?,?,?)", driver.getDriverNumber(),
                driver.getName(), driver.getTeam(), driver.getPoints());

        if (update > 0)
            System.out.println("Driver " + driver.getName() + " has been added to the drivers table.");
    }

    public void addDrivers(List<Driver> drivers) {
        drivers.forEach(x -> addDriver(x));
    }

    public List<Driver> getDrivers(){
        String SQL = "SELECT * FROM drivers_table";
        return getJdbcTemplate().query(SQL, new DriverRowMapper());
    }

    @PostConstruct
    public void init() {
        setDataSource(dataSource);
    }

}
