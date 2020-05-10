package com.example.sqlite2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText t1,t2,t3,t4 ;
    Button bn1,bn2,bn3,bn4 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        t1=(EditText) findViewById(R.id.ed1);
        t2=(EditText) findViewById(R.id.ed2);
        t3=(EditText) findViewById(R.id.ed3);
        t4=(EditText)findViewById(R.id.ed4);

        bn1 = (Button)findViewById(R.id.bt1);
        bn2 = (Button)findViewById(R.id.bt2);
        bn3 = (Button)findViewById(R.id.bt3);
        bn4 = (Button) findViewById(R.id.bt4);

        AddData();
        viewAll();
        UpdateData();
        DeleteData();

    }

    public void DeleteData()
    {
        bn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Integer deletedRows = myDb.deleteData(t4.getText().toString());
                if(deletedRows>0)
                {
                    Toast.makeText(MainActivity.this, " Rows Deleted ", Toast.LENGTH_SHORT).show();
                 }

                else
                 {
                    Toast.makeText(MainActivity.this, " Rows not Deleted", Toast.LENGTH_SHORT).show();
                 }
            }
        });
    }


    public void UpdateData()
    {
        bn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  boolean isUpdate = myDb.updateData(t1.getText().toString(),
                          t2.getText().toString()
                          ,t3.getText().toString(),
                          t4.getText().toString());

                  if(isUpdate == true)
                  {
                      Toast.makeText(MainActivity.this, " Data updated ", Toast.LENGTH_SHORT).show();
                  }
                else
                    {
                      Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                  }
            }
        });
    }


    public void AddData(){
        bn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               boolean isInserted = myDb.insertData(t1.getText().toString(),
                       t2.getText().toString(),
                       t3.getText().toString(),t4.getText().toString());
               if(isInserted == true)
                   Toast.makeText(MainActivity.this, " Data inserted ", Toast.LENGTH_SHORT).show();
               else
               {
                   Toast.makeText(MainActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    public void viewAll()
    {
        bn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Cursor res = myDb.getAllData();
               if(res.getCount()==0)
               {
                   //show message
                   showMessage("Error","Nothing found");
                   return;
               }
               else {

                   StringBuffer buffer = new StringBuffer();
                   while (res.moveToNext())
                   {

                       buffer.append("ID -" +"\t " +res.getString(0) +"\n"+"--------------"+"\n");

                       buffer.append("Name -"+ " \t" +res.getString(1) +"\n"+"--------------"+"\n");
                       buffer.append("Surname -" +"\t " +res.getString(2) +"\n"+"--------------"+"\n");
                       buffer.append("Marks -"+ "\t " +res.getString(3) +"\n"+"--------------"+"\n");
                   }
                //show all data
                    showMessage("data",buffer.toString());
               }

            }
        });
    }


        public void showMessage(String title , String Message)

        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(Message);
            builder.show();
        }

}



