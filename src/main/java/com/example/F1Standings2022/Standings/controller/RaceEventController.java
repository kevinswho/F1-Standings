package com.example.F1Standings2022.Standings.controller;

import com.example.F1Standings2022.Standings.entity.RaceEvent;
import com.example.F1Standings2022.Standings.service.RaceEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/races")
public class RaceEventController {

    private final RaceEventService raceEventService;

    @Autowired
    public RaceEventController(RaceEventService raceEventService) { this.raceEventService = raceEventService; }

    @GetMapping("/data")
    public List<RaceEvent> getRaceEvents() {
        return raceEventService.getRaceEvents();
    }

    @GetMapping("/text")
    public String raceEventDisplay() {
        List<RaceEvent> raceEvents = raceEventService.getRaceEvents();

        StringBuffer raceEventsString = new StringBuffer();

        for (int i=0; i<raceEvents.size(); i++) {
            raceEventsString.append(raceEvents.get(i).toString());
            raceEventsString.append("<br />");
        }

        return raceEventsString.toString();
    }

}
