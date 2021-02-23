package ua.com.foxminded.formula1.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Race {

    private Map<Racer, RaceRecord> records = new LinkedHashMap<>();
    private String date;

    public Race(String date) {
        this.date = date;
    }

    public void addRecord(Racer racer, RaceRecord record) {
        this.records.putIfAbsent(racer, record);
    }

    public Map<Racer, RaceRecord> getRaceGrid() {
        Map<Racer, RaceRecord> sortedGrid = new LinkedHashMap<>();
        this.records.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(record -> sortedGrid.put(record.getKey(), record.getValue()));
        return sortedGrid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Race)) {
            return false;
        }
        Race another = (Race) obj;
        return this.date.equals(another.date) && this.records.equals(another.records);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + this.date.hashCode();
        for (Map.Entry<Racer, RaceRecord> entry : this.records.entrySet()) {
            hash = 11 * hash + entry.getKey().hashCode() + entry.getValue().hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        return "Race: " + this.date;
    }
}
