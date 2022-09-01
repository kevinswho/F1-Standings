package com.example.F1Standings2022.Standings.entity;

import javax.persistence.*;

@Entity
@Table(name="race_events_table")

public class RaceEvent {

    @Id
    @SequenceGenerator(
        name = "race_event_sequence",
        sequenceName = "race_event_sequence",
        allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "race_event_sequence"
    )

    private String name;
    private String winner;

    public RaceEvent(String name, String winner) {
        this.name = name;
        this.winner = winner;
    }

    public RaceEvent() { super(); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        String result = String.format("<b>Race: </b>%s <b>Winner: </b>%s",name,winner);
        return result;
    }

}
