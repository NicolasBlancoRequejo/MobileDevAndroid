package be.pxl.a05_events_intents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class d_volume_default extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_volume = (Button)findViewById(R.id.sound_btn_volume);
        btn_volume.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent myIntent = new Intent(d_volume_default.this, d_volume_events.class);
        int value = 50;
        myIntent.putExtra("default", value);
        d_volume_default.this.startActivity(myIntent);
    }
}
