package com.example.woj.happynumbers.Rosetta.NotExecuted;
/**
 * Created by welli on 29/08/2015.
 */
import android.content.Context;

import com.example.woj.happynumbers.Rosetta.Util.Resources;

import java.util.*;
import java.util.regex.*;
import java.io.*;

public class DataMunging2 {

    public static final Pattern e = Pattern.compile("\\s+");
    public static String FILE_NAME = "readings";



    public static void execute(String fileName,Context context) {
        File file = Resources.createFile(fileName,context);

        try {

            BufferedReader infile = new BufferedReader(new FileReader(file));
            List<String> duplicates = new ArrayList<String>();
            Set<String> datestamps = new HashSet<String>(); //for the datestamps

            String eingabe;
            int all_ok = 0;//all_ok for lines in the given pattern e
            while ((eingabe = infile.readLine()) != null) {
                String[] fields = e.split(eingabe); //we tokenize on empty fields
                if (fields.length != 49) //we expect 49 fields in a record
                    System.out.println("Format not ok!");
                if (datestamps.add(fields[0])) { //not duplicated
                    int howoften = (fields.length - 1) / 2 ; //number of measurement
                    //devices and values
                    for (int n = 1; Integer.parseInt(fields[2*n]) >= 1; n++) {
                        if (n == howoften) {
                            all_ok++ ;
                            break ;
                        }
                    }
                } else {
                    duplicates.add(fields[0]); //first field holds datestamp
                }
            }
            infile.close();
            System.out.println("The following " + duplicates.size() + " datestamps were duplicated:");
            for (String x : duplicates)
                System.out.println(x);
            System.out.println(all_ok + " records were complete and ok!");
        } catch (IOException e) {
            System.err.println("Can't open file " + file);
            System.exit(1);
        }
    }
}
