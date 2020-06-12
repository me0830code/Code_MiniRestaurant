package com.example.minirestaurant.Controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.minirestaurant.R;
import com.example.minirestaurant.Support.DBHelper;

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
