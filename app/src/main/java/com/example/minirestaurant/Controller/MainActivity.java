package com.example.minirestaurant.Controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.minirestaurant.Model.CommentInfo;
import com.example.minirestaurant.Model.OrderInfo;
import com.example.minirestaurant.Model.ProductInfo;
import com.example.minirestaurant.Model.ReportInfo;
import com.example.minirestaurant.Model.UserInfo;
import com.example.minirestaurant.R;
import com.example.minirestaurant.Support.DBHelper;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.Dictionary;

public class MainActivity extends AppCompatActivity {

    private DBHelper myDBHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.SetInit() ;
    }

    private void SetInit() {

        // Let LocalDB Bind to Current Context
        myDBHelper = new DBHelper(this) ;
    }
}
