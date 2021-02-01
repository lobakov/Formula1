package ua.com.foxminded.formula1.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import ua.com.foxminded.formula1.exception.InvalidLineupsException;
import ua.com.foxminded.formula1.model.Race;
import ua.com.foxminded.formula1.model.RaceRecord;
import ua.com.foxminded.formula1.model.Racer;

public class Championship {

    private Map<String, Racer> roster = new HashMap<>();
    private Map<String, Race> season = new LinkedHashMap<>();

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
            this.season.putIfAbsent(date, race);
            for (Map.Entry<String, String> entry: entries.getValue().entrySet()) {
                abbreviation = entry.getKey();
                start = entry.getValue();
                finish = finishers.get(date).get(abbreviation);
                race.addRecord(this.roster.get(abbreviation), new RaceRecord(start, finish));
            }
        }
    }

    private void validate(Map<String, Map<String, String>> starters, Map<String, Map<String, String>> finishers) {
        boolean sizeEquals = true;
        if (starters.size() >= finishers.size()) {
            Iterator<Entry<String, Map<String, String>>> startersIterator = starters.entrySet().iterator();
            Iterator<Entry<String, Map<String, String>>> finishersIterator = finishers.entrySet().iterator();
            while (startersIterator.hasNext()) {
                Map.Entry<String, Map<String, String>> startersEntry = startersIterator.next();
                Map.Entry<String, Map<String, String>> finishersEntry = finishersIterator.next();
                if (!(startersEntry.getValue().size() == finishersEntry.getValue().size())) {
                    sizeEquals = false;
                    break;
                }
            }
        } else {
            sizeEquals = false;
        }
        if (!sizeEquals) {
            throw new InvalidLineupsException("Amount of racers finished is greater than racers started the race!");
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

    private void addRacerToRoster(String abbreviation, String name, String team) {
        this.roster.putIfAbsent(abbreviation, new Racer(abbreviation, name, team));
    }

    public Map<String, Race> getSeason() {
        return this.season;
    }

    public Map<String, Racer> getRoster() {
        return this.roster;
    }

    @Override
    public String toString() {
        return this.roster.toString() + System.lineSeparator() + this.season.toString();
    }
}
