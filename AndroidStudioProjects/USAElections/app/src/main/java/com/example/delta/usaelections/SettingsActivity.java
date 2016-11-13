package com.example.delta.usaelections;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Delta on 11-11-16.
 */

public class SettingsActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyVoterSettings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        Spinner typeSpinner = (Spinner)findViewById(R.id.typeSpinner);
        typeSpinner.setSelection(settings.getInt("type",0));

        SeekBar yearSeekBar = (SeekBar)findViewById(R.id.yearSeekBar);
        yearSeekBar.setProgress(settings.getInt("year",54)); //default = 54
        yearSeekBar.setMax(75); //MAX 450 - 525
        final TextView yearsTextView = (TextView)findViewById(R.id.yearsTextView);
        yearsTextView.setText(String.valueOf(yearSeekBar.getProgress()*4+1800));
        yearSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress *= 4;
                progress += 1800;
                yearsTextView.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        CheckBox voterCheckBox = (CheckBox)findViewById(R.id.voterCheckBox);
        voterCheckBox.setChecked(settings.getBoolean("voter", false));
    }

    @Override
    protected void onStop(){
        super.onStop();

        Spinner typeSpinner = (Spinner)findViewById(R.id.typeSpinner);
        int type = typeSpinner.getSelectedItemPosition();

        SeekBar yearSeekBar = (SeekBar)findViewById(R.id.yearSeekBar);
        int year = yearSeekBar.getProgress();

        CheckBox voterCheckBox = (CheckBox)findViewById(R.id.voterCheckBox);
        Boolean voter = voterCheckBox.isChecked();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("type", type);
        editor.putInt("year", year);
        editor.putBoolean("voter", voter);
        editor.commit();
    }
}
