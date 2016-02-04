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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import java.text.DecimalFormat;


public class anDOF extends Activity  implements SeekBar.OnSeekBarChangeListener, OnLongClickListener, OnClickListener{
    SeekBar mDofSeekBar, mFocalSeekBar, mApertureSeekBar, mDistanceSeekBar;
    TextView mDofText, mFocalText, mApertureText, mDistanceText, mHyperfocal;
    TextView mMin, mMax, mDof, mDist, mExit;
    Button mOptions;

    // temp values for focal-calculation
    Double leftLimit = -1.0;
    Double rightLimit = -1.0;
    int focalIndex;

	// Data-Instance for get/set values
    public static Data myData;

    public void onStart() {
    	super.onStart();
    	
        // show current camera+lens on startup        
        Toast.makeText(getApplicationContext(), myData.getCameraName(-1)+"\n"+
        										myData.getLensName(-1),
 			   Toast.LENGTH_LONG).show();
    }
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myData = new Data(this);
		
		// read prefs on startup, otherwise coc is 0
		myData.readPrefs();
                
		checkNewVersion();
		if(Build.VERSION.SDK_INT > 10) hideIcon();

        setContentView(R.layout.main);

        
        // Text entries
        mHyperfocal = (TextView)findViewById(R.id.hyperfocal);
        mHyperfocal.setOnClickListener(this);
        mMin = (TextView)findViewById(R.id.min);
        mMax = (TextView)findViewById(R.id.max);
        mDof = (TextView)findViewById(R.id.dof);
        mDist = (TextView)findViewById(R.id.dist);
        
        // DOF widget
        mDofSeekBar = (SeekBar)findViewById(R.id.dof_seek);
        mDofSeekBar.setMax(298);
        mDofText = (TextView)findViewById(R.id.dof_text);
        mDofText.setOnLongClickListener(this);
        mDofSeekBar.setOnSeekBarChangeListener(this);
        mDofSeekBar.setEnabled(false);
    	mDofText.setTextColor(myData.getHighlightedTextColor() );
        
        // Distance widget
        mDistanceSeekBar = (SeekBar)findViewById(R.id.distance_seek);
        mDistanceSeekBar.setMax(myData.getDistanceCount());
        mDistanceSeekBar.setProgress(myData.getDistanceIndex());
        mDistanceSeekBar.setOnSeekBarChangeListener(this);
        mDistanceText = (TextView)findViewById(R.id.distance_text);
        mDistanceText.setOnLongClickListener(this);
		mDistanceText.setText(getResources().getString(R.string.distance) +": " + myData.getDistanceName(-1));
        
        // Focal widgets
        mFocalSeekBar = (SeekBar)findViewById(R.id.focal_seek);
        mFocalSeekBar.setMax(myData.getFocalCount());
        mFocalSeekBar.setProgress(myData.getFocalIndex());
        mFocalSeekBar.setOnSeekBarChangeListener(this);
        mFocalText = (TextView)findViewById(R.id.focal_text);
        mFocalText.setOnLongClickListener(this);
		mFocalText.setText(getResources().getString(R.string.focal) + ": " + myData.getFocalName(-1) + " mm");
		if(myData.getFocalCount()==1) mFocalSeekBar.setVisibility(4); // no choice -> seekbar hidden

		// Aperture widgets
        mApertureSeekBar = (SeekBar)findViewById(R.id.aperture_seek);
        mApertureSeekBar.setMax(myData.getApertureCount());
        mApertureSeekBar.setProgress(myData.getApertureIndex());
        mApertureSeekBar.setOnSeekBarChangeListener(this);
        mApertureText = (TextView)findViewById(R.id.aperture_text);
        mApertureText.setOnLongClickListener(this);
		mApertureText.setText(getResources().getString(R.string.aperture) + ": " + myData.getApertureName(-1) + " ");
        
        calcDof();
        
    }
    
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
    	
    	// prevent overshooting max-value by quickly sliding right		
		if(progress>=seekBar.getMax()) {
			progress=seekBar.getMax()-1;
			seekBar.setProgress(progress);
		}
		
		if (seekBar == mDofSeekBar) {
			myData.setGivenDof(progress2dof(progress));
			mDofText.setText(getResources().getString(R.string.dof) +": " + myData.getGivenDof() + " m");
		}
		
		if (seekBar == mDistanceSeekBar) {
			myData.setDistanceIndex(progress);
			mDistanceText.setText(getResources().getString(R.string.distance) +": " + myData.getDistanceName(progress));
		}

		if (seekBar == mFocalSeekBar) {
			myData.setFocalIndex(progress);
			mFocalText.setText(getResources().getString(R.string.focal) + ": " + myData.getFocalName(progress) + " mm");
		}
			
    	if (seekBar == mApertureSeekBar) {
    		myData.setApertureIndex(progress);
    		mApertureText.setText(getResources().getString(R.string.aperture) + ": " + myData.getApertureName(progress) + " ");
    	}

		switch (myData.getCalcMode()) {
		case 0:
			calcDof();
			break;
        case 1:
        	calcDistance();
			calcDof();
        	break;
    	case 2:
    		calcFocal();
    		break;
        case 3:
        	calcAperture();
			calcDof();
        	break;
		}
		
    }

    
    
    /**
     * (Re-)calculates Dof and refreshes values shown
     * Called on startup and after input-changes
     * 
     */
    public void calcDof() {

		 Double focal = myData.getFocalNr(-1);
		 Double aperture = myData.getApertureNr(-1);
		 
		 Double hyperFocal = calcHyperfocal(focal,aperture);
		 
		 Double dofNear = 0.0;
		 Double dofFar  = 0.0;
		 double calculatedDof = 0.0;
		 Double distance;
		 int units = 1;

		 distance = myData.getDistanceNr(-1) * 10;
		 dofNear = ((hyperFocal - focal) * distance) / (hyperFocal + distance - (2*focal));

		// Prevent 'divide by zero' when calculating far distance.
		 if ( (hyperFocal - distance) <= 0.00001)
			 dofFar = 10000000.0; // set infinity at 10000m
		 else
			 dofFar = ((hyperFocal-focal) * distance) / (hyperFocal - distance);

		 
		 // Convert depth of field numbers to proper units
		 dofNear = dofNear / 1000.0 / units;
		 dofFar  = dofFar / 1000.0 / units;
		 calculatedDof = dofFar-dofNear;

		 // convert hyperfocal distance to proper units
		 hyperFocal = hyperFocal / 1000.0 / units;
			 

		 //Display results
		 mDofSeekBar.setProgress(dof2progress(calculatedDof));        	 


		 showValues(dofNear, dofFar, distance, hyperFocal);
    }
    
    public double calcHyperfocal(double focal, double aperture) {
		Double CoC = myData.getCoc(-1);
    	return ((focal * focal) / (aperture * CoC) + focal);
    }
    
    
    public void showValues(double dofNear, double dofFar, double distance, double hyperFocal) {
    	
		 String dofText = "";

		 DecimalFormat df = new DecimalFormat("0.00");  // show decimal even for even numbers
		 DecimalFormat dfi = new DecimalFormat("#");    // no decimal for large numbers

		 mHyperfocal.setText(getResources().getString(R.string.hyperfocal) + ": " + df.format(hyperFocal) + " m ");
		 
		 mDist.setText(myData.getDistanceName(-1));
		 
		 mMin.setText(df.format(dofNear) + " m");
		 
		 if (dofFar >= 10000.0 ) {// set infinity at 10000m
			 mMax.setText(getResources().getString(R.string.infinity));
			 dofText = "\u221E";
		 } else if (dofFar >= 100) {
			 mMax.setText(dfi.format(dofFar) + " m");
			 dofText = dfi.format(dofFar - dofNear) + " m";
		 } else {
			 mMax.setText(df.format(dofFar) + " m");
			 dofText = df.format(dofFar - dofNear) + " m";
		 }
		 
		mDof.setText(dofText);
        mDofText.setText(getResources().getString(R.string.dof) + ": " + dofText);
    	
    }
    
    
    public void calcDistance() {
		 Double CoC = myData.getCoc(-1);
		 Double dof = myData.getGivenDof();
		 Double focal = myData.getFocalNr(-1);
		 Double aperture = myData.getApertureNr(-1);

		 // Convert depth of field numbers to proper units
		 dof = dof * 1000.0;

		 Double hyperFocal = (focal * focal) / (aperture * CoC) + focal;

		 Double distance = (-(2*focal*hyperFocal-2*focal*focal+2*focal*dof)-Math.sqrt(Math.pow((2*focal*hyperFocal-2*focal*focal+2*focal*dof),2)-4*(2*focal-2*hyperFocal-dof)*(hyperFocal*hyperFocal*dof-2*focal*hyperFocal*dof)) ) / (2*(2*focal-2*hyperFocal-dof));
		 distance = distance/10.0;
		 int distanceIndex = myData.distance2progress(distance);
			
		 myData.setDistanceIndex(distanceIndex);
	    	
		 mDistanceSeekBar.setProgress(distanceIndex);
		 mDistanceText.setText(getResources().getString(R.string.distance) + ": " + myData.getDistanceName(-1));
		 
    }
    
    public void calcFocal() {
    	Double CoC = myData.getCoc(-1);

		Double dof = myData.getGivenDof();
		Double distance = myData.getDistanceNr(-1) * 10;
		Double aperture = myData.getApertureNr(-1);
		Double focal = myData.getFocalNr(-1);
		
		// limit reached -> block dof-slider
		if(leftLimit>=0 && dof>=leftLimit) {
			myData.setGivenDof(leftLimit);
			mDofSeekBar.setProgress(dof2progress(leftLimit));      
		} else if(rightLimit>=0 && dof<=rightLimit) {
			myData.setGivenDof(rightLimit);
			mDofSeekBar.setProgress(dof2progress(rightLimit));        	 
		} else {
			noLimitReached();
		}
		
		// Convert depth of field numbers to proper units
		dof = dof * 1000.0;
		
		// calculate focal-value (4 alternatives)
		double a3 = (2*distance*aperture*CoC) / dof;
		double a2 = (CoC*CoC*dof*aperture*aperture-2*distance*distance*aperture*CoC) / dof;
		double a1 = (2*CoC*CoC*dof*aperture*aperture*distance) / dof;
		double a0 = (-CoC*CoC*dof*aperture*aperture*distance*distance) / dof;

		double T1 = -a3/4;
		double T2 = a2*a2 - 3*a3*a1 + 12*a0;
		double T3 = (2*Math.pow(a2,3) - 9*a3*a2*a1 + 27*a1*a1 + 27*a3*a3*a0 - 72*a2*a0)/2;
		double T4 = (-Math.pow(a3,3) + 4*a3*a2 - 8*a1)/32;
		double T5 = (3*a3*a3 - 8*a2)/48;

		double R1 = Math.sqrt(T3*T3 - Math.pow(T2,3));
		double R2 = Math.cbrt(T3 + R1);
		double R3 = (1/12)*(T2/R2 + R2);
		double R4 = Math.sqrt(T5 + R3);
		double R5 = 2*T5 - R3;
		double R6 = T4/R4;

		double r1 = T1 - R4 - Math.sqrt(R5 - R6);
		double r2 = T1 - R4 + Math.sqrt(R5 - R6);
		double r3 = T1 + R4 - Math.sqrt(R5 + R6);
		double r4 = T1 + R4 + Math.sqrt(R5 + R6);

		double focalMin = myData.getFocalNr(0);
		double focalMax = myData.getFocalNr(myData.getFocalCount()-1);


		// choose correct alternative
		if(r1>=focalMin && r1<=focalMax) {
			focal=r1;
		} else if(r2>=focalMin && r2<=focalMax) {
			focal=r2;
		} else if(r3>=focalMin && r3<=focalMax) {
			focal=r3;
		} else if(r4>=focalMin && r4<=focalMax) {
			focal=r4;
		} else {
			// no valid focal-value found -> limit reached
			if( focal < ((focalMin+focalMax)/2) ) {
				leftLimitReached();
			} else {
				rightLimitReached();
			}
		}
		
		focalIndex = myData.focal2progress(focal);

		// focal slider at limit
		if(focalIndex==0) {
			leftLimitReached();
		} else if(focalIndex==myData.getFocalCount()-1) {
			rightLimitReached();
		} 

		myData.setFocalIndex(focalIndex);
	    mFocalSeekBar.setProgress(focalIndex);
		mFocalText.setText(getResources().getString(R.string.focal) + ": " + myData.getFocalName(-1) + " mm");

    	double dofNear = (distance - (dof/2)) / 1000.0;
    	double dofFar = (distance + (dof/2)) / 1000.0;
    	double hyperFocal = (calcHyperfocal(focal, aperture)) / 1000.0;
    	
    	// only show values if no limit is reached
		if(leftLimit<0 && rightLimit<0) {
	    	showValues(dofNear, dofFar, distance, hyperFocal);
		}
    
    }
    
    public void leftLimitReached() {
    	focalIndex=0;
    	myData.setFocalIndex(focalIndex);
    	calcDof();
    	leftLimit = myData.getGivenDof();
    }
    
    public void rightLimitReached() {
    	focalIndex=myData.getFocalCount()-1;
    	myData.setFocalIndex(focalIndex); 
    	calcDof();
    	rightLimit = myData.getGivenDof();
    }
    
    public void noLimitReached() {
    	leftLimit = -1.0;
    	rightLimit = -1.0;
    }
    
    
    public void calcAperture() {
		Double CoC = myData.getCoc(-1);

		Double dof = myData.getGivenDof();
		Double distance = myData.getDistanceNr(-1) * 10;
		Double focal = myData.getFocalNr(-1);

		// Convert depth of field numbers to proper units
		dof = dof * 1000.0;
		
		Double hyperFocal = (-(2*Math.pow(distance,2)-2*distance*focal-2*focal*dof) - Math.sqrt(Math.pow(2*Math.pow(distance,2)-2*distance*focal-2*focal*dof,2) - 4*(-dof)*(Math.pow(distance,2)*dof-2*distance*focal*dof-2*Math.pow(distance,2)*focal+2*distance*Math.pow(focal,2))) ) / (2*(-dof));
		
		Double aperture = ( focal*focal / (hyperFocal-focal) ) / CoC;
		int apertureIndex = myData.aperture2progress(aperture);
		
		myData.setApertureIndex(apertureIndex);
    	
    	mApertureSeekBar.setProgress(apertureIndex);
    	mApertureText.setText(getResources().getString(R.string.aperture) + ": " + myData.getApertureName(-1));
    	
    }
    
    
    /**
     * Converts dof-value to progress for seekbar
     * uses different scale depending on values - eg. 0.79-0.80 = 100-200
     * 
     */
    public int dof2progress(double dof) {
		 int calcProgress = 0;

		 if(dof<1) {
			 calcProgress = (int) (dof*100);        	 
		 } else if(dof<10) {
			 calcProgress = (int) (100+(dof*100-100)/10.0);        	 
		 } else if(dof<100) {
			 calcProgress = (int) (190+(dof-10));
		 } else if(dof<1000) {
			 calcProgress = (int) (280+(dof-100)/100.0);
		 } else if(dof<10000) {
			 calcProgress = (int) (289+(dof-1000)/1000.0);
		 } else {
			 calcProgress = 298;
		 }

		 return calcProgress;
    }
    
    /**
     * Converts progress of seekbar to dof-value 
     * reverse calculation of dof2progress
     * 
     */
    public double progress2dof(int progress) {
    	double dof;
    	
    	if(progress<100) {
    		dof=(progress/100.0);
    	} else if(progress<190) {
    		dof=((progress-100)*10+100)/100.0;
    	} else if(progress<280) {
    		dof=progress-190+10;
    	} else if(progress<289) {
    		dof=(progress-280)*100+100;
    	} else if(progress<298) {
    		dof=(progress-289)*1000+1000;
    	} else {
    		dof=10000;
    	}
    	return dof;
    }
    
    protected void onPause() {
        super.onPause();

        myData.savePrefs();

    }

     public void onStartTrackingTouch(SeekBar seekBar) {
     }

     public void onStopTrackingTouch(SeekBar seekBar) {
     }
     
     @Override public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.main, menu);
         return true;
     }

     @Override
     public boolean onPrepareOptionsMenu(Menu menu) {

    	 refreshCloseup(menu.findItem(R.id.closeup));
    	 
    	 return super.onPrepareOptionsMenu(menu);

     }
     
     @Override public boolean onOptionsItemSelected(MenuItem item) {

    	 Intent intent;
			switch (item.getItemId()) {
            case R.id.currentlens:
            	intent = new Intent(this, CurrentLens.class);
            	startActivity(intent);  
				finish();
				return true;
			
            case R.id.closeup:
            	myData.changeCloseupEnabled();

            	refreshCloseup(item);
            	myData.setDistanceIndex(0);
            	myData.savePrefs();
            	this.onCreate(null);
            	
            	Toast.makeText(getApplicationContext(), getCloseupToast(),
          			   Toast.LENGTH_LONG).show();

                return true;
			
			case R.id.mycamera:
            	intent = new Intent(this, MyCamera.class);
            	startActivity(intent);  
				finish();
				return true;

            case R.id.mylenses:
            	intent = new Intent(this, MyLenses.class);
            	startActivity(intent);  
				finish();
				return true;

            case R.id.resetsettings:
            	myData.resetSettings();
            	finish();
            	startActivity(getIntent());
            	
            	return true;

            case R.id.help:
            	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.url)));
            	startActivity(browserIntent);
            	return true;

            default:
                return super.onOptionsItemSelected(item);
        }

     }
     
 	@Override
 	public boolean onLongClick(View v) {

 		// Vibrate for 300 milliseconds
 		Vibrator mVibrator = (Vibrator) getSystemService(getApplicationContext().VIBRATOR_SERVICE);
 		mVibrator.vibrate(300); 		

 		if(v.getId() == R.id.dof_text) {
 	        mDofSeekBar.setEnabled(false);
            mDofSeekBar.setThumb(getResources().getDrawable(R.drawable.icon_disabled));
 	        mDistanceSeekBar.setEnabled(true);
            mDistanceSeekBar.setThumb(getResources().getDrawable(R.drawable.icon_seek));
 	        mFocalSeekBar.setEnabled(true);
            mFocalSeekBar.setThumb(getResources().getDrawable(R.drawable.icon_seek));
 	        mApertureSeekBar.setEnabled(true);
            mApertureSeekBar.setThumb(getResources().getDrawable(R.drawable.icon_seek));
 		} else {
 	        mDofSeekBar.setEnabled(true);
            mDofSeekBar.setThumb(getResources().getDrawable(R.drawable.icon_seek));
 	        mDistanceSeekBar.setEnabled(false);
            mDistanceSeekBar.setThumb(getResources().getDrawable(R.drawable.icon_disabled));
 	        mFocalSeekBar.setEnabled(false);
            mFocalSeekBar.setThumb(getResources().getDrawable(R.drawable.icon_disabled));
 	        mApertureSeekBar.setEnabled(false);
            mApertureSeekBar.setThumb(getResources().getDrawable(R.drawable.icon_disabled));
 		}

    	mDofText.setTextColor(myData.getDefaultTextColor());
    	mDistanceText.setTextColor(myData.getDefaultTextColor());
    	mFocalText.setTextColor(myData.getDefaultTextColor());
    	mApertureText.setTextColor(myData.getDefaultTextColor());
 		
        switch (v.getId()) {
        case R.id.dof_text:
        	myData.setCalcMode(0);
        	mDofText.setTextColor(myData.getHighlightedTextColor());
 			return true;
        case R.id.distance_text:
        	myData.setCalcMode(1);
        	mDistanceText.setTextColor(myData.getHighlightedTextColor());
 			return true;
        case R.id.focal_text:
        	myData.setCalcMode(2);
        	mFocalText.setTextColor(myData.getHighlightedTextColor());
 			return true;
        case R.id.aperture_text:
        	myData.setCalcMode(3);
        	mApertureText.setTextColor(myData.getHighlightedTextColor());
 			return true;
		}
 			
 		return false;
 	}

	@Override
	public void onClick(View v) {
 		if(v.getId() == R.id.hyperfocal && mDistanceSeekBar.isEnabled()) {
 			// TODO: set distance to hyperfocal
 		}

		
	}

 	private void refreshCloseup(MenuItem item) {
    	 
    	 if(myData.getCloseupEnabled()){
    		 item.setIcon(R.drawable.closeup);
    	 }else{
    		 item.setIcon(R.drawable.closeup_off);
    	 }
    	 
     }

     private String getCloseupToast() {

    	 String closeupToast;
    	 
    	 if(myData.getCloseupEnabled()){
    		 closeupToast = getResources().getString(R.string.closeup_on);
    	 }else{
    		 closeupToast = getResources().getString(R.string.closeup_off);
    	 }
    	 
    	 return closeupToast;
     }

     @SuppressLint("NewApi")
	private void hideIcon() {
         getActionBar().setDisplayShowHomeEnabled(false); 
     }

     private void checkNewVersion() {
    	 PackageInfo pInfo;
    	 int lastVersion = myData.getLastRunVersionCode();

    	 try {
    		 pInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_META_DATA);
    		 if ( lastVersion < pInfo.versionCode ) {

    			 adjustSavedLenses(lastVersion, pInfo.versionCode);
    			 
    			 myData.setLastRunVersionCode(pInfo.versionCode);
    			 myData.savePrefs();
    		 }
    	 
    	 } catch (NameNotFoundException e) {
    		 e.printStackTrace();
    	 }
     }
     
     public void adjustSavedLenses(int lastVersion, int currentVersion) {
    	 
    	 int[] myLenses = myData.getMyLenses();

    	 for(int version=lastVersion+1;version<=currentVersion;version++) {
    		 int[] addedLenses = myData.getAddedLenses(version);

    		 for(int i=0;i<addedLenses.length;i++) {
        		 for(int j=0;j<myLenses.length;j++) {

        			 if(myLenses[j] >= addedLenses[i]) {
        				 // increase currentLens
        				 if(myLenses[j] == myData.getLensIndex()) {
        					 myData.setLensIndex(myData.getLensIndex()+1);
        				 }
        				 // increase myLens
        				 myLenses[j]++;
        				 
        			 }
        		 }
        	 }
    	 }
    	 
    	 myData.savePrefs();
    	 
     }

}
