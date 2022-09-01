package com.example.F1Standings2022.Standings.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="constructors_table")
public class ConstructorTeam {

    @Id
    @SequenceGenerator(
            name = "constructor_team_sequence",
            sequenceName = "constructor_team_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "constructor_team_sequence"
    )

    private String name;

    @Transient
    private List<Driver> driversList;

    private String drivers;

    private Integer points;
    private Integer driverPointsDifference;

    public ConstructorTeam(String name, List<Driver> driversList, Integer points, Integer driverPointsDifference) {
        this.name = name;
        this.driversList = driversList;

        StringBuffer driversString = new StringBuffer();
        driversList.forEach(x -> driversString.append(x.getName() + ", "));
        driversString.setLength(driversString.length() - 2);
        this.drivers = driversString.toString();

        this.points = points;
        this.driverPointsDifference = driverPointsDifference;
    }

    public ConstructorTeam() { super(); }

    public String getName() {
        return name;
    }

    public String getDrivers() {
        return drivers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Driver> getDriversList() {
        return driversList;
    }

    public void setDriversList(ArrayList<Driver> drivers) {
        this.driversList = drivers;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getDriverPointsDifference() {
        return driverPointsDifference;
    }

    public void setDriverPointsDifference(Integer driverPointsDifference) {
        this.driverPointsDifference = driverPointsDifference;
    }

    public boolean containsDriver(Driver driver) {
        boolean containsDriverResult = false;

        for (int i=0; i<driversList.size(); i++) {
            if (driversList.get(i).getDriverNumber() == driver.getDriverNumber()) {
                containsDriverResult = true;
            }
        }

        return containsDriverResult;
    }

    @Override
    public String toString() {
        StringBuffer driversStringBuffer = new StringBuffer();
        for (int i=0; i<driversList.size(); i++) {
            driversStringBuffer.append(driversList.get(i).getName() + ", ");
        }

        driversStringBuffer.setLength(driversStringBuffer.length() - 2);

        String driverString = driversStringBuffer.toString();

        String result = String.format("<b>Constructor:</b> %s, <b>Drivers:</b> %s, <b>Championship Points:</b> %d, <b>Driver Performance Difference:</b> %d"
                , name, drivers, points, driverPointsDifference);
        return result;
    }

}
