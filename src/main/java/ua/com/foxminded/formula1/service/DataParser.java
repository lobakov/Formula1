package ua.com.foxminded.formula1.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataParser {

    private static final int ABBREVIATION_INDEX = 0;
    private static final String DELIMITER = "_";
    private static final int LINE_LENGTH = 26;
    private static final int NAME_INDEX = 1;
    private static final int RACER_START = 0;
    private static final int RACER_END = 3;
    private static final int TEAM_INDEX = 2;
    private static final int TIME_START = 14;
    private static final int TIME_END = 26;
 
    private void verifyFormat(List<String> lines) {
        for (String line: lines) {
            if (line.length() != LINE_LENGTH) {
                throw new WrongLineFormatException("Lineup has invalid record: line length mismatch!");
            }
        }
    }

    public Map<String, Map<String, String>> parseLineup(List<String> lineup) {
        verifyFormat(lineup);

        Map<String, Map<String, String>> result = new HashMap<>();
        String racer;
        String time;
        String date;

        for (String record: lineup) {
            racer = record.substring(RACER_START, RACER_END);
            date = record.substring(RACER_END, TIME_START - 1);
            time =  record.substring(TIME_START, TIME_END);
            result.putIfAbsent(date, new HashMap<>());
            result.get(date).putIfAbsent(racer, time);
        }
        return result;
    }

    public Map<String, Map<String, String>> parseAbbreviations(List<String> abbreviations) {
        Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();
        String[] splitLine = new String[3];

        for (String line: abbreviations) {
            splitLine = line.split(DELIMITER);
            result.put(splitLine[ABBREVIATION_INDEX], Map.of(splitLine[NAME_INDEX], splitLine[TEAM_INDEX]));
        }
        return result;
    }
}
