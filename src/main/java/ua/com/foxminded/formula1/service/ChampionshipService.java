package ua.com.foxminded.formula1.service;

import java.io.IOException;
import java.util.Map;

import ua.com.foxminded.formula1.model.Race;
import ua.com.foxminded.formula1.model.Racer;
import ua.com.foxminded.formula1.view.Formatter;

public class ChampionshipService {

    private Reader reader;
    private DataParser parser;
    private Formatter<Map <String, Race>> formatter;

    public ChampionshipService(Reader reader, DataParser parser, Formatter<Map<String, Race>> formatter) {
        this.reader = reader;
        this.parser = parser;
        this.formatter = formatter;
    }

    public String getResults(String startLog, String finishLog, String dictionary) throws IOException {
        Map<String, Map<String, String>> startLineup = parser.parseLineup(reader.read(startLog));
        Map<String, Map<String, String>> endLineup = parser.parseLineup(reader.read(finishLog));
        Map<String, Map<String, String>> abbreviations = parser.parseAbbreviations(reader.read(dictionary));

        Championship championship = new Championship();
        Map<String, Racer> roster = championship.getRoster(abbreviations);
        Map<String, Race> season = championship.getSeasonResults(startLineup, endLineup, roster);
        return formatter.format(season);
    }
}
