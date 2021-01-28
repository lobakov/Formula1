package ua.com.foxminded.formula1.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Race {

    private Map<Racer, RaceRecord> race = new LinkedHashMap<>();
    private String date;

    public Race(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void addRecord(Racer racer, RaceRecord record) {
        this.race.putIfAbsent(racer, record);
    }

    public RaceRecord getRecord(Racer racer) {
        return this.race.get(racer);
    }

    public Map<Racer, RaceRecord> getRaceGrid() {
        Map<Racer, RaceRecord> sortedGrid = new LinkedHashMap<>();
        this.race.entrySet()
                 .stream()
                 .sorted(Map.Entry.comparingByValue())
                 .forEachOrdered(x -> sortedGrid.put(x.getKey(), x.getValue()));
        return sortedGrid;
    }

    @Override
    public String toString() {
        return "Race: " + this.date;
    }
}
