package ua.com.foxminded.formula1.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.com.foxminded.formula1.model.Race;
import ua.com.foxminded.formula1.model.RaceRecord;
import ua.com.foxminded.formula1.model.Racer;
import ua.com.foxminded.formula1.service.FileReader;

public class GridFormatterTest {

    private static final int ABB_INDEX = 0;
    private static final int FINISH_TIME_INDEX = 1;
    private static final String LINE_SEPARATOR = "-";
    private static final int NAME_INDEX = 1;
    private static final String NL = System.lineSeparator();
    private static final String PARTS_SEPARATOR = "_";
    private static final String RACE_DATE = "2018-05-24";
    private static final int RACE_INDEX = 1;
    private static final int RACER_INDEX = 0;
    private static final int START_TIME_INDEX = 0;
    private static final int TEAM_INDEX = 2;

    private Formatter<Map<String, Race>> formatter = new GridFormatter();
    private Map<String, Race> input;

    @BeforeEach
    void initInput() {
        input = new LinkedHashMap<>();
        input.put(RACE_DATE, new Race(RACE_DATE));
    }

    @Test
    void shouldSortGridProperly() {
        initInputSortedRaceGrid();

        String expected = initExpectedSortedRaceGrid();

        String actual = formatter.format(input);

        assertEquals(expected, actual);
    }

    private void initInputSortedRaceGrid() {
        input.get(RACE_DATE).addRecord(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"),
                new RaceRecord("12:14:12.054", "12:15:24.067"));
        input.get(RACE_DATE).addRecord(new Racer("SVF", "Sebastian Vettel", "FERRARI"),
                new RaceRecord("12:02:58.917", "12:04:03.332"));
        input.get(RACE_DATE).addRecord(new Racer("LHM", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));
    }

    private String initExpectedSortedRaceGrid() {
        return "Race: " + RACE_DATE + NL + "1. Sebastian Vettel | FERRARI | 01:04.415" + NL
                + "2. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.013" + NL
                + "3. Lewis Hamilton | MERCEDES | 01:12.460";
    }

    @Test
    void shouldFormatRaceGridProperly() throws IOException {
        initInputFormattedRaceGrid();

        String expected = initExpectedFormattedRaceGrid();

        String actual = formatter.format(input);

        assertEquals(expected, actual);
    }

    private void initInputFormattedRaceGrid() throws IOException {
        FileReader reader = new FileReader();
        String testLog = "test.log";
        List<String> lines = reader.read(testLog);

        for (String line: lines) {
            String[] tokenizedLine = line.split(LINE_SEPARATOR);
            String[] racerCredentials = tokenizedLine[RACER_INDEX].split(PARTS_SEPARATOR);
            String[] raceTimings = tokenizedLine[RACE_INDEX].split(PARTS_SEPARATOR);

            input.get(RACE_DATE).addRecord(new Racer(racerCredentials[ABB_INDEX], racerCredentials[NAME_INDEX],
                                                     racerCredentials[TEAM_INDEX]),
                                           new RaceRecord(raceTimings[START_TIME_INDEX],
                                                     raceTimings[FINISH_TIME_INDEX]));
        }
    }

    private String initExpectedFormattedRaceGrid() {
        StringJoiner joiner = new StringJoiner("");
        joiner.add("Race: " + RACE_DATE + NL)
              .add("1. Sebastian Vettel | FERRARI | 01:04.415" + NL)
              .add("2. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.013" + NL);
        for (int i = 3; i < 16; i++) {
            joiner.add(i + ". Lewis Hamilton | MERCEDES | 01:12.460" + NL);
        }
        joiner.add("-".repeat(72) + NL).add("16. Lewis Hamilton | MERCEDES | 01:12.460");
        return joiner.toString();
    }
}
