package ua.com.foxminded.formula1.model;

import java.time.Duration;
import java.time.LocalTime;

public class RaceRecord implements Comparable<RaceRecord> {

    private final String startTime;
    private final String finishTime;
    private final Duration raceTime;

    public RaceRecord(String start, String finish) {
        this.startTime = start;
        this.finishTime = finish;
        this.raceTime = computeRaceTime();
    }

    public Duration getRaceTime() {
        return this.raceTime;
    }

    @Override
    public int compareTo(RaceRecord anotherRecord) {
        return this.raceTime.compareTo(anotherRecord.raceTime);
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
        return "Start: " + this.startTime + " Finish: " + this.finishTime + " Time: " + this.raceTime.toString();
    }

    private Duration computeRaceTime() {
        LocalTime start = LocalTime.parse(this.startTime);
        LocalTime finish = LocalTime.parse(this.finishTime);
        return Duration.between(start, finish);
    }
}
