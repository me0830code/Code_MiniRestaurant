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

    private DBHelper myDBHelper ;

    enum ModeType {

        Manual(""),
        Select("Select"),
        Insert("Insert into"),
        Update("Update"),
        Delete("Delete from"),
        In(""),
        NotIn(""),
        Exist(""),
        NotExist(""),
        Count(""),
        Sum(""),
        Max(""),
        Min(""),
        Avg(""),
        Having("") ;



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
    }

    private void SetInit() {

        // Let LocalDB Bind to Current Context
        myDBHelper = new DBHelper(this) ;

        // Assign Default Data
        myDBHelper.AssignDefaultData() ;

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

        Button buttonIn = (Button) findViewById(R.id.buttonIn) ;
        buttonIn.setOnClickListener(this) ;

        Button buttonNotIn = (Button) findViewById(R.id.buttonNotIn) ;
        buttonNotIn.setOnClickListener(this) ;

        Button buttonExist = (Button) findViewById(R.id.buttonExist) ;
        buttonExist.setOnClickListener(this) ;

        Button buttonNotExist = (Button) findViewById(R.id.buttonNotExist) ;
        buttonNotExist.setOnClickListener(this) ;

        Button buttonCount = (Button) findViewById(R.id.buttonCount) ;
        buttonCount.setOnClickListener(this) ;

        Button buttonSum = (Button) findViewById(R.id.buttonSum) ;
        buttonSum.setOnClickListener(this) ;

        Button buttonMax = (Button) findViewById(R.id.buttonMax) ;
        buttonMax.setOnClickListener(this) ;

        Button buttonMin = (Button) findViewById(R.id.buttonMin) ;
        buttonMin.setOnClickListener(this) ;

        Button buttonAvg = (Button) findViewById(R.id.buttonAvg) ;
        buttonAvg.setOnClickListener(this) ;

        Button buttonHaving = (Button) findViewById(R.id.buttonHaving) ;
        buttonHaving.setOnClickListener(this) ;
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

            case R.id.buttonIn : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.In) ;
                break ;
            }

            case R.id.buttonNotIn : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.NotIn) ;
                break ;
            }

            case R.id.buttonExist : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.Exist) ;
                break ;
            }

            case R.id.buttonNotExist : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.NotExist) ;
                break ;
            }

            case R.id.buttonCount : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.Count) ;
                break ;
            }

            case R.id.buttonSum : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.Sum) ;
                break ;
            }

            case R.id.buttonMax : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.Max) ;
                break ;
            }

            case R.id.buttonMin : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.Min) ;
                break ;
            }

            case R.id.buttonAvg : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.Avg) ;
                break ;
            }

            case R.id.buttonHaving : {
                thisIntent.putExtra(DetailActivity.intentKeyString, ModeType.Having) ;
                break ;
            }
        }

        startActivity(thisIntent) ;
    }
}
