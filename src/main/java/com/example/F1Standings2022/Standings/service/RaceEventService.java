package com.example.F1Standings2022.Standings.service;

import com.example.F1Standings2022.Standings.dao.RaceEventDao;
import com.example.F1Standings2022.Standings.entity.RaceEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceEventService {

    @Autowired
    private RaceEventDao raceEventDao;

    public String addRaces(List<RaceEvent> races) {
        raceEventDao.addRaceEvents(races);
        return "2022 F1 races added";
    }

    public List<RaceEvent> getRaceEvents() {
        return raceEventDao.getRaceEvents();
    }

}
