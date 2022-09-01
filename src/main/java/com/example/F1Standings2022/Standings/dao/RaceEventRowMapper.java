package com.example.F1Standings2022.Standings.dao;

import com.example.F1Standings2022.Standings.entity.RaceEvent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RaceEventRowMapper implements RowMapper<RaceEvent> {

    @Override
    public RaceEvent mapRow(ResultSet rs, int rowNumber) throws SQLException {
        RaceEvent race = new RaceEvent(rs.getString("name"), rs.getString("winner"));
        return race;
    }

}
