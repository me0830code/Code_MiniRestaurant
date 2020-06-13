package com.example.minirestaurant.Controller;

import android.content.Context;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minirestaurant.Model.UserInfo;
import com.example.minirestaurant.R;
import com.example.minirestaurant.Support.DBHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    static final String intentKeyString = "IntentKey" ;

    private DBHelper myDBHelper ;

    private MainActivity.ModeType thisModeType ;
    private ArrayList<DBHelper.TableType> totalTables ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        setContentView(R.layout.activity_detail) ;

        this.SetInit() ;
    }

    private void SetInit() {

        // Get this Mode Type
        thisModeType = (MainActivity.ModeType) getIntent().getSerializableExtra(this.intentKeyString) ;

        // Setting Title Label with Current Mode Type
        this.setTitle(thisModeType.name()) ;

        // Let LocalDB Bind to Current Context
        myDBHelper = new DBHelper(this) ;

        // Binding All UI Components
        TextView firstTitle = (TextView) findViewById(R.id.firstTitle) ;
        TextView firstContent = (TextView) findViewById(R.id.firstContent) ;

        // Setting SQL Mode
        firstContent.setText(thisModeType.modeSQLName) ;

        TextView secondTitle = (TextView) findViewById(R.id.secondTitle) ;
        Spinner secondContent = (Spinner) findViewById(R.id.secondContent) ;
        secondContent.setOnItemSelectedListener(this); ;

        // Data Source of Tables Spinner
        ArrayAdapter secondContentData = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item) ;

        // Fetch Total Tables of this Local Database
        totalTables = myDBHelper.GetTotalTables() ;
        for(int index = 0; index < totalTables.size(); index ++) { secondContentData.add(totalTables.get(index).tableName) ; }

        // Assign Data Source to Tables Spinner
        secondContent.setAdapter(secondContentData) ;

        TextView thirdTitle = (TextView) findViewById(R.id.thirdTitle) ;
        Spinner thirdContent = (Spinner) findViewById(R.id.thirdContent) ;
        thirdContent.setOnItemSelectedListener(this) ;

        TextView fourthTitle = (TextView) findViewById(R.id.fourthTitle) ;
        EditText fourthContent = (EditText) findViewById(R.id.fourthContent) ;

        TextView fifthTitle = (TextView) findViewById(R.id.fifthTitle) ;
        EditText fifthContent = (EditText) findViewById(R.id.fifthContent) ;

        Button buttonSendCommand = (Button) findViewById(R.id.buttonSendCommand) ;
        buttonSendCommand.setOnClickListener(this) ;

        // Setting UI Components
        switch (thisModeType) {

            case Manual:

                // Close Table Spinner
                secondContent.setEnabled(false) ;
                secondContent.setClickable(false) ;
                secondContentData.clear() ;

                // Close Column Spinner
                thirdContent.setEnabled(false) ;
                thirdContent.setClickable(false) ;

                // Close Value Input
                fourthContent.setEnabled(false) ;
                fourthContent.setClickable(false) ;

                fifthTitle.setText("Input Command");
                break ;

            case Select:

                // Close Value Input
                fourthContent.setEnabled(false) ;
                fourthContent.setClickable(false) ;
                break ;

            case Insert:

                // Close Column Spinner
                thirdContent.setEnabled(false) ;
                thirdContent.setClickable(false) ;

                // Close Value Input
                fourthContent.setEnabled(false) ;
                fourthContent.setClickable(false) ;

                fifthTitle.setText("Input Command");
                break ;

            case Update: break ;

            case Delete:

                // Close Column Spinner
                thirdContent.setEnabled(false) ;
                thirdContent.setClickable(false) ;

                // Close Value Input
                fourthContent.setEnabled(false) ;
                fourthContent.setClickable(false) ;
                break ;

            case Nested:

                // Close Table Spinner
                secondContent.setEnabled(false) ;
                secondContent.setClickable(false) ;
                secondContentData.clear() ;

                // Close Column Spinner
                thirdContent.setEnabled(false) ;
                thirdContent.setClickable(false) ;

                // Close Value Input
                fourthContent.setEnabled(false) ;
                fourthContent.setClickable(false) ;

                // Close Command Input
                fifthTitle.setText("Default Nested Command") ;
                fifthContent.setText("Nested") ;
                fifthContent.setEnabled(false) ;
                fifthContent.setClickable(false) ;
                break ;

            case Aggregate:

                // Close Table Spinner
                secondContent.setEnabled(false) ;
                secondContent.setClickable(false) ;
                secondContentData.clear() ;

                // Close Column Spinner
                thirdContent.setEnabled(false) ;
                thirdContent.setClickable(false) ;

                // Close Value Input
                fourthContent.setEnabled(false) ;
                fourthContent.setClickable(false) ;

                // Close Command Input
                fifthTitle.setText("Default Aggregate Command");
                fifthContent.setText("Aggregate") ;
                fifthContent.setEnabled(false) ;
                fifthContent.setClickable(false) ;
                break ;
        }
    }

    public void onClick(View view) {
        this.DismissKeyboard() ;

        TextView firstContent = (TextView) findViewById(R.id.firstContent) ;
        Spinner secondContent = (Spinner) findViewById(R.id.secondContent) ;
        Spinner thirdContent = (Spinner) findViewById(R.id.thirdContent) ;
        EditText fourthContent = (EditText) findViewById(R.id.fourthContent) ;
        EditText fifthContent = (EditText) findViewById(R.id.fifthContent) ;

        // Setting SQL Command
        DBHelper.SQLCommandType thisSQLCommandType ;

        String thisSQLCommand ;

        String whichTable = secondContent.getSelectedItemPosition() >= 0 ?
                secondContent.getSelectedItem().toString() : "" ;

        String whichColumn = secondContent.getSelectedItemPosition() >= 0 && thirdContent.getSelectedItemPosition() >= 0 ?
                thirdContent.getSelectedItem().toString() : "" ;

        switch (thisModeType) {

            case Select:

                thisSQLCommandType = DBHelper.SQLCommandType.Query ;

                // Select [Column] From [Table] Where [Condition] ;
                thisSQLCommand = firstContent.getText().toString() + " " + whichColumn +
                                 " From " + whichTable ;

                if (fifthContent.getText().toString().length() > 0) {
                    thisSQLCommand = thisSQLCommand + " Where " + fifthContent.getText().toString() ;
                }

                thisSQLCommand = thisSQLCommand + " ;";
                break ;

            case Insert:

                thisSQLCommandType = DBHelper.SQLCommandType.Execute ;

                // Insert into [Table] ( Column1, ... ) Values ( Value1, ...) ;
                thisSQLCommand = firstContent.getText().toString() + " " + whichTable + " " + fifthContent.getText().toString() + " ;";
                break ;

            case Update:

                thisSQLCommandType = DBHelper.SQLCommandType.Execute ;

                // Update [Table] Set [Column] = [Value] Where [Condition] ;
                thisSQLCommand = firstContent.getText().toString() + " " + whichTable +
                                 " Set " + whichColumn + " = " + fourthContent.getText().toString() ;

                if (fifthContent.getText().toString().length() > 0) {
                    thisSQLCommand = thisSQLCommand + " Where " + fifthContent.getText().toString() ;
                }

                thisSQLCommand = thisSQLCommand + " ;";
                break ;

            case Delete:

                thisSQLCommandType = DBHelper.SQLCommandType.Execute ;

                // Delete from [Table] Where [Condition] ;
                thisSQLCommand = firstContent.getText().toString() + " " + whichTable ;

                if (fifthContent.getText().toString().length() > 0) {
                    thisSQLCommand = thisSQLCommand + " Where " + fifthContent.getText().toString() ;
                }

                thisSQLCommand = thisSQLCommand + " ;";
                break ;

            default :

                thisSQLCommandType = fifthContent.getText().toString().split(" ")[0].equalsIgnoreCase("Select") ?
                        DBHelper.SQLCommandType.Query : DBHelper.SQLCommandType.Execute ;

                // Manual or Default SQL Command
                thisSQLCommand = fifthContent.getText().toString() ;
                break ;
        }

        // Empty SQL Command
        if (thisSQLCommand.length() == 0) {
            Toast.makeText(this, "Empty SQL Command, Please Try Again", Toast.LENGTH_LONG).show() ;
            return ;
        }

        // Clean User Input
        fifthContent.setText("") ;

        Log.d("_SQL", thisSQLCommand) ;

        // Send SQL Command
        Dictionary resultDict = myDBHelper.ExecuteSQLCommand(totalTables.get(secondContent.getSelectedItemPosition()), thisSQLCommandType, thisSQLCommand) ;

        if (thisSQLCommandType == DBHelper.SQLCommandType.Query) {

            ArrayList<UserInfo> res = (ArrayList<UserInfo>) resultDict.get(DBHelper.DictionaryKeyType.DataKey) ;

            if (res.size() > 0) {
                Log.d("_SQL_Dict", "有東西") ;
                Log.d("_SQL_1", res.get(0).GetUserID()) ;
                Log.d("_SQL_2", res.get(0).GetUserName()) ;
                Log.d("_SQL_3", res.get(0).GetUserAge()) ;
                Log.d("_SQL_4", res.get(0).GetUserGender()) ;
            } else {
                Log.d("_SQL_Dict", "沒東西") ;
            }
        } else {
            Log.d("_SQL_Dict", resultDict.get(DBHelper.DictionaryKeyType.SuccessKey).toString()) ;
        }

        // Update Showing ListView
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        if (parent.getId() == R.id.secondContent) {

            // Only Select & Update Can Choose Column
            if (thisModeType == MainActivity.ModeType.Select || thisModeType == MainActivity.ModeType.Update) {

                Spinner thirdContent = (Spinner) findViewById(R.id.thirdContent) ;

                // Data Source of Columns Spinner
                ArrayAdapter thirdContentData = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item) ;

                // User Can Use All Columns(*) in Select Mode
                if (thisModeType == MainActivity.ModeType.Select) { thirdContentData.add("*") ; }

                thirdContentData.addAll(totalTables.get(pos).totalColumns) ;

                // Assign Data Source to Columns Spinner
                thirdContent.setAdapter(thirdContentData) ;
            }
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
