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

import android.app.ListActivity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;

public class MyLenses extends ListActivity {
    ListView mList;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.mylenses);

        setListAdapter(new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_multiple_choice, anDOF.myData.getAllLensNames()));
        
        mList =  getListView();
        mList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mList.setTextFilterEnabled(true);

        // select my current lenses
        int[] oldLenses = anDOF.myData.getMyLenses();
        for(int i=0;i<oldLenses.length;i++) {
        	mList.setItemChecked(oldLenses[i], true);
        }
    }


    protected void onPause() {
        super.onPause();
        anDOF.myData.savePrefs();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	
        	SparseBooleanArray checked = mList.getCheckedItemPositions();
        	int nrSelected=0;

        	// count checked lenses
        	for(int i=0;i<checked.size();i++) {
        		if(checked.valueAt(i)) {
        			nrSelected++;
        		}
        	}
        	
        	int[] newLenses = new int[nrSelected];
        	int nrDeselected = 0;

			for(int i=0;i<checked.size();i++){
        		if(checked.valueAt(i)) {
        			newLenses[i-nrDeselected] = checked.keyAt(i);
        		} else {
        			// deselected lens -> don't increase array-index
        			nrDeselected++;
        		}
        	}        	
        	
        	anDOF.myData.setMyLenses(newLenses);
        	
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
