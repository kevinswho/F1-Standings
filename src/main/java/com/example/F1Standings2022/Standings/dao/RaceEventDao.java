package com.example.F1Standings2022.Standings.dao;

import com.example.F1Standings2022.Standings.entity.RaceEvent;
import com.example.F1Standings2022.Standings.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class RaceEventDao extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;

    public void addRaceEvent(RaceEvent race) {
        int update = getJdbcTemplate().update("INSERT INTO race_events_table(name,winner) VALUES(?,?)",
                race.getName(), race.getWinner());

        if (update > 0)
            System.out.println(race.getName() + " with winner " + race.getWinner() + " has been added to the race events table.");
    }

    public void addRaceEvents(List<RaceEvent> races) {
        races.forEach(x -> addRaceEvent(x));
    }

    public List<RaceEvent> getRaceEvents() {
        String SQL = "SELECT * FROM race_events_table";
        return getJdbcTemplate().query(SQL, new RaceEventRowMapper());
    }

    @PostConstruct
    public void init() {
        setDataSource(dataSource);
    }

}
