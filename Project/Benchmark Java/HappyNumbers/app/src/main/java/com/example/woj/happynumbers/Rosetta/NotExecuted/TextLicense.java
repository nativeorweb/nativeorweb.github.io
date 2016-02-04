package com.example.woj.happynumbers.Rosetta.NotExecuted;

/**
 * Created by welli on 29/08/2015.
 */
import android.content.Context;

import com.example.woj.happynumbers.Rosetta.Util.Resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class TextLicense {

    public static String FILE_NAME = "licenseTest";

    public static void execute(String fileName,Context context){

        File file = Resources.createFile(fileName,context);

        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));

            int max = Integer.MIN_VALUE;
            LinkedList<String> dates = new LinkedList<String>();
            String line;
            int count = 0;
            while((line = in.readLine()) != null){
                if(line.startsWith("License OUT ")) count++;
                if(line.startsWith("License IN ")) count--;
                if(count > max){
                    max = count;
                    String date = line.split(" ")[3];
                    dates.clear();
                    dates.add(date);
                }else if(count == max){
                    String date = line.split(" ")[3];
                    dates.add(date);
                }
            }
            System.out.println("Max licenses out: "+max);
            System.out.println("At time(s): "+dates);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}