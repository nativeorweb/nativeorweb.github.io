/* anDOF

* Copyright (C) 2013, 2014, 2015 Harald Ulver
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


import java.text.DecimalFormat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

public class Data {
	
	private final int defaultCoc = 30;  // 30 -> 0.03
	private final int defaultDistance = 23;  // 23=2.5m, 12 = 1m
	private final int defaultFocal = 26;  // 26=50mm
	private final int defaultAperture = 0;  // 0=f/2.8
	private final int defaultLens = 5;  // 5=Canon 24-70
    private final int[] defaultlenses = new int[] {defaultLens,10};  // 10=Canon 50mm
    
    // add new entry for each new version-nr, empty tag {} if no new lens was added
    private final int[][] addedLenses = new int[][] {
    		{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},
    		{5,8,20},{45,46,47,61},{27,52}
    };
	
	private final int defaultTextColor = Color.parseColor("#BEBEBE");
    private final int highlightedTextColor = Color.parseColor("#0000FF");
    
	private Context passedContext;
    
    private SharedPreferences mPrefs;
    public int mCurrentCamera, mCustomCoc, mCameraSet, mCurrentLens, mCurrentDistance, mCurrentFocal, mCurrentAperture;
    private double givenDof;
    private int[] mylenses;
    
    public boolean closeupEnabled;
    private int calcMode = 0;  // which slider gets calculated: 0=dof, 1=distance, 2=focal, 3=aperture
 	
	DecimalFormat three = new DecimalFormat("#.###");
	
	private int lastRunVersionCode;
    
	public Data(Context context) {
		passedContext = context;
	}
	
	public void resetSettings() {
		passedContext.getSharedPreferences("andof", 0).edit().clear().commit();		
		readPrefs();
	}

	
    public void readPrefs() {
    	
        mPrefs = passedContext.getSharedPreferences("andof", 0);
        mCurrentCamera = mPrefs.getInt("camera", -1);
        mCustomCoc = mPrefs.getInt("customCoc", defaultCoc);  
        mCameraSet = mPrefs.getInt("cameraSet",-1);
        
        mCurrentDistance = mPrefs.getInt("distance", defaultDistance); 
        mCurrentFocal = mPrefs.getInt("focal", defaultFocal);  
        mCurrentAperture = mPrefs.getInt("aperture", defaultAperture);  
        mCurrentLens = lensIoobe(mPrefs.getInt("lens",defaultLens)); 
        
        closeupEnabled = mPrefs.getBoolean("closeupenabled", false);
        mylenses = loadArray("mylenses", mPrefs);
        
        lastRunVersionCode = mPrefs.getInt("lastRunVersionCode",0);
                
   }
    
    public void savePrefs() {

    	SharedPreferences.Editor ed = mPrefs.edit();
        ed.putInt("camera", mCurrentCamera);
        ed.putInt("customCoc", mCustomCoc);
        ed.putInt("cameraSet",  mCameraSet);
        
        ed.putInt("focal", mCurrentFocal);
        ed.putInt("aperture", mCurrentAperture);
        ed.putInt("distance", mCurrentDistance);
        ed.putInt("lens", mCurrentLens);

        ed.putBoolean("closeupenabled", closeupEnabled);
        ed.putInt("lastRunVersionCode", lastRunVersionCode);

        saveArray(mylenses, "mylenses", ed);
        
        ed.commit();
    }
    
    // save selected lenses as array in sharedPreferences
    public boolean saveArray(int[] array, String arrayName, SharedPreferences.Editor editor) {   
        editor.putInt(arrayName +"_size", array.length);  
        for(int i=0;i<array.length;i++)  
            editor.putInt(arrayName + "_" + i, array[i]);  
        return editor.commit();  
    } 

    public int[] loadArray(String arrayName, SharedPreferences prefs) {  
        int size = prefs.getInt(arrayName + "_size", 0);  
        if (size == 0) {
        	return defaultlenses;
        }
        int[] array = new int[size];  
        for(int i=0;i<size;i++)  
            array[i] = lensIoobe(prefs.getInt(arrayName + "_" + i, 0));  
        return array;  
    }  

    
    // Reset all Sliders after Lens-Change
    public void resetPrefs() {
    	if((getFocalEnd()-getFocalStart())>defaultFocal) {
    		mCurrentFocal = defaultFocal; 
    	} else {
    		mCurrentFocal = 0;
    	}

    	if((getApertureEnd()-getApertureStart())>defaultAperture) {
    		mCurrentAperture = defaultAperture; 
    	} else {
    		mCurrentAperture = 0;
    	}
    	
    	if((getDistanceEnd()-getDistanceStart())>defaultDistance) {
    		mCurrentDistance = defaultDistance; 
    	} else {
    		mCurrentDistance = 0;
    	}
    	
    }
    
    
    
    
    
    // Getter and Setter: index=-1 means current
    
    
    public String[] getAllLensNames() {
    	return mLensName;
    }
    
    public String[] getMyLensNames() {
    	String[] mylensnames = new String[mylenses.length];
    	for(int i=0;i<mylensnames.length;i++) {         	
			mylensnames[i]=mLensName[lensIoobe(mylenses[i])];
    	}
        return mylensnames;
    }
    
    public int getLensCount() {
    	return mLensName.length;
    }
    
    public int getLensIndex () {
    	return mCurrentLens;
    }

    public void setLensIndex (int lens) {
    	mCurrentLens = lens;
    }
    
    public int getMyLensIndex() {
    	for(int i=0;i<mylenses.length;i++) {
    		if(mylenses[i]==mCurrentLens) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    public void setMyLensIndex (int mylens) {
    	mCurrentLens = mylenses[mylens];
    }
    
    public String getLensName (int index) {

    	if(index == -1) { 
    		if(mCurrentLens == -1) {
    			return passedContext.getString(R.string.noLens);
    		} else {
        		index = mCurrentLens; 
    		}
    	}
    	return mLensName[index];
    }
        
    
    public int getFocalStart() {
    	return Integer.parseInt(mLensValue[mCurrentLens][1]);
    }
    
    public int getFocalEnd() {
    	return Integer.parseInt(mLensValue[mCurrentLens][2])+1;  // ende um eins zu niedrig
    }
    
    public int getApertureStart() {
    	return Integer.parseInt(mLensValue[mCurrentLens][3]);
    }
    
    public int getApertureEnd() {
    	return Integer.parseInt(mLensValue[mCurrentLens][4])+1;  // ende um eins zu niedrig
    }
    
    public int getDistanceStart() {

    	// ignore min-distance if closeup-filter is used
    	if(getCloseupEnabled()) {
    		return 0;
    	} else {
        	return Integer.parseInt(mLensValue[mCurrentLens][5]);
    	}
    }
    
    public int getDistanceEnd() {
    	return mDistance.length; // open end
    }
    

    
    public String[] getAllCameraNames() {
    	return mCameraName;
    }
    
    public int getCameraCount() {
    	return mCameraName.length;
    }
    
    public double getCoc (int index) {
    	
    	if(index == -1) { 
    		if(isCameraSet()) {
        		index = mCurrentCamera; 
    		} else {
    			return (double)mCustomCoc/1000;
    		}
    	}

    	return Double.valueOf(mCameraCoc[index]);    	

    }
    
    public boolean isCameraSet() {
    	return mCameraSet==1;
    }
    
    public String getCameraName (int index) {
    	if(index == -1) { 
    		if(isCameraSet()) {
        		index = mCurrentCamera; 
    		} else {
    			return passedContext.getString(R.string.customCoc)+" "+getCoc(-1);
    		}
    	}
    	return mCameraName[index];
    }
    
    public int getCurrentCamera () {
    	return mCurrentCamera;
    }

    public void setCurrentCamera (int camera) {
    	mCurrentCamera = camera;
    	mCameraSet=1;
    }
    
    public void setCustomCoc (double coc) {
    	mCustomCoc = (int)Math.round(coc*1000); 
    	if(mCustomCoc==0) mCustomCoc=1;	// too small number -> minimum
    	mCameraSet=-1;
    }
    
    
	public int getDistanceLength() {
		return mDistance.length;
	}
	
    public int getDistanceCount() {
    	return getDistanceEnd()-getDistanceStart();
    }
    
	public double getDistanceNrMinus(int index) {
    	return Float.parseFloat(mDistance[getDistanceStart()-index][0]);
	}

    public double getDistanceNr(int index) {
    	if(index == -1) { index = mCurrentDistance; }
    	return Float.parseFloat(mDistance[index+getDistanceStart()][0]);
    }
    
    public String getDistanceName(int index) {
    	if(index == -1) { index = mCurrentDistance; }
    	return mDistance[index+getDistanceStart()][1];
    }
    
    public int getDistanceIndex() {
    	return mCurrentDistance;
	}

	public void setDistanceIndex(int distance) {
		this.mCurrentDistance = distance;
	}
	
	public double getGivenDof() {
		return givenDof;
	}
	
	public void setGivenDof(double dof) {
		this.givenDof = dof;
	}
	
	public int getFocalLength() {
		return mFocal.length;
	}
	
    public int getFocalCount() {
    	return getFocalEnd()-getFocalStart();
    }
    
	public double getFocalNrMinus(int index) {
    	return Float.parseFloat(mFocal[getFocalStart()-index]);
	}

	public double getFocalNr(int index) {
    	if(index == -1) { index = mCurrentFocal; }
    	return Float.parseFloat(mFocal[index+getFocalStart()]);
	}

	public String getFocalName(int index) {
    	if(index == -1) { index = mCurrentFocal; }
    	return mFocal[index+getFocalStart()];
	}

	public int getFocalIndex() {
		return mCurrentFocal;
	}

	public void setFocalIndex(int focal) {
		this.mCurrentFocal = focal;
	}
	
	public int getApertureLength() {
		return mAperture.length;
	}
	
    public int getApertureCount() {
    	return getApertureEnd()-getApertureStart();
    }
    
	public double getApertureNrMinus(int index) {
    	return Float.parseFloat(mAperture[getApertureStart()-index][0]);
	}

	public double getApertureNr(int index) {
    	if(index == -1) { index = mCurrentAperture; }
    	return Float.parseFloat(mAperture[index+getApertureStart()][0]);
	}

	public String getApertureName(int index) {
    	if(index == -1) { index = mCurrentAperture; }
    	return mAperture[index+getApertureStart()][1];
	}

	public int getApertureIndex() {
		return mCurrentAperture;
	}

	public void setApertureIndex(int aperture) {
		this.mCurrentAperture = aperture;
	}

	
	public boolean getCloseupEnabled() {
		return closeupEnabled;
	}
	
	public void changeCloseupEnabled() {
		if(this.closeupEnabled) {
			this.closeupEnabled = false;
		} else {
			this.closeupEnabled = true;
		}
	}
	
	public void setMyLenses(int[] lenses) {
		mylenses = lenses;
	}
	
	public int[] getMyLenses() {
		return mylenses;
	}
	
	public int[] getAddedLenses(int version) {
		return addedLenses[version];
	}

	// prevent IndexOutOfBoundException (=Ioobe) if number of lenses decreases after upgrade
	private int lensIoobe(int index) {
		if(index>=mLensName.length) {
			return mLensName.length - 1;
		}
		return index;
	}

	public int getCalcMode() {
		return calcMode;
	}
	
	public void setCalcMode(int mode) {
		this.calcMode = mode;
	}
	
    public int getDefaultTextColor() {
		return defaultTextColor;
	}

	public int getHighlightedTextColor() {
		return highlightedTextColor;
	}

	public void setLastRunVersionCode(int version) {
		this.lastRunVersionCode = version;
	}
	
	public int getLastRunVersionCode() {
		return lastRunVersionCode;
	}
		
	
    public int distance2progress(double distance) {
    	double limitLow, limitHigh;
    	
    	for(int i=0; i<getDistanceCount(); i++) {
    	
    		if(i==0) {
    			if(getDistanceStart()==0) {
    				limitLow=getDistanceNr(0) - 0.5;
    			} else {
        			limitLow=(getDistanceNr(0) + getDistanceNrMinus(1) )/2;
    			}
    		} else {
    			limitLow=(getDistanceNr(i) + getDistanceNr(i-1) )/2;
    		}
    		
   		
    		if(i<(getDistanceCount()-1) || getDistanceEnd()<getDistanceLength()) {
    			limitHigh=(getDistanceNr(i) + getDistanceNr(i+1) )/2;
    		} else {
    			limitHigh=getDistanceNr(i) + 0.5;
    		}
    			
    		if(distance >= limitLow) {
    			if(distance < limitHigh) {
    				return i;
    			}
    		} else {
    			return 0;
    		}
        
    	}
    	return getDistanceCount()-1;
    }

    
    public int focal2progress(double focal) {
    	double limitLow, limitHigh;
    	
    	for(int i=0; i<getFocalCount(); i++) {
    	
    		if(i==0) {
    			if(getFocalStart()==0) {
    				limitLow=getFocalNr(0) - 0.5;
    			} else {
        			limitLow=(getFocalNr(0) + getFocalNrMinus(1) )/2;
    			}
    		} else {
    			limitLow=(getFocalNr(i) + getFocalNr(i-1) )/2;
    		}
    		
   		
    		if(i<(getFocalCount()-1) || getFocalEnd()<getFocalLength()) {
    			limitHigh=(getFocalNr(i) + getFocalNr(i+1) )/2;
    		} else {
    			limitHigh=getFocalNr(i) + 0.5;
    		}
    			
    		if(focal >= limitLow) {
    			if(focal < limitHigh) {
    				return i;
    			}
    		} else {
    			return 0;
    		}
        
    	}
    	return getDistanceCount()-1;
    }

    
    public int aperture2progress(double aperture) {
    	double limitLow, limitHigh;
    	
    	for(int i=0; i<getApertureCount(); i++) {
    	
    		if(i==0) {
    			if(getApertureStart()==0) {
    				limitLow=getApertureNr(0) - 0.5;
    			} else {
        			limitLow=(getApertureNr(0) + getApertureNrMinus(1) )/2;
    			}
    		} else {
    			limitLow=(getApertureNr(i) + getApertureNr(i-1) )/2;
    		}
    		
   		
    		if(i<(getApertureCount()-1) || getApertureEnd()<getApertureLength()) {
    			limitHigh=(getApertureNr(i) + getApertureNr(i+1) )/2;
    		} else {
    			limitHigh=getApertureNr(i) + 0.5;
    		}
    			
    		if(aperture >= limitLow) {
    			if(aperture < limitHigh) {
    				return i;
    			}
    		} else {
    			return 0;
    		}
        
    	}
    	return getApertureCount()-1;
    }
    



		
    
    
    private String[] mFocal = {
    		"2", "3", "4", "4.5", "5.6", "6", "7", "8", "9", "10", "10.5", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "140", "141", "142", "143", "144", "145", "146", "147", "148", "149", "150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "160", "161", "162", "163", "164", "165", "166", "167", "168", "169", "170", "171", "172", "173", "174", "175", "176", "177", "178", "179", "180", "181", "182", "183", "184", "185", "186", "187", "188", "189", "190", "191", "192", "193", "194", "195", "196", "197", "198", "199", "200", "201", "202", "203", "204", "205", "206", "207", "208", "209", "210", "211", "212", "213", "214", "215", "216", "217", "218", "219", "220", "221", "222", "223", "224", "225", "226", "227", "228", "229", "230", "231", "232", "233", "234", "235", "236", "237", "238", "239", "240", "241", "242", "243", "244", "245", "246", "247", "248", "249", "250", "251", "252", "253", "254", "255", "256", "257", "258", "259", "260", "261", "262", "263", "264", "265", "266", "267", "268", "269", "270", "271", "272", "273", "274", "275", "276", "277", "278", "279", "280", "281", "282", "283", "284", "285", "286", "287", "288", "289", "290", "291", "292", "293", "294", "295", "296", "297", "298", "299", "300", "301", "302", "303", "304", "305", "306", "307", "308", "309", "310", "311", "312", "313", "314", "315", "316", "317", "318", "319", "320", "321", "322", "323", "324", "325", "326", "327", "328", "329", "330", "331", "332", "333", "334", "335", "336", "337", "338", "339", "340", "341", "342", "343", "344", "345", "346", "347", "348", "349", "350", "351", "352", "353", "354", "355", "356", "357", "358", "359", "360", "361", "362", "363", "364", "365", "366", "367", "368", "369", "370", "371", "372", "373", "374", "375", "376", "377", "378", "379", "380", "381", "382", "383", "384", "385", "386", "387", "388", "389", "390", "391", "392", "393", "394", "395", "396", "397", "398", "399", "400", "500"
    };
	
	private String[][] mAperture = {
			{"1", "f/1"}, {"1.1380711874577", "f/1.1"}, {"1.2761423749154", "f/1.2"}, {"1.4142135623731", "f/1.4"}, {"1.60947570824873", "f/1.6"}, {"1.80473785412437", "f/1.8"}, {"2", "f/2"}, {"2.2761423749154", "f/2.2"}, {"2.55228474983079", "f/2.5"}, {"2.82842712474619", "f/2.8"}, {"3.21895141649746", "f/3.2"}, {"3.60947570824873", "f/3.5"}, {"4", "f/4"}, {"4.55228474983079", "f/4.5"}, {"5.10456949966159", "f/5"}, {"5.65685424949238", "f/5.6"}, {"6.43790283299492", "f/6.3"}, {"7.21895141649746", "f/7.1"}, {"8", "f/8"}, {"9.1045694996616", "f/9"}, {"10.2091389993232", "f/10"}, {"11.3137084989848", "f/11"}, {"12.8758056659899", "f/13"}, {"14.4379028329949", "f/14"}, {"16", "f/16"}, {"18.2091389993232", "f/18"}, {"20.4182779986464", "f/20"}, {"22.6274169979695", "f/22"}, {"25.7516113319797", "f/25"}, {"28.8758056659898", "f/29"}, {"32", "f/32"}, {"36.4182779986464", "f/36"}, {"40.8365559972927", "f/40"}, {"45.2548339959391", "f/45"}
	};

	private String[][] mDistance = {
			{"1", "1 cm"}, {"2", "2 cm"}, {"3", "3 cm"}, {"4", "4 cm"}, {"5", "5 cm"}, {"6", "6 cm"}, {"7", "7 cm"}, {"8", "8 cm"}, {"9", "9 cm"}, {"10", "10 cm"}, {"14", "14 cm"}, {"20", "20 cm"}, {"22", "22 cm"}, {"24", "24 cm"}, {"28", "28 cm"}, {"30", "30 cm"}, {"31", "31 cm"}, {"35", "35 cm"}, {"36", "36 cm"}, {"38", "38 cm"}, {"40", "40 cm"}, {"45", "45 cm"}, {"49", "49 cm"}, {"50", "50 cm"}, {"58", "58 cm"}, {"60", "60 cm"}, {"70", "70 cm"}, {"75", "75 cm"}, {"80", "80 cm"}, {"85", "85 cm"}, {"90", "90 cm"}, {"95", "95 cm"}, {"100", "1 m"}, {"110", "1.1 m"}, {"120", "1.2 m"}, {"130", "1.3 m"}, {"140", "1.4 m"}, {"150", "1.5 m"}, {"160", "1.6 m"}, {"170", "1.7 m"}, {"180", "1.8 m"}, {"190", "1.9 m"}, {"200", "2.0 m"}, {"250", "2.5 m"}, {"300", "3.0 m"}, {"350", "3.5 m"}, {"400", "4.0 m"}, {"450", "4.5 m"}, {"500", "5.0 m"}, {"550", "5.5 m"}, {"600", "6.0 m"}, {"650", "6.5 m"}, {"700", "7.0 m"}, {"750", "7.5 m"}, {"800", "8.0 m"}, {"850", "8.5 m"}, {"900", "9.0 m"}, {"950", "9.5 m"}, {"1000", "10 m"}, {"1500", "15 m"}, {"2000", "20 m"}, {"2500", "25 m"}, {"3000", "30 m"}, {"3500", "35 m"}, {"4000", "40 m"}, {"4500", "45 m"}, {"5000", "50 m"}, {"10000", "100 m"}, {"10000000", "\u221E"}
	};
    
	
    private final String[] mCameraName = {
    		"Agfa ePhoto CL-18", "AgfaPhoto DC-600uw", "Canon 1D", "Canon 1D Mark II", "Canon 1D Mark II N", "Canon 1D Mark III", "Canon 1D Mark IV", "Canon 1D X", "Canon 1D C", "Canon 1DS", "Canon 1Ds Mark II", "Canon 1Ds Mark III", "Canon 5D", "Canon 5D Mark II", "Canon 5D Mark III", "Canon 7D", "Canon 10D", "Canon 20D", "Canon 20Da", "Canon 30D", "Canon 40D", "Canon 50D", "Canon 60D", "Canon 60Da", "Canon D30", "Canon D60", "Canon Digital Rebel / 300D", "Canon Digital Rebel T1i / 500D", "Canon Digital Rebel T2i / 550D", "Canon Digital Rebel T3i / 600D", "Canon Digital Rebel T4i / 650D", "Canon Digital Rebel T3 / 1100D", "Canon Digital Rebel XS / 1000D", "Canon Digital Rebel XT / 350D", "Canon Digital Rebel XTi / 400D", "Canon Digital Rebel XSi / 450D", "Canon PowerShot A10", "Canon PowerShot A20", "Canon PowerShot A30", "Canon PowerShot A40", "Canon PowerShot A60", "Canon PowerShot A70", "Canon PowerShot A75", "Canon PowerShot A80", "Canon PowerShot A85", "Canon PowerShot A95", "Canon PowerShot A100", "Canon PowerShot A200", "Canon PowerShot A300", "Canon PowerShot A310", "Canon PowerShot A400", "Canon PowerShot A430", "Canon PowerShot A450", "Canon PowerShot A460", "Canon PowerShot A470", "Canon PowerShot A480", "Canon PowerShot A490", "Canon PowerShot A495", "Canon PowerShot A510", "Canon PowerShot A520", "Canon PowerShot A530", "Canon PowerShot A540", "Canon PowerShot A550", "Canon PowerShot A560", "Canon PowerShot A570 IS", "Canon PowerShot A580", "Canon PowerShot A590 IS", "Canon PowerShot A610", "Canon PowerShot A620", "Canon PowerShot A630", "Canon PowerShot A640", "Canon PowerShot A650", "Canon PowerShot A650 IS", "Canon PowerShot A700", "Canon PowerShot A710 IS", "Canon PowerShot A720 IS", "Canon PowerShot A800", "Canon PowerShot A810", "Canon PowerShot A820", "Canon PowerShot A900", "Canon PowerShot A1000 IS", "Canon PowerShot A1100 IS", "Canon PowerShot A1200", "Canon PowerShot A1300", "Canon PowerShot A2000 IS", "Canon PowerShot A2100 IS", "Canon PowerShot A2300 IS", "Canon PowerShot A2400 IS", "Canon PowerShot A3000 IS", "Canon PowerShot A3100 IS", "Canon PowerShot A3200 IS", "Canon PowerShot A3400 IS", "Canon PowerShot A3300 IS", "Canon PowerShot A4000 IS", "Canon PowerShot D10", "Canon PowerShot D20", "Canon PowerShot E1", "Canon PowerShot G1", "Canon PowerShot G1X", "Canon PowerShot G2", "Canon PowerShot G3", "Canon PowerShot G5", "Canon PowerShot G6", "Canon PowerShot G7", "Canon PowerShot G9", "Canon PowerShot G10", "Canon PowerShot G11", "Canon PowerShot G12", "Canon Powershot Pro1", "Canon PowerShot Pro 90 IS", "Canon Powershot S1 IS", "Canon Powershot S2 IS", "Canon Powershot S3 IS", "Canon Powershot S5 IS", "Canon PowerShot S30", "Canon PowerShot S40", "Canon PowerShot S45", "Canon PowerShot S50", "Canon PowerShot S60", "Canon PowerShot S70", "Canon PowerShot S80", "Canon PowerShot S90", "Canon PowerShot S95", "Canon PowerShot S110 Elph", "Canon PowerShot S200 Elph", "Canon PowerShot S230 Elf", "Canon PowerShot S300 Elph", "Canon PowerShot S330 Elph", "Canon PowerShot S400", "Canon PowerShot S410", "Canon PowerShot S500", "Canon Powershot SD10 Elph", "Canon Powershot SD20 Elph", "Canon Powershot SD30 Elph", "Canon Powershot SD40", "Canon Powershot SD100 Elph", "Canon Powershot SD110 Elph", "Canon Powershot SD200", "Canon Powershot SD300", "Canon Powershot SD400", "Canon Powershot SD430 Wireless", "Canon Powershot SD450", "Canon Powershot SD500", "Canon Powershot SD550", "Canon Powershot SD600", "Canon Powershot SD630", "Canon Powershot SD700 IS", "Canon Powershot SD750", "Canon Powershot SD770 IS", "Canon Powershot SD780 IS", "Canon Powershot SD790 IS", "Canon Powershot SD800 IS", "Canon Powershot SD850 IS", "Canon Powershot SD870 IS", "Canon Powershot SD880 IS", "Canon Powershot SD890 IS", "Canon Powershot SD900", "Canon Powershot SD940 IS", "Canon Powershot SD950 IS", "Canon Powershot SD960 IS", "Canon Powershot SD970 IS", "Canon Powershot SD980 IS", "Canon Powershot SD990 IS", "Canon Powershot SD1000", "Canon Powershot SD1100 IS", "Canon Powershot SD1200", "Canon Powershot SD1300 IS", "Canon Powershot SD1400 IS", "Canon Powershot SD3500 IS", "Canon Powershot SD4000 IS", "Canon Powershot SD4500 IS", "Canon Powershot SX1 IS", "Canon Powershot SX10 IS", "Canon Powershot SX20 IS", "Canon Powershot SX30 IS", "Canon Powershot SX100 IS", "Canon Powershot SX110 IS", "Canon Powershot SX120 IS", "Canon Powershot SX130 IS", "Canon Powershot SX200 IS", "Canon Powershot SX210 IS", "Canon Powershot SX240 HS", "Canon Powershot SX260 HS", "Casio Exilim EX-F1", "Casio Exilim EX-FC100", "Casio Exilim EX-FH20", "Casio Exilim EX-FH25", "Casio Exilim EX-FH100", "Casio Exilim EX-FS10", "Casio Exilim EX-G1", "Casio Exilim EX-H5", "Casio Exilim EX-H10", "Casio Exilim EX-H15", "Casio Exilim EX-H20G", "Casio Exilim EX-H30", "Casio Exilim EX-M2", "Casio Exilim EX-P505", "Casio Exilim EX-P600", "Casio Exilim EX-P700", "Casio Exilim EX-S2", "Casio Exilim EX-S5", "Casio Exilim EX-S7", "Casio Exilim EX-S10", "Casio Exilim EX-S12", "Casio Exilim EX-S100", "Casio Exilim EX-S200", "Casio Exilim EX-S500", "Casio Exilim EX-S600", "Casio Exilim EX-S600D", "Casio Exilim EX-S770", "Casio Exilim EX-S880", "Casio Exilim EX-V7", "Casio Exilim EX-V8", "Casio Exilim EX-Z5", "Casio Exilim EX-Z16", "Casio Exilim EX-Z29", "Casio Exilim EX-Z30", "Casio Exilim EX-Z35", "Casio Exilim EX-Z40", "Casio Exilim EX-Z50", "Casio Exilim EX-Z55", "Casio Exilim EX-Z57", "Casio Exilim EX-Z60", "Casio Exilim EX-Z70", "Casio Exilim EX-Z75", "Casio Exilim EX-Z77", "Casio Exilim EX-Z80", "Casio Exilim EX-Z85", "Casio Exilim EX-Z90", "Casio Exilim EX-Z100", "Casio Exilim EX-Z110", "Casio Exilim EX-Z120", "Casio Exilim EX-Z200", "Casio Exilim EX-Z250", "Casio Exilim EX-Z270", "Casio Exilim EX-Z300", "Casio Exilim EX-Z400", "Casio Exilim EX-Z450", "Casio Exilim EX-Z500", "Casio Exilim EX-Z550", "Casio Exilim EX-Z600", "Casio Exilim EX-Z700", "Casio Exilim EX-Z750", "Casio Exilim EX-Z800", "Casio Exilim EX-Z850", "Casio Exilim EX-Z1000", "Casio Exilim EX-Z1050", "Casio Exilim EX-Z1080", "Casio Exilim EX-Z1200SR", "Casio Exilim EX-Z2000", "Casio Exilim EX-Z2300", "Casio Exilim EX-ZR10", "Casio Exilim EX-ZR15", "Casio Exilim EX-ZR100", "Casio Exilim EX-ZR200", "Casio Exilim EX-ZR300", "Casio Exilim EX-ZS5", "Casio Exilim EX-ZS6", "Casio Exilim EX-ZS10", "Casio Exilim EX-ZS12", "Casio Exilim EX-ZS15", "Casio Exilim EX-ZS20", "Casio Exilim EX-ZS150", "Casio Exilim QV-R51", "Casio Exilim QV-R61", "Casio Exilim QV-R62", "Contax N Digital", "Contax TVS Digital", "Contax SL300RT", "Epson PhotoPC 3100 Z", "Epson PhotoPC L-410", "Epson L-500V", "Epson RD-1", "Epson RD-1S", "Epson RD-1X", "Fujifilm FinePix 30i", "Fujifilm FinePix A100", "Fujifilm FinePix A120", "Fujifilm FinePix A150", "Fujifilm FinePix A170", "Fujifilm FinePix A203", "Fujifilm FinePix A303", "Fujifilm FinePix A330", "Fujifilm FinePix A340", "Fujifilm FinePix A345", "Fujifilm FinePix A350", "Fujifilm FinePix A400", "Fujifilm FinePix A600", "Fujifilm FinePix A610", "Fujifilm FinePix A800", "Fujifilm FinePix A920", "Fujifilm FinePix AV200", "Fujifilm FinePix AV250", "Fujifilm FinePix AX280", "Fujifilm FinePix AX350", "Fujifilm FinePix E500", "Fujifilm FinePix E510", "Fujifilm FinePix E550", "Fujifilm FinePix E900", "Fujifilm FinePix F10", "Fujifilm FinePix F11", "Fujifilm FinePix F20", "Fujifilm FinePix F30", "Fujifilm FinePix F31fd", "Fujifilm FinePix F40fd", "Fujifilm FinePix F50fd", "Fujifilm FinePix F60fd", "Fujifilm FinePix F70EXR", "Fujifilm FinePix F80EXR", "Fujifilm FinePix F100fd", "Fujifilm FinePix F200EXR", "Fujifilm FinePix F300EXR", "Fujifilm FinePix F401", "Fujifilm FinePix F402", "Fujifilm FinePix F440", "Fujifilm FinePix F450", "Fujifilm FinePix F455", "Fujifilm FinePix F470", "Fujifilm FinePix F480", "Fujifilm FinePix F500EXR", "Fujifilm FinePix F510", "Fujifilm FinePix F550", "Fujifilm FinePix F600EXR", "Fujifilm FinePix F660EXR", "Fujifilm FinePix F601", "Fujifilm FinePix F650", "Fujifilm FinePix F700", "Fujifilm FinePix F770EXR", "Fujifilm FinePix F710", "Fujifilm FinePix F750", "Fujifilm FinePix F810", "Fujifilm FinePix IS-1", "Fujifilm FinePix IS Pro", "Fujifilm FinePix HS-10", "Fujifilm FinePix HS20EXR", "Fujifilm FinePix HS30EXR", "Fujifilm FinePix J30", "Fujifilm FinePix J100", "Fujifilm FinePix J120", "Fujifilm FinePix J110W", "Fujifilm FinePix J150W", "Fujifilm FinePix JV200", "Fujifilm FinePix JX200", "Fujifilm FinePix JX300", "Fujifilm FinePix JX350", "Fujifilm FinePix JX370", "Fujifilm FinePix JX500", "Fujifilm FinePix JX550", "Fujifilm FinePix JZ100", "Fujifilm FinePix JZ200", "Fujifilm FinePix JZ300", "Fujifilm FinePix JZ500", "Fujifilm FinePix S100", "Fujifilm FinePix S602", "Fujifilm FinePix 2600", "Fujifilm FinePix 2650", "Fujifilm FinePix 2800", "Fujifilm FinePix 3800", "Fujifilm FinePix 4800", "Fujifilm FinePix 4900", "Fujifilm FinePix S200EXR", "Fujifilm FinePix S1000fd", "Fujifilm FinePix S1500", "Fujifilm FinePix S1600", "Fujifilm FinePix S1800", "Fujifilm FinePix S2000HD", "Fujifilm FinePix S2500HD", "Fujifilm FinePix S2800HD", "Fujifilm FinePix S2950", "Fujifilm FinePix S3000", "Fujifilm FinePix S3100", "Fujifilm FinePix S3200", "Fujifilm FinePix S3500", "Fujifilm FinePix S4000", "Fujifilm FinePix S4200", "Fujifilm FinePix S4500", "Fujifilm FinePix S5000", "Fujifilm FinePix S5200 / S5600", "Fujifilm FinePix S5500 / S5100", "Fujifilm FinePix S5700", "Fujifilm FinePix SL240", "Fujifilm FinePix SL300", "Fujifilm FinePix 6800", "Fujifilm FinePix 7000", "Fujifilm FinePix S6500fd", "Fujifilm FinePix S8000fd", "Fujifilm FinePix S8100fd", "Fujifilm FinePix S9000 / S9500", "Fujifilm FinePix S1 Pro", "Fujifilm FinePix S2 Pro", "Fujifilm FinePix S3 Pro", "Fujifilm FinePix S3 Pro UVIR", "Fujifilm FinePix S5 Pro", "Fujifilm FinePix S20 Pro", "Fujifilm FinePix T200", "Fujifilm FinePix T300", "Fujifilm FinePix T400", "Fujifilm FinePix V10", "Fujifilm FinePix W3", "Fujifilm FinePix X10", "Fujifilm FinePix X100", "Fujifilm FinePix XP30", "Fujifilm FinePix XP50", "Fujifilm FinePix XP150", "Fujifilm FinePix X-Pro1", "Fujifilm FinePix X-S1", "Fujifilm FinePix Z1", "Fujifilm FinePix Z2", "Fujifilm FinePix Z3", "Fujifilm FinePix Z20fd", "Fujifilm FinePix Z33WP", "Fujifilm FinePix Z35", "Fujifilm FinePix Z50fd", "Fujifilm FinePix Z70", "Fujifilm FinePix Z90", "Fujifilm FinePix Z110", "Fujifilm FinePix Z200fd", "Fujifilm FinePix Z700EXR", "Fujifilm FinePix Z900EXR", "Fujifilm FinePix Z1000EXR", "GE E1050", "Hewlett Packard PhotoSmart 120", "Hewlett Packard PhotoSmart 318", "Hewlett Packard PhotoSmart 320", "Hewlett Packard PhotoSmart 435", "Hewlett Packard PhotoSmart 620", "Hewlett Packard PhotoSmart 635", "Hewlett Packard PhotoSmart 715", "Hewlett Packard PhotoSmart 720", "Hewlett Packard PhotoSmart 735", "Hewlett Packard PhotoSmart 812", "Hewlett Packard PhotoSmart 850", "Hewlett Packard PhotoSmart 912", "Hewlett Packard PhotoSmart 935", "Hewlett Packard PhotoSmart 945", "Hewlett Packard PhotoSmart C215", "Hewlett Packard PhotoSmart M22", "Hewlett Packard PhotoSmart M23", "Hewlett Packard PhotoSmart M307", "Hewlett Packard PhotoSmart M407", "Hewlett Packard PhotoSmart M417", "Hewlett Packard PhotoSmart M425", "Hewlett Packard PhotoSmart M437", "Hewlett Packard PhotoSmart M517", "Hewlett Packard PhotoSmart M525", "Hewlett Packard PhotoSmart M527", "Hewlett Packard PhotoSmart M537", "Hewlett Packard PhotoSmart R507", "Hewlett Packard PhotoSmart R607", "Hewlett Packard PhotoSmart R707", "Hewlett Packard PhotoSmart R717", "Hewlett Packard PhotoSmart R817", "Hewlett Packard PhotoSmart R818", "Hewlett Packard PhotoSmart R837", "Kodak CX-7430", "Kodak CX-6330", "Kodak CX-7330", "Kodak CX-7530", "Kodak DC-5000", "Kodak DCS-14N", "Kodak DCS-720X", "Kodak DCS-760", "Kodak DCS Pro", "Kodak DX-4530", "Kodak DX-6440", "Kodak DX-6490", "Kodak DX-7440", "Kodak DX-7590", "Kodak DX-7630", "Kodak LS-743", "Kodak LS-753", "Kodak PalmPix 100/500", "Kodak Z730", "Kodak Z760", "Kodak Z7590", "Konica KD-310Z", "Konica KD-400", "Konica KD-500", "Konica Minolta Dimage 5", "Konica Minolta Dimage 7", "Konica Minolta Dimage 7i", "Konica Minolta Dimage 7Hi", "Konica Minolta Dimage A1", "Konica Minolta Dimage A2", "Konica Minolta Dimage A200", "Konica Minolta Dimage E50", "Konica Minolta Dimage E223", "Konica Minolta Dimage F100", "Konica Minolta Dimage F300", "Konica Minolta Dimage G400", "Konica Minolta Dimage G600", "Konica Minolta Dimage S404", "Konica Minolta Dimage S414", "Konica Minolta Dimage X", "Konica Minolta Dimage Xg", "Konica Minolta Dimage Xi", "Konica Minolta Dimage Xt", "Konica Minolta Dimage X1", "Konica Minolta Dimage X20", "Konica Minolta Dimage X31", "Konica Minolta Dimage X50", "Konica Minolta Dimage X60", "Konica Minolta Dimage Z1", "Konica Minolta Dimage Z2", "Konica Minolta Dimage Z3", "Konica Minolta Dimage Z5", "Konica Minolta Dimage Z6", "Konica Minolta Dimage Z10", "Konica Minolta Dimage Z20", "Konica Minolta Maxxum 5D", "Konica Minolta Maxxum 7D", "Kyocera FineCam L4v", "Kyocera FineCam M410R", "Kyocera FineCam S3", "Kyocera FineCam S3x", "Kyocera FineCam S4", "Kyocera FineCam S5", "Kyocera FineCam SL300R", "Kyocera FineCam SL400R", "Leica C-Lux 1", "Leica C-Lux 3", "Leica Digilux 1", "Leica Digilux 2", "Leica Digilux 3", "Leica D-Lux", "Leica D-Lux 2", "Leica D-Lux 3", "Leica D-Lux 4", "Leica D-Lux 5", "Leica M8", "Leica M8.2", "Leica M9", "Leica M9-P", "Leica M-Monochrom", "Leica V-LUX 1", "Leica V-LUX 2", "Leica V-LUX 3", "Leica V-LUX 20", "Leica V-LUX 30", "Leica V-LUX 40", "Leica X1", "Leica X2", "Mamiya ZD", "Microtek Take-it D1", "Minolta Dimage 5", "Minolta Dimage 7", "Minolta Dimage 7i", "Minolta Dimage 7Hi", "Minolta Dimage A1", "Minolta Dimage A2", "Minolta Dimage E223", "Minolta Dimage F100", "Minolta Dimage F300", "Minolta Dimage G400", "Minolta Dimage G600", "Minolta Dimage S404", "Minolta Dimage S414", "Minolta Dimage X", "Minolta Dimage Xg", "Minolta Dimage Xi", "Minolta Dimage Xt", "Minolta Dimage X20", "Minolta Dimage X50", "Minolta Dimage Z1", "Minolta Dimage Z2", "Nikon Coolpix 2000", "Nikon Coolpix 2100", "Nikon Coolpix 2200", "Nikon Coolpix 2500", "Nikon Coolpix 3100", "Nikon Coolpix 3200", "Nikon Coolpix 3500", "Nikon Coolpix 3700", "Nikon Coolpix 4100", "Nikon Coolpix 4200", "Nikon Coolpix 4300", "Nikon Coolpix 4500", "Nikon Coolpix 4600", "Nikon Coolpix 4800", "Nikon Coolpix 5000", "Nikon Coolpix 5200", "Nikon Coolpix 5400", "Nikon Coolpix 5600", "Nikon Coolpix 5700", "Nikon Coolpix 5900", "Nikon Coolpix 7600", "Nikon Coolpix 7900", "Nikon Coolpix 8400", "Nikon Coolpix 8700", "Nikon Coolpix 8800", "Nikon Coolpix S9600 / S9100", "Nikon Coolpix 775", "Nikon Coolpix 885", "Nikon Coolpix 995", "Nikon Coolpix AW100", "Nikon Coolpix L1", "Nikon Coolpix L2", "Nikon Coolpix L3", "Nikon Coolpix L4", "Nikon Coolpix L5", "Nikon Coolpix L6", "Nikon Coolpix L10", "Nikon Coolpix L11", "Nikon Coolpix L12", "Nikon Coolpix L14", "Nikon Coolpix L15", "Nikon Coolpix L16", "Nikon Coolpix L18", "Nikon Coolpix L19", "Nikon Coolpix L20", "Nikon Coolpix L21", "Nikon Coolpix L22", "Nikon Coolpix L24", "Nikon Coolpix L26", "Nikon Coolpix L100", "Nikon Coolpix L110", "Nikon Coolpix L120", "Nikon Coolpix L810", "Nikon Coolpix P1", "Nikon Coolpix P2", "Nikon Coolpix P3", "Nikon Coolpix P4", "Nikon Coolpix P50", "Nikon Coolpix P60", "Nikon Coolpix P80", "Nikon Coolpix P90", "Nikon Coolpix P100", "Nikon Coolpix P300", "Nikon Coolpix P310", "Nikon Coolpix P500", "Nikon Coolpix P510", "Nikon Coolpix P5000", "Nikon Coolpix P5100", "Nikon Coolpix P6000", "Nikon Coolpix P7000", "Nikon Coolpix P7100", "Nikon Coolpix SQ", "Nikon Coolpix S1", "Nikon Coolpix S2", "Nikon Coolpix S3", "Nikon Coolpix S4", "Nikon Coolpix S5", "Nikon Coolpix S6", "Nikon Coolpix S7c", "Nikon Coolpix S9", "Nikon Coolpix S10", "Nikon Coolpix S30", "Nikon Coolpix S50", "Nikon Coolpix S50c", "Nikon Coolpix S51", "Nikon Coolpix S51c", "Nikon Coolpix S52c", "Nikon Coolpix S60", "Nikon Coolpix S70", "Nikon Coolpix S80", "Nikon Coolpix S100", "Nikon Coolpix S200", "Nikon Coolpix S210", "Nikon Coolpix S220", "Nikon Coolpix S230", "Nikon Coolpix S500", "Nikon Coolpix S510", "Nikon Coolpix S520", "Nikon Coolpix S520", "Nikon Coolpix S550", "Nikon Coolpix S560", "Nikon Coolpix S570", "Nikon Coolpix S600", "Nikon Coolpix S610", "Nikon Coolpix S610c", "Nikon Coolpix S620", "Nikon Coolpix S630", "Nikon Coolpix S640", "Nikon Coolpix S1100pj", "Nikon Coolpix S1200pj", "Nikon Coolpix S3000", "Nikon Coolpix S3000", "Nikon Coolpix S3100", "Nikon Coolpix S3300", "Nikon Coolpix S4100", "Nikon Coolpix S4300", "Nikon Coolpix S5100", "Nikon Coolpix S6000", "Nikon Coolpix S6100", "Nikon Coolpix S6200", "Nikon Coolpix S6300", "Nikon Coolpix S8000", "Nikon Coolpix S8200", "Nikon Coolpix S8100", "Nikon Coolpix S9100", "Nikon Coolpix S9300", "Nikon 1 J1", "Nikon 1 V1", "Nikon D1H", "Nikon D1X", "Nikon D2H", "Nikon D2Hs", "Nikon D2X", "Nikon D2Xs", "Nikon D3", "Nikon D3x", "Nikon D3s", "Nikon D4", "Nikon D40", "Nikon D40x", "Nikon D50", "Nikon D60", "Nikon D70", "Nikon D70s", "Nikon D80", "Nikon D90", "Nikon D100", "Nikon D200", "Nikon D300", "Nikon D300S", "Nikon D600", "Nikon D610", "Nikon D700", "Nikon D800", "Nikon D800E", "Nikon D3000", "Nikon D3100", "Nikon D3200", "Nikon D5000", "Nikon D5100", "Nikon D7000", "Olympus AZ-1", "Olympus Brio D-230", "Olympus C-160", "Olympus C-2500L", "Olympus C-3020", "Olympus C-3040", "Olympus C-460 ZOOM del Sol", "Olympus C-4000", "Olympus C-4040Z", "Olympus C-50", "Olympus C-55 / C-5500", "Olympus C-60", "Olympus C-70 / C-7000", "Olympus C-500", "Olympus C-5000", "Olympus C-5050", "Olympus C-5060", "Olympus C-5500", "Olympus C-700 ZU", "Olympus C-7070", "Olympus C-7000", "Olympus C-730 CZ", "Olympus C-720", "Olympus C-730", "Olympus C-740", "Olympus C-750", "Olympus C-765", "Olympus C-770", "Olympus C-8080", "Olympus D-40", "Olympus D-100 Brio", "Olympus D-150 Brio", "Olympus D-370", "Olympus D-380", "Olympus D-390", "Olympus D-395", "Olympus D-425", "Olympus D-435", "Olympus D-510", "Olympus D-520", "Olympus D-535 / C-370", "Olympus D-540", "Olympus D-545", "Olympus D-550", "Olympus D-560", "Olympus D-580", "Olympus D-590", "Olympus D-595", "Olympus D-630", "Olympus E-1", "Olympus E-3", "Olympus E-5", "Olympus E-10", "Olympus E-100 RS", "Olympus E-20", "Olympus E-30", "Olympus E-300", "Olympus E-330", "Olympus E-400", "Olympus E-410", "Olympus E-420", "Olympus E-450", "Olympus E-500", "Olympus E-510", "Olympus E-520", "Olympus E-600", "Olympus E-620", "Olympus E-M5", "Olympus E-P1", "Olympus E-P2", "Olympus E-P3", "Olympus E-PL1", "Olympus E-PL1s", "Olympus E-PL2", "Olympus E-PL3", "Olympus E-PM1", "Olympus Ferrari DIGITAL MODEL 2004", "Olympus FE-20", "Olympus FE-25", "Olympus FE-26", "Olympus FE-45", "Olympus FE-46", "Olympus FE-47", "Olympus FE-100", "Olympus FE-110", "Olympus FE-115", "Olympus FE-120", "Olympus FE-130", "Olympus FE-140", "Olympus FE-150", "Olympus FE-170", "Olympus FE-180", "Olympus FE-200", "Olympus FE-210", "Olympus FE-230", "Olympus FE-240", "Olympus FE-250", "Olympus FE-270", "Olympus FE-280", "Olympus FE-290", "Olympus FE-300", "Olympus FE-310", "Olympus FE-340", "Olympus FE-350", "Olympus FE-360", "Olympus FE-370", "Olympus FE-3000", "Olympus FE-4000", "Olympus FE-4030", "Olympus FE-4040", "Olympus FE-5000", "Olympus FE-5010", "Olympus FE-5020", "Olympus FE-5500", "Olympus ImageLink", "Olympus IR-300", "Olympus IR-500", "Olympus SP-310", "Olympus SP-320", "Olympus SP-350", "Olympus SP-500 UZ", "Olympus SP-510 UZ", "Olympus SP-550 UZ", "Olympus SP-560 UZ", "Olympus SP-565 UZ", "Olympus SP-570 UZ", "Olympus SP-590 UZ", "Olympus SP-600 UZ", "Olympus SP-610 UZ", "Olympus SP-620 UZ", "Olympus SP-700", "Olympus SP-800 UZ", "Olympus SP-810 UZ", "Olympus Stylus 300", "Olympus Stylus 410", "Olympus Stylus 500", "Olympus Stylus 550WP", "Olympus Stylus 600", "Olympus Stylus 700", "Olympus Stylus 720 SW", "Olympus Stylus 725 SW", "Olympus Stylus 730", "Olympus Stylus 740", "Olympus Stylus 750", "Olympus Stylus 760", "Olympus Stylus 770", "Olympus Stylus 780", "Olympus Stylus 790 SW", "Olympus Stylus 800", "Olympus Stylus 810", "Olympus Stylus 820", "Olympus Stylus 830", "Olympus Stylus 840", "Olympus Stylus 850 SW", "Olympus Stylus 1000", "Olympus Stylus 1010", "Olympus Stylus 1020", "Olympus Stylus 1030 SW", "Olympus Stylus 1040", "Olympus Stylus 1050 SW", "Olympus Stylus 1200", "Olympus Stylus 5000", "Olympus Stylus 5010", "Olympus Stylus 5050", "Olympus Stylus 7000", "Olympus Stylus 7030", "Olympus Stylus 7010", "Olympus Stylus 7040", "Olympus Stylus 7050", "Olympus Stylus 9000", "Olympus Stylus 9010", "Olympus Stylus Tough 3000", "Olympus Stylus Tough 6000", "Olympus Stylus Tough 6010", "Olympus Stylus Tough 6020", "Olympus Stylus Tough 8000", "Olympus Stylus Tough 8010", "Olympus Stylus Tough TG-1 iHS ", "Olympus Stylus Verve", "Olympus Stylus Verve s", "Olympus SH-21", "Olympus SZ-10", "Olympus SZ-11", "Olympus SZ-12", "Olympus SZ-30MR", "Olympus SZ-31MR", "Olympus TG-310", "Olympus TG-320", "Olympus TG-610", "Olympus TG-810", "Olympus TG-820 His", "Olympus VG-110", "Olympus VG-120", "Olympus VG-145", "Olympus VG-160", "Olympus VR-320", "Olympus VR-330", "Olympus VR-340", "Olympus XZ-1", "Panasonic Lumix DMC-3D1", "Panasonic Lumix DMC-F7", "Panasonic Lumix DMC-FH3", "Panasonic Lumix DMC-FH5", "Panasonic Lumix DMC-FH6", "Panasonic Lumix DMC-FH7", "Panasonic Lumix DMC-FH8", "Panasonic Lumix DMC-FH25", "Panasonic Lumix DMC-FH27", "Panasonic Lumix DMC-FP1", "Panasonic Lumix DMC-FP2", "Panasonic Lumix DMC-FP3", "Panasonic Lumix DMC-FP5", "Panasonic Lumix DMC-FP7", "Panasonic Lumix DMC-FP8", "Panasonic Lumix DMC-FS3", "Panasonic Lumix DMC-FS7", "Panasonic Lumix DMC-FS10", "Panasonic Lumix DMC-FS11", "Panasonic Lumix DMC-FS12", "Panasonic Lumix DMC-FS15", "Panasonic Lumix DMC-FS16", "Panasonic Lumix DMC-FS20", "Panasonic Lumix DMC-FS25", "Panasonic Lumix DMC-FS30", "Panasonic Lumix DMC-FS33", "Panasonic Lumix DMC-FS42", "Panasonic Lumix DMC-FS62", "Panasonic Lumix DMC-FX01", "Panasonic Lumix DMC-FX07", "Panasonic Lumix DMC-FX2", "Panasonic Lumix DMC-FX3", "Panasonic Lumix DMC-FX7", "Panasonic Lumix DMC-FX8", "Panasonic Lumix DMC-FX9", "Panasonic Lumix DMC-FX10", "Panasonic Lumix DMC-FX12", "Panasonic Lumix DMC-FX30", "Panasonic Lumix DMC-FX33", "Panasonic Lumix DMC-FX35", "Panasonic Lumix DMC-FX37", "Panasonic Lumix DMC-FX48", "Panasonic Lumix DMC-FX50", "Panasonic Lumix DMC-FX55", "Panasonic Lumix DMC-FX66", "Panasonic Lumix DMC-FX65", "Panasonic Lumix DMC-FX75", "Panasonic Lumix DMC-FX78", "Panasonic Lumix DMC-FX90", "Panasonic Lumix DMC-FX100", "Panasonic Lumix DMC-FX150", "Panasonic Lumix DMC-FX500", "Panasonic Lumix DMC-FX580", "Panasonic Lumix DMC-FX700", "Panasonic Lumix DMC-FZ1", "Panasonic Lumix DMC-FZ3", "Panasonic Lumix DMC-FZ4", "Panasonic Lumix DMC-FZ5", "Panasonic Lumix DMC-FZ7", "Panasonic Lumix DMC-FZ8", "Panasonic Lumix DMC-FZ10", "Panasonic Lumix DMC-FZ18", "Panasonic Lumix DMC-FZ20", "Panasonic Lumix DMC-FZ28", "Panasonic Lumix DMC-FZ30", "Panasonic Lumix DMC-FZ35", "Panasonic Lumix DMC-FZ40", "Panasonic Lumix DMC-FZ47", "Panasonic Lumix DMC-FZ50", "Panasonic Lumix DMC-FZ100", "Panasonic Lumix DMC-FZ150", "Panasonic Lumix DMC-G1", "Panasonic Lumix DMC-G2", "Panasonic Lumix DMC-G3", "Panasonic Lumix DMC-G10", "Panasonic Lumix DMC-GF1", "Panasonic Lumix DMC-GF2", "Panasonic Lumix DMC-GF3", "Panasonic Lumix DMC-GF5", "Panasonic Lumix DMC-GH1", "Panasonic Lumix DMC-GH2", "Panasonic Lumix DMC-GX1", "Panasonic Lumix DMC-L1", "Panasonic Lumix DMC-L10", "Panasonic Lumix DMC-LC1", "Panasonic Lumix DMC-LC20", "Panasonic Lumix DMC-LC40", "Panasonic Lumix DMC-LC5", "Panasonic Lumix DMC-LC50", "Panasonic Lumix DMC-LC70", "Panasonic Lumix DMC-LC80", "Panasonic Lumix DMC-LS1", "Panasonic Lumix DMC-LS2", "Panasonic Lumix DMC-LS5", "Panasonic Lumix DMC-LS60", "Panasonic Lumix DMC-LS75", "Panasonic Lumix DMC-LS80", "Panasonic Lumix DMC-LS85", "Panasonic Lumix DMC-LX1", "Panasonic Lumix DMC-LX2", "Panasonic Lumix DMC-LX3", "Panasonic Lumix DMC-LX5", "Panasonic Lumix DMC-LZ1", "Panasonic Lumix DMC-LZ2", "Panasonic Lumix DMC-LZ3", "Panasonic Lumix DMC-LZ5", "Panasonic Lumix DMC-LZ6", "Panasonic Lumix DMC-LZ7", "Panasonic Lumix DMC-LZ8", "Panasonic Lumix DMC-LZ10", "Panasonic Lumix DMC-S1", "Panasonic Lumix DMC-S2", "Panasonic Lumix DMC-S3", "Panasonic Lumix DMC-SZ1", "Panasonic Lumix DMC-SZ7", "Panasonic Lumix DMC-TS1", "Panasonic Lumix DMC-TS2", "Panasonic Lumix DMC-TS3", "Panasonic Lumix DMC-TS4", "Panasonic Lumix DMC-TS10", "Panasonic Lumix DMC-TS20", "Panasonic Lumix DMC-TZ1", "Panasonic Lumix DMC-TZ2", "Panasonic Lumix DMC-TZ3", "Panasonic Lumix DMC-TZ4", "Panasonic Lumix DMC-TZ5", "Panasonic Lumix DMC-TZ6", "Panasonic Lumix DMC-TZ7", "Panasonic Lumix DMC-TZ8", "Panasonic Lumix DMC-TZ10", "Panasonic Lumix DMC-TZ50", "Panasonic Lumix DMC-ZR1", "Panasonic Lumix DMC-ZS5", "Panasonic Lumix DMC-ZS7", "Panasonic Lumix DMC-ZS8", "Panasonic Lumix DMC-ZS10", "Panasonic Lumix DMC-ZS15", "Panasonic Lumix DMC-ZS20", "Panasonic Lumix DMC-ZX3", "Pentax EI-100", "Pentax Optio 30", "Pentax Optio 33WR", "Pentax Optio 43WR", "Pentax Optio 33LF", "Pentax Optio 50", "Pentax Optio 60", "Pentax Optio 230", "Pentax Optio 330", "Pentax Optio 330GS", "Pentax Optio 430", "Pentax Optio 430 RS", "Pentax Optio 450", "Pentax Optio 550", "Pentax Optio 555", "Pentax Optio 750Z", "Pentax Optio A10", "Pentax Optio A20", "Pentax Optio A30", "Pentax Optio A40", "Pentax Optio E10", "Pentax Optio E20", "Pentax Optio E30", "Pentax Optio E40", "Pentax Optio E50", "Pentax Optio E60", "Pentax Optio E70", "Pentax Optio E80", "Pentax Optio E90", "Pentax Optio H90", "Pentax Optio I-10", "Pentax Optio LS465", "Pentax Optio MX4", "Pentax Optio M10", "Pentax Optio M20", "Pentax Optio M30", "Pentax Optio M40", "Pentax Optio M50", "Pentax Optio M60", "Pentax Optio M90", "Pentax Optio P70", "Pentax Optio P80", "Pentax Optio RS1500", "Pentax Optio RZ10", "Pentax Optio RZ18", "Pentax Optio S", "Pentax Optio S1", "Pentax Optio S4i", "Pentax Optio S5i", "Pentax Optio S5n", "Pentax Optio S5z", "Pentax Optio S6", "Pentax Optio S7", "Pentax Optio S10", "Pentax Optio S12", "Pentax Optio S30", "Pentax Optio S40", "Pentax Optio S45", "Pentax Optio S50", "Pentax Optio S55", "Pentax Optio S60", "Pentax Optio SV", "Pentax Optio SVi ", "Pentax Optio T10", "Pentax Optio T20", "Pentax Optio T30", "Pentax Optio V10", "Pentax Optio VS20", "Pentax Optio X", "Pentax Optio W10", "Pentax Optio W20", "Pentax Optio W30", "Pentax Optio W80", "Pentax Optio W90", "Pentax Optio WG-1", "Pentax Optio WG-1 GPS", "Pentax Optio WG-2", "Pentax Optio WG-2 GPS", "Pentax Optio WP", "Pentax Optio WPi ", "Pentax Optio WS80", "Pentax Optio Z10", "Pentax 645D", "Pentax *ist D", "Pentax *ist DL", "Pentax *ist DL2", "Pentax *ist DS", "Pentax *ist DS2", "Pentax K-r", "Pentax K-x", "Pentax K-01", "Pentax K-5", "Pentax K7", "Pentax K10D", "Pentax K20D", "Pentax K-30", "Pentax K100D", "Pentax K100D Super", "Pentax K110D", "Pentax K200D", "Pentax K2000", "Pentax Q", "Pentax X70", "Pentax X90", "Ricoh CX1", "Ricoh CX2", "Ricoh CX3", "Ricoh CX4", "Ricoh CX5", "Ricoh CX6", "Ricoh Caplio 500G wide", "Ricoh Caplio RX", "Ricoh Caplio R2", "Ricoh Caplio R3", "Ricoh Caplio R4", "Ricoh Caplio R5", "Ricoh Caplio R6", "Ricoh Caplio R7", "Ricoh Caplio R8", "Ricoh Caplio R30", "Ricoh Caplio R40", "Ricoh Caplio RR530", "Ricoh Caplio RR770", "Ricoh Caplio G600", "Ricoh Caplio GX", "Ricoh Caplio GX8", "Ricoh Caplio GX100", "Ricoh Caplio GX200", "Ricoh G700SE", "Ricoh GR", "Ricoh GR Digital II", "Ricoh GR Digital III", "Ricoh GR Digital IV", "Ricoh GXR (50mm)", "Ricoh GXR (28mm - 300mm)", "Ricoh GXR (28mm)", "Ricoh GXR Mount A12", "Ricoh GXR (24mm - 85mm) A16", "Ricoh PX", "Samsung AQ100", "Samsung Digimax 230", "Samsung Digimax 410", "Samsung Digimax 240", "Samsung Digimax A4", "Samsung Digimax A5", "Samsung Digimax A6", "Samsung Digimax A7", "Samsung Digimax A40", "Samsung Digimax A50", "Samsung Digimax A55W", "Samsung Digimax A400", "Samsung Digimax A302", "Samsung Digimax A402", "Samsung Digimax A502", "Samsung Digimax i5", "Samsung Digimax i50 MP3", "Samsung Digimax L50", "Samsung Digimax L60", "Samsung Digimax L55W", "Samsung Digimax L700", "Samsung Digimax U-CA 4", "Samsung Digimax U-CA 5", "Samsung Digimax V3", "Samsung Digimax V4", "Samsung Digimax V6", "Samsung Digimax V50", "Samsung Digimax V70", "Samsung Digimax V700", "Samsung Digimax V800", "Samsung CL5", "Samsung CL80", "Samsung DV300F", "Samsung GX-1L", "Samsung GX-1S", "Samsung GX-10", "Samsung GX-20", "Samsung HZ15", "Samsung HZ25W", "Samsung HZ30W", "Samsung HZ35W", "Samsung HZ50W", "Samsung i85", "Samsung L73", "Samsung L77", "Samsung L100", "Samsung L110", "Samsung L210", "Samsung L310W", "Samsung MV800", "Samsung NV3", "Samsung NV4", "Samsung NV7 OPS", "Samsung NV10", "Samsung NV11", "Samsung NV30", "Samsung NV40", "Samsung NX5", "Samsung NX10", "Samsung NX11", "Samsung NX20", "Samsung NX100", "Samsung NX200", "Samsung NX210", "Samsung NX1000", "Samsung PL120", "Samsung PL170", "Samsung PL200", "Samsung PL210", "Samsung PRO815", "Samsung S630", "Samsung S730", "Samsung S830", "Samsung S1030", "Samsung S1050", "Samsung SH100", "Samsung SL30", "Samsung SL620", "Samsung SL820", "Samsung ST30", "Samsung ST65", "Samsung ST66", "Samsung ST76", "Samsung ST80", "Samsung ST90", "Samsung ST95", "Samsung ST97", "Samsung ST100", "Samsung ST200F", "Samsung ST600", "Samsung ST700", "Samsung ST6500", "Samsung TL205", "Samsung TL210", "Samsung TL220", "Samsung TL225", "Samsung TL240", "Samsung TL320", "Samsung TL350", "Samsung TL500", "Samsung WB150F", "Samsung WB210", "Samsung WB700", "Samsung WB750", "Sanyo Xacti DSC-S1", "Sanyo Xacti DSC-S3", "Sanyo Xacti DSC-S4", "Sanyo Xacti J4", "Sanyo VPC-AZ3", "Sanyo VPC-J1", "Sigma DP1", "Sigma DP1 Merrill", "Sigma DP1s", "Sigma DP1x", "Sigma DP2x", "Sigma DP2", "Sigma DP2 Merrill", "Sigma DP2s", "Sigma SD1", "Sigma SD1 Merrill", "Sigma SD-9", "Sigma SD-10", "Sigma SD-14", "Sigma SD-15", "Sony Alpha DSLR-A100", "Sony Alpha DSLR-A200", "Sony Alpha DSLR-A230", "Sony Alpha DSLR-A290", "Sony Alpha DSLR-A300", "Sony Alpha DSLR-A330", "Sony Alpha DSLR-A350", "Sony Alpha DSLR-A380", "Sony Alpha DSLR-A390", "Sony Alpha DSLR-A450", "Sony Alpha DSLR-A500", "Sony Alpha DSLR-A550", "Sony Alpha DSLR-A560", "Sony Alpha DSLR-A580", "Sony Alpha DSLR-A700", "Sony Alpha DSLR-A850", "Sony Alpha DSLR-A900", "Sony Cyber Shot DSC-F88", "Sony Cyber Shot DSC-F707", "Sony Cyber Shot DSC-F717", "Sony Cyber Shot DSC-F828", "Sony Cyber Shot DSC-G1", "Sony Cyber Shot DSC-G3", "Sony Cyber Shot DSC-H1", "Sony Cyber Shot DSC-H2", "Sony Cyber Shot DSC-H3", "Sony Cyber Shot DSC-H5", "Sony Cyber Shot DSC-H7", "Sony Cyber Shot DSC-H7V", "Sony Cyber Shot DSC-H9", "Sony Cyber Shot DSC-H9V", "Sony Cyber Shot DSC-H10", "Sony Cyber Shot DSC-H20", "Sony Cyber Shot DSC-H50", "Sony Cyber Shot DSC-H55", "Sony Cyber Shot DSC-H70", "Sony Cyber Shot DSC-H90", "Sony Cyber Shot DSC-HX1", "Sony Cyber Shot DSC-HX10V", "Sony Cyber Shot DSC-HX20V", "Sony Cyber Shot DSC-HX30V", "Sony Cyber Shot DSC-HX100V", "Sony Cyber Shot DSC-HX200V", "Sony Cyber Shot DSC-L1", "Sony Cyber Shot DSC-M1", "Sony Cyber Shot DSC-M2", "Sony Cyber Shot DSC-N1", "Sony Cyber Shot DSC-N2", "Sony Cyber Shot DSC-P2", "Sony Cyber Shot DSC-P3", "Sony Cyber Shot DSC-P30", "Sony Cyber Shot DSC-P31", "Sony Cyber Shot DSC-P32", "Sony Cyber Shot DSC-P41", "Sony Cyber Shot DSC-P5", "Sony Cyber Shot DSC-P7", "Sony Cyber Shot DSC-P8", "Sony Cyber Shot DSC-P9", "Sony Cyber Shot DSC-P10", "Sony Cyber Shot DSC-P50", "Sony Cyber Shot DSC-P51", "Sony Cyber Shot DSC-P52", "Sony Cyber Shot DSC-P71", "Sony Cyber Shot DSC-P72", "Sony Cyber Shot DSC-P73", "Sony Cyber Shot DSC-P92", "Sony Cyber Shot DSC-P93", "Sony Cyber Shot DSC-P100", "Sony Cyber Shot DSC-P120", "Sony Cyber Shot DSC-P150", "Sony Cyber Shot DSC-P200", "Sony Cyber Shot DSC-R1", "Sony Cyber Shot DSC-RX100", "Sony Cyber Shot DSC-S40", "Sony Cyber Shot DSC-S60", "Sony Cyber Shot DSC-S75", "Sony Cyber Shot DSC-S85", "Sony Cyber Shot DSC-S90", "Sony Cyber Shot DSC-S600", "Sony Cyber Shot DSC-S650", "Sony Cyber Shot DSC-S700", "Sony Cyber Shot DSC-S730", "Sony Cyber Shot DSC-S750", "Sony Cyber Shot DSC-S780", "Sony Cyber Shot DSC-S800", "Sony Cyber Shot DSC-T1", "Sony Cyber Shot DSC-T2", "Sony Cyber Shot DSC-T3", "Sony Cyber Shot DSC-T5", "Sony Cyber Shot DSC-T7", "Sony Cyber Shot DSC-T9", "Sony Cyber Shot DSC-T10", "Sony Cyber Shot DSC-T11", "Sony Cyber Shot DSC-T20", "Sony Cyber Shot DSC-T30", "Sony Cyber Shot DSC-T33", "Sony Cyber Shot DSC-T50", "Sony Cyber Shot DSC-T70", "Sony Cyber Shot DSC-T77", "Sony Cyber Shot DSC-T90", "Sony Cyber Shot DSC-T100", "Sony Cyber Shot DSC-T110", "Sony Cyber Shot DSC-T200", "Sony Cyber Shot DSC-T300", "Sony Cyber Shot DSC-T500", "Sony Cyber Shot DSC-T700", "Sony Cyber Shot DSC-T900", "Sony Cyber Shot DSC-TX1", "Sony Cyber Shot DSC-TX5", "Sony Cyber Shot DSC-TX9", "Sony Cyber Shot DSC-TX10", "Sony Cyber Shot DSC-TX20", "Sony Cyber Shot DSC-TX55", "Sony Cyber Shot DSC-TX66", "Sony Cyber Shot DSC-TX100V", "Sony Cyber Shot DSC-TX200V", "Sony Cyber Shot DSC-U20", "Sony Cyber Shot DSC-U40", "Sony Cyber Shot DSC-U60", "Sony Cyber Shot DSC-V1", "Sony Cyber Shot DSC-V3", "Sony Cyber Shot DSC-W1", "Sony Cyber Shot DSC-W5", "Sony Cyber Shot DSC-W7", "Sony Cyber Shot DSC-W20", "Sony Cyber Shot DSC-W30", "Sony Cyber Shot DSC-W35", "Sony Cyber Shot DSC-W50", "Sony Cyber Shot DSC-W55", "Sony Cyber Shot DSC-W70", "Sony Cyber Shot DSC-W80", "Sony Cyber Shot DSC-W90", "Sony Cyber Shot DSC-W100", "Sony Cyber Shot DSC-W120", "Sony Cyber Shot DSC-W130", "Sony Cyber Shot DSC-W150", "Sony Cyber Shot DSC-W170", "Sony Cyber Shot DSC-W200", "Sony Cyber Shot DSC-W230", "Sony Cyber Shot DSC-W290", "Sony Cyber Shot DSC-W300", "Sony Cyber Shot DSC-W510", "Sony Cyber Shot DSC-W530", "Sony Cyber Shot DSC-W550", "Sony Cyber Shot DSC-W560", "Sony Cyber Shot DSC-W570", "Sony Cyber Shot DSC-W610", "Sony Cyber Shot DSC-W620", "Sony Cyber Shot DSC-W650", "Sony Cyber Shot DSC-W690", "Sony Cyber Shot DSC-WX5", "Sony Cyber Shot DSC-WX9", "Sony Cyber Shot DSC-WX10", "Sony Cyber Shot DSC-WX30", "Sony Cyber Shot DSC-WX50", "Sony Cyber Shot DSC-WX70", "Sony Cyber Shot DSC-WX150", "Sony NEX-3", "Sony NEX-5", "Sony NEX-5N", "Sony NEX-C3", "Sony NEX-F3", "Sony NEX-7", "Sony Cyber Shot DSC-WX1", "Sony Mavica MVC-CD200", "Sony Mavica MVC-CD250", "Sony Mavica MVC-CD300", "Sony Mavica MVC-CD350", "Sony Mavica MVC-CD400", "Sony Mavica MVC-CD500", "Sony Mavica MVC-FD100", "Sony Mavica MVC-FD200", "Sony SLT-A33", "Sony SLT-A35", "Sony SLT-A37", "Sony SLT-A55", "Sony SLT-A57", "Sony SLT-A65", "Sony SLT-A77", "Vivitar Vivicam 3330", "Viewpoint Photography ", "  Analog 35mm", "  Analog APS", "  Analog 6x4.5", "  Analog 6x6", "  Analog 6x7", "  Analog 6x9", "  Analog 4x5", "  Analog 5x7", "  Analog 8x10"
    };

    private final String[] mCameraCoc = {
    		"0.005", "0.005", "0.023", "0.023", "0.023", "0.023", "0.023", "0.03", "0.03", "0.03", "0.03", "0.03", "0.03", "0.03", "0.03", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.019", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.006", "0.004", "0.004", "0.005", "0.005", "0.004", "0.004", "0.004", "0.004", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.006", "0.006", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.007", "0.007", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.016", "0.006", "0.006", "0.006", "0.006", "0.006", "0.006", "0.007", "0.007", "0.007", "0.008", "0.006", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.006", "0.006", "0.006", "0.006", "0.006", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.006", "0.005", "0.005", "0.005", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.004", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.006", "0.006", "0.006", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.006", "0.03", "0.006", "0.005", "0.006", "0.005", "0.005", "0.02", "0.02", "0.02", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.007", "0.005", "0.007", "0.007", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.007", "0.007", "0.007", "0.007", "0.007", "0.007", "0.007", "0.007", "0.007", "0.007", "0.006", "0.006", "0.007", "0.007", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.007", "0.006", "0.006", "0.006", "0.007", "0.005", "0.007", "0.006", "0.006", "0.006", "0.007", "0.007", "0.02", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.007", "0.007", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.008", "0.007", "0.005", "0.005", "0.005", "0.005", "0.007", "0.007", "0.007", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.007", "0.007", "0.007", "0.005", "0.005", "0.007", "0.02", "0.02", "0.02", "0.02", "0.02", "0.007", "0.005", "0.005", "0.005", "0.005", "0.006", "0.008", "0.02", "0.005", "0.005", "0.005", "0.02", "0.008", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.007", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.008", "0.006", "0.005", "0.005", "0.005", "0.004", "0.006", "0.006", "0.005", "0.006", "0.006", "0.007", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.004", "0.005", "0.005", "0.005", "0.004", "0.005", "0.005", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.005", "0.007", "0.03", "0.019", "0.023", "0.03", "0.006", "0.005", "0.005", "0.005", "0.005", "0.007", "0.005", "0.005", "0.004", "0.005", "0.006", "0.005", "0.006", "0.006", "0.006", "0.006", "0.008", "0.008", "0.008", "0.008", "0.008", "0.008", "0.005", "0.005", "0.006", "0.006", "0.005", "0.006", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.006", "0.004", "0.004", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.02", "0.02", "0.005", "0.005", "0.006", "0.006", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.006", "0.008", "0.015", "0.005", "0.007", "0.007", "0.006", "0.006", "0.023", "0.023", "0.03", "0.03", "0.03", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.023", "0.023", "0.039", "0.006", "0.006", "0.008", "0.008", "0.008", "0.008", "0.008", "0.005", "0.006", "0.006", "0.005", "0.006", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.004", "0.005", "0.005", "0.005", "0.005", "0.004", "0.004", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.006", "0.005", "0.005", "0.008", "0.006", "0.006", "0.005", "0.008", "0.006", "0.006", "0.006", "0.008", "0.008", "0.008", "0.007", "0.005", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.006", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.004", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.011", "0.011", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.03", "0.03", "0.03", "0.03", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.03", "0.03", "0.03", "0.03", "0.03", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.005", "0.005", "0.004", "0.008", "0.006", "0.006", "0.005", "0.006", "0.006", "0.006", "0.006", "0.006", "0.006", "0.005", "0.006", "0.006", "0.006", "0.006", "0.005", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.008", "0.006", "0.004", "0.004", "0.004", "0.004", "0.004", "0.005", "0.005", "0.005", "0.005", "0.004", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.015", "0.015", "0.015", "0.008", "0.006", "0.008", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.005", "0.005", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.005", "0.005", "0.005", "0.004", "0.004", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.005", "0.005", "0.006", "0.005", "0.005", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.015", "0.008", "0.004", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.007", "0.007", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.004", "0.004", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.004", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.006", "0.005", "0.006", "0.006", "0.006", "0.006", "0.006", "0.006", "0.006", "0.006", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.05", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.006", "0.006", "0.005", "0.006", "0.006", "0.006", "0.006", "0.02", "0.005", "0.02", "0.02", "0.02", "0.005", "0.005", "0.005", "0.006", "0.004", "0.005", "0.006", "0.006", "0.006", "0.005", "0.004", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.006", "0.006", "0.006", "0.006", "0.006", "0.005", "0.005", "0.005", "0.02", "0.02", "0.02", "0.02", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.006", "0.005", "0.005", "0.006", "0.006", "0.005", "0.005", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.005", "0.005", "0.005", "0.005", "0.008", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.005", "0.005", "0.005", "0.004", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.007", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.018", "0.02", "0.018", "0.018", "0.018", "0.018", "0.02", "0.018", "0.02", "0.02", "0.018", "0.018", "0.018", "0.018", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.03", "0.03", "0.005", "0.008", "0.008", "0.008", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.005", "0.006", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.005", "0.006", "0.006", "0.005", "0.005", "0.005", "0.006", "0.005", "0.006", "0.005", "0.006", "0.006", "0.006", "0.006", "0.006", "0.018", "0.011", "0.005", "0.005", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.007", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.006", "0.006", "0.006", "0.006", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.006", "0.005", "0.005", "0.005", "0.005", "0.007", "0.005", "0.005", "0.007", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.005", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.005", "0.005", "0.005", "0.006", "0.005", "0.006", "0.006", "0.005", "0.005", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.02", "0.006", "0.006", "0.03", "0.025", "0.045", "0.045", "0.06", "0.07", "0.1", "0.15", "0.2"
    };
    

	private final String[] mLensName = {
			"Canon 16-35mm f/2.8", "Canon 17-40mm f/4", "Canon 17-55mm f/2.8", "Canon 17-85mm f/4.5-5.6", "Canon 20mm f/2.8", "Canon 20-35mm f/2.8", "Canon 24-70mm f/2.8", "Canon 24-105mm f/4", "Canon 35mm f/1.4", "Canon 40mm f/2.8", "Canon 50mm f/1.2", "Canon 50mm f/1.4", "Canon 50mm f/1.8 II", "Canon 55-250mm f/4-5.6", "Canon 70-200mm f/2.8", "Canon 70-200mm f/4", "Canon 85mm f/1.2", "Canon 85mm f/1.8", "Canon 100mm f/2.8", "Canon 135mm f/2", "Canon 400mm f/2.8", "Leica 21mm f/3.4", "Nikon 10.5mm f/2.8G", "Nikon 11-27.5mm f/3.5-5.6", "Nikon 14-24mm f/2.8", "Nikon 17-55mm f/2.8G", "Nikon 18-55mm f/3.5-5.6", "Nikon 18-70mm f/3,5-4,5", "Nikon 18-105mm f/3.5-5.6", "Nikon 18-200mm f/3.5-5.6 G", "Nikon 24-70mm f/2.8G", "Nikon 35mm f/1.8G", "Nikon 50mm f/1.8D", "Nikon 50mm f/1.8G", "Nikon 58mm f/1.4G", "Nikon 70-200mm f/2.8G", "Nikon 80-200mm f/2.8D", "Nikon 105mm f/2.8G", "Nikon 300mm f/2.8", "Olympus 12mm f/2", "Olympus 17mm f/1.8", "Olympus 45mm f/1.8", "Panasonic 12-32mm f/3.5-5.6", "Panasonic 25mm f/1.4", "Panasonic 42.5mm f/1.2", "Pentax 50mm f/1.4", "Ricoh Rikenon 35-70mm f/3.4-4.5", "Ricoh Rikenon 50mm f/1.7", "Sakar 35-70mm f/3.5-5.3", "Sigma 10-20mm f/4-5.6", "Sigma 17-70mm f/2.8-4", "Sigma 18-35mm f/1.8", "Sigma 18-200mm f/3.5-6.3", "Sigma 18-250mm f/3.5-6.3", "Sigma 30mm f/2.8", "Sigma 35mm f/1.4", "Sigma 50mm f/1.4", "Sigma 50-500mm f/4.5-6.3", "Sigma 70-300mm f/4-5.6", "Sigma 150-500mm f/5-6.3", "Sony 18-55mm f/3.5-5.6", "Tamron 18-270mm f/3.5-6.3", "Tamron 24-70mm f/2.8", "Vivitar 28-200mm f/3.5-5.3", "  unlimited"
	};

	private final String[][] mLensValue = {
			{"Canon", "16", "35", "9", "27", "14"}, {"Canon", "17", "40", "12", "27", "14"}, {"Canon", "17", "55", "9", "27", "17"}, {"Canon", "17", "85", "13", "30", "17"}, {"Canon", "20", "20", "9", "27", "13"}, {"Canon", "20", "35", "9", "27", "23"}, {"Canon", "24", "70", "9", "27", "19"}, {"Canon", "24", "105", "12", "27", "21"}, {"Canon", "35", "35", "3", "27", "15"}, {"Canon", "40", "40", "9", "27", "15"}, {"Canon", "50", "50", "2", "24", "21"}, {"Canon", "50", "50", "3", "27", "21"}, {"Canon", "50", "50", "5", "27", "21"}, {"Canon", "55", "250", "12", "30", "33"}, {"Canon", "70", "200", "9", "30", "34"}, {"Canon", "70", "200", "12", "30", "34"}, {"Canon", "85", "85", "2", "24", "31"}, {"Canon", "85", "85", "5", "27", "29"}, {"Canon", "100", "100", "9", "30", "15"}, {"Canon", "135", "135", "6", "30", "30"}, {"Canon", "400", "400", "9", "30", "44"}, {"Leica", "21", "21", "11", "24", "26"}, {"Nikon", "10", "10", "9", "27", "10"}, {"Nikon", "11", "27", "11", "24", "15"}, {"Nikon", "14", "24", "9", "27", "14"}, {"Nikon", "17", "55", "9", "27", "18"}, {"Nikon", "18", "55", "11", "31", "14"}, {"Nikon", "18", "70", "11", "29", "19"}, {"Nikon", "18", "105", "11", "31", "21"}, {"Nikon", "18", "200", "11", "31", "23"}, {"Nikon", "24", "70", "9", "27", "19"}, {"Nikon", "35", "35", "5", "27", "15"}, {"Nikon", "50", "50", "5", "27", "21"}, {"Nikon", "50", "50", "5", "24", "21"}, {"Nikon", "58", "58", "3", "24", "24"}, {"Nikon", "70", "200", "9", "27", "36"}, {"Nikon", "80", "200", "9", "27", "40"}, {"Nikon", "105", "105", "9", "30", "16"}, {"Nikon", "300", "300", "9", "27", "42"}, {"Olympus", "12", "12", "6", "27", "11"}, {"Olympus", "17", "17", "5", "27", "13"}, {"Olympus", "45", "45", "5", "27", "23"}, {"Panasonic", "12", "32", "11", "27", "11"}, {"Panasonic", "25", "25", "3", "24", "15"}, {"Panasonic", "42", "42", "2", "24", "23"}, {"Pentax", "50", "50", "3", "27", "21"}, {"Ricoh", "35", "70", "11", "27", "17"}, {"Ricoh", "50", "50", "5", "24", "25"}, {"Sakar", "35", "70", "11", "30", "27"}, {"Sigma", "9", "20", "13", "30", "13"}, {"Sigma", "17", "70", "9", "27", "12"}, {"Sigma", "18", "35", "5", "24", "14"}, {"Sigma", "18", "200", "11", "32", "21"}, {"Sigma", "18", "250", "11", "27", "17"}, {"Sigma", "30", "30", "9", "27", "15"}, {"Sigma", "35", "35", "3", "24", "15"}, {"Sigma", "50", "50", "3", "24", "21"}, {"Sigma", "50", "401", "13", "30", "40"}, {"Sigma", "70", "300", "12", "27", "37"}, {"Sigma", "150", "401", "14", "29", "42"}, {"Sony", "18", "55", "11", "30", "13"}, {"Tamron", "18", "270", "11", "32", "22"}, {"Tamron", "24", "70", "9", "27", "19"}, {"Vivitar", "28", "200", "11", "27", "43"}, {"misc", "0", "401", "0", "33", "0"} 
	};

	
}
