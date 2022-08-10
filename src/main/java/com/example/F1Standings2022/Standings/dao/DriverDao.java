package com.example.F1Standings2022.Standings.dao;


import com.example.F1Standings2022.Standings.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("drivers")
public class DriverDao {

    List<Driver> DB = new ArrayList<>();

    public void addDrivers(List<Driver> drivers) {
        DB.addAll(drivers);
    }

    public List<Driver> getDrivers(){
        return DB;
    }

}
