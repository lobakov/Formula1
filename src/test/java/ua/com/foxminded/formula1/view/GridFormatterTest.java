package ua.com.foxminded.formula1.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.foxminded.formula1.model.Race;
import ua.com.foxminded.formula1.model.RaceRecord;
import ua.com.foxminded.formula1.model.Racer;

public class GridFormatterTest {

    private static final String NL = System.lineSeparator();
    private static final String RACE_DATE = "2018-05-24";
    private Formatter<Map <String, Race>> formatter = new GridFormatter();
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
        return "Race: " + RACE_DATE + NL
                + "1. Sebastian Vettel | FERRARI | 01:04.415" + NL
                + "2. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.013" + NL
                + "3. Lewis Hamilton | MERCEDES | 01:12.460";
    }

    @Test
    void shouldFormatRaceGridProperly() {
        initInputFormattedRaceGrid();

        String expected = initExpectedFormattedRaceGrid();

        String actual = formatter.format(input);

        assertEquals(expected, actual);
    }

    private void initInputFormattedRaceGrid() {
        input.get(RACE_DATE).addRecord(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"),
                new RaceRecord("12:14:12.054", "12:15:24.067"));
        input.get(RACE_DATE).addRecord(new Racer("SVF", "Sebastian Vettel", "FERRARI"),
                new RaceRecord("12:02:58.917", "12:04:03.332"));

        Character letter = 'M';
        for (int i = 1; i < 15; i ++) {
            input.get(RACE_DATE).addRecord(new Racer("LH" + (letter++).toString(), "Lewis Hamilton", "MERCEDES"),
                    new RaceRecord("12:18:20.125", "12:19:32.585"));
        }
    }

    private String initExpectedFormattedRaceGrid() {
        StringJoiner joiner = new StringJoiner("");
        joiner.add("Race: " + RACE_DATE + NL)
              .add("1. Sebastian Vettel | FERRARI | 01:04.415" + NL)
              .add("2. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.013" + NL);
        for (int i = 3; i < 16; i ++) {
            joiner.add(i + ". Lewis Hamilton | MERCEDES | 01:12.460" + NL);
        }
        joiner.add("-".repeat(72) + NL)
              .add("16. Lewis Hamilton | MERCEDES | 01:12.460");
        return joiner.toString();
    }
}
