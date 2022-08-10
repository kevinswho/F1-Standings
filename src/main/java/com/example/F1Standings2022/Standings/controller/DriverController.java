package com.example.F1Standings2022.Standings.controller;

import com.example.F1Standings2022.Standings.entity.Driver;
import com.example.F1Standings2022.Standings.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/drivers")
public class DriverController {

    private final DriverService driverservice;

    @Autowired
    public DriverController(DriverService driverservice){
        this.driverservice = driverservice;
    }

    @GetMapping("/data")
    public List<Driver> getDrivers() {
        return driverservice.getDrivers();
    }

    @GetMapping("/text")
    public String driverDisplay() {
        List<Driver> drivers = driverservice.getDrivers();

        StringBuffer driverString = new StringBuffer();

        for (int i=0; i<drivers.size(); i++) {
            driverString.append(drivers.get(i).toString());
            driverString.append("<br />");
        }

        return driverString.toString();
    }

}
