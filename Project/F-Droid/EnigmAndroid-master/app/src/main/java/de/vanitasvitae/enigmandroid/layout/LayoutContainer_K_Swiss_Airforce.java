package de.vanitasvitae.enigmandroid.layout;

import android.widget.Spinner;

import de.vanitasvitae.enigmandroid.R;
import de.vanitasvitae.enigmandroid.enigma.Enigma;
import de.vanitasvitae.enigmandroid.enigma.EnigmaStateBundle;
import de.vanitasvitae.enigmandroid.enigma.Enigma_K_Swiss_Airforce;

/**
 * LayoutContainer for the Enigma Model K
 * This class contains the layout and controls the layout elements such as spinners and stuff
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
public class LayoutContainer_K_Swiss_Airforce extends LayoutContainer
{
    private Enigma_K_Swiss_Airforce enigma;

    protected Spinner rotor1View;
    protected Spinner rotor2View;
    protected Spinner rotor3View;

    protected Spinner rotor1PositionView;
    protected Spinner rotor2PositionView;
    protected Spinner rotor3PositionView;
    protected Spinner reflectorPositionView;

    public LayoutContainer_K_Swiss_Airforce()
    {
        super();
        main.setTitle("KSA - EnigmAndroid");
        this.resetLayout();
    }

    @Override
    public Enigma getEnigma() {
        return this.enigma;
    }

    @Override
    protected void initializeLayout() {
        this.rotor1View = (Spinner) main.findViewById(R.id.rotor1);
        this.rotor2View = (Spinner) main.findViewById(R.id.rotor2);
        this.rotor3View = (Spinner) main.findViewById(R.id.rotor3);
        this.rotor1PositionView = (Spinner) main.findViewById(R.id.rotor1position);
        this.rotor2PositionView = (Spinner) main.findViewById(R.id.rotor2position);
        this.rotor3PositionView = (Spinner) main.findViewById(R.id.rotor3position);
        this.reflectorPositionView = (Spinner) main.findViewById(R.id.reflector_position);

        Character[] rotorPositionArray = new Character[26];
        for(int i=0; i<26; i++) {rotorPositionArray[i] = (char) (65+i); /*Fill with A..Z*/}

        prepareSpinnerAdapter(rotor1View, R.array.rotors_1_3);
        prepareSpinnerAdapter(rotor2View, R.array.rotors_1_3);
        prepareSpinnerAdapter(rotor3View, R.array.rotors_1_3);

        prepareSpinnerAdapter(rotor1PositionView, rotorPositionArray);
        prepareSpinnerAdapter(rotor2PositionView, rotorPositionArray);
        prepareSpinnerAdapter(rotor3PositionView, rotorPositionArray);
        prepareSpinnerAdapter(reflectorPositionView, rotorPositionArray);
    }

    @Override
    public void resetLayout() {
        enigma = new Enigma_K_Swiss_Airforce();
        setLayoutState(enigma.getState());
        output.setText("");
        input.setText("");
    }

    @Override
    public void setLayoutState(EnigmaStateBundle state)
    {
        this.rotor1View.setSelection(state.getTypeRotor1());
        this.rotor2View.setSelection(state.getTypeRotor2());
        this.rotor3View.setSelection(state.getTypeRotor3());
        this.rotor1PositionView.setSelection(state.getRotationRotor1());
        this.rotor2PositionView.setSelection(state.getRotationRotor2());
        this.rotor3PositionView.setSelection(state.getRotationRotor3());
        this.reflectorPositionView.setSelection(state.getRotationReflector());
    }

    @Override
    public void syncStateFromLayoutToEnigma()
    {
        EnigmaStateBundle state = getEnigma().getState();
        state.setTypeRotor1(rotor1View.getSelectedItemPosition());
        state.setTypeRotor2(rotor2View.getSelectedItemPosition());
        state.setTypeRotor3(rotor3View.getSelectedItemPosition());
        state.setRotationRotor1(rotor1PositionView.getSelectedItemPosition());
        state.setRotationRotor2(rotor2PositionView.getSelectedItemPosition());
        state.setRotationRotor3(rotor3PositionView.getSelectedItemPosition());
        state.setRotationReflector(reflectorPositionView.getSelectedItemPosition());
        getEnigma().setState(state);
    }

    @Override
    public void showRingSettingsDialog()
    {
        new RingSettingsDialogBuilder.RingSettingsDialogBuilderRotRotRotRef().
                createRingSettingsDialog(getEnigma().getState());
    }
}
