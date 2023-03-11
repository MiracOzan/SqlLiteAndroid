package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText EditStudentsNumber;
    EditText EditStudentsName;
    EditText EditStudentsSurName;
    Button AddInsert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.textView);
        EditStudentsNumber = (EditText) findViewById(R.id.StudentsNumberText);
        EditStudentsName = (EditText) findViewById(R.id.StudentsNameTextBox);
        EditStudentsSurName = (EditText) findViewById(R.id.StudentsSurNameTextBox);
        AddInsert = (Button) findViewById(R.id.AddInsertButton);


        try {
            SQLiteDatabase database = this.openOrCreateDatabase(
                    "students",
                    MODE_PRIVATE,
                    null);



            AddInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    database.execSQL("INSERT INTO Students(Number,Name,SurName) VALUES ('"
                            + Integer.parseInt(EditStudentsNumber.getText().toString()) + "' , '"
                            + EditStudentsName.getText().toString() + "','"
                            + EditStudentsSurName.getText().toString() + "')");

                    EditStudentsNumber.setText("");
                    EditStudentsName.setText("");
                    EditStudentsSurName.setText("");

                    Cursor cursor = database.rawQuery("SELECT * FROM Students ORDER BY Number DESC",null);
                    int StudentsNumber  = cursor.getColumnIndex("Number");
                    int StudentsName = cursor.getColumnIndex("Name");
                    int StudentsSurName = cursor.getColumnIndex("SurName");
                    
                    while (cursor.moveToNext()){
                        textView.append(cursor.getInt(StudentsNumber) + "\t" + cursor.getString(StudentsName) + "\t" + cursor.getString(StudentsSurName)+"\n");
                    }
                    cursor.close();

                }

            });

        } catch (Exception eX1){
            eX1.printStackTrace();
        }






    }
}