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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class CurrentLens extends ListActivity {
    ListView mList;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.currentlens);

        setListAdapter(new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_single_choice, anDOF.myData.getMyLensNames()));
        
        mList =  getListView();
        mList.setItemChecked(anDOF.myData.getMyLensIndex(), true);
        mList.setTextFilterEnabled(true);
               
        mList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {

				anDOF.myData.setMyLensIndex(position);
				anDOF.myData.resetPrefs();
				goHome();
				
            }
        });

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
