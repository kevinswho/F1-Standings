package com.example.F1Standings2022.Standings;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
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

    private Integer driverNumber;
    private String name;
    private String team;
    private Integer points;

    public Driver(Integer driverNumber, String name, String team, Integer points) {
        this.driverNumber = driverNumber;
        this.name = name;
        this.team = team;
        this.points = points;
    }

    public Driver() {
        super();
    }

    public Integer getDriverNumber() {
        return driverNumber;
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
}
