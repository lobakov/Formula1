package ua.com.foxminded.formula1.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import ua.com.foxminded.formula1.model.Race;
import ua.com.foxminded.formula1.model.RaceRecord;
import ua.com.foxminded.formula1.model.Racer;

public class Championship {

    private Map<String, Racer> roster = new HashMap<>();
    private Map<String, Race> races = new LinkedHashMap<>();

    public Race getRaceByDate(String date) {
        return this.races.get(date);
    }

    public Map<String, Race> getAllRaces() {
        return this.races;
    }

    public void addRace(String date) {
        this.races.putIfAbsent(date, new Race(date));
    }

    public void fillSeason(Map<String, Map<String, String>> starters, Map<String, Map<String, String>> finishers) {
        validate(starters, finishers);

        Race race;
        String date;
        String start;
        String finish;
        String abbreviation;

        for (Map.Entry<String, Map<String, String>> entries: starters.entrySet()) {
            date = entries.getKey();
            race = new Race(date);
            this.races.putIfAbsent(date, race);
            for (Map.Entry<String, String> entry: entries.getValue().entrySet()) {
                abbreviation = entry.getKey();
                start = entry.getValue();
                finish = finishers.get(date).get(abbreviation);
                race.addRecord(this.roster.get(abbreviation), new RaceRecord(start, finish));
            }
        }
    }

    private void validate(Map<String, Map<String, String>> starters, Map<String, Map<String, String>> finishers) {
        if (finishers.size() > starters.size()) {
            throw new InvalidLineupsException("Amount of racers finished the race is greater than the amount of racers started the race!");
        }
    }

    public void fillRoster(Map<String, Map<String, String>> source) {
        String abbreviation;
        for (Map.Entry<String, Map<String, String>> entries: source.entrySet()) {
            abbreviation = entries.getKey();
            for (Map.Entry<String, String> entry: entries.getValue().entrySet()) {
                addRacerToRoster(abbreviation, entry.getKey(), entry.getValue());
            }       
        }
    }

    public Map<String, Racer> getRoster() {
        return this.roster;
    }

    public Racer getRacerByAbbreviation(String abbreviation) {
        return this.roster.get(abbreviation);
    }

    public void addRacerToRoster(String abbreviation, String name, String team) {
        this.roster.putIfAbsent(abbreviation, new Racer(abbreviation, name, team));
    }

    @Override
    public String toString() {
        return this.roster.toString() + System.lineSeparator() + this.races.toString();
    }
}
