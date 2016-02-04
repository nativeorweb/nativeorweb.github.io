package com.example.woj.happynumbers.TCLBG.Experiments;

/**
 * Created by welli on 16/10/2015.
 */
/*

#6
 * The Computer Language Benchmarks Game
 * http://benchmarksgame.alioth.debian.org/
http://benchmarksgame.alioth.debian.org/u64q/program.php?test=revcomp&lang=java&id=6

 * contributed by Jon Edvardsson
 * added parallel processing to the original
 * program by Anthony Donnefort and Enotus.
 */

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

public final class revcomp {

    static final ForkJoinPool fjPool = new ForkJoinPool();

    static final byte[] map = new byte[128];
    public static final String DEFAULT_FILE = "Input10000000.txt";

    static {
        String[] mm = {"ACBDGHK\nMNSRUTWVYacbdghkmnsrutwvy",
                "TGVHCDM\nKNSYAAWBRTGVHCDMKNSYAAWBR"};
        for (int i = 0; i < mm[0].length(); i++)
            map[mm[0].charAt(i)] = (byte) mm[1].charAt(i);
    }

    private static class Reverse extends RecursiveAction {
        private byte[] buf;
        private int begin;
        private int end;

        public Reverse(byte[] buf, int begin, int end) {
            this.buf = buf;
            this.begin = begin;
            this.end = end;
        }

        protected void compute() {
            byte[] buf = this.buf;
            int begin = this.begin;
            int end = this.end;

            while (true) {
                byte bb = buf[begin];
                if (bb == '\n')
                    bb = buf[++begin];
                byte be = buf[end];
                if (be == '\n')
                    be = buf[--end];
                if (begin > end)
                    break;
                buf[begin++] = be;
                buf[end--] = bb;
            }
        }
    }

    public static void execute(int loop,String fileName, Context context) throws IOException, InterruptedException {


        for (int loopInx = 0; loopInx < loop; loopInx++) {

            byte[] buf = getBytesArray(fileName, context);

            List<Reverse> tasks = new LinkedList<Reverse>();

            for (int i = 0; i < buf.length; ) {
                while (buf[i++] != '\n') ;
                int data = i;
                byte b;
                while (i < buf.length && (b = buf[i++]) != '>') {
                    buf[i - 1] = map[b];
                }
                Reverse task = new Reverse(buf, data, i - 2);
                fjPool.execute(task);
                //count.incrementAndGet();
                tasks.add(task);
            }
            for (Reverse task : tasks) {
                task.join();
            }
        }
        // System.out.write(buf);
    }

    private static byte[] getBytesArray(String fileName, Context context) throws IOException {
        InputStream input = null;
        try {
            input = context.getAssets().open(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] buf = new byte[1048576];

        while ((nRead = input.read(buf, 0, buf.length)) != -1) {
            buffer.write(buf, 0, nRead);
        }

        buffer.flush();
        return buffer.toByteArray();
    }
}
