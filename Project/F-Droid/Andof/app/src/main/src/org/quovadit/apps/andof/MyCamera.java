/* anDOF
* Copyright (C) 2013, 2014 Harald Ulver
*
* This program is Free Software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as
* published by the Free Software Foundation, either version 3 of the
* License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package org.quovadit.apps.andof;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MyCamera extends Activity {

private RadioButton radioCamera;
private RadioButton radioCoc;

private Spinner spinnerCameramodel;
private EditText textCustomcoc;
private int activeRadio;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mycamera);
        
        radioCamera = (RadioButton) findViewById(R.id.radio_camera);
        radioCoc = (RadioButton) findViewById(R.id.radio_customcoc);
        spinnerCameramodel = (Spinner) findViewById(R.id.spinner_cameramodel);
        textCustomcoc = (EditText) findViewById(R.id.text_customcoc);
              
        spinnerCameramodel.setAdapter(new ArrayAdapter<String>(this,
              		android.R.layout.simple_spinner_item, anDOF.myData.getAllCameraNames()));
              
        spinnerCameramodel.setSelection(anDOF.myData.getCurrentCamera());
        
        textCustomcoc.setText(anDOF.myData.getCoc(-1)+"");

        if(anDOF.myData.isCameraSet()) {
        	spinnerCameramodel.setEnabled(true);
        	textCustomcoc.setEnabled(false);
        	radioCamera.setChecked(true);
        	activeRadio=0;
        } else {
        	spinnerCameramodel.setEnabled(false);
        	radioCoc.setChecked(true);
        	textCustomcoc.setEnabled(true);
        	activeRadio=1;
        }
        
        
    }
    
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_camera:
                if (checked) {
                	spinnerCameramodel.setEnabled(true);
                	textCustomcoc.setEnabled(false);
                	activeRadio=0;
                }                	
                break;
            case R.id.radio_customcoc:
                if (checked) {
                	spinnerCameramodel.setEnabled(false);
                	textCustomcoc.setEnabled(true);
                	activeRadio=1;
                }
                break;
        }
    }
    
    public void saveCamera(View view) {

        switch(activeRadio) {
        	case 0:
        		anDOF.myData.setCurrentCamera(spinnerCameramodel.getSelectedItemPosition());
        		break;
        	case 1:
        		Double newCoc = Double.parseDouble(textCustomcoc.getText().toString());
        		anDOF.myData.setCustomCoc(newCoc);
        		
        		break;
        }

        anDOF.myData.savePrefs();

        
        goHome();
    	
    }

    protected void onPause() {
        super.onPause();
        anDOF.myData.savePrefs();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	goHome();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    
    /**
     * Close this Activity and restart anDOF
     * Is called after selecting a value, and after pressing the back-button
     * This way the back-button in anDOF-Activity closes the app (as expected)
     */
    private void goHome() {
		Intent i = new Intent(getApplicationContext(), anDOF.class);
		startActivity(i);				

		finish();								
    	
    }


}
