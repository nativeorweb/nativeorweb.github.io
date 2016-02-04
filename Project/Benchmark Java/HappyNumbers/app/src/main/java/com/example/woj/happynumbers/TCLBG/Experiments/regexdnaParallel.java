package com.example.woj.happynumbers.TCLBG.Experiments;

/**
 * Created by welli on 16/10/2015.
 */


/*
#5

   The Computer Language Benchmarks Game
   http://benchmarksgame.alioth.debian.org/
//http://benchmarksgame.alioth.debian.org/u64q/program.php?test=regexdna&lang=java&id=5
   contributed by Michael Stover
 */
import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class regexdnaParallel {

    public static final String DEFAULT_FILE = "Input5000000.txt";

    private static final Map<String, String> replacements = new HashMap<String, String>();

    static {

        replacements.put("W", "(a|t)");
        replacements.put("Y", "(c|t)");
        replacements.put("K", "(g|t)");
        replacements.put("M", "(a|c)");
        replacements.put("S", "(c|g)");
        replacements.put("R", "(a|g)");
        replacements.put("B", "(c|g|t)");
        replacements.put("D", "(a|g|t)");
        replacements.put("V", "(a|c|g)");
        replacements.put("H", "(a|c|t)");
        replacements.put("N", "(a|c|g|t)");
    }

    public static void execute(String fileName, Context context) throws IOException {

        InputStream input = null;
        try {
            input = context.getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader r = new BufferedReader(new InputStreamReader(input,
                "US-ASCII"));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = r.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }

        int initialLength = sb.length();

        final String sequence = sb.toString().replaceAll(">.*\n|\n", "");

        int codeLength = sequence.length();

        String[] variants = { "agggtaaa|tttaccct", "[cgt]gggtaaa|tttaccc[acg]",
                "a[act]ggtaaa|tttacc[agt]t", "ag[act]gtaaa|tttac[agt]ct",
                "agg[act]taaa|ttta[agt]cct", "aggg[acg]aaa|ttt[cgt]ccct",
                "agggt[cgt]aa|tt[acg]accct", "agggta[cgt]a|t[acg]taccct",
                "agggtaa[cgt]|[acg]ttaccct" };

        final Map<String, Integer> results = new HashMap<String, Integer>();
        ThreadGroup tg = new ThreadGroup("regexWork");
        for (String v : variants) {
            final String variant = v;
            new Thread(tg, v) {
                @Override
                public void run() {
                    int count = 0;
                    Matcher m = Pattern.compile(variant).matcher(sequence);
                    while (m.find()) {
                        count++;
                    }
                    results.put(variant, count);
                }
            }.start();
        }
        Thread[] threads = new Thread[variants.length];
        tg.enumerate(threads);
        for (Thread t : threads) {
            try {
                if (t != null) {
                    t.join();
                }
            } catch (InterruptedException e) {
                // noop
            }
        }
        tg.destroy();
        for (String variant : variants) {
           // System.out.println(
            String args = variant + " " + results.get(variant);
            //);
        }
        StringBuffer buf = new StringBuffer();
        Matcher m = Pattern.compile("[WYKMSRBDVHN]").matcher(sequence);
        while (m.find()) {
            m.appendReplacement(buf, "");
            buf.append(replacements.get(m.group()));
        }
        m.appendTail(buf);

       // System.out.println();
//        System.out.println(initialLength);
//        System.out.println(codeLength);
//        System.out.println(buf.length());
    }
}