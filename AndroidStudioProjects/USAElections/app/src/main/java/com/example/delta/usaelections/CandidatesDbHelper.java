package com.example.delta.usaelections;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.List;

public class CandidatesDbHelper extends SQLiteOpenHelper implements BaseColumns {
    // If you change the database schema, you must increment the database version.
    public static final String DB_NAME = "election_db";
    public static final int DB_VER = 1;

    public static final String TABLE_NAME = "candidates";
    public static final String COL_NAME = "name";
    public static final String COL_PARTY = "party";
    public static final String COL_AGE = "age";
    public static final String COL_VOTES = "votes";


    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COL_NAME + TEXT_TYPE + COMMA_SEP +
                    COL_PARTY + TEXT_TYPE + COMMA_SEP +
                    COL_AGE + TEXT_TYPE + COMMA_SEP +
                    COL_VOTES + TEXT_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public CandidatesDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public void insertCandidate(SQLiteDatabase db, Candidate candidate){
        ContentValues values = new ContentValues();
        values.put(COL_NAME, candidate.getName());
        values.put(COL_PARTY, candidate.getParty());
        values.put(COL_AGE, candidate.getAge());
        values.put(COL_VOTES, candidate.getVotes());
        long newRowId = db.insert(TABLE_NAME, null, values);
    }
    public List<Candidate> getCandidates(SQLiteDatabase db){
        String[] projection = {
                _ID,
                COL_NAME,
                COL_PARTY,
                COL_AGE,
                COL_VOTES
        };

        String sortOrder =
                COL_NAME + " DESC";

        Cursor c = db.query(
                TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );



        return null;
    }
}
