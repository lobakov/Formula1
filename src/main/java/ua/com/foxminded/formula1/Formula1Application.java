package ua.com.foxminded.formula1;

import java.io.IOException;

import ua.com.foxminded.formula1.service.ChampionshipService;

public class Formula1Application {

    public static void main(String[] args) throws IOException {
        String startLog = "start.log";
        String finishLog = "end.log";
        String abbreviationsDictionary = "abbreviations.txt";
        ChampionshipService championshipService = new ChampionshipService();
        championshipService.printResults(startLog, finishLog, abbreviationsDictionary);
    }
}
