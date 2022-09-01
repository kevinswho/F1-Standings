package com.example.F1Standings2022.Standings.dao;

import com.example.F1Standings2022.Standings.entity.Driver;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverRowMapper implements RowMapper<Driver> {

    @Override
    public Driver mapRow(ResultSet rs, int rowNumber) throws SQLException {
        Driver driver = new Driver(rs.getInt("number"), rs.getString("name"),
                rs.getString("team"), rs.getInt("points"));

        return driver;
    }

}
