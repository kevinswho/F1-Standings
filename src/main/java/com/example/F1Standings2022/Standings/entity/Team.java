package com.example.F1Standings2022.Standings.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Teams")
public class Team {

    @Id
    private String name;
    private Integer points;

    @Column
    @ElementCollection(targetClass=Driver.class)
    private List<Driver> drivers;

    public Team(String name, Integer points, List<Driver> drivers) {
        this.name = name;
        this.points = points;
        this.drivers = drivers;
    }

    public String getName() {
        return name;
    }

    public Integer getPoints() {
        return points;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public String toString(){
        StringBuffer driversString = new StringBuffer();

        for (int i=0; i<drivers.size(); i++) {
            driversString.append(drivers.get(i).getName());
            if (i!=(drivers.size()-1)) {
                driversString.append(",");
            }
        }

        String result = String.format("Constructor: %s Points: %d Drivers: %s",name,points,driversString.toString());

        return result;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Team)){
            return false;
        } else {
            Team team = (Team) o;
            if (team.getName().equals(name)){
                return true;
            } else {
                return false;
            }
        }
    }
}
