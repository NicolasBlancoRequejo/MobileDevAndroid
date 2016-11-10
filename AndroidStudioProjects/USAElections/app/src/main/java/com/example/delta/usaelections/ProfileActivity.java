package com.example.delta.usaelections;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Delta on 10-11-16.
 */

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.candidate_profile);
        Intent i = getIntent();
        Candidate candidate = (Candidate)i.getSerializableExtra("candidate");

        ImageView picImageView = (ImageView) findViewById(R.id.picImageView);
        Context context = picImageView.getContext();
        int id = context.getResources().getIdentifier("pic_"+candidate.getAlias(), "drawable", context.getPackageName());
        picImageView.setImageResource(id);

        switch (candidate.getParty()) {
            case "democrat":
                picImageView.setBackgroundColor(Color.parseColor("#426ef4"));
                break;
            case "republican":
                picImageView.setBackgroundColor(Color.parseColor("#f44242"));
                break;
            default: picImageView.setBackgroundColor(Color.parseColor("#f442d9"));
        }

        TextView partyTextView = (TextView) findViewById(R.id.partyTextView);
        partyTextView.setText(candidate.getParty());

        TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
        nameTextView.setText(candidate.getName());

        TextView ageTextView = (TextView) findViewById(R.id.ageTextView);
        ageTextView.setText(""+candidate.getAge());

        TextView likeTextView = (TextView) findViewById(R.id.likeTextView);
        likeTextView.setText(""+candidate.getLikes());
    }

}
