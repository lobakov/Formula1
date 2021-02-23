package ua.com.foxminded.formula1.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import ua.com.foxminded.formula1.exception.InvalidLineupsException;
import ua.com.foxminded.formula1.exception.RaceMismatchException;
import ua.com.foxminded.formula1.model.Race;
import ua.com.foxminded.formula1.model.RaceRecord;
import ua.com.foxminded.formula1.model.Racer;

public class Championship {

    public Map<String, Racer> getRoster(Map<String, Map<String, String>> abbreviations) {
        Map<String, Racer> result = new HashMap<>();
        abbreviations.forEach((abbreviation, racer) -> {
            racer.forEach((name, team) -> {
                result.putIfAbsent(abbreviation, new Racer(abbreviation, name, team));
            });
        });
        return result;
    }

    public Map<String, Race> getSeasonResults(Map<String, Map<String, String>> starters,
                        Map<String, Map<String, String>> finishers, Map<String, Racer> roster) {
        validate(starters, finishers);
        Map<String, Race> result = new LinkedHashMap<>();
        starters.forEach((date, record) -> {
            Race race = new Race(date);
            result.putIfAbsent(date, race);
            record.forEach((racer, start) -> {
                String finish = finishers.get(date).get(racer);
                race.addRecord(roster.get(racer), new RaceRecord(start, finish));
            });
        });
        return result;
    }

    private void validate(Map<String, Map<String, String>> starters, Map<String, Map<String, String>> finishers) {
        if (starters.size() != finishers.size()) {
            throw new RaceMismatchException("Amount of races in start and end logs are different!");
        }

        Iterator<Entry<String, Map<String, String>>> startersIterator = starters.entrySet().iterator();
        Iterator<Entry<String, Map<String, String>>> finishersIterator = finishers.entrySet().iterator();
        while (startersIterator.hasNext()) {
            Map.Entry<String, Map<String, String>> startersEntry = startersIterator.next();
            Map.Entry<String, Map<String, String>> finishersEntry = finishersIterator.next();
            if (startersEntry.getValue().size() < finishersEntry.getValue().size()) {
                throw new InvalidLineupsException("Amount of racers finished is greater than racers started the race!");
            }
        }
    }
}
