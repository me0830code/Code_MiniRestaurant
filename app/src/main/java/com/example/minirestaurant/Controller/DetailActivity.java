package com.example.minirestaurant.Controller;

import android.content.Context;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minirestaurant.R;
import com.example.minirestaurant.Support.DBHelper;

import java.util.ArrayList;
import java.util.Arrays;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    static final String intentKeyString = "IntentKey" ;

    private DBHelper myDBHelper ;
    private ArrayList<DBHelper.TableType> totalTables ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_detail) ;

        this.SetInit() ;
    }

    private void SetInit() {

        // Let LocalDB Bind to Current Context
        myDBHelper = new DBHelper(this) ;

        // Fetch Total Tables of this Local Database
        totalTables = myDBHelper.GetTotalTables() ;

        // Binding All UI Components
        TextView firstTitle = (TextView) findViewById(R.id.firstTitle) ;
        TextView firstContent = (TextView) findViewById(R.id.firstContent) ;

        TextView secondTitle = (TextView) findViewById(R.id.secondTitle) ;
        Spinner secondContent = (Spinner) findViewById(R.id.secondContent) ;
        ArrayAdapter secondContentData = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item) ;
        for(int index = 0; index < totalTables.size(); index ++) {
            secondContentData.add(totalTables.get(index).tableName) ;
        }

        TextView thirdTitle = (TextView) findViewById(R.id.thirdTitle) ;
        Spinner thirdContent = (Spinner) findViewById(R.id.thirdContent) ;
        ArrayAdapter thirdContentData = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item) ;

        TextView fourthTitle = (TextView) findViewById(R.id.fourthTitle) ;
        EditText fourthContent = (EditText) findViewById(R.id.fourthContent) ;

        TextView fifthTitle = (TextView) findViewById(R.id.fifthTitle) ;
        EditText fifthContent = (EditText) findViewById(R.id.fifthContent) ;

        Button buttonSendCommand = (Button) findViewById(R.id.buttonSendCommand) ;
        buttonSendCommand.setOnClickListener(this) ;

        // Setting UI Components
        MainActivity.FunctionType thisFunctionType = (MainActivity.FunctionType) getIntent().getSerializableExtra(this.intentKeyString) ;

        switch (thisFunctionType) {

            case Manual:

                firstContent.setText("---------------------------------------------") ;
                firstContent.setTextColor(Color.LTGRAY);

                secondContent.setEnabled(false) ;
                secondContent.setClickable(false) ;

                thirdContent.setEnabled(false) ;
                thirdContent.setClickable(false) ;

                fourthContent.setText("-------------------------------------------------") ;
                fourthContent.setEnabled(false) ;
                fourthContent.setClickable(false) ;

                fifthTitle.setText("Input Command");

                break ;

            case Select:

                firstContent.setText(thisFunctionType.name()) ;
                secondContent.setAdapter(secondContentData) ;

                break ;
            case Insert: break ;
            case Update: break ;
            case Delete: break ;
            case Nested: break ;
            case Aggregate: break ;
        }

        // Setting Title Label
        this.setTitle(thisFunctionType.functionName) ;
    }

    public void onClick(View view) {
        this.DismissKeyboard() ;

        // Send SQL Command
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        switch (view.getId()) {
            case R.id.secondContent: break ;
            case R.id.thirdContent: break ;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void DismissKeyboard() {

        View view = this.getCurrentFocus() ;

        if (view == null) { return ; }

        InputMethodManager myKeyboard = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE) ;
        myKeyboard.hideSoftInputFromWindow(view.getWindowToken(), 0) ;
    }
}
