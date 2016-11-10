package com.example.delta.usaelections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Delta on 10-11-16.
 */

public class CandidateAdaptor extends ArrayAdapter<Candidate> {

    private int layoutResource;

    public CandidateAdaptor(Context context, int layoutResource, List<Candidate> candidateList) {
        super(context, layoutResource, candidateList);
        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        Candidate candidate = getItem(position);

        if (candidate != null) {
            TextView nameTextView = (TextView) view.findViewById(R.id.item_nameTextView);
            ImageView logoImageView = (ImageView) view.findViewById(R.id.item_logoImageView);

            if (nameTextView != null) {
                nameTextView.setText(candidate.getName());
            }

            if (logoImageView != null) {
                switch (candidate.getParty()) {
                    case "democrat":
                        logoImageView.setImageResource(R.drawable.logo_democrat);
                        break;
                    case "republican":
                        logoImageView.setImageResource(R.drawable.logo_republican);
                        break;
                    default:
                        logoImageView.setImageResource(R.drawable.logo_default);
                }

            }
        }

        return view;
    }
}
