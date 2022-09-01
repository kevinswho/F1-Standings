package com.example.F1Standings2022.Standings.entity;

import com.example.F1Standings2022.Standings.service.RaceEventService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RaceEventConfig {

    private RaceEventService raceEventService;

    @Autowired
    public RaceEventConfig(RaceEventService raceEventService) {
        this.raceEventService = raceEventService;
    }

    public static List<RaceEvent> getRaceEventsList(){
        final String raceEventsURL = "https://ergast.com/api/f1/2022/results/1.json";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(raceEventsURL, String.class);

        JSONObject obj = new JSONObject(result);
        JSONArray raceEventsWinners = obj.getJSONObject("MRData")
                .getJSONObject("RaceTable")
                .getJSONArray("Races")
                ;

        List<RaceEvent> races = new ArrayList<>();

        for(int i=0; i<raceEventsWinners.length(); i++) {
            //System.out.println(raceEventsWinners);
            String raceName = raceEventsWinners.getJSONObject(i).get("raceName").toString();

            String raceWinnerFirstName = raceEventsWinners.getJSONObject(i).getJSONArray("Results").
                    getJSONObject(0).getJSONObject("Driver").get("givenName").toString();
            String raceWinnerLastName = raceEventsWinners.getJSONObject(i).getJSONArray("Results").
                    getJSONObject(0).getJSONObject("Driver").get("familyName").toString();
            String raceWinner = raceWinnerFirstName + " " + raceWinnerLastName;

            RaceEvent curr = new RaceEvent(raceName, raceWinner);
            //System.out.println(curr.toString());
            races.add(curr);
        }

        return races;
    }

    @Bean
    CommandLineRunner commandLineRunnerRaceEvents(){
        return args -> {
            List<RaceEvent> pullRaceEvents = getRaceEventsList();
            raceEventService.addRaces(pullRaceEvents);
        };
    }

}
