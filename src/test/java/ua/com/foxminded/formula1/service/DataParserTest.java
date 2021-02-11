package ua.com.foxminded.formula1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.com.foxminded.formula1.exception.WrongLineFormatException;

public class DataParserTest {

    private List<String> lines;
    private DataParser dataParser = new DataParser();

    @BeforeEach
    void initInputLines() {
        lines = new ArrayList<>();
    }

    @Test
    void shouldThrowExceptionWhenWrongLinupLineLength() {
        lines.add("SVF2018-05-24_12:02:58.91");

        Exception thrownException = assertThrows(WrongLineFormatException.class,
                () -> dataParser.parseLineup(lines));

        assertEquals("Lineup has invalid record: line format mismatch!", thrownException.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenWrongLinupMarkupFormat() {
        lines.add("SVF2018-05-24|12:02:58.917");

        Exception thrownException = assertThrows(WrongLineFormatException.class,
                () -> dataParser.parseLineup(lines));

        assertEquals("Lineup has invalid record: line format mismatch!", thrownException.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenWrongLinupAbbreviationFormat() {
        lines.add("S1F2018-05-24_12:02:58.917");

        Exception thrownException = assertThrows(WrongLineFormatException.class,
                () -> dataParser.parseLineup(lines));

        assertEquals("Lineup has invalid record: line format mismatch!", thrownException.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenWrongLinupDateFormat() {
        lines.add("SVF2018-24-14_12:02:58.917");

        Exception thrownException = assertThrows(WrongLineFormatException.class,
                () -> dataParser.parseLineup(lines));

        assertEquals("Lineup has invalid record: line format mismatch!", thrownException.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenWrongLinupTimeFormat() {
        lines.add("SVF2018-05-24_12:02:58:917");

        Exception thrownException = assertThrows(WrongLineFormatException.class,
                () -> dataParser.parseLineup(lines));

        assertEquals("Lineup has invalid record: line format mismatch!", thrownException.getMessage());
    }

    @Test
    void shouldProperlyParseLineup() {
        lines.add("SVF2018-05-24_12:02:58.917");
        lines.add("NHR2018-05-24_12:02:49.914");
        lines.add("FAM2018-05-24_12:13:04.512");
        lines.add("FAM2018-05-28_12:13:04.512");

        Map<String, Map<String, String>> expected = new LinkedHashMap<>();
        expected.put("2018-05-24", new LinkedHashMap<>());
        expected.get("2018-05-24").put("SVF", "12:02:58.917");
        expected.get("2018-05-24").put("FAM", "12:13:04.512");
        expected.get("2018-05-24").put("NHR", "12:02:49.914");
        expected.put("2018-05-28", new LinkedHashMap<>());
        expected.get("2018-05-28").put("FAM", "12:13:04.512");

        Map<String, Map<String, String>> actual = dataParser.parseLineup(lines);

        assertEquals(expected.toString(), actual.toString());
    }

    void shouldThrowExceptionWhenWrongAbbreviationsMarkupFormat() {
        lines.add("DRR Daniel Ricciardo RED BULL RACING TAG HEUER");

        Exception thrownException = assertThrows(WrongLineFormatException.class,
                () -> dataParser.parseAbbreviations(lines));

        assertEquals("Abbreviations have invalid record: line format mismatch!", thrownException.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenWrongAbbreviationsAbbreviationFormat() {
        lines.add("D1R_Daniel Ricciardo_RED BULL RACING TAG HEUER");

        Exception thrownException = assertThrows(WrongLineFormatException.class,
                () -> dataParser.parseAbbreviations(lines));

        assertEquals("Abbreviations have invalid record: line format mismatch!", thrownException.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenWrongAbbreviationsNameFormat() {
        lines.add("DRR_Daniel R1cciardo_RED BULL RACING TAG HEUER");

        Exception thrownException = assertThrows(WrongLineFormatException.class,
                () -> dataParser.parseAbbreviations(lines));

        assertEquals("Abbreviations have invalid record: line format mismatch!", thrownException.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenWrongAbbreviationsTeamFormat() {
        lines.add("DRR_Daniel Ricciardo_RED BULL&RACING TAG HEUER");

        Exception thrownException = assertThrows(WrongLineFormatException.class,
                () -> dataParser.parseAbbreviations(lines));

        assertEquals("Abbreviations have invalid record: line format mismatch!", thrownException.getMessage());
    }

    @Test
    void shouldProperlyParseAbbreviations() {
        lines.add("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER");
        lines.add("SVF_Sebastian Vettel_FERRARI");
        lines.add("LHM_Lewis Hamilton_MERCEDES");

        Map<String, Map<String, String>> expected = new LinkedHashMap<>();
        expected.put("LHM", new LinkedHashMap<>());
        expected.put("SVF", new LinkedHashMap<>());
        expected.put("DRR", new LinkedHashMap<>());
        expected.get("LHM").put("Lewis Hamilton", "MERCEDES");
        expected.get("SVF").put("Sebastian Vettel", "FERRARI");
        expected.get("DRR").put("Daniel Ricciardo", "RED BULL RACING TAG HEUER");

        Map<String, Map<String, String>> actual = dataParser.parseAbbreviations(lines);

        assertEquals(expected.toString(), actual.toString());
    }
}
