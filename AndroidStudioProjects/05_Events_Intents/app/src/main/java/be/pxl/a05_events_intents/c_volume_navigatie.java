package be.pxl.a05_events_intents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class c_volume_navigatie extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_volume = (Button)findViewById(R.id.sound_btn_volume);
        btn_volume.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent myIntent = new Intent(c_volume_navigatie.this, c_volume_events.class);
        c_volume_navigatie.this.startActivity(myIntent);
    }
}
