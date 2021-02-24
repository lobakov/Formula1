package ua.com.foxminded.formula1.view;

import java.time.Duration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

import ua.com.foxminded.formula1.model.Race;
import ua.com.foxminded.formula1.model.RaceRecord;
import ua.com.foxminded.formula1.model.Racer;

public class GridFormatter implements Formatter<Map<String, Race>> {

    private static final String COLUMN_SEPARATOR = " | ";
    private static final String NL = System.lineSeparator();
    private static final int MILLIS_IN_MINUTE = 60000;
    private static final int MILLIS_IN_SECOND = 1000;
    private static final int NOT_QUALIFIED = 16;
    private static final String RESULTS_SEPARATOR = "-".repeat(72);

    @Override
    public String format(Map<String, Race> race) {
        StringJoiner joiner = new StringJoiner("");

        race.forEach((date, record) -> {
            Iterator<Entry<Racer, RaceRecord>> iterator = record.getRaceGrid().entrySet().iterator();
            joiner.add("Race: ").add(date).add(NL);

            int count = 0;
            while (iterator.hasNext()) {
                ++count;
                if (count == NOT_QUALIFIED) {
                    joiner.add(RESULTS_SEPARATOR).add(NL);
                }
                Map.Entry<Racer, RaceRecord> entry = iterator.next();
                Racer racer = entry.getKey();
                String time = durationToString(entry.getValue().getRaceTime());
                joiner.add(String.valueOf(count)).add(". ")
                      .add(racer.getName()).add(COLUMN_SEPARATOR)
                      .add(racer.getTeam()).add(COLUMN_SEPARATOR)
                      .add(time);
                if (iterator.hasNext()) {
                    joiner.add(NL);
                }
            }
        });
        return joiner.toString();
    }

    private String durationToString(Duration duration) {
        long millis = duration.toMillis();
        return String.format("0%d:%02d.%03d", millis / MILLIS_IN_MINUTE,
                        (millis % MILLIS_IN_MINUTE) / MILLIS_IN_SECOND, millis % MILLIS_IN_SECOND);
    }
}
