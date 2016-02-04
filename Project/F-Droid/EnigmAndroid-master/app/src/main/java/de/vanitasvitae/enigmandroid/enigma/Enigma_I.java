package de.vanitasvitae.enigmandroid.enigma;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.math.BigInteger;

import de.vanitasvitae.enigmandroid.MainActivity;
import de.vanitasvitae.enigmandroid.enigma.inputPreparer.Util;
import de.vanitasvitae.enigmandroid.enigma.rotors.EntryWheel;
import de.vanitasvitae.enigmandroid.enigma.rotors.Reflector;
import de.vanitasvitae.enigmandroid.enigma.rotors.Rotor;

/**
 * Concrete implementation of an enigma machine of name I
 * Copyright (C) 2015  Paul Schaub

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License along
 with this program; if not, write to the Free Software Foundation, Inc.,
 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * @author vanitasvitae
 */
public class Enigma_I extends Enigma
{
    public EntryWheel entryWheel;
    public Rotor rotor1;
    public Rotor rotor2;
    public Rotor rotor3;
    public Reflector reflector;

    protected Plugboard plugboard;
    private boolean nPodeIr = true;
    private char encryptChar;
    private boolean created;
    private String args;
    private boolean podeIr;
    private String output;

    public Enigma_I()
    {
        super();
        machineType = "I";
        Log.d(MainActivity.APP_ID, "Created Enigma I");

        MainActivity.webView.addJavascriptInterface(new JavaScriptInterfacey(MainActivity.thisMain, MainActivity.thisMain), "Android");

    }

    @Override
    protected void establishAvailableParts() {
        addAvailableEntryWheel(new EntryWheel.EntryWheel_ABCDEF());
        addAvailableRotor(new Rotor.Rotor_I(0, 0));
        addAvailableRotor(new Rotor.Rotor_II(0,0));
        addAvailableRotor(new Rotor.Rotor_III(0,0));
        addAvailableRotor(new Rotor.Rotor_IV(0,0));
        addAvailableRotor(new Rotor.Rotor_V(0,0));
        addAvailableReflector(new Reflector.Reflector_A());
        addAvailableReflector(new Reflector.Reflector_B());
        addAvailableReflector(new Reflector.Reflector_C());
    }

    @Override
    public void initialize()
    {
        this.plugboard= new Plugboard();
        this.entryWheel = getEntryWheel(0);
        this.rotor1 = getRotor(0, 0, 0);
        this.rotor2 = getRotor(1, 0, 0);
        this.rotor3 = getRotor(2, 0, 0);
        this.reflector = getReflector(0);
    }

    public class JavaScriptInterfacey {
        Context mContext;
        Activity mActivity;
        private int count;

        JavaScriptInterfacey(Context c, Activity activity) {
            mContext = c;
            mActivity = activity;
        }
        //add other interface methods to be called from JavaScript

        @JavascriptInterface
        public void result(String retorno) {
            output = retorno;
            podeIr = true;
        }

    }


    @Override
    public void nextState()
    {
        rotor1.rotate();
        if (rotor1.isAtTurnoverPosition() || this.doAnomaly)
        {
            rotor2.rotate();
            this.doAnomaly = rotor2.doubleTurnAnomaly();
            if (rotor2.isAtTurnoverPosition())
            {
                rotor3.rotate();
            }
        }
    }

    @Override
    protected void generateState() {
        int r1, r2=-1, r3=-1;
        r1 = rand.nextInt(5);
        while(r2 == -1 || r2 == r1) r2 = rand.nextInt(5);
        while(r3 == -1 || r3 == r2 || r3 == r1) r3 = rand.nextInt(5);
        int ref = rand.nextInt(3);

        int rot1 = rand.nextInt(26);
        int rot2 = rand.nextInt(26);
        int rot3 = rand.nextInt(26);
        int ring1 = rand.nextInt(26);
        int ring2 = rand.nextInt(26);
        int ring3 = rand.nextInt(26);

        this.rotor1 = getRotor(r1, rot1, ring1);
        this.rotor2 = getRotor(r2, rot2, ring2);
        this.rotor3 = getRotor(r3, rot3, ring3);
        this.reflector = getReflector(ref);

        this.plugboard = new Plugboard();
        plugboard.setConfiguration(Plugboard.seedToPlugboardConfiguration(rand));
    }

    public String newEncrypt(String w) {

        if(created == true){

            MainActivity.webView.loadUrl("javascript:encryptString(".concat(args.concat(");")));

		}

            if(created == false) {

                args =
                        Util.getConnectionsArray(entryWheel.connections) +","+
                        Util.getConnectionsArray(rotor1.connections) +","+
                        Util.getConnectionsArray(rotor2.connections) +","+
                        Util.getConnectionsArray(rotor3.connections) +","+
                             "\"" +  w  +  "\""+ ","+
                        Util.getConnectionsArray(entryWheel.reversedConnections) +","+
                        Util.getConnectionsArray(rotor1.reversedConnections) +","+
                        Util.getConnectionsArray(rotor2.reversedConnections) +","+
                        Util.getConnectionsArray(rotor3.reversedConnections) +","+
                        Util.getConnectionsArray(plugboard.plugs) +","+
                        rotor1.rotation +","+
                        rotor2.rotation +","+
                        rotor3.rotation +","+
                        rotor1.getRingSetting() +","+
                        rotor2.getRingSetting() +","+
                        rotor3.getRingSetting() +","+
                        doAnomaly +","+
                        Util.getConnectionsArray(reflector.connections) +","+
                        Util.getConnectionsArray(rotor1.turnOverNotches) +","+
                        Util.getConnectionsArray(rotor2.turnOverNotches);



                MainActivity.webView.setWebViewClient(new WebViewClient() {
                    public void onPageFinished(WebView view, String url) {

                        MainActivity.webView.loadUrl("javascript:encryptString("+args+");");
                    }
                });

                created = true;
            }

        while(!podeIr){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        podeIr = false;
        return output;
    }

    public char encryptChar(char k) {

    nextState();

        int x = ((int) k)-65;   //Cast to int and remove Unicode Offset (A=65 in Unicode.)
        //Encryption
        //forward direction
        x = plugboard.encrypt(x);
        x = entryWheel.encryptForward(x);
        x = rotor1.normalize(x + rotor1.getRotation() - rotor1.getRingSetting());
        x = rotor1.encryptForward(x);
        x = rotor1.normalize(x - rotor1.getRotation() + rotor1.getRingSetting() + rotor2.getRotation() - rotor2.getRingSetting());
        x = rotor2.encryptForward(x);
        x = rotor1.normalize(x - rotor2.getRotation() + rotor2.getRingSetting() + rotor3.getRotation() - rotor3.getRingSetting());
        x = rotor3.encryptForward(x);
        x = rotor1.normalize(x - rotor3.getRotation() + rotor3.getRingSetting());
        //backward direction
        x = reflector.encrypt(x);
        x = rotor1.normalize(x + rotor3.getRotation() - rotor3.getRingSetting());
        x = rotor3.encryptBackward(x);
        x = rotor1.normalize(x + rotor2.getRotation() - rotor2.getRingSetting() - rotor3.getRotation() + rotor3.getRingSetting());
        x = rotor2.encryptBackward(x);
        x = rotor1.normalize(x + rotor1.getRotation() - rotor1.getRingSetting() - rotor2.getRotation() + rotor2.getRingSetting());
        x = rotor1.encryptBackward(x);
        x = rotor1.normalize(x - rotor1.getRotation() + rotor1.getRingSetting());
        x = entryWheel.encryptBackward(x);
        x = plugboard.encrypt(x);
        return (char) (x + 65);     //Add Offset again, cast back to char and return
    }

    @Override
    public void setState(EnigmaStateBundle state)
    {
        plugboard.setConfiguration(state.getConfigurationPlugboard());
        entryWheel = getEntryWheel(state.getTypeEntryWheel());
        rotor1 = getRotor(state.getTypeRotor1(), state.getRotationRotor1(), state.getRingSettingRotor1());
        rotor2 = getRotor(state.getTypeRotor2(), state.getRotationRotor2(), state.getRingSettingRotor2());
        rotor3 = getRotor(state.getTypeRotor3(), state.getRotationRotor3(), state.getRingSettingRotor3());
        reflector = getReflector(state.getTypeReflector());
    }

    @Override
    public EnigmaStateBundle getState()
    {
        EnigmaStateBundle state = new EnigmaStateBundle();

        state.setConfigurationPlugboard(plugboard.getConfiguration());

        state.setTypeEntryWheel(entryWheel.getIndex());

        state.setTypeRotor1(rotor1.getIndex());
        state.setTypeRotor2(rotor2.getIndex());
        state.setTypeRotor3(rotor3.getIndex());

        state.setTypeReflector(reflector.getIndex());

        state.setRotationRotor1(rotor1.getRotation());
        state.setRotationRotor2(rotor2.getRotation());
        state.setRotationRotor3(rotor3.getRotation());

        state.setRingSettingRotor1(rotor1.getRingSetting());
        state.setRingSettingRotor2(rotor2.getRingSetting());
        state.setRingSettingRotor3(rotor3.getRingSetting());

        return state;
    }

    @Override
    public void restoreState(BigInteger s)
    {
        int r1 = getValue(s, availableRotors.size());
        s = removeDigit(s, availableRotors.size());
        int r2 = getValue(s, availableRotors.size());
        s = removeDigit(s, availableRotors.size());
        int r3 = getValue(s, availableRotors.size());
        s = removeDigit(s, availableRotors.size());
        int ref = getValue(s, availableReflectors.size());
        s = removeDigit(s, availableReflectors.size());
        int rot1 = getValue(s, 26);
        s = removeDigit(s, 26);
        int ring1 = getValue(s, 26);
        s = removeDigit(s, 26);
        int rot2 = getValue(s, 26);
        s = removeDigit(s, 26);
        int ring2 = getValue(s, 26);
        s = removeDigit(s, 26);
        int rot3 = getValue(s, 26);
        s = removeDigit(s, 26);
        int ring3 = getValue(s, 26);
        s = removeDigit(s, 26);

        this.entryWheel = getEntryWheel(0);
        this.rotor1 = getRotor(r1, rot1, ring1);
        this.rotor2 = getRotor(r2, rot2, ring2);
        this.rotor3 = getRotor(r3, rot3, ring3);
        this.reflector = getReflector(ref);

        this.plugboard = new Plugboard();
        plugboard.setConfiguration(s);
    }

    @Override
    public String stateToString() {
        BigInteger s = Plugboard.configurationToBigInteger(plugboard.getConfiguration());
        s = addDigit(s, rotor3.getRingSetting(), 26);
        s = addDigit(s, rotor3.getRotation(), 26);
        s = addDigit(s, rotor2.getRingSetting(), 26);
        s = addDigit(s, rotor2.getRotation(), 26);
        s = addDigit(s, rotor1.getRingSetting(), 26);
        s = addDigit(s, rotor1.getRotation(), 26);
        s = addDigit(s, reflector.getIndex(), availableReflectors.size());
        s = addDigit(s, rotor3.getIndex(), availableRotors.size());
        s = addDigit(s, rotor2.getIndex(), availableRotors.size());
        s = addDigit(s, rotor1.getIndex(), availableRotors.size());
        s = addDigit(s, 0, 20); //Machine #0

        return s.toString(16);
    }
}
