package com.example.minirestaurant.Support;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.widget.Toast;

import com.example.minirestaurant.Model.CommentInfo;
import com.example.minirestaurant.Model.ManufacturerInfo;
import com.example.minirestaurant.Model.OrderInfo;
import com.example.minirestaurant.Model.ProductInfo;
import com.example.minirestaurant.Model.ReportInfo;
import com.example.minirestaurant.Model.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;

public class DBHelper extends SQLiteOpenHelper {

    enum DictionaryKeyType {

        SuccessKey("Success"),
        DataKey("Data") ;

        public final String keyName ;

        DictionaryKeyType(String keyName) {
            this.keyName = keyName ;
        }
    }

    enum SQLCommandType {

        Execute,
        Query
    }

    public enum TableType {

        User(userTableName, new ArrayList<String>(Arrays.asList(uID, userName, userAge, userGender))),
        Product(productTableName, new ArrayList<String>(Arrays.asList(pID, mID, productName, productPrice, productColdOrHot))),
        Manufacturer(manufacturerTableName, new ArrayList<String>(Arrays.asList(mID, manufacturerName, manufacturerCountry, manufacturerPeopleNum))),
        Order(orderTableName, new ArrayList<String>(Arrays.asList(oID, uID, pID, productAmount, totalPrice))),
        Comment(commentTableName, new ArrayList<String>(Arrays.asList(cID, uID, commentDate, commentContent, commentRating))),
        Report(reportTableName, new ArrayList<String>(Arrays.asList(cID, uID, reportDate))) ;

        public final String tableName ;
        public final ArrayList<String> totalColumns ;

        TableType(String tableName, ArrayList<String> totalColumns) {
            this.tableName = tableName ;
            this.totalColumns = totalColumns ;
        }
    }

    private Context nowContext ;

    // --------------------- Database Setting           ---------------------

    // Database name & version
    private static final String databaseName = "LocalDB" ;
    private static final int databaseVersion = 1 ;
    // ----------------------------------------------------------------------

    // --------------------- User Table Setting         ---------------------

    // User Table Name
    private static final String userTableName = "UserInfo" ;

    // Columns for Creating User Table
    private static final String uID = "uID" ;
    private static final String userName = "Name" ;
    private static final String userAge = "Age" ;
    private static final String userGender = "Gender" ;

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

    // ----------------------------------------------------------------------

    // --------------------- Product Table Setting      ---------------------

    // Product Table Name
    private static final String productTableName = "ProductInfo" ;

    // Columns for Creating Product Table
    private static final String pID = "pID" ;
    private static final String productName = "Name" ;
    private static final String productPrice = "Price" ;
    private static final String productColdOrHot = "ColdOrHot" ;

    // SQL Script for Creating Product Table
    // CREATE TABLE IF NOT EXISTS ProductInfo ( pID INTEGER PRIMARY KEY AUTOINCREMENT, mID INTEGER, Name VARCHAR(255), Price INTEGER, ColdOrHot VARCHAR(255) ) ;
    private final String createProductTableSQL = "CREATE TABLE IF NOT EXISTS " + this.productTableName +
            " ( " + this.pID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + this.mID + " INTEGER, "
            + this.productName + " VARCHAR(255), "
            + this.productPrice + " INTEGER, "
            + this.productColdOrHot + " VARCHAR(255)" +
            " ) ; " ;

    // SQL Script for Deleting Product Table
    // DROP TABLE IF EXISTS ProductInfo ;
    private final String deleteProductTableSQL = "DROP TABLE IF EXISTS " + this.productTableName + " ; " ;

    // ----------------------------------------------------------------------

    // --------------------- Manufacturer Table Setting ---------------------

    // Manufacturer Table Name
    private static final String manufacturerTableName = "ManufacturerInfo" ;

    // Columns for Creating Manufacturer Table
    private static final String mID = "mID" ;
    private static final String manufacturerName = "Name" ;
    private static final String manufacturerCountry = "Country" ;
    private static final String manufacturerPeopleNum = "PeopleNum" ;

    // SQL Script for Creating Manufacturer Table
    // CREATE TABLE IF NOT EXISTS ManufacturerInfo ( mID INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR(255), Country VARCHAR(255), PeopleNum INTEGER ) ;
    private final String createManufacturerTableSQL = "CREATE TABLE IF NOT EXISTS " + this.manufacturerTableName +
            " ( " + this.mID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + this.manufacturerName + " VARCHAR(255), "
            + this.manufacturerCountry + " VARCHAR(255), "
            + this.manufacturerPeopleNum + " INTEGER" +
            " ) ; " ;

    // SQL Script for Deleting Manufacturer Table
    // DROP TABLE IF EXISTS ManufacturerInfo ;
    private final String deleteManufacturerTableSQL = "DROP TABLE IF EXISTS " + this.manufacturerTableName + " ; " ;

    // ----------------------------------------------------------------------

    // --------------------- Order Table Setting        ---------------------

    // Order Table Name
    private static final String orderTableName = "OrderInfo" ;

    // Columns for Creating Order Table
    private static final String oID = "oID" ;
    private static final String productAmount = "Amount" ;
    private static final String totalPrice = "Price" ;

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

    // ----------------------------------------------------------------------

    // --------------------- Comment Table Setting      ---------------------

    // Comment Table Name
    private static final String commentTableName = "CommentInfo" ;

    // Columns for Creating Comment Table
    private static final String cID = "cID" ;
    private static final String commentDate = "Date" ;
    private static final String commentContent = "Content" ;
    private static final String commentRating = "Rating" ;

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

    // ----------------------------------------------------------------------

    // --------------------- Report Table Setting       ---------------------

    // Report Table Name
    private static final String reportTableName = "ReportInfo" ;

    // Columns for Creating Report Table
    private static final String reportDate = "Date" ;

    // SQL Script for Creating Report Table
    // CREATE TABLE IF NOT EXISTS ReportInfo ( cID INTEGER, uID INTEGER, Date VARCHAR(255), PRIMARY KEY ( cID , uID ) ) ;
    private final String createReportTableSQL = "CREATE TABLE IF NOT EXISTS " + this.reportTableName +
            " ( " + this.cID + " INTEGER, "
            + this.uID + " INTEGER, "
            + this.reportDate + " VARCHAR(255), "
            + "PRIMARY KEY ( " + this.cID + " , " + this.uID + " )" +
            " ) ; " ;

    // SQL Script for Deleting Report Table
    // DROP TABLE IF EXISTS ReportInfo ;
    private final String deleteReportTableSQL = "DROP TABLE IF EXISTS " + this.reportTableName + " ; " ;

    // ----------------------------------------------------------------------

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

        // 3. Manufacturer Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.createManufacturerTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Create Manufacturer Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }

        // 4. Order Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.createOrderTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Create Order Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }

        // 5. Comment Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.createCommentTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Create Comment Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }

        // 6. Report Table
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

        // 3. Manufacturer Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.deleteManufacturerTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Delete Manufacturer Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }

        // 4. Order Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.deleteOrderTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Delete Order Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }

        // 5. Comment Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.deleteCommentTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Delete Comment Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }

        // 6. Report Table
        // Determine Whether this SQL Command is Success of Not
        try {

            sqLiteDatabase.execSQL(this.deleteReportTableSQL) ;
        } catch ( Exception e ) {

            Toast.makeText(this.nowContext, "Delete Report Table Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
        }
    }

    public ArrayList<TableType> GetTotalTables() {
        return new ArrayList<TableType>(Arrays.asList(TableType.User, TableType.Product, TableType.Manufacturer,
                                                      TableType.Order, TableType.Comment, TableType.Report)) ;
    }

    public Dictionary ExecuteSQLCommand(TableType tableTpe, SQLCommandType commandType, String commandSQL) {

        SQLiteDatabase myLocalDB ;
        Cursor myCursor ;

        Dictionary resultDict = new Hashtable() ;

        switch (commandType) {

            // INSERT, UPDATE, DELETE
            case Execute:

                myLocalDB = this.getWritableDatabase() ;

                // Determine Whether this SQL Command is Success of Not
                try {

                    myLocalDB.execSQL(commandSQL) ;

                    resultDict.put(DictionaryKeyType.SuccessKey, true) ;
                    Toast.makeText(this.nowContext, "Execute " + commandType.name() + " Success ！", Toast.LENGTH_LONG).show() ;
                } catch ( Exception e ) {

                    resultDict.put(DictionaryKeyType.SuccessKey, false) ;
                    Toast.makeText(this.nowContext, "Execute " + commandType.name() + " Fail :\n\n" + e.toString(), Toast.LENGTH_LONG).show() ;
                }

                myLocalDB.close() ;
                break ;

            // SELECT
            // Nested Query (IN, NOT IN, EXISTS, NOT EXISTS)
            // Aggregate Functions (COUNT, SUM, MAX, MIN, AVG , HAVING)
            case Query:

                myLocalDB = this.getReadableDatabase() ;

                myCursor = myLocalDB.rawQuery(commandSQL, null) ;

                switch (tableTpe) {
                    case User:

                        // Columns of UserInfo Table
                        // 1. uID INTEGER PRIMARY KEY AUTOINCREMENT
                        // 2. Name VARCHAR(255)
                        // 3. Age INTEGER
                        // 4. Gender VARCHAR(255)

                        ArrayList<UserInfo> totalUserInfos = new ArrayList<UserInfo>() ;

                        // Read Data by All Rows from Table
                        while (myCursor.moveToNext()) {

                            UserInfo eachUserInfo = new UserInfo() ;
                            eachUserInfo.init(myCursor.getInt(0), myCursor.getString(1), myCursor.getInt(2), myCursor.getString(3)) ;

                            totalUserInfos.add(eachUserInfo) ;
                        }

                        resultDict.put(DictionaryKeyType.DataKey, totalUserInfos) ;
                        break ;

                    case Product:

                        // Columns of ProductInfo Table
                        // 1. pID INTEGER PRIMARY KEY AUTOINCREMENT
                        // 2. mID INTEGER
                        // 3. Name VARCHAR(255)
                        // 4. Price INTEGER
                        // 5. ColdOrHot VARCHAR(255)

                        ArrayList<ProductInfo> totalProductInfos = new ArrayList<ProductInfo>() ;

                        // Read Data by All Rows from Table
                        while (myCursor.moveToNext()) {

                            ProductInfo eachProductInfo = new ProductInfo() ;
                            eachProductInfo.init(myCursor.getInt(0), myCursor.getInt(1), myCursor.getString(2), myCursor.getInt(3), myCursor.getString(4)) ;

                            totalProductInfos.add(eachProductInfo) ;
                        }

                        resultDict.put(DictionaryKeyType.DataKey, totalProductInfos) ;
                        break ;

                    case Manufacturer:

                        // Columns of Manufacturer Table
                        // 1. mID INTEGER PRIMARY KEY AUTOINCREMENT
                        // 2. Name VARCHAR(255)
                        // 3. Country VARCHAR(255)
                        // 4. PeopleNum INTEGER

                        ArrayList<ManufacturerInfo> totalManufacturerInfos = new ArrayList<ManufacturerInfo>() ;

                        // Read Data by All Rows from Table
                        while (myCursor.moveToNext()) {

                            ManufacturerInfo eachManufacturerInfo = new ManufacturerInfo() ;
                            eachManufacturerInfo.init(myCursor.getInt(0), myCursor.getString(1), myCursor.getString(2), myCursor.getInt(3)) ;

                            totalManufacturerInfos.add((eachManufacturerInfo)) ;
                        }

                        resultDict.put(DictionaryKeyType.DataKey, totalManufacturerInfos) ;
                        break ;

                    case Order:

                        // Columns of OrderInfo Table
                        // 1. oID INTEGER PRIMARY KEY AUTOINCREMENT
                        // 2. uID INTEGER
                        // 3. pID INTEGER
                        // 4. Amount INTEGER
                        // 5. Price INTEGER

                        ArrayList<OrderInfo> totalOrderInfos = new ArrayList<OrderInfo>() ;

                        // Read Data by All Rows from Table
                        while (myCursor.moveToNext()) {

                            OrderInfo eachOrderInfo = new OrderInfo() ;
                            eachOrderInfo.init(myCursor.getInt(0), myCursor.getInt(1), myCursor.getInt(2), myCursor.getInt(3), myCursor.getInt(4)) ;

                            totalOrderInfos.add(eachOrderInfo) ;
                        }

                        resultDict.put(DictionaryKeyType.DataKey, totalOrderInfos) ;
                        break ;

                    case Comment:

                        // Columns of CommentInfo Table
                        // 1. cID INTEGER PRIMARY KEY AUTOINCREMENT
                        // 2. uID INTEGER
                        // 3. Date VARCHAR(255)
                        // 4. Content VARCHAR(255)
                        // 5. Rating INTEGER

                        ArrayList<CommentInfo> totalCommentInfos = new ArrayList<CommentInfo>() ;

                        // Read Data by All Rows from Table
                        while (myCursor.moveToNext()) {

                            CommentInfo eachCommentInfo = new CommentInfo() ;
                            eachCommentInfo.init(myCursor.getInt(0), myCursor.getInt(1), myCursor.getString(2), myCursor.getString(3), myCursor.getInt(4)) ;

                            totalCommentInfos.add(eachCommentInfo) ;
                        }

                        resultDict.put(DictionaryKeyType.DataKey, totalCommentInfos) ;
                        break ;

                    case Report:

                        // Columns of ReportInfo Table
                        // 1. cID INTEGER PRIMARY KEY
                        // 2. uID INTEGER PRIMARY KEY
                        // 3. Date VARCHAR(255)

                        ArrayList<ReportInfo> totalReportInfos = new ArrayList<ReportInfo>() ;

                        // Read Data by All Rows from Table
                        while (myCursor.moveToNext()) {

                            ReportInfo eachReportInfo = new ReportInfo() ;
                            eachReportInfo.init(myCursor.getInt(0), myCursor.getInt(1), myCursor.getString(2)) ;

                            totalReportInfos.add(eachReportInfo) ;
                        }

                        resultDict.put(DictionaryKeyType.DataKey, totalReportInfos) ;
                        break ;
                }

                myCursor.close() ;
                myLocalDB.close() ;
                break ;
        }

        return resultDict ;
    }
}
