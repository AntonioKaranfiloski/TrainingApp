package com.example.trainingapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editTitle, editDescription, editKilometers, editDenivelation, editId;// editDate;
    Button btnAddData;
    Button btnviewAll;
    Button btnviewUpdate;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myDb = new DatabaseHelper(this);

        editTitle = (EditText) findViewById(R.id.editText_Title);
        editDescription = (EditText) findViewById(R.id.editText_Description);
        editKilometers = (EditText) findViewById(R.id.editText_Kilometers);
        editDenivelation = (EditText) findViewById(R.id.editText_Denivelation);
        editId = (EditText) findViewById(R.id.editText_Id);
        //editDate = (EditText) findViewById(R.id.editText_Date);
        btnAddData = (Button) findViewById(R.id.btn_addData);
        btnviewAll = (Button) findViewById(R.id.btn_view_all);
        btnviewUpdate = (Button) findViewById(R.id.btn_update);
        btnDelete = (Button) findViewById(R.id.btn_delete);

        AddData();
        viewAll();
        UpdateData();
        DeleteData();
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    public void OpenA (View view){
        startActivity(new Intent(this, ActivityA.class));
    }



    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Integer deletedRows = myDb.deleteData(editId.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void UpdateData(){
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = myDb.updateData(editId.getText().toString(),
                                editTitle.getText().toString(), editDescription.getText().toString(),
                                editKilometers.getText().toString(), editDenivelation.getText().toString());
                                //editDate.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                            else
                            Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                     boolean isInserted = myDb.InsertData(editTitle.getText().toString(),
                                editDescription.getText().toString(),
                                editKilometers.getText().toString(),
                                editDenivelation.getText().toString());
                             //editDate.getText().toString());
                     if (isInserted == true)
                         Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                     else
                         Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       Cursor res = myDb.getAllData();
                       if (res.getCount() == 0){
                           //show message
                           showMessage("Error", "No data found");
                           return;
                       }

                       StringBuffer buffer = new StringBuffer();
                       while (res.moveToNext()){
                        buffer.append("Id: " + res.getString(0) + "\n");
                           buffer.append("Title: " + res.getString(1) + "\n");
                           buffer.append("Description: " + res.getString(2) + "\n");
                           buffer.append("Kilometers: " + res.getString(3) + "\n");
                           buffer.append("Denivelation: " + res.getString(4) + "\n");
                           //buffer.append("Date: " + res.getString(5) + "\n\n");
                       }

                       //show all data
                        showMessage("Data", buffer.toString());
                    }
                }
        );
    }

public void showMessage(String title, String Message){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setCancelable(true);
    builder.setTitle(title);
    builder.setMessage(Message);
    builder.show();
}

}
