package com.example.F1Standings2022.Standings.service;

import com.example.F1Standings2022.Standings.entity.Driver;
import com.example.F1Standings2022.Standings.dao.DriverDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    @Autowired
    private DriverDao driverDao;

    /*public String addDriver(Driver driver) {
        driverDao.save(driver);
        return "added " + driver.toString();
    }*/

    public String addDrivers(List<Driver> drivers) {
        driverDao.addDrivers(drivers);
        return "2022 f1 drivers added";
    }

    public List<Driver> getDrivers() {
        return driverDao.getDrivers();
    }

}
