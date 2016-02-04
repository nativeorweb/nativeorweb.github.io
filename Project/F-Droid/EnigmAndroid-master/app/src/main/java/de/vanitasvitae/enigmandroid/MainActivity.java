package de.vanitasvitae.enigmandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.math.BigInteger;
import java.security.SecureRandom;

import de.vanitasvitae.enigmandroid.enigma.Enigma;
import de.vanitasvitae.enigmandroid.enigma.EnigmaStateBundle;
import de.vanitasvitae.enigmandroid.enigma.inputPreparer.InputPreparer;
import de.vanitasvitae.enigmandroid.layout.LayoutContainer;
import de.vanitasvitae.enigmandroid.layout.PassphraseDialogBuilder;

/**
 * Main Android Activity of the app
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
public class MainActivity extends Activity
{
    private static final int RESULT_SETTINGS = 1;
    private static final String URI_CHANGELOG =
            "https://github.com/vanitasvitae/EnigmAndroid/blob/master/CHANGELOG.txt";
    public static final String APP_ID = "EnigmAndroid";

    private LayoutContainer layoutContainer;
    private String prefMachineType;
    private String prefNumericLanguage;
    private String prefMessageFormatting;

    private SecureRandom secureRandom;
    private Vibrator v;
    public static WebView webView;
    public static MainActivity thisMain;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        thisMain = this;
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        webView = new WebView(this);
        webView.loadUrl("file:///android_asset/www/index.html");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                layoutContainer.doCrypto();
            }
        });

        secureRandom = new SecureRandom();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.prefMachineType = sharedPreferences.getString(SettingsActivity.PREF_MACHINE_TYPE, getResources().
                getStringArray(R.array.pref_alias_machine_type)[0]);
        ActivitySingleton singleton = ActivitySingleton.getInstance();
        singleton.setActivity(this);
        updateContentView();
        layoutContainer = LayoutContainer.createLayoutContainer(prefMachineType);
        updatePreferenceValues();
        //Handle shared text
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type))
            {
                String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
                if (sharedText != null)
                {
                    layoutContainer.getInput().setRawText(sharedText);
                }
            }
        }
        try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
     //   layoutContainer.doCrypto();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.updateContentView();
    }

    private void updateContentView()
    {
        switch (prefMachineType)
        {
            case "I":
                setContentView(R.layout.activity_main_i_m3);
                break;
            case "M3":
                this.setContentView(R.layout.activity_main_i_m3);
                break;
            case "M4":
                this.setContentView(R.layout.activity_main_m4);
                break;
            case "D":
                this.setContentView(R.layout.activity_main_d);
                break;
            case "K":
            case "KS":
            case "KSA":
            case "T":
            case "R":
            case "G31":
            case "G312":
            case "G260":
                this.setContentView(R.layout.activity_main_g_k_r_t);
                break;
            default:
                this.setContentView(R.layout.activity_main_i_m3);
                break;
        }
    }

    private void updatePreferenceValues()
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.setPrefMachineType(sharedPreferences.getString(SettingsActivity.PREF_MACHINE_TYPE, getResources().
                getStringArray(R.array.pref_alias_machine_type)[0]));
        this.setPrefNumericLanguage(sharedPreferences.getString(SettingsActivity.PREF_NUMERIC_LANGUAGE, getResources().
                getStringArray(R.array.pref_alias_numeric_spelling_language)[0]));
        this.setPrefMessageFormatting(sharedPreferences.getString(SettingsActivity.PREF_MESSAGE_FORMATTING, getResources().
                getStringArray(R.array.pref_alias_message_formatting)[0]));
    }

    private void setPrefMachineType(String type)
    {
        if(prefMachineType == null || !prefMachineType.equals(type))
        {
            prefMachineType = type;
            String savedInput = "";
            if(layoutContainer != null)
            {
                savedInput = layoutContainer.getInput().getText();
            }
            updateContentView();
            layoutContainer = LayoutContainer.createLayoutContainer(prefMachineType);
            layoutContainer.setInputPreparer(InputPreparer.createInputPreparer());
            layoutContainer.getInput().setText(savedInput);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            sharedPreferences.edit().putString(SettingsActivity.PREF_MACHINE_TYPE, type).apply();
        }
    }

    public String getPrefMachineType()
    {
        if(prefMachineType != null) return prefMachineType;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.prefMachineType = sharedPreferences.getString(SettingsActivity.PREF_MACHINE_TYPE, getResources().
                    getStringArray(R.array.pref_alias_machine_type)[0]);
        return prefMachineType;
    }

    public void setPrefNumericLanguage(String lang)
    {
        if(prefNumericLanguage == null || !prefNumericLanguage.equals(lang))
        {
            prefNumericLanguage = lang;
            layoutContainer.setInputPreparer(InputPreparer.createInputPreparer());
        }
    }

    public String getPrefNumericLanguage()
    {
        if(prefNumericLanguage != null) return prefNumericLanguage;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.prefNumericLanguage = sharedPreferences.getString(SettingsActivity.PREF_NUMERIC_LANGUAGE, getResources().
                getStringArray(R.array.pref_alias_numeric_spelling_language)[0]);
        return prefNumericLanguage;
    }

    public void setPrefMessageFormatting(String messageFormatting)
    {
        if(prefMessageFormatting == null || !prefMessageFormatting.equals(messageFormatting))
        {
            prefMessageFormatting = messageFormatting;
            layoutContainer.setEditTextAdapter(messageFormatting);
        }
    }

    public String getPrefMessageFormatting()
    {
        if(prefMessageFormatting != null) return prefMessageFormatting;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.prefMessageFormatting = sharedPreferences.getString(SettingsActivity.PREF_MESSAGE_FORMATTING, getResources().
                getStringArray(R.array.pref_alias_message_formatting)[0]);
        return prefMessageFormatting;
    }

    public SecureRandom getSecureRandom()
    {
        return this.secureRandom;
    }

    public void onDialogFinished(EnigmaStateBundle state)
    {
        layoutContainer.getEnigma().setState(state);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        this.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    /**
     * Handle Options menu clicks
     */
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_reset)
        {
            layoutContainer.resetLayout();
            Toast.makeText(getApplicationContext(), R.string.message_reset,
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_random_configuration)
        {
            layoutContainer.getEnigma().randomState();
            layoutContainer.syncStateFromEnigmaToLayout();
            Toast.makeText(getApplicationContext(), R.string.message_random,
                    Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_choose_ringsetting)
        {
            layoutContainer.showRingSettingsDialog();
            return true;
        }
        else if (id == R.id.action_settings)
        {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivityForResult(i, RESULT_SETTINGS);
        }
        else if (id == R.id.action_about)
        {
            showAboutDialog();
            return true;
        }
        else if (id == R.id.action_send)
        {
            if(layoutContainer.getOutput().getText().length() == 0)
            {
                Toast.makeText(this, R.string.error_no_text_to_send, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, layoutContainer.getOutput().getModifiedText());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
            }
        }
        else if (id == R.id.action_receive_scan)
        {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.initiateScan();
            return true;
        }
        else if(id == R.id.action_share_scan)
        {
            IntentIntegrator QRIntegrator = new IntentIntegrator(this);
            layoutContainer.syncStateFromLayoutToEnigma();
            Log.d(APP_ID, "Sharing configuration to QR: " + layoutContainer.getEnigma().stateToString());
            QRIntegrator.shareText(APP_ID+"/"+layoutContainer.getEnigma().stateToString());
            return true;
        }
        else if(id == R.id.action_enter_seed)
        {
            new PassphraseDialogBuilder().showDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Set the chosen Configuration to the enigma, get the input string from the input text box and
     * prepare it, set the input to the prepared text, encrypt the prepared input and set the
     * encrypted string to the output text box and update the spinners to their new positions.
     * @param v View
     */
    public void doCrypto(View v)
    {
        layoutContainer.doCrypto();
    }

    /**
     * Show a Dialog containing information about the app, license, usage, author and a link
     * to the changelog
     */
    private void showAboutDialog()
    {
        final View aboutView = View.inflate(this, R.layout.dialog_about, null);
		//Get and set Version code
        PackageInfo pInfo = null;
        try{ pInfo = getPackageManager().getPackageInfo(this.getPackageName(), 0);}
        catch (PackageManager.NameNotFoundException e){ e.printStackTrace();}
        String version = pInfo.versionName+ " ("+pInfo.versionCode+")";
        TextView versionText = (TextView) aboutView.findViewById(R.id.about_version_section);
        versionText.setText(version);

		//Build and show dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.title_about_dialog);
        builder.setView(aboutView)
                .setCancelable(true)
                .setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.button_show_changelog, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                        openWebPage(URI_CHANGELOG);
                    }
                }).show();
    }

    /**
     * Handle preference changes
     * @param requestCode requestCode
     * @param resultCode resultCode (RESULT_SETTINGS is defined at the top)
     * @param data data (not important here)
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SETTINGS:
            {
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
                this.setPrefMachineType(sharedPrefs.getString(SettingsActivity.PREF_MACHINE_TYPE, getResources()
                        .getStringArray(R.array.pref_alias_machine_type)[0]));
                this.setPrefNumericLanguage(sharedPrefs.getString(SettingsActivity.PREF_NUMERIC_LANGUAGE, getResources().
                        getStringArray(R.array.pref_alias_numeric_spelling_language)[0]));
                this.setPrefMessageFormatting(sharedPrefs.getString(SettingsActivity.PREF_MESSAGE_FORMATTING,
                        getResources().getStringArray(R.array.pref_alias_message_formatting)[0]));
                break;
            }
            case IntentIntegrator.REQUEST_CODE:
                IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                if (scanResult != null) {
                    String content = scanResult.getContents();
                    if(content == null) Log.e(APP_ID, "Error! Received nothing from QR-Code!");
                    else {
                        Log.d(APP_ID, "Received " + content + " from QR-Code!");
                        restoreStateFromCode(content);
                    }
                }
        }
    }

    /**
     * Set EnigmAndroid into a certain state as described in the QR-Code
     * @param mem content of the QR-Code
     */
    public void restoreStateFromCode(String mem)
    {
        if(!mem.startsWith(APP_ID+"/"))
        {
            Toast.makeText(this, R.string.error_no_valid_qr, Toast.LENGTH_LONG).show();
        }
        else
        {
            mem = mem.substring((APP_ID+"/").length());
            BigInteger s = new BigInteger(mem, 16);
            Log.d(APP_ID, "Try to restore configuration from BigInteger value "+ s.toString());
            setPrefMachineType(Enigma.chooseEnigmaFromSave(s));
            updateContentView();
            layoutContainer = LayoutContainer.createLayoutContainer(getPrefMachineType());
            layoutContainer.getEnigma().restoreState(Enigma.removeDigit(s,20));
            layoutContainer.setInputPreparer(InputPreparer.createInputPreparer());
            layoutContainer.syncStateFromEnigmaToLayout();
        }
    }

    /**
     * Set EnigmAndroid into a state calculated from the seed.
     * @param seed seed
     */
    public void createStateFromSeed(String seed)
    {
        setPrefMachineType(Enigma.chooseEnigmaFromSeed(seed));
        updateContentView();
        layoutContainer = LayoutContainer.createLayoutContainer(getPrefMachineType());
        layoutContainer.getEnigma().setStateFromSeed(seed);
        layoutContainer.setInputPreparer(InputPreparer.createInputPreparer());
        layoutContainer.syncStateFromEnigmaToLayout();
    }

    /**
     * Open the web page with the URL url
     * @param url URL of the website
     */
    private void openWebPage(String url) {
        Uri webPage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void finalizar() {

        v.vibrate(1500);
        this.finish();
    }

    /**
 * Singleton that grants access to an Activity from anywhere within the app
 */
public static class ActivitySingleton
{
    private static ActivitySingleton instance = null;
    private Activity activity;

    //private constructor
    private ActivitySingleton(){}
    //Singleton method
    public static ActivitySingleton getInstance()
    {
        if(instance == null) instance = new ActivitySingleton();
        return instance;
    }

    /**
     * Set an Activity that the Singleton returns
     * @param activity activity that's stored
     */
    public void setActivity(Activity activity)
    {
        this.activity = activity;
    }

    /**
     * Returns the stored Activity
     * @return stored Activity
     */
    public Activity getActivity()
    {
        return activity;
    }

}
}
