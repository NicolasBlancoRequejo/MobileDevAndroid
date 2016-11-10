package com.example.delta.usaelections;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.delta.usaelections.R.id.listview;

public class MainActivity extends AppCompatActivity {
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Candidate> candidateList = new ArrayList<>();
        Candidate trump = new Candidate("Donald Trump", "republican", "trump", 70, 225);
        Candidate clinton = new Candidate("Hillary Clinton", "democrat", "clinton", 69, 175);
        Candidate denhaag = new Candidate("Bob Denhaag", "denhagerski", "placeholder", 42, 5);
        candidateList.add(trump);
        candidateList.add(clinton);
        candidateList.add(denhaag);

        listView = (ListView)findViewById(R.id.listview);
        CandidateAdaptor candidateAdaptor = new CandidateAdaptor(this, R.layout.candidate_item, candidateList);
        listView.setAdapter(candidateAdaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                Candidate candidate = (Candidate) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("candidate", candidate);
                startActivityForResult(intent, 1);
            }

        });
    }
}
