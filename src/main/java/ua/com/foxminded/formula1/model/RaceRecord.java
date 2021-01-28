package ua.com.foxminded.formula1.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class RaceRecord implements Comparable<RaceRecord> {

    private final int raceTime;
    private final String raceTimeString;
    private final String startTime;
    private final String finishTime;
    
    public RaceRecord(String start, String finish) {
        this.startTime = start;
        this.finishTime = finish;
        this.raceTime = computeRaceTime();

        DateFormat formatter = new SimpleDateFormat("mm:ss.SSS");
        Date date = new Date(this.raceTime);
        this.raceTimeString = formatter.format(date);
    }
    
    public String getStartTime() {
        return this.startTime;
    }
    
    public String getFinishTime() {
        return this.finishTime;
    }
    
    private int computeRaceTime() {
        LocalTime start = LocalTime.parse(this.startTime);
        LocalTime end = LocalTime.parse(this.finishTime);
        return (int) start.until(end, ChronoUnit.MILLIS);
    }

    public int getRaceTime() {
        return this.raceTime;
    }

    public String getRaceTimeString() {
        return this.raceTimeString;
    }

    @Override
    public int compareTo(RaceRecord anotherRecord) {
        return this.getRaceTime() - anotherRecord.getRaceTime();
    }

    @Override
    public boolean equals(Object obj) {
        return this.getRaceTime() == ((RaceRecord) obj).getRaceTime();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Start: " + this.startTime + " Finish: " + this.finishTime + " Time: " + this.raceTimeString;
    }
}
