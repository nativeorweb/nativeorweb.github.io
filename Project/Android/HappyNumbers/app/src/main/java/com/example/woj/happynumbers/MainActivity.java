package com.example.woj.happynumbers;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.example.woj.happynumbers.Rosetta.Experiments.BubbleSort;
import com.example.woj.happynumbers.Rosetta.Experiments.CountingSort;
import com.example.woj.happynumbers.Rosetta.Experiments.GnomeSort;
import com.example.woj.happynumbers.Rosetta.Experiments.HeapSort;
import com.example.woj.happynumbers.Rosetta.Experiments.InsertSort;
import com.example.woj.happynumbers.Rosetta.Experiments.MergeSort;
import com.example.woj.happynumbers.Rosetta.Experiments.NQueens;
import com.example.woj.happynumbers.Rosetta.Experiments.PancakeSort;
import com.example.woj.happynumbers.Rosetta.Experiments.PerfectNumber;
import com.example.woj.happynumbers.Rosetta.Experiments.QuickSort;
import com.example.woj.happynumbers.Rosetta.Experiments.SeqNonSquares;
import com.example.woj.happynumbers.Rosetta.Experiments.ShellSort;
import com.example.woj.happynumbers.TCLBG.Experiments.binarytrees;

import java.io.IOException;


public class MainActivity extends Activity {
    WebView webView;

    static TextView textView = null;
    static Vibrator v = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);

        textView = (TextView) findViewById(R.id.textview);

//        try {
//             this.runTCLBG();
               this.runRosetta();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        this.finalizar();
    }

    private void runTCLBG() throws Exception {
       // binarytrees.execute(binarytrees.DEFAULT_NUMBER);
        //fannkuchredux.execute(fannkuchredux.DEFAULT_NUMBER);
       //fasta.execute(fasta.DEFAULT_NUMBER);
       //nbody.execute(nbody.DEFAULT_NUMBER);
       // spectralnorm.execute(spectralnorm.DEFAULT_NUMBER);

        //fastaParallel.execute(fastaParallel.DEFAULT_NUMBER);


        //regexdna.execute(regexdna.DEFAULT_FILE, this);
      //regexdnaParallel.execute(regexdnaParallel.DEFAULT_FILE,this);
        //revcomp.execute(20, revcomp.DEFAULT_FILE, this);
        //knucleotide.execute(knucleotide.DEFAULT_FILE, this);


    }

    public void finalizar() {

        textView.setText("Over!");

        // Vibrate for 1500 milliseconds
        v.vibrate(1500);
       // this.finish();

    }

    private void runRosetta() {

       // ShellSort.execute(ShellSort.DEFAULT_SIZE);

        //HofstadterQ.execute(HofstadterQ.JAVA, HofstadterQ.DEFAULT_NUMBER,HofstadterQ.DEFAULT_LOOP);
        //PerfectNumber.execute(PerfectNumber.JS, PerfectNumber.LOOP);
        //

      /*
      MatrixMult.execute(MatrixMult.DEFAULT_NUMBER);
      Sieve.execute(Sieve.DEFAULT_NUMBER);
      Tower.execute(Tower.JS, Tower.DEFAULT_NUMBER);
      HappyNumbers.execute(HappyNumbers.DEFAULT_NUMBER);
      Combinations.execute(Combinations.DEFAULT_NUMBER_FST, Combinations.DEFAULT_NUMBER_SND);
      CountFactors.execute(CountFactors.DEFAULT_NUMBER);

      SeqNonSquares.execute(SeqNonSquares.DEFAULT_NUMBER);
        KnapsackUnbounded.execute();
        ZeroOneKnapsackForTourists.execute(
                ZeroOneKnapsackForTourists.DEFAULT_LOOP,
                ZeroOneKnapsackForTourists.DEFAULT_SIZE);

        BoundedKnapsackForTourists.execute(
                BoundedKnapsackForTourists.DEFAULT_LOOP,
                BoundedKnapsackForTourists.DEFAULT_SIZE);
        ManOrBoy.execute(ManOrBoy.LOOP, ManOrBoy.DEFAULT_NUMBER);
        NQueens.execute(NQueens.DEFAULT_LOOP, NQueens.DEFAULT_SIZE);
        PerfectNumber.execute(PerfectNumber.JS, PerfectNumber.LOOP);
        SeqNonSquares.execute(SeqNonSquares.DEFAULT_NUMBER);
        BubbleSort.execute(BubbleSort.DEFAULT_LOOP);
        CountingSort.execute(CountingSort.DEFAULT_SIZE);
        GnomeSort.execute(GnomeSort.DEFAULT_SIZE);
        HeapSort.execute(HeapSort.DEFAULT_SIZE);
        InsertSort.execute(InsertSort.DEFAULT_SIZE);
        MergeSort.execute(MergeSort.DEFAULT_SIZE,MergeSort.DEFAULT_LOOP);
        PancakeSort.execute(PancakeSort.DEFAULT_SIZE);
        QuickSort.execute(QuickSort.DEFAULT_SIZE);

 SleepSort.execute(SleepSort.DEFAULT_SIZE,SleepSort.DEFAULT_LOOP);
  ShellSort.execute(ShellSort.DEFAULT_SIZE);
*/


        //TextProcessing1.execute(TextProcessing1.FILE_NAME, this);
        //DataMunging2.execute(DataMunging2.FILE_NAME, this);
//        TextLicense.execute(TextLicense.FILE_NAME,this);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
