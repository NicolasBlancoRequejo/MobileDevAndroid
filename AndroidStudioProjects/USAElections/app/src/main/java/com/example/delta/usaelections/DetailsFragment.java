package com.example.delta.usaelections;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the secondary fragment, displaying the details of a particular
 * item.
 */

public class DetailsFragment extends Fragment {
    /**
     * Create a new instance of DetailsFragment, initialized to
     * show the text at 'index'.
     */

    public static DetailsFragment newInstance(int index) {
        DetailsFragment f = new DetailsFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
            return null;
        }

        View v = inflater.inflate(R.layout.candidate_profile, container, false);

        setHasOptionsMenu(true);

        ElectionUtils electionUtils = new ElectionUtils();
        List<Candidate> candidateList = electionUtils.getCandidates(getActivity());

        final Candidate candidate = candidateList.get(getShownIndex());

        ImageView picImageView = (ImageView) v.findViewById(R.id.picImageView);
        Context context = picImageView.getContext();
        String[] splittedName = candidate.getName().toLowerCase().split(" ");
        String formattedName = splittedName[0]+"_"+splittedName[1];
        int id = context.getResources().getIdentifier(formattedName, "drawable", context.getPackageName());
        if(id != 0){
            picImageView.setImageResource(id);
        }else{
            picImageView.setImageResource(R.drawable.place_holder);
        }

        switch (candidate.getParty()) {
            case "Democratic party":
                picImageView.setBackgroundColor(Color.parseColor("#426ef4"));
                break;
            case "Republican Party":
                picImageView.setBackgroundColor(Color.parseColor("#f44242"));
                break;
            default: picImageView.setBackgroundColor(Color.parseColor("#f442d9"));
        }

        TextView partyTextView = (TextView) v.findViewById(R.id.partyTextView);
        partyTextView.setText(candidate.getParty());

        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        nameTextView.setText(candidate.getName());

        TextView ageTextView = (TextView) v.findViewById(R.id.ageTextView);
        ageTextView.setText(""+candidate.getAge());

        final TextView likeTextView = (TextView) v.findViewById(R.id.likeTextView);
        likeTextView.setText(""+candidate.getVotes());

        Button likeButton = (Button)v.findViewById(R.id.likeButton);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int likes = Integer.parseInt(likeTextView.getText().toString());
                likes += 1;
                likeTextView.setText(""+likes);
                candidate.setVotes(likes);


                Context context = getActivity();
                CharSequence text = "Thanks for voting!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        return v;

    }
}
