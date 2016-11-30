package com.example.laz.appoo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laz.appoo.DAO.StudentDAO;

public class CreateStudentActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnSave;
    private EditText edName;
    private EditText edEmail;
    private EditText edTag;
    private EditText edPhone;
    private EditText edCourse;
    private EditText edSchool;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);

        toolbar = (Toolbar) findViewById(R.id.my_action_bar);
        setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        edName = (EditText) findViewById(R.id.edName);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edTag = (EditText) findViewById(R.id.edTag);
        edPhone = (EditText) findViewById(R.id.edPhone);
        edCourse = (EditText) findViewById(R.id.edCourse);
        edSchool = (EditText) findViewById(R.id.edSchool);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setName(String.valueOf(edName.getText()));
                student.setEmail(String.valueOf(edEmail.getText()));
                student.setTag(String.valueOf(edTag.getText()));
                student.setPhone(String.valueOf(edPhone.getText()));
                student.setCourse(String.valueOf(edCourse.getText()));
                student.setSchool(String.valueOf(edSchool.getText()));

                StudentDAO dao = new StudentDAO(getApplicationContext());
                dao.insert(student);

                finish();
            }
        });
    }
}
