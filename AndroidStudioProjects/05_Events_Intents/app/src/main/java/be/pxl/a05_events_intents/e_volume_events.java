package be.pxl.a05_events_intents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;


public class e_volume_events extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, CheckBox.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.e_volume_events);

        Button btn_ok = (Button)findViewById(R.id.volume_btn_ok);
        btn_ok.setOnClickListener(this);

        Button btn_cancel = (Button)findViewById(R.id.volume_btn_cancel);
        btn_cancel.setOnClickListener(this);

        SeekBar skbar_ringtone = (SeekBar)findViewById(R.id.volume_skbar_ringtone);
        skbar_ringtone.setOnSeekBarChangeListener(this);

        CheckBox checkbox_equal = (CheckBox)findViewById(R.id.volume_checkbox_equal);
        checkbox_equal.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        SeekBar skbar_ringtone = (SeekBar)findViewById(R.id.volume_skbar_ringtone);
        SeekBar skbar_media = (SeekBar)findViewById(R.id.volume_skbar_media);
        SeekBar skbar_alarm = (SeekBar)findViewById(R.id.volume_skbar_alarm);
        SeekBar skbar_mention = (SeekBar)findViewById(R.id.volume_skbar_mention);

        switch (v.getId()) {

            case R.id.volume_btn_ok:
                Context context = getApplicationContext();
                CharSequence text =
                        "Beltoon:\t" + skbar_ringtone.getProgress() + "\n" +
                                "Media:\t" + skbar_media.getProgress() + "\n" +
                                "Alarm:\t" + skbar_alarm.getProgress() + "\n" +
                                "Melding:\t" + skbar_mention.getProgress() + "\n";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                break;

            case R.id.volume_btn_cancel:
                Intent intent = new Intent();
                String value =
                        "Calling volume: \t" + skbar_ringtone.getProgress() + "\n" +
                                "Media volume: \t" + skbar_mention.getProgress() + "\n";
                intent.putExtra("edittextvalue",value);
                setResult(RESULT_OK, intent);
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        SeekBar skbar_mention = (SeekBar)findViewById(R.id.volume_skbar_mention);
        if (b){
            skbar_mention.setEnabled(false);
            SeekBar skbar_ringtone = (SeekBar)findViewById(R.id.volume_skbar_ringtone);
            skbar_mention.setProgress(skbar_ringtone.getProgress());
        }else{
            skbar_mention.setEnabled(true);
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        CheckBox checkbox_equal = (CheckBox)findViewById(R.id.volume_checkbox_equal);
        SeekBar skbar_mention = (SeekBar)findViewById(R.id.volume_skbar_mention);

        if (checkbox_equal.isChecked()){
            skbar_mention.setProgress(seekBar.getProgress());
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


}
