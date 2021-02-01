package ua.com.foxminded.formula1.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import ua.com.foxminded.formula1.model.Race;
import ua.com.foxminded.formula1.model.RaceRecord;
import ua.com.foxminded.formula1.model.Racer;

public class GridFormatterTest {

    private static final String NL = System.lineSeparator();
    private Formatter<Map <String, Race>> formatter = new GridFormatter();

    @Test
    void shouldSortGridProperly() {
        Map<String, Race> input = new LinkedHashMap<>();
        input.put("2018-05-24", new Race("2018-05-24"));
        input.get("2018-05-24").addRecord(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"),
                new RaceRecord("12:14:12.054", "12:15:24.067"));
        input.get("2018-05-24").addRecord(new Racer("SVF", "Sebastian Vettel", "FERRARI"),
                new RaceRecord("12:02:58.917", "12:04:03.332"));
        input.get("2018-05-24").addRecord(new Racer("LHM", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));

        String expected = "Race: 2018-05-24" + NL
                + "1. Sebastian Vettel | FERRARI | 01:04.415" + NL
                + "2. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.013" + NL
                + "3. Lewis Hamilton | MERCEDES | 01:12.460";

        String actual = formatter.format(input);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFormatRaceGridProperly() {
        Map<String, Race> input = new LinkedHashMap<>();
        input.put("2018-05-24", new Race("2018-05-24"));
        input.get("2018-05-24").addRecord(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"),
                new RaceRecord("12:14:12.054", "12:15:24.067"));
        input.get("2018-05-24").addRecord(new Racer("SVF", "Sebastian Vettel", "FERRARI"),
                new RaceRecord("12:02:58.917", "12:04:03.332"));
        input.get("2018-05-24").addRecord(new Racer("LHM", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));
        input.get("2018-05-24").addRecord(new Racer("LHA", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));
        input.get("2018-05-24").addRecord(new Racer("LHB", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));
        input.get("2018-05-24").addRecord(new Racer("LHC", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));
        input.get("2018-05-24").addRecord(new Racer("LHD", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));
        input.get("2018-05-24").addRecord(new Racer("LHE", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));
        input.get("2018-05-24").addRecord(new Racer("LHF", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));
        input.get("2018-05-24").addRecord(new Racer("LHG", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));
        input.get("2018-05-24").addRecord(new Racer("LHH", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));
        input.get("2018-05-24").addRecord(new Racer("LHI", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));
        input.get("2018-05-24").addRecord(new Racer("LHJ", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));
        input.get("2018-05-24").addRecord(new Racer("LHK", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));
        input.get("2018-05-24").addRecord(new Racer("LHL", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));
        input.get("2018-05-24").addRecord(new Racer("LHN", "Lewis Hamilton", "MERCEDES"),
                new RaceRecord("12:18:20.125", "12:19:32.585"));
        
        String expected = "Race: 2018-05-24" + NL
                + "1. Sebastian Vettel | FERRARI | 01:04.415" + NL
                + "2. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.013" + NL
                + "3. Lewis Hamilton | MERCEDES | 01:12.460" + NL
                + "4. Lewis Hamilton | MERCEDES | 01:12.460" + NL
                + "5. Lewis Hamilton | MERCEDES | 01:12.460" + NL
                + "6. Lewis Hamilton | MERCEDES | 01:12.460" + NL
                + "7. Lewis Hamilton | MERCEDES | 01:12.460" + NL
                + "8. Lewis Hamilton | MERCEDES | 01:12.460" + NL
                + "9. Lewis Hamilton | MERCEDES | 01:12.460" + NL
                + "10. Lewis Hamilton | MERCEDES | 01:12.460" + NL
                + "11. Lewis Hamilton | MERCEDES | 01:12.460" + NL
                + "12. Lewis Hamilton | MERCEDES | 01:12.460" + NL
                + "13. Lewis Hamilton | MERCEDES | 01:12.460" + NL
                + "14. Lewis Hamilton | MERCEDES | 01:12.460" + NL
                + "15. Lewis Hamilton | MERCEDES | 01:12.460" + NL
                + "-".repeat(72) + NL
                + "16. Lewis Hamilton | MERCEDES | 01:12.460";

        String actual = formatter.format(input);

        assertEquals(expected, actual);
    }
}
