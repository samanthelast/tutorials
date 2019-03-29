package com.mashtion.sqlitetestapp;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDatabase;
    EditText mNameEditText;
    EditText mSurnameEditText;
    EditText mMarkEditText;
    EditText mIdEditText;
    Button mInsertButton;
    Button mViewAllButton;
    Button mUpdateDataButton;
    Button mDeleteDataButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabase = new DatabaseHelper(this);

        mIdEditText = (EditText) findViewById(R.id.et_id);
        mNameEditText = (EditText) findViewById(R.id.et_name);
        mSurnameEditText = (EditText) findViewById(R.id.et_surname);
        mMarkEditText = (EditText) findViewById(R.id.et_mark);
        mInsertButton = (Button) findViewById(R.id.btn_insert);
        mViewAllButton = (Button) findViewById(R.id.btn_viewAll);
        mUpdateDataButton = (Button) findViewById(R.id.btn_update);
        mDeleteDataButton = (Button) findViewById(R.id.btn_delete);
        addData();
        viewAllData();
        updateData();
        deleteData();
    }
    private void updateData(){
        mUpdateDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean result = myDatabase.updateData(
                        mIdEditText.getText().toString(),
                        mNameEditText.getText().toString(),
                        mSurnameEditText.getText().toString(),
                        mMarkEditText.getText().toString()
                );
                if (result ){
                    Toast.makeText(MainActivity.this,"Data updated Successfully :)", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"Data update failed :(", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void viewAllData (){
        mViewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = myDatabase.getAllData();
                if(cursor.getCount() == 0){
                    showResult("fuck!","the Database is empty");
                    return;

                }
                    StringBuffer stringBuffer = new StringBuffer();
                    while (cursor.moveToNext()){
                        stringBuffer.append("iD: " + cursor.getString(0) + "\n");
                        stringBuffer.append("Name: " + cursor.getString(1) + "\n" );
                        stringBuffer.append("Surname: " + cursor.getString(2)+ "\n" );
                        stringBuffer.append("Mark: " + cursor.getString(3)+ "\n\n");

                    }
                String result = stringBuffer.toString();
                showResult("found the Data!", result);

            }
        });
    }
    private void showResult(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    private void addData() {
        mInsertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean ifInserted = myDatabase.insert(mNameEditText.getText().toString(),mSurnameEditText.getText().toString(), mMarkEditText.getText().toString());
                if(ifInserted){
                    Toast.makeText(MainActivity.this,"Data Inserted Successfully :)", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"Data insertion failure :( ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void deleteData(){
        mDeleteDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = myDatabase.deleteData(mIdEditText.getText().toString());
                if(result > 0){
                    Toast.makeText(MainActivity.this,"Data Deleted Successfully :)", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(MainActivity.this,"Data Delete failed :(", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}

