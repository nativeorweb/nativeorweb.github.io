package com.example.woj.happynumbers.Rosetta.NotExecuted;
/**
 * Created by welli on 26/08/2015.
 */
import android.content.Context;

import com.example.woj.happynumbers.Rosetta.Util.Resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

public class TextProcessing1 {


    static int num_readings = 0;
    static int total = 0;
    static int reject_run = 0;
    static int reject_run_max = 0;
    static String reject_run_date = "";

    public static String FILE_NAME = "readings";


    public static void execute(String fileName,Context context) {

        File file = Resources.createFile(fileName,context);


        //executeJava(file);
        executeJS(file,context);

    }

    private static void executeJS(File file,Context context) {



        String line;
        try {
            try (
                    InputStream fis = new FileInputStream(file);
                    InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
                    BufferedReader br = new BufferedReader(isr);
            ) {
                while ((line = br.readLine()) != null) {
                    line_stats(line, true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        InputStream input = null;
//        try {
//            input = context.getAssets().open(FILE_NAME);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        while ( ! input.) {
//            var line = file.ReadLine();
//            line_stats(line, (show_lines-- > 0));
//        }
//        fh.close();
    }

    private static void line_stats(String line, Boolean print_line) {
        int readings = 0;
        int rejects = 0;
        int total = 0;
        String[] fields = line.split("\t");
        String date = fields[0];

        while (fields.length > 0) {
            float value = Float.parseFloat(fields[1]);
            float flag = Integer.parseInt(fields[2], 10);
            readings++;
            if (flag <= 0) {
                rejects++;
                reject_run++;
            }
            else {
                total += value;
                if (reject_run > reject_run_max) {
                    reject_run_max = reject_run;
                    reject_run_date = date;
                }
                reject_run = 0;
            }
        }

        num_readings += readings - rejects;
        TextProcessing1.total += total;

        if (print_line) {
            System.out.println(
                    "Line: " + date + "\t" +
                            "Reject: " + rejects + "\t" +
                            "Accept: " + (readings - rejects) + "\t" +
                            "Line_tot: " + dec3(total) + "\t" +
                            "Line_avg: " + ((readings == rejects) ? "0.0" : dec3(total / (readings - rejects)))
            );
        }
    }

    public static double dec3(int value) {
        return Math.round(value * 1e3) / 1e3;
    }
    private static void executeJava(File file) {

        Locale.setDefault(new Locale("en", "US"));
        Metrics metrics = new Metrics();

        int dataGap = 0;
        String gapBeginDate = null;

        try (Scanner lines = new Scanner(file)) {
            while (lines.hasNextLine()) {

                double lineTotal = 0.0;
                int linePairs = 0;
                int lineInvalid = 0;
                String lineDate;

                try (Scanner line = new Scanner(lines.nextLine())) {

                    lineDate = line.next();

                    while (line.hasNext()) {
                        final double value = line.nextDouble();
                        if (line.nextInt() <= 0) {
                            if (dataGap == 0)
                                gapBeginDate = lineDate;
                            dataGap++;
                            lineInvalid++;
                            continue;
                        }
                        lineTotal += value;
                        linePairs++;

                        metrics.addDataGap(dataGap, gapBeginDate, lineDate);
                        dataGap = 0;
                    }
                }
                metrics.addLine(lineTotal, linePairs);
                metrics.lineResult(lineDate, lineInvalid, linePairs, lineTotal);
            }
            metrics.report();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static class Metrics {
        private List<String[]> gapDates;
        private int maxDataGap = -1;
        private double total;
        private int pairs;
        private int lineResultCount;

        void addLine(double tot, double prs) {
            total += tot;
            pairs += prs;
        }

        void addDataGap(int gap, String begin, String end) {
            if (gap > 0 && gap >= maxDataGap) {
                if (gap > maxDataGap) {
                    maxDataGap = gap;
                    gapDates = new ArrayList<>();
                }
                gapDates.add(new String[]{begin, end});
            }
        }

        void lineResult(String date, int invalid, int prs, double tot) {
//            if (lineResultCount >= 3)
//                return;
           // out.printf("%10s  out: %2d  in: %2d  tot: %10.3f  avg: %10.3f%n",
           //         date, invalid, prs, tot, (prs > 0) ? tot / prs : 0.0);
            lineResultCount++;
        }

        void report() {
           // out.printf("%ntotal    = %10.3f%n", total);
          //  out.printf("readingsmeu = %6d%n", pairs);
         //   out.printf("average  = %010.3f%n", total / pairs);
         //   out.printf("%nmaximum run(s) of %d invalid measurements: %n",
         //           maxDataGap);
         //   for (String[] dates : gapDates)
          //      out.printf("begins at %s and ends at %s%n", dates[0], dates[1]);

        }
    }
}