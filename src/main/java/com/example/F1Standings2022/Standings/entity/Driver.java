package com.example.F1Standings2022.Standings.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="drivers_table")
public class Driver {
    @Id
    @SequenceGenerator(
            name = "driver_sequence",
            sequenceName = "driver_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "driver_sequence"
    )

    private Integer number;
    private String name;
    private String team;
    private Integer points;

    public Driver(Integer driverNumber, String name, String team, Integer points) {
        this.number = driverNumber;
        this.name = name;
        this.team = team;
        this.points = points;
    }

    public Driver() {
        super();
    }

    public Integer getDriverNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public Integer getPoints() {
        return points;
    }

    @Override
    public String toString() {
        String result = String.format("<b>Driver/Number:</b> %s - %d, <b>Team:</b> %s, <b>Championship Points:</b> %d", name, number, team, points);
        return result;
    }

    @Override
    public boolean equals (Object o) {
        if (!(o instanceof Driver)){
            return false;
        } else {
            Driver driver = (Driver) o;
            if (driver.getDriverNumber() == number) {
                return true;
            } else {
                return false;
            }
        }
    }
}
