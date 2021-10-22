package com.example.sqltraining;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etContact, etBirth;
    Button btnInsert, btnUpdate, btnDelete, btnShow;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_name);
        etContact = findViewById(R.id.et_contact);
        etBirth = findViewById(R.id.et_birth);

        btnInsert = findViewById(R.id.btn_insert);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        btnShow = findViewById(R.id.btn_show_data);

        DB = new DBHelper(this);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String contact = etContact.getText().toString();
                String birth = etBirth.getText().toString();

                Boolean checkingInsertData = DB.insertUserData(name,contact,birth);
                if (checkingInsertData == true){
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String contact = etContact.getText().toString();
                String birth = etBirth.getText().toString();

                Boolean checkingUpdateData = DB.updateUserData(name,contact,birth);
                if (checkingUpdateData == true){
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "Entry Not Updated", Toast.LENGTH_LONG).show();
                }
            }
        });





        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();

                Boolean checkingDeleteData = DB.deleteUserData(name);
                if (checkingDeleteData == true){
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });



        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getUserData();

                if (res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Name: "+ res.getString(0)+"\n");
                    buffer.append("Contact: "+ res.getString(1)+"\n");
                    buffer.append("Birth: "+ res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}