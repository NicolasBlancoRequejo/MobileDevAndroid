package com.example.delta.usaelections;

/**
 * Created by Delta on 14-11-16.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import static com.example.delta.usaelections.CandidatesDbHelper.*;

public class CandidatesDataSource {
    private SQLiteDatabase db;
    private CandidatesDbHelper dbHelper;
    private String[] allCandidates = {
            _ID,
            COL_NAME,
            COL_PARTY,
            COL_AGE,
            COL_VOTES
    };

    public CandidatesDataSource(Context context){
        Log.d("list", "CandidatesDataSource -> Constructor");
        dbHelper = new CandidatesDbHelper(context);
    }

    public void open() throws SQLException{
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public boolean doesDBExists(){
        boolean dbExists;
        try{
            Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            dbExists =  mCursor.moveToFirst();
        }catch(Exception ex){
            dbExists = false;
        }
        return dbExists;
    }

    public void createCandidate(Candidate candidate){
        Log.d("list", "CandidatesDataSource -> createCandidate, met candidate = "+candidate.getName());
        ContentValues values = new ContentValues();
        values.put(COL_NAME, candidate.getName());
        values.put(COL_PARTY, candidate.getParty());
        values.put(COL_AGE, candidate.getAge());
        values.put(COL_VOTES, candidate.getVotes());
        long newRowId = db.insert(TABLE_NAME, null, values);
    }

    public List<Candidate> getAllCandidates(){
        Log.d("list", "CandidatesDataSource -> getAllCandidates");
        List <Candidate> candidatesList = new ArrayList<Candidate>();
        Cursor cursor = db.query(TABLE_NAME, allCandidates, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Candidate candidate = cursortoCandidate(cursor);
            Log.d("list", "CandidatesDataSource -> getAllCandidates met candidate "+candidate.getName());
            candidatesList.add(candidate);
            cursor.moveToNext();
        }

        return candidatesList;
    }

    private Candidate cursortoCandidate(Cursor cursor){
        Candidate candidate = new Candidate();
        candidate.setName(cursor.getString(1));
        candidate.setParty(cursor.getString(2));
        candidate.setAge(cursor.getInt(3));
        candidate.setVotes(cursor.getInt(4));
        return candidate;
    }

    public void populateCandidates(Context context){
        Log.d("list", "CandidatesDataSource -> populateCandidates");
        ElectionUtils electionUtils = new ElectionUtils();
        List<Candidate> candidateList2 = electionUtils.getCandidates(context);
        for (Candidate candidate : candidateList2) {
            createCandidate(candidate);
        }
    }

    public void deleteAllCandidates(){
        db.delete(TABLE_NAME, null, null);
    }

    public void updateVotesCandidate(Candidate candidate){
        int votes = candidate.getVotes();
        ContentValues values = new ContentValues();
        values.put(COL_VOTES, votes);

        String selection = COL_NAME + " = ?";
        String[] selectionArgs = {candidate.getName()};

        int count = db.update(
                TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }
}
