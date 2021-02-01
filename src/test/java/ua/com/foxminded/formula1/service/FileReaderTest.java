package ua.com.foxminded.formula1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FileReaderTest {

    private FileReader fileReader = new FileReader();

    @Test
    void shouldReadLinesFromFile() throws IOException {
        String inputFile = "start.log";

        List<String> expected = new ArrayList<>();
        expected.add("SVF2018-05-24_12:02:58.917");
        expected.add("NHR2018-05-24_12:02:49.914");
        expected.add("FAM2018-05-24_12:13:04.512");
        expected.add("KRF2018-05-24_12:03:01.250");
        expected.add("SVM2018-05-24_12:18:37.735");
        expected.add("MES2018-05-24_12:04:45.513");
        expected.add("LSW2018-05-24_12:06:13.511");
        expected.add("BHS2018-05-24_12:14:51.985");
        expected.add("EOF2018-05-24_12:17:58.810");
        expected.add("RGH2018-05-24_12:05:14.511");
        expected.add("SSW2018-05-24_12:16:11.648");
        expected.add("KMH2018-05-24_12:02:51.003");
        expected.add("PGS2018-05-24_12:07:23.645");
        expected.add("CSR2018-05-24_12:03:15.145");
        expected.add("SPF2018-05-24_12:12:01.035");
        expected.add("DRR2018-05-24_12:14:12.054");
        expected.add("LHM2018-05-24_12:18:20.125");
        expected.add("CLS2018-05-24_12:09:41.921");
        expected.add("VBM2018-05-24_12:00:00.000");

        List<String> actual = fileReader.read(inputFile);

        assertEquals(expected.toString(), actual.toString());
    }
}
