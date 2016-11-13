package com.example.delta.usaelections;

import android.provider.BaseColumns;

/**
 * Created by Delta on 13-11-16.
 */

public class ElectionContract {
    public static final String DB_NAME = "election_db";
    public static final int DB_VER = 1;

    public static final class Candidates implements BaseColumns {
        public static final String TABLE_NAME = "candidates";
        public static final String COL_NAME = "name";
        public static final String COL_PARTY = "party";
        public static final String COL_AGE = "age";
        public static final String COL_VOTES = "votes";
    }
}
