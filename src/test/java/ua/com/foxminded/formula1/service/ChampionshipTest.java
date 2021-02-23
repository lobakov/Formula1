package ua.com.foxminded.formula1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.com.foxminded.formula1.exception.InvalidLineupsException;
import ua.com.foxminded.formula1.model.Race;
import ua.com.foxminded.formula1.model.RaceRecord;
import ua.com.foxminded.formula1.model.Racer;

public class ChampionshipTest {

    private static Map<String, Map<String, String>> startLineup;
    private static Map<String, Map<String, String>> endLineup;
    private static Map<String, Map<String, String>> abbreviations;
    private static Map<String, Racer> roster;

    private Championship championship;

    @BeforeAll
    private static void initData() {
        startLineup = new LinkedHashMap<>();
        startLineup.put("2018-05-24", new LinkedHashMap<>());
        startLineup.get("2018-05-24").put("DRR", "12:14:12.054");
        startLineup.get("2018-05-24").put("SVF", "12:02:58.917");
        startLineup.get("2018-05-24").put("LHM", "12:18:20.125");

        endLineup = new LinkedHashMap<>();
        endLineup.put("2018-05-24", new LinkedHashMap<>());
        endLineup.get("2018-05-24").put("DRR", "12:15:24.067");
        endLineup.get("2018-05-24").put("SVF", "12:04:03.332");
        endLineup.get("2018-05-24").put("LHM", "12:19:32.585");

        abbreviations = new LinkedHashMap<>();
        abbreviations.put("DRR", new HashMap<>());
        abbreviations.get("DRR").put("Daniel Ricciardo", "RED BULL RACING TAG HEUER");
        abbreviations.put("SVF", new HashMap<>());
        abbreviations.get("SVF").put("Sebastian Vettel", "FERRARI");
        abbreviations.put("LHM", new HashMap<>());
        abbreviations.get("LHM").put("Lewis Hamilton", "MERCEDES");
        abbreviations.put("RRR", new HashMap<>());
        abbreviations.get("RRR").put("Mr Racer", "DEFAULT TEAM");
    }

    @BeforeEach
    void initChampionship() {
        championship = new Championship();
        roster = championship.getRoster(abbreviations);
    }

    @Test
    void shouldProperlyFillRoster() {
        Map<String, Racer> expected = new LinkedHashMap<>();
        expected.put("LHM", new Racer("LHM", "Lewis Hamilton", "MERCEDES"));
        expected.put("SVF", new Racer("SVF", "Sebastian Vettel", "FERRARI"));
        expected.put("RRR", new Racer("RRR", "Mr Racer", "DEFAULT TEAM"));
        expected.put("DRR", new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"));

        Map<String, Racer> actual = roster;
        assertEquals(expected, actual);
    }

    @Test
    void shouldProperlyFillSeason() {
        Map<String, Race> expected = new LinkedHashMap<>();

        expected.put("2018-05-24", new Race("2018-05-24"));
        expected.get("2018-05-24").addRecord(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"),
                new RaceRecord("12:14:12.054", "12:15:24.067"));
        expected.get("2018-05-24").addRecord(new Racer("SVF", "Sebastian Vettel", "FERRARI"),
                new RaceRecord("12:02:58.917", "12:04:03.332"));
        expected.get("2018-05-24").addRecord(new Racer("LHM", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));
        expected.get("2018-05-24").addRecord(new Racer("RRR", "Mr Racer", "DEFAULT TEAM"),
                new RaceRecord("11:11:11.585", "12:51:11.585"));

        Map<String, Race> actual = championship.getSeasonResults(startLineup, endLineup, roster);

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionWhenFinishersMoreThanStarters() {
        endLineup.get("2018-05-24").put("RRR", "12:51:11.585");

        Exception thrownException = assertThrows(InvalidLineupsException.class,
                () -> championship.getSeasonResults(startLineup, endLineup, roster));

        assertEquals("Amount of racers finished is greater than racers started the race!",
                thrownException.getMessage());

        startLineup.get("2018-05-24").put("RRR", "11:11:11.585");
    }
}
