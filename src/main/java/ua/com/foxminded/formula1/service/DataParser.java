package ua.com.foxminded.formula1.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import ua.com.foxminded.formula1.exception.WrongLineFormatException;

public class DataParser {

    private static final int ABBREVIATION_INDEX = 0;
    private static final String ABBREVIATION_PATTERN = "^[A-Z]{3}";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DELIMITER = "_";
    private static final int LINE_LENGTH = 26;
    private static final int LINEUP_LEFT_INDEX = 0;
    private static final int LINEUP_RIGHT_INDEX = 1;
    private static final int MARKUP_LINEUP_PARTS = 2;
    private static final int MARKUP_ABBREVIATION_PARTS = 3;
    private static final int NAME_INDEX = 1;
    private static final String NAME_PATTERN = "^[a-zA-Z ]*$";
    private static final int RACER_START = 0;
    private static final int RACER_END = 3;
    private static final int TEAM_INDEX = 2;
    private static final String TEAM_PATTERN = "^[a-zA-Z0-9 ]*$";
    private static final String TIME_FORMAT = "HH:mm:ss.SSS";
    private static final int TIME_END = 26;
    private static final int TIME_START = 13;

    public Map<String, Map<String, String>> parseLineup(List<String> lineup) {
        verifyLineupInput(lineup);

        Map<String, Map<String, String>> result = new HashMap<>();
        String racer;
        String time;
        String date;

        for (String record: lineup) {
            racer = record.substring(RACER_START, RACER_END);
            date = record.substring(RACER_END, TIME_START);
            time =  record.substring(TIME_START + 1, TIME_END);
            result.putIfAbsent(date, new HashMap<>());
            result.get(date).putIfAbsent(racer, time);
        }
        return result;
    }

    private void verifyLineupInput(List<String> lines) {
        for (String line: lines) {
            if (!lineFormatValid(line)) {
                throw new WrongLineFormatException("Lineup has invalid record: line format mismatch!");
            }
        }
    }

    private boolean lineFormatValid(String line) {
        String[] tokenizedLine = line.split(DELIMITER);

        return validLineLength(line)
                && validMarkup(tokenizedLine, MARKUP_LINEUP_PARTS)
                && validFormat(tokenizedLine[LINEUP_LEFT_INDEX]
                                            .substring(RACER_START, RACER_END), ABBREVIATION_PATTERN)
                && validDateTimeFormat(tokenizedLine[LINEUP_LEFT_INDEX]
                                                    .substring(RACER_END, TIME_START - 1), DATE_FORMAT)
                && validDateTimeFormat(tokenizedLine[LINEUP_RIGHT_INDEX], TIME_FORMAT);
    }

    private boolean validLineLength(String line) {
        return line.length() == LINE_LENGTH;
    }

    private boolean validMarkup(String[] tokenizedLine, int length) {
        return tokenizedLine.length == length;
    }

    private boolean validFormat(String line, String pattern) {
        Pattern formatPattern = Pattern.compile(pattern);
        return formatPattern.matcher(line).matches();
    }

    private boolean validDateTimeFormat(String dateTime, String format) {
        try {
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat(format);
            dateTimeFormat.setLenient(false);
            dateTimeFormat.parse(dateTime);
        } catch (DateTimeParseException | ParseException ex) {
            return false;
        }
        return true;
    }

    public Map<String, Map<String, String>> parseAbbreviations(List<String> abbreviations) {
        verifyAbbreviationsInput(abbreviations);

        Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();
        String[] splitLine = new String[MARKUP_ABBREVIATION_PARTS];

        for (String line: abbreviations) {
            splitLine = line.split(DELIMITER);
            result.put(splitLine[ABBREVIATION_INDEX], Map.of(splitLine[NAME_INDEX], splitLine[TEAM_INDEX]));
        }
        return result;
    }

    private void verifyAbbreviationsInput(List<String> lines) {
        for (String line: lines) {
            if (!abbreviationsFormatValid(line)) {
                throw new WrongLineFormatException("Abbreviations have invalid record: line format mismatch!");
            }
        }
    }

    private boolean abbreviationsFormatValid(String line) {
        String[] tokenizedLine = line.split(DELIMITER);

        return validMarkup(tokenizedLine, MARKUP_ABBREVIATION_PARTS)
                && validFormat(tokenizedLine[ABBREVIATION_INDEX], ABBREVIATION_PATTERN)
                && validFormat(tokenizedLine[NAME_INDEX], NAME_PATTERN)
                && validFormat(tokenizedLine[TEAM_INDEX], TEAM_PATTERN);
    }
}
