package com.example.woj.happynumbers.TCLBG.Experiments;

/**
 * Created by welli on 25/10/2015.
 */

/*

#4
   The Computer Language Benchmarks Game
   http://benchmarksgame.alioth.debian.org/
   http://benchmarksgame.alioth.debian.org/u64/program.php?test=regexdna&lang=java&id=4
   contributed by Razii, idea taken from Elliott Hughes and Roger Millington
*/

import android.content.Context;

import java.io.*;
import java.util.regex.*;
import java.util.*;

public final class regexdna {

   private static final Map<String, String> replacements = new HashMap<String, String>();
    public static final String DEFAULT_FILE = "Input5000000.txt";

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

   static abstract class Rewriter {
      private Pattern pattern;
      private Matcher matcher;

      public Rewriter(String regularExpression) {

         this.pattern = Pattern.compile(regularExpression);
      }

      public String group(int i) {
         return matcher.group(i);
      }

      public abstract String replacement();

      public String rewrite(CharSequence original) {
         return rewrite(original, new StringBuffer(original.length())).toString();
      }

      public StringBuffer rewrite(CharSequence original, StringBuffer destination) {
         this.matcher = pattern.matcher(original);
         while (matcher.find()) {
            matcher.appendReplacement(destination, "");
            destination.append(replacement());
         }
         matcher.appendTail(destination);
         return destination;
      }
   }

   public static void execute(String fileName, Context context)
   throws IOException {

       InputStream input = null;
       try {
           input = context.getAssets().open(fileName);
       } catch (IOException e) {
           e.printStackTrace();
       }

      Reader r = new InputStreamReader(input);
      StringBuilder sb = new StringBuilder(5100000);
      char[] cbuf = new char[16384];
      int charsRead;
      while ((charsRead = r.read(cbuf)) != -1)
         sb.append(cbuf, 0, charsRead);

      int initialLength = sb.length();

      String sequence = new Rewriter(">.*\n|\n") {

         public String replacement() {
            return "";
         }
      }.rewrite(sb);


      int codeLength = sequence.length();

      String[] variants = { "agggtaaa|tttaccct" ,
                       "[cgt]gggtaaa|tttaccc[acg]",
                       "a[act]ggtaaa|tttacc[agt]t",
                       "ag[act]gtaaa|tttac[agt]ct",
                       "agg[act]taaa|ttta[agt]cct",
                       "aggg[acg]aaa|ttt[cgt]ccct",
                       "agggt[cgt]aa|tt[acg]accct",
                       "agggta[cgt]a|t[acg]taccct",
                       "agggtaa[cgt]|[acg]ttaccct"
                     };

      for (String variant : variants) {

         int count = 0;
         Matcher m = Pattern.compile(variant).matcher(sequence);
         while (m.find())
            count++;
         //System.out.println(variant + " " + count);
      }

      sequence = new Rewriter("[WYKMSRBDVHN]") {

         public String replacement() {
            return replacements.get(group(0));
         }
      }.rewrite(sequence);

//      System.out.println();
//      System.out.println(initialLength);
//      System.out.println(codeLength);
//      System.out.println(sequence.length());

   }
}


