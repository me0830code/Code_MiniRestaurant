package com.example.minirestaurant.Support;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.widget.Toast;

import com.example.minirestaurant.Model.UserInfo;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

enum SQLCommandType {

    Execute,
    Query
}

enum TableType {

    User("UserInfo"),
    Product("ProductInfo"),
    Order("OrderInfo"),
    Comment("CommentInfo"),
    Report("ReportInfo") ;

    public final String tableName ;

    TableType(String name) {
        this.tableName = name ;
    }
}

public class DBHelper extends SQLiteOpenHelper {

    private Context nowContext ;

    // Keys of Return Dictionary
    public final String successDictKey = "Success" ;
    public final String dataDictKey = "Data" ;

    // --------------------- Database Setting      ---------------------

    // Database name & version
    private static final String databaseName = "LocalDB" ;
    private static final int databaseVersion = 1 ;
    // -----------------------------------------------------------------

    // --------------------- User Table Setting    ---------------------

    // User Table Name
    private final String userTableName = "UserInfo" ;

    // Columns for Creating User Table
    private final String uID = "uID" ;
    private final String userName = "Name" ;
    private final String userAge = "Age" ;
    private final String userGender = "Gender" ;

    // SQL Script for Creating User Table
    // CREATE TABLE IF NOT EXISTS UserInfo ( uID INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(255), Age INTEGER, Gender VARCHAR(255) ) ;
    private final String createUserTableSQL = "CREATE TABLE IF NOT EXISTS " + this.userTableName +
            " ( " + this.uID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + this.userName + " VARCHAR(255), "
            + this.userAge + " INTEGER, "
            + this.userGender + " VARCHAR(255)" +
            " ) ; " ;

    // SQL Script for Deleting User Table
    // DROP TABLE IF EXISTS UserInfo ;
    private final String deleteUserTableSQL = "DROP TABLE IF EXISTS " + this.userTableName + " ; " ;

    // -----------------------------------------------------------------

    // --------------------- Product Table Setting ---------------------

    // Product Table Name
    private final String productTableName = "ProductInfo" ;

    // Columns for Creating Product Table
    private final String pID = "pID" ;
    private final String productName = "Name" ;
    private final String productPrice = "Price" ;
    private final String productColdOrHot = "ColdOrHot" ;

    // SQL Script for Creating Product Table
    // CREATE TABLE IF NOT EXISTS ProductInfo ( pID INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(255), Price INTEGER, ColdOrHot VARCHAR(255) ) ;
    private final String createProductTableSQL = "CREATE TABLE IF NOT EXISTS " + this.productTableName +
            " ( " + this.pID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + this.productName + " VARCHAR(255), "
            + this.productPrice + " INTEGER, "
            + this.productColdOrHot + " VARCHAR(255)" +
            " ) ; " ;

    // SQL Script for Deleting Product Table
    // DROP TABLE IF EXISTS ProductInfo ;
    private final String deleteProductTableSQL = "DROP TABLE IF EXISTS " + this.productTableName + " ; " ;

    // -----------------------------------------------------------------

    // --------------------- Order Table Setting   ---------------------

    // Order Table Name
    private final String orderTableName = "OrderInfo" ;

    // Columns for Creating Order Table
    private final String oID = "oID" ;
    private final String productAmount = "Amount" ;
    private final String totalPrice = "Price" ;

    // SQL Script for Creating Order Table
    // CREATE TABLE IF NOT EXISTS OrderInfo ( oID INTEGER PRIMARY KEY AUTOINCREMENT, uID INTEGER, pID INTEGER, Amount INTEGER, Price INTEGER ) ;
    private final String createOrderTableSQL = "CREATE TABLE IF NOT EXISTS " + this.orderTableName +
            " ( " + this.oID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + this.uID + " INTEGER, "
            + this.pID + " INTEGER, "
            + this.productAmount + " INTEGER, "
            + this.totalPrice + " INTEGER" +
            " ) ; " ;

    // SQL Script for Deleting Order Table
    // DROP TABLE IF EXISTS OrderInfo ;
    private final String deleteOrderTableSQL = "DROP TABLE IF EXISTS " + this.orderTableName + " ; " ;

    // -----------------------------------------------------------------

    // --------------------- Comment Table Setting ---------------------

    // Comment Table Name
    private final String commentTableName = "CommentInfo" ;

    // Columns for Creating Comment Table
    private final String cID = "cID" ;
    private final String commentDate = "Date" ;
    private final String commentContent = "Content" ;
    private final String commentRating = "Rating" ;

    // SQL Script for Creating Comment Table
    // CREATE TABLE IF NOT EXISTS CommentInfo ( cID INTEGER PRIMARY KEY AUTOINCREMENT, uID INTEGER, Date VARCHAR(255), Content VARCHAR(255), Rating INTEGER ) ;
    private final String createCommentTableSQL = "CREATE TABLE IF NOT EXISTS " + this.commentTableName +
            " ( " + this.cID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + this.uID + " INTEGER, "
            + this.commentDate + " VARCHAR(255), "
            + this.commentContent + " VARCHAR(255), "
            + this.commentRating + " INTEGER" +
            " ) ; " ;

    // SQL Script for Deleting Comment Table
    // DROP TABLE IF EXISTS CommentInfo ;
    private final String deleteCommentTableSQL = "DROP TABLE IF EXISTS " + this.commentTableName + " ; " ;

    // -----------------------------------------------------------------

    // --------------------- Report Table Setting  ---------------------

    // Report Table Name
    private final String reportTableName = "ReportInfo" ;

    // Columns for Creating Report Table
    private final String rID = "rID" ;
    private final String reportDate = "Date" ;

    // SQL Script for Creating Report Table
    // CREATE TABLE IF NOT EXISTS ReportInfo ( rID INTEGER PRIMARY KEY AUTOINCREMENT, uID INTEGER, cID INTEGER, Date VARCHAR(255) ) ;
    private final String createReportTableSQL = "CREATE TABLE IF NOT EXISTS " + this.reportTableName +
            " ( " + this.rID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + this.uID + " INTEGER, "
            + this.cID + " INTEGER, "
            + this.reportDate + " VARCHAR(255)" +
            " ) ; " ;

    // SQL Script for Deleting Report Table
    // DROP TABLE IF EXISTS ReportInfo ;
    private final String deleteReportTableSQL = "DROP TABLE IF EXISTS " + this.reportTableName + " ; " ;

    // -----------------------------------------------------------------

    public DBHelper(Context context) {
        super(context, databaseName, null, databaseVersion) ;

        this.nowContext = context ;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create Total Tables

        // 1. User Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.createUserTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Create User Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }

        // 2. Product Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.createProductTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Create Product Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }

        // 3. Order Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.createOrderTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Create Order Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }

        // 4. Comment Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.createCommentTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Create Comment Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }

        // 5. Report Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.createReportTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Create Report Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // Upgrade Total Tables

        // 1. User Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.deleteUserTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Delete User Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }

        // 2. Product Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.deleteProductTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Delete Product Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }

        // 3. Order Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.deleteOrderTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Delete Order Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }

        // 4. Comment Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.deleteCommentTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Delete Comment Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }

        // 5. Report Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.deleteReportTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Delete Report Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }
    }

    public Dictionary ExecuteSQLCommand(SQLCommandType commandType, String commandSQL) {

        // todo -> create, updrade 全部五個table都要
        // todo -> 先把功能寫起來再來作ui
        // todo -> 我想做的事情是，有個 fun (select, insert ...) 直接過來執行
        // todo -> 然後我可以直接去取得我要什麼table? 有哪些column ? 這我只要assign值之後然後丟上面的command執行就可以了

        Dictionary resultDict = new Hashtable() ;

        switch (commandType) {

            // INSERT, UPDATE, DELETE
            case Execute:

                // Determine Whether this SQL Command is Success of Not
                try {

                    SQLiteDatabase myLocalDB = this.getWritableDatabase() ;
                    myLocalDB.execSQL(commandSQL) ;

                    resultDict.put(successDictKey, true) ;
                    Toast.makeText(this.nowContext, "Execute " + commandType.name() + " Success ！", Toast.LENGTH_LONG).show() ;
                } catch ( Exception e ) {

                    resultDict.put(successDictKey, false) ;
                    Toast.makeText(this.nowContext, "Execute " + commandType.name() + " Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
                }

                break ;

            // SELECT
            // Nested Query (IN, NOT IN, EXISTS, NOT EXISTS)
            // Aggregate Functions (COUNT, SUM, MAX, MIN, AVG , HAVING)
            case Query:


                break ;
        }

        return resultDict ;
    }
}
