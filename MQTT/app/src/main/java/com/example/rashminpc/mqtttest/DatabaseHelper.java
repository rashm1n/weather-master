package com.example.rashminpc.mqtttest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rashmin PC on 2/26/2018.
 */


//use this class later


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "weatherdata";
    public static final String TABLE_NAME = "sensordata";
    public static final String COL1 = "date";
    public static final String COL2 = "temp";
    public static final String COL3 = "humid";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COL1 + " TEXT PRIMARY KEY," + COL2 + " INTEGER,"              //create a table if doesn't exists
                + COL3 + " INTEGER" + ")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE  IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public void addItem(String date, int temp, int humid) {
        SQLiteDatabase dbase = this.getWritableDatabase();      // getWritableDatabase returns a SQLlite dbase object , if exists it calls onupgrade and if doesnt onCreate
        ContentValues vals = new ContentValues();             //used to sort of store contents to add to the database
        vals.put(COL1, date);
        vals.put(COL2, temp);
        vals.put(COL3, humid);
        dbase.insert(TABLE_NAME, null, vals);
        dbase.close();
    }


    //will not be nessasary

    public int updateItem(String date, int temp, int humid) {
        SQLiteDatabase dbase = this.getWritableDatabase();
        ContentValues vals = new ContentValues();             //used to sort of store contents to add to the database
        vals.put(COL1, date);
        vals.put(COL2, temp);
        vals.put(COL3, humid);
        return dbase.update(TABLE_NAME, vals, COL1 + "= ?", new String[]{date});         //method in SQLite
    }

    public void getAllTemp() {
        // Select All Query
        String selectQuery = "SELECT temp FROM " + TABLE_NAME;
        List<Integer> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
    }

    public void getAllHumid() {
        // Select All Query
        String selectQuery = "SELECT humid FROM " + TABLE_NAME;
        List<Integer> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getInt(0));
            } while (cursor.moveToNext());
        }
    }
}

//////////////////////////////////////////////////////////////////////////////////////////
///////////////////////USAGE//////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////


//AndroidSQLiteTutorialActivity
//        package com.androidhive.androidsqlite;
//
//        import java.util.List;
//
//        import android.app.Activity;
//        import android.os.Bundle;
//        import android.util.Log;
//        import android.widget.TextView;
//
//public class AndroidSQLiteTutorialActivity extends Activity {
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//
//        DatabaseHandler db = new DatabaseHandler(this);
//
//        /**
//         * CRUD Operations
//         * */
//        // Inserting Contacts
//        Log.d("Insert: ", "Inserting ..");
//        db.addContact(new Contact("Ravi", "9100000000"));
//        db.addContact(new Contact("Srinivas", "9199999999"));
//        db.addContact(new Contact("Tommy", "9522222222"));
//        db.addContact(new Contact("Karthik", "9533333333"));
//
//        // Reading all contacts
//        Log.d("Reading: ", "Reading all contacts..");
//        List<Contact> contacts = db.getAllContacts();
//
//        for (Contact cn : contacts) {
//            String log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
//            // Writing Contacts to log
//            Log.d("Name: ", log);
//        }
//    }
//}
