package com.example.laz.appoo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.laz.appoo.DAO.StudentDAO;
import com.example.laz.appoo.Tasks.SincServerTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnCreate;
    private ArrayAdapter<Student> adapter;
    private ListView lvStudents;
    private ArrayList<Student> students;
    private Button btnSinc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.my_action_bar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CreateStudentActivity.class));
            }
        });

        btnSinc = (Button) findViewById(R.id.btnSinc);
        btnSinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(verifyConection()){
                    Log.v("INTERNET", "LIGADA");
                    SincServerTask task = new SincServerTask("http://172.29.6.189/PHP/web-oo/api/new-student", students);
                    task.execute("http://127.0.0.1/PHP/web-oo/new-student");
                    StudentDAO dao = new StudentDAO(getApplicationContext());
                    dao.deleteAll();
                    finish();
                    startActivity(getIntent());
                }else{
                    Log.v("INTERNET", "DESLIGADA");
                    Toast.makeText(getApplicationContext(),"Sem conexão", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.updateView();

        lvStudents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = new Student();

                student = adapter.getItem(position);
                return false;
            }
        });
        registerForContextMenu(lvStudents);
    }

    
    @Override
    protected void onResume() {
        super.onResume();

        this.updateView();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        this.updateView();
    }

    public void updateView(){
        lvStudents = (ListView) findViewById(R.id.lvStudents);
        StudentDAO studentDAO = new StudentDAO(this);
        students = studentDAO.select();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,students);
        lvStudents.setAdapter(adapter);
    }

    public  boolean verifyConection() {
        boolean connected;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            connected = true;
        } else {
            connected = false;
        }
        return connected;
    }

}
