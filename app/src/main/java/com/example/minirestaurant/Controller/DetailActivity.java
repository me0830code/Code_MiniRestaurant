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

import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minirestaurant.Model.CommentInfo;
import com.example.minirestaurant.Model.ManufacturerInfo;
import com.example.minirestaurant.Model.OrderInfo;
import com.example.minirestaurant.Model.ProductInfo;
import com.example.minirestaurant.Model.ReportInfo;
import com.example.minirestaurant.Model.UserInfo;
import com.example.minirestaurant.R;
import com.example.minirestaurant.Support.DBHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

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

            default:

                // Clean Text of this SQL Mode
                firstContent.setText("") ;

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
                fifthTitle.setText("Default Input Command") ;
                fifthContent.setText(thisModeType.modeSQLName) ;
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
        DBHelper.TableType thisTableType = null ;
        DBHelper.SQLCommandType thisSQLCommandType = null ;

        String thisSQLCommand = "" ;

        String whichTable = secondContent.getSelectedItemPosition() >= 0 ?
                secondContent.getSelectedItem().toString() : "" ;

        String whichColumn = secondContent.getSelectedItemPosition() >= 0 && thirdContent.getSelectedItemPosition() >= 0 ?
                thirdContent.getSelectedItem().toString() : "" ;

        switch (thisModeType) {

            case Select:

                thisTableType = totalTables.get(secondContent.getSelectedItemPosition()) ;

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

                thisTableType = totalTables.get(secondContent.getSelectedItemPosition()) ;

                thisSQLCommandType = DBHelper.SQLCommandType.Execute ;

                // Insert into [Table] ( Column1, ... ) Values ( Value1, ...) ;
                thisSQLCommand = firstContent.getText().toString() + " " + whichTable + " " + fifthContent.getText().toString() + " ;";
                break ;

            case Update:

                thisTableType = totalTables.get(secondContent.getSelectedItemPosition()) ;

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

                thisTableType = totalTables.get(secondContent.getSelectedItemPosition()) ;

                thisSQLCommandType = DBHelper.SQLCommandType.Execute ;

                // Delete from [Table] Where [Condition] ;
                thisSQLCommand = firstContent.getText().toString() + " " + whichTable ;

                if (fifthContent.getText().toString().length() > 0) {
                    thisSQLCommand = thisSQLCommand + " Where " + fifthContent.getText().toString() ;
                }

                thisSQLCommand = thisSQLCommand + " ;";
                break ;

            default :

                Log.d("_Hello", "Hello") ;

                Log.d("_fif?", fifthContent.getText().toString()) ;

                if (fifthContent.getText().toString().length() == 0) { break ; }

                String tableName = "" ;
                String inputCommand = fifthContent.getText().toString().toUpperCase() ;

                if (inputCommand.contains("FROM")) {

                    // Select [Column] From [Table] Where [Condition] ;
                    // Delete from [Table] Where [Condition] ;
                    String tempStr = inputCommand.split("FROM")[1] ;
                    Log.d("_FROM1", tempStr) ;

                    // where -> ;

                    if (tempStr.contains("WHERE")) {
                        tempStr = tempStr.split("WHERE")[0] ;
                    }

                    if (tempStr.contains("GROUP")) {
                        tempStr = tempStr.split("GROUP")[0] ;
                    }

                    tempStr = tempStr.replace(" ", "") ;
                    tableName = tempStr.split(";")[0] ;
                    Log.d("_FROM2", tableName) ;
                } else if (inputCommand.contains("UPDATE")) {

                    // Update [Table] Set [Column] = [Value] Where [Condition] ;
                    String tempStr = inputCommand.split("UPDATE")[1] ;
                    Log.d("_Update1", tempStr) ;

                    tempStr = tempStr.split("SET")[0] ;
                    tableName = tempStr.replace(" ", "") ;
                    Log.d("_Update2", tableName) ;
                } else if (inputCommand.contains("INTO")) {

                    // Insert into [Table] ( Column1, ... ) Values ( Value1, ...) ;
                    String tempStr = inputCommand.split("INTO")[1] ;
                    Log.d("into1", tempStr) ;

                    tempStr = tempStr.split("\\(")[0] ;
                    tableName = tempStr.replace(" ", "") ;
                    Log.d("into2", tableName) ;
                }

                Log.d("_Com", thisSQLCommand) ;
                Log.d("_Cut", tableName) ;

                if (tableName.equalsIgnoreCase(DBHelper.TableType.User.tableName)) {
                    thisTableType = DBHelper.TableType.User ;
                } else if (tableName.equalsIgnoreCase(DBHelper.TableType.Product.tableName)) {
                    thisTableType = DBHelper.TableType.Product ;
                } else if (tableName.equalsIgnoreCase(DBHelper.TableType.Manufacturer.tableName)) {
                    thisTableType = DBHelper.TableType.Manufacturer ;
                } else if (tableName.equalsIgnoreCase(DBHelper.TableType.Order.tableName)) {
                    thisTableType = DBHelper.TableType.Order ;
                } else if (tableName.equalsIgnoreCase(DBHelper.TableType.Comment.tableName)) {
                    thisTableType = DBHelper.TableType.Comment ;
                } else if (tableName.equalsIgnoreCase(DBHelper.TableType.Report.tableName)) {
                    thisTableType = DBHelper.TableType.Report ;
                }

                Log.d("_Type", thisTableType.toString()) ;

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
        if (fifthContent.isEnabled()) { fifthContent.setText("") ; }

        Log.d("_SQL", thisSQLCommand) ;

        // Send SQL Command
        Dictionary resultDict = myDBHelper.ExecuteSQLCommand(thisTableType, thisSQLCommandType, thisSQLCommand, true) ;

        if (thisSQLCommandType == DBHelper.SQLCommandType.Query) {

            // Show Data of Current Selected Table
            ShowDataTable(resultDict, thisTableType) ;

//            ArrayList<UserInfo> res = (ArrayList<UserInfo>) resultDict.get(DBHelper.DictionaryKeyType.DataKey) ;

//            if (res.size() > 0) {
//                Log.d("_SQL_Dict", "有東西: " + res.size()) ;
//                Log.d("_SQL_1", "ID: " + res.get(0).GetUserID()) ;
//                Log.d("_SQL_2", "Name: " + res.get(0).GetUserName()) ;
//                Log.d("_SQL_3", "Age: " + res.get(0).GetUserAge()) ;
//                Log.d("_SQL_4", "Gender: " + res.get(0).GetUserGender()) ;
//            } else {
//                Log.d("_SQL_Dict", "沒東西") ;
//            }
        } else {
            Log.d("_SQL_Dict", resultDict.get(DBHelper.DictionaryKeyType.SuccessKey).toString()) ;

            // Send SQL Command
            Dictionary resultValue = myDBHelper.ExecuteSQLCommand(thisTableType, DBHelper.SQLCommandType.Query, "Select * From " + thisTableType.tableName + " ;", true) ;

            // Show Data of Current Selected Table
            ShowDataTable(resultValue, thisTableType) ;
        }

        // Update Showing ListView
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        if (parent.getId() == R.id.secondContent) {

            Spinner secondContent = (Spinner) findViewById(R.id.secondContent) ;

            // Send SQL Command
            Dictionary resultValue = myDBHelper.ExecuteSQLCommand(totalTables.get(secondContent.getSelectedItemPosition()), DBHelper.SQLCommandType.Query, "Select * From " + totalTables.get(secondContent.getSelectedItemPosition()).tableName + " ;", true) ;

            // Show Data of Current Selected Table
            ShowDataTable(resultValue, totalTables.get(secondContent.getSelectedItemPosition())) ;

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

    private void ShowDataTable(Dictionary resultValue, DBHelper.TableType thisTableType) {

        ArrayAdapter<String> dataSurce = new ArrayAdapter(this, android.R.layout.simple_list_item_1) ;
        ListView dataListView = (ListView) findViewById(R.id.dataListView) ;

        switch (thisTableType) {

            case User:

                ArrayList<UserInfo> totalUserInfos = (ArrayList<UserInfo>) resultValue.get(DBHelper.DictionaryKeyType.DataKey) ;

                for (int index = 0 ; index < totalUserInfos.size() ; index ++) {

                    UserInfo eachUserInfo = totalUserInfos.get(index) ;

                    String thisUID = eachUserInfo.GetUserID().length() > 0 ? "\n" + thisTableType.totalColumns.get(0) + ": " + eachUserInfo.GetUserID() : "" ;
                    String thisName = eachUserInfo.GetUserName().length() > 0 ? "\n" + thisTableType.totalColumns.get(1) + ": " + eachUserInfo.GetUserName() : "" ;
                    String thisAge = eachUserInfo.GetUserAge().length() > 0 ? "\n" + thisTableType.totalColumns.get(2) + ": " + eachUserInfo.GetUserAge() : "" ;
                    String thisGender = eachUserInfo.GetUserGender().length() > 0 ? "\n" + thisTableType.totalColumns.get(3) + ": " + eachUserInfo.GetUserGender() : "" ;

                    String thisData = "[ " + (index + 1) + " ]\n" + thisUID + thisName + thisAge + thisGender ;

                    dataSurce.add(thisData) ;
                }

                break ;

            case Product:

                ArrayList<ProductInfo> totalProductInfos = (ArrayList<ProductInfo>) resultValue.get(DBHelper.DictionaryKeyType.DataKey) ;

                for (int index = 0 ; index < totalProductInfos.size() ; index ++) {

                    ProductInfo eachProductInfo = totalProductInfos.get(index) ;

                    String thisPID = eachProductInfo.GetProductID().length() > 0 ? "\n" + thisTableType.totalColumns.get(0) + ": " + eachProductInfo.GetProductID() : "" ;
                    String thisMID = eachProductInfo.GetManufacturerID().length() > 0 ? "\n" + thisTableType.totalColumns.get(1) + ": " + eachProductInfo.GetManufacturerID() : "" ;
                    String thisName = eachProductInfo.GetProductName().length() > 0 ? "\n" + thisTableType.totalColumns.get(2) + ": " + eachProductInfo.GetProductName() : "" ;
                    String thisPrice = eachProductInfo.GetProductPrice().length() > 0 ? "\n" + thisTableType.totalColumns.get(3) + ": " + eachProductInfo.GetProductPrice() : "" ;
                    String thisColdOrHot = eachProductInfo.GetProductColdOrHot().length() > 0 ? "\n" + thisTableType.totalColumns.get(4) + ": " + eachProductInfo.GetProductColdOrHot() : "" ;

                    String thisData = "[ " + (index + 1) + " ]\n" + thisPID + thisMID + thisName + thisPrice + thisColdOrHot ;

                    dataSurce.add(thisData) ;
                }

                break ;

            case Manufacturer:

                ArrayList<ManufacturerInfo> totalManufacturerInfos = (ArrayList<ManufacturerInfo>) resultValue.get(DBHelper.DictionaryKeyType.DataKey) ;

                for (int index = 0 ; index < totalManufacturerInfos.size() ; index ++) {

                    ManufacturerInfo eachManufacturerInfo = totalManufacturerInfos.get(index) ;

                    String thisMID = eachManufacturerInfo.GetManufacturerID().length() > 0 ? "\n" + thisTableType.totalColumns.get(0) + ": " + eachManufacturerInfo.GetManufacturerID() : "" ;
                    String thisName = eachManufacturerInfo.GetManufacturerName().length() > 0 ? "\n" + thisTableType.totalColumns.get(1) + ": " + eachManufacturerInfo.GetManufacturerName() : "" ;
                    String thisCountry = eachManufacturerInfo.GetManufacturerCountry().length() > 0 ? "\n" + thisTableType.totalColumns.get(2) + ": " + eachManufacturerInfo.GetManufacturerCountry() : "" ;
                    String thisPeopleNum = eachManufacturerInfo.GetManufacturerPeopleNum().length() > 0 ? "\n" + thisTableType.totalColumns.get(3) + ": " + eachManufacturerInfo.GetManufacturerPeopleNum() : "" ;

                    String thisData = "[ " + (index + 1) + " ]\n" + thisMID + thisName + thisCountry + thisPeopleNum ;

                    dataSurce.add(thisData) ;
                }

                break ;

            case Order:

                ArrayList<OrderInfo> totalOrderInfos = (ArrayList<OrderInfo>) resultValue.get(DBHelper.DictionaryKeyType.DataKey) ;

                for (int index = 0 ; index < totalOrderInfos.size() ; index ++) {

                    OrderInfo eachOrderInfo = totalOrderInfos.get(index) ;

                    String thisOID = eachOrderInfo.GetOrderID().length() > 0 ? "\n" + thisTableType.totalColumns.get(0) + ": " + eachOrderInfo.GetOrderID() : "" ;
                    String thisUID = eachOrderInfo.GetUserID().length() > 0 ? "\n" + thisTableType.totalColumns.get(1) + ": " + eachOrderInfo.GetUserID() : "" ;
                    String thisPID = eachOrderInfo.GetProductID().length() > 0 ? "\n" + thisTableType.totalColumns.get(2) + ": " + eachOrderInfo.GetProductID() : "" ;
                    String thisAmount = eachOrderInfo.GetProductAmount().length() > 0 ? "\n" + thisTableType.totalColumns.get(3) + ": " + eachOrderInfo.GetProductAmount() : "" ;
                    String thisPrice = eachOrderInfo.GetTotalPrice().length() > 0 ? "\n" + thisTableType.totalColumns.get(4) + ": " + eachOrderInfo.GetTotalPrice() : "" ;

                    String thisData = "[ " + (index + 1) + " ]\n" + thisOID + thisUID + thisPID + thisAmount + thisPrice ;

                    dataSurce.add(thisData) ;
                }

                break ;

            case Comment:

                ArrayList<CommentInfo> totalCommentInfos = (ArrayList<CommentInfo>) resultValue.get(DBHelper.DictionaryKeyType.DataKey) ;

                for (int index = 0 ; index < totalCommentInfos.size() ; index ++) {

                    CommentInfo eachCommentInfo = totalCommentInfos.get(index) ;

                    String thisCID = eachCommentInfo.GetCommentID().length() > 0 ? "\n" + thisTableType.totalColumns.get(0) + ": " + eachCommentInfo.GetCommentID() : "" ;
                    String thisUID = eachCommentInfo.GetUserID().length() > 0 ? "\n" + thisTableType.totalColumns.get(1) + ": " + eachCommentInfo.GetUserID() : "" ;
                    String thisDate = eachCommentInfo.GetCommentDate().length() > 0 ? "\n" + thisTableType.totalColumns.get(2) + ": " + eachCommentInfo.GetCommentDate() : "" ;
                    String thisContent = eachCommentInfo.GetCommentContent().length() > 0 ? "\n" + thisTableType.totalColumns.get(3) + ": " + eachCommentInfo.GetCommentContent() : "" ;
                    String thisRating = eachCommentInfo.GetCommentRating().length() > 0 ? "\n" + thisTableType.totalColumns.get(4) + ": " + eachCommentInfo.GetCommentRating() : "" ;

                    String thisData = "[ " + (index + 1) + " ]\n" + thisCID + thisUID + thisDate + thisContent + thisRating ;

                    dataSurce.add(thisData) ;
                }

                break ;

            case Report:

                ArrayList<ReportInfo> totalReportInfos = (ArrayList<ReportInfo>) resultValue.get(DBHelper.DictionaryKeyType.DataKey) ;

                for (int index = 0 ; index < totalReportInfos.size() ; index ++) {

                    ReportInfo eachReportInfo = totalReportInfos.get(index) ;

                    String thisCID = eachReportInfo.GetCommentID().length() > 0 ? "\n" + thisTableType.totalColumns.get(0) + ": " + eachReportInfo.GetCommentID() : "" ;
                    String thisUID = eachReportInfo.GetUserID().length() > 0 ? "\n" + thisTableType.totalColumns.get(1) + ": " + eachReportInfo.GetUserID() : "" ;
                    String thisDate = eachReportInfo.GetReportDate().length() > 0 ? "\n" + thisTableType.totalColumns.get(2) + ": " + eachReportInfo.GetReportDate() : "" ;

                    String thisData = "[ " + (index + 1) + " ]\n" + thisCID + thisUID + thisDate ;

                    dataSurce.add(thisData) ;
                }

                break ;
        }

        // Assign Data Source to ListView
        dataListView.setAdapter(dataSurce) ;
    }
}
