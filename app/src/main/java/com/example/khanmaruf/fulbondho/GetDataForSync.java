package com.example.khanmaruf.fulbondho;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;

public class GetDataForSync {

    int [] syncData = new int[14];
    int i = 0;


    // Fetch Data --------------------------------------------
    public int [] fetchDataForSync() {
        //Start Copy
        Log.d("myLog", "*********** Database Start *************");
        dbCopy mydb = new dbCopy(null);
        String dbPath = mydb.DB_PATH + "/fulbondhu.db";
        try {
            mydb.createDataBase();
            Log.d("myLog", "Database Create");
        } catch (IOException e) {
            Log.d("myLog", "Database Not Create");
            e.printStackTrace();
        }


        SQLiteDatabase myDatabase = null;

        Log.d("DB PAth", dbPath + "\n" + mydb.DB_PATH);

        try {
            myDatabase = SQLiteDatabase.openDatabase(dbPath, null,
                    SQLiteDatabase.OPEN_READWRITE);
        } catch (Exception e) {
            Log.d("myLog", "Database Open Problem menu");
        }

        int roundNo = 1;
        for (roundNo = 1 ; roundNo <8 ; roundNo++) {

            String sql = "SELECT * FROM android_score_table where round = " + roundNo + " ; ";
            Cursor c = null;// d = null;

            try {
                c = myDatabase.rawQuery(sql, null);
                Log.d("myLog", "raw query not error in menu");
            } catch (Exception e) {
                Log.d("myLog", "raw query error in menu");
                e.printStackTrace();
            }


            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                syncData[i++] = c.getInt(c.getColumnIndex("currect_answer"));
                syncData[i++] = c.getInt(c.getColumnIndex("time"));
            }

        }

        Log.d("Data fetch call", "Calll");

        myDatabase.close();

        return syncData;
    }
}
