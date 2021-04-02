package ua.com.foxminded.formula1;

import java.io.IOException;

import ua.com.foxminded.formula1.service.ChampionshipService;
import ua.com.foxminded.formula1.service.DataParser;
import ua.com.foxminded.formula1.service.FileReader;
import ua.com.foxminded.formula1.view.GridFormatter;

public class Formula1Application {

    public static void main(String[] args) throws IOException {
        String startLog = "start.log";
        String finishLog = "end.log";
        String abbreviations = "abbreviations.txt";

        ChampionshipService service = new ChampionshipService(new FileReader(), new DataParser(), new GridFormatter());
        System.out.println(service.getResults(startLog, finishLog, abbreviations));
    }
}
