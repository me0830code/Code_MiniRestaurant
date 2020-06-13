package com.example.minirestaurant.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.example.minirestaurant.Model.ProductInfo;
import com.example.minirestaurant.Model.ReportInfo;
import com.example.minirestaurant.R;
import com.example.minirestaurant.Support.DBHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    enum ModeType {

        Manual(""),
        Select("Select"),
        Insert("Insert into"),
        Update("Update"),
        Delete("Delete from"),
        Nested(""),
        Aggregate("") ;

        public final String modeSQLName ;

        ModeType(String modeSQLName) {
            this.modeSQLName = modeSQLName ;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_main) ;

        this.SetInit() ;

//        String find = "SELECT * FROM ReportInfo" ;
//        Dictionary temp1 = myDBHelper.ExecuteSQLCommand(DBHelper.TableType.Report, DBHelper.SQLCommandType.Query, find) ;
//        ArrayList<ReportInfo> res1 = (ArrayList<ReportInfo>) temp1.get(myDBHelper.dataDictKey) ;
//
//        String find2 = "SELECT * FROM ProductInfo" ;
//        Dictionary temp2 = myDBHelper.ExecuteSQLCommand(DBHelper.TableType.Product, DBHelper.SQLCommandType.Query, find2) ;
//        ArrayList<ProductInfo> res2 = (ArrayList<ProductInfo>) temp1.get(myDBHelper.dataDictKey) ;
//
//        String command = "INSERT INTO ReportInfo (cID, uID, Date) VALUES ( \"1\", \"2\", \"Hello world :)\") ;" ;
//        Dictionary temp3 = myDBHelper.ExecuteSQLCommand(DBHelper.TableType.Report, DBHelper.SQLCommandType.Execute, command) ;
//        Boolean res3 = (Boolean) temp3.get(myDBHelper.successDictKey) ;
//
//        String command2 = "INSERT INTO ProductInfo (pID, mID, Name, Price, ColdOrHot) VALUES ( 1, 2, \"AAAA\", 100, \"冷\")" ;
//        Dictionary temp4 = myDBHelper.ExecuteSQLCommand(DBHelper.TableType.Product, DBHelper.SQLCommandType.Execute, command2) ;
//        Boolean res4 = (Boolean) temp4.get(myDBHelper.successDictKey) ;
//
//        Dictionary temp5 = myDBHelper.ExecuteSQLCommand(DBHelper.TableType.Report, DBHelper.SQLCommandType.Query, find) ;
//        ArrayList<ReportInfo> res5 = (ArrayList<ReportInfo>) temp5.get(myDBHelper.dataDictKey) ;
//
//        Dictionary temp6 = myDBHelper.ExecuteSQLCommand(DBHelper.TableType.Product, DBHelper.SQLCommandType.Query, find2) ;
//        ArrayList<ProductInfo> res6 = (ArrayList<ProductInfo>) temp6.get(myDBHelper.dataDictKey) ;
//
//        Log.d("_1", String.valueOf(res1.size())) ;
//        Log.d("_2", String.valueOf(res2.size())) ;
//        Log.d("_3", res3.toString()) ;
//        Log.d("_4", res4.toString()) ;
//        Log.d("_5", String.valueOf(res5.size()) + res5.get(0).GetReportDate()) ;
//        Log.d("_6", String.valueOf(res6.size()) + res6.get(0).GetProductName()) ;

    }

    private void SetInit() {

        // Assign Function for Each Button
        Button buttonManual = (Button) findViewById(R.id.buttonManual) ;
        buttonManual.setOnClickListener(this) ;

        Button buttonSelect = (Button) findViewById(R.id.buttonSelect) ;
        buttonSelect.setOnClickListener(this) ;

        Button buttonInsert = (Button) findViewById(R.id.buttonInsert) ;
        buttonInsert.setOnClickListener(this) ;

        Button buttonUpdate = (Button) findViewById(R.id.buttonUpdate) ;
        buttonUpdate.setOnClickListener(this) ;

        Button buttonDelete = (Button) findViewById(R.id.buttonDelete) ;
        buttonDelete.setOnClickListener(this) ;

        Button buttonNested = (Button) findViewById(R.id.buttonNested) ;
        buttonNested.setOnClickListener(this) ;

        Button buttonAggregate = (Button) findViewById(R.id.buttonAggregate) ;
        buttonAggregate.setOnClickListener(this) ;
    }

    public void onClick(View view) {

        Intent thisIntent = new Intent(MainActivity.this, DetailActivity.class) ;

        switch (view.getId()) {

            case R.id.buttonManual : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.Manual) ;
                break ;
            }

            case R.id.buttonSelect : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.Select) ;
                break ;
            }

            case R.id.buttonInsert : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.Insert) ;
                break ;
            }

            case R.id.buttonUpdate : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.Update) ;
                break ;
            }

            case R.id.buttonDelete : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.Delete) ;
                break ;
            }

            case R.id.buttonNested : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.Nested) ;
                break ;
            }

            case R.id.buttonAggregate : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.Aggregate) ;
                break ;
            }
        }

        startActivity(thisIntent) ;
    }
}
