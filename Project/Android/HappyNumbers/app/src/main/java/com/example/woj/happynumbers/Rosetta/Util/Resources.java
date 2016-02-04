package com.example.woj.happynumbers.Rosetta.Util;

import android.content.Context;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by welli on 26/08/2015.
 */
public class Resources {

    public static int[] criarArray(int tamanhoArray){

        int[] retorno = new int[tamanhoArray];

        for (int i = 0; i < tamanhoArray; i++) {
            retorno[i] = (int) Math.floor(Math.random() * (tamanhoArray));
        }
        return retorno;
    }

    public static String[] criarArrayString(int tamanhoArray){

        String[] retorno = new String[tamanhoArray];
        String possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < tamanhoArray; i++) {
            retorno[i] = String.valueOf(possible.charAt((int) Math.floor(Math.random() * possible.length())));
        }
        return retorno;
    }

    public static File createFile(String nameFile,Context context) {

        System.out.println("COMEÇOU A COPIAR O ARQUIVO");

        final File dir = new File(context.getFilesDir() + "");
        dir.mkdirs(); //create folders where write files
        final File file = new File(dir,nameFile+".txt");
        InputStream input = null;
        try {
            input = context.getAssets().open(nameFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            OutputStream output = new FileOutputStream(file);

            System.out.println("ENTROU NO TRY");

            try {
                try {
                    byte[] buffer = new byte[input.available()];
                    input.read(buffer);
                    output.write(buffer);
                    output.flush();
                } finally {
                    output.close();
                }
            } catch (Exception e) {
                e.printStackTrace(); // handle exception, define IOException and others
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ACABOU DE COPIAR O ARQUIVO");

        return file;
    }
}
