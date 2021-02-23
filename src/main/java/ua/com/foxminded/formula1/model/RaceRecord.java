package ua.com.foxminded.formula1.model;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RaceRecord implements Comparable<RaceRecord> {

    private static final String TIME_FORMAT = "mm:ss.SSS";

    private final int raceTime;
    private final String raceTimeString;
    private final String startTime;
    private final String finishTime;

    public RaceRecord(String start, String finish) {
        this.startTime = start;
        this.finishTime = finish;
        this.raceTime = computeRaceTime();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        Instant millis = Instant.ofEpochMilli(raceTime);
        LocalTime localTime = LocalTime.ofInstant(millis, ZoneId.systemDefault());
        this.raceTimeString = localTime.format(formatter);
    }

    public String getRaceTimeString() {
        return this.raceTimeString;
    }

    @Override
    public int compareTo(RaceRecord anotherRecord) {
        return this.raceTime - anotherRecord.raceTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof RaceRecord)) {
            return false;
        }
        RaceRecord another = (RaceRecord) obj;
        return this.startTime.equals(another.startTime) && this.finishTime.equals(another.finishTime);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.startTime.hashCode();
        hash = 31 * hash + this.finishTime.hashCode();
        return hash;
    }

    @Override
    public String toString() {
        return "Start: " + this.startTime + " Finish: " + this.finishTime + " Time: " + this.raceTimeString;
    }

    private int computeRaceTime() {
        LocalTime start = LocalTime.parse(this.startTime);
        LocalTime finish = LocalTime.parse(this.finishTime);
        return (int) start.until(finish, ChronoUnit.MILLIS);
    }
}
