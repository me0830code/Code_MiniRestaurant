package com.example.minirestaurant.Support;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private Context nowContext ;

    // Database name & version
    private static final String databaseName = "LocalDB" ;
    private static final int databaseVersion = 1 ;

    // Table name
    private final String tableName = "TelephoneBook" ;

    // Columns for Creating Table
    private final String uID = "ID" ;
    private final String userName = "User" ;
    private final String phoneNum = "Phone" ;

    // SQL Script for Creating Table
    // CREATE TABLE IF NOT EXISTS TelephoneBook ( ID INTEGER PRIMARY KEY AUTOINCREMENT, User VARCHAR(255), Phone VARCHAR(255) ) ;
    private final String createTableSQL = "CREATE TABLE IF NOT EXISTS " + this.tableName +
            " ( " + this.uID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + this.userName + " VARCHAR(255), "
            + this.phoneNum + " VARCHAR(255)" +
            " ) ; " ;

    // SQL Script for Deleting Table
    // DROP TABLE IF EXISTS TelephoneBook ;
    private final String deleteTableSQL = "DROP TABLE IF EXISTS " + this.tableName + " ; " ;

    public DBHelper(Context context) {
        super(context, databaseName, null, databaseVersion) ;

        this.nowContext = context ;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {

            sqLiteDatabase.execSQL(this.createTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Create Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try {

            sqLiteDatabase.execSQL(this.deleteTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Delete Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }
    }

    public void InsertToLocalDB(String name, String phone) {
    }

    public void UpdateToLocalDB(int id, String newName, String newPhone) {
    }

    public void DeleteFromLocalDB(int id) {
    }

    // Read the Data Set of this Local Database
//    public ArrayList<String> GetTotalContactInfo() {
//        return [] ;
//    }
}
