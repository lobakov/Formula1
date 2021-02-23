package ua.com.foxminded.formula1.view;

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
                joiner.add(String.valueOf(count)).add(". ").add(racer.getName()).add(COLUMN_SEPARATOR)
                      .add(racer.getTeam()).add(COLUMN_SEPARATOR).add(entry.getValue().getRaceTimeString());
                if (iterator.hasNext()) {
                    joiner.add(NL);
                }
            }
        });
        return joiner.toString();
    }
}
