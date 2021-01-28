package ua.com.foxminded.formula1;

import java.io.IOException;
import java.util.Map;

import ua.com.foxminded.formula1.model.Race;
import ua.com.foxminded.formula1.service.Championship;
import ua.com.foxminded.formula1.service.DataParser;
import ua.com.foxminded.formula1.service.FileReader;
import ua.com.foxminded.formula1.view.ConsoleGridPrinter;
import ua.com.foxminded.formula1.view.Formatter;
import ua.com.foxminded.formula1.view.GridFormatter;
import ua.com.foxminded.formula1.view.GridPrinter;

public class Formula1 {
    
    public static void main(String[] args) throws IOException {
        String startLog = "start.log";
        String endLog = "end.log";
        String abbreviationsFile = "abbreviations.txt";

        FileReader fileReader = new FileReader();
        DataParser parser = new DataParser();
        Championship championship = new Championship();

        Map<String, Map<String, String>> startLineup = parser.parseLineup(fileReader.read(startLog));
        Map<String, Map<String, String>> endLineup = parser.parseLineup(fileReader.read(endLog));
        Map<String, Map<String, String>> abbreviations = parser.parseAbbreviations(fileReader.read(abbreviationsFile));
        
        championship.fillRoster(abbreviations);
        championship.fillSeason(startLineup, endLineup);

        Formatter<Map <String, Race>> formatter = new GridFormatter();
        GridPrinter printer = new ConsoleGridPrinter();

        printer.print(formatter.format(championship.getAllRaces()));
    }
}
