package com.example.F1Standings2022.Standings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DriverController {

    private final DriverService driverservice;

    @Autowired
    public DriverController(DriverService driverservice){
        this.driverservice = driverservice;
    }

    @GetMapping
    public List<Driver> getDrivers() {
        return driverservice.getDrivers();
    }

}
