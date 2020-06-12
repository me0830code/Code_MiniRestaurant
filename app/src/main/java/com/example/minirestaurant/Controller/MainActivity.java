package com.example.minirestaurant.Controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.minirestaurant.Model.ProductInfo;
import com.example.minirestaurant.Model.ReportInfo;
import com.example.minirestaurant.R;
import com.example.minirestaurant.Support.DBHelper;

import java.util.ArrayList;
import java.util.Dictionary;

public class MainActivity extends AppCompatActivity {

    private DBHelper myDBHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        // Let LocalDB Bind to Current Context
        myDBHelper = new DBHelper(this) ;
    }
}
