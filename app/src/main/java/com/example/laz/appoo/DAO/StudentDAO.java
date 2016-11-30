package com.example.laz.appoo.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.example.laz.appoo.BD.DBHelper;
import com.example.laz.appoo.MainActivity;
import com.example.laz.appoo.Student;

import java.util.ArrayList;

/**
 * Created by laz on 30/11/16.
 */

public class StudentDAO {
    private DBHelper helper;
    private Context ctx;

    public StudentDAO(Context ctx){
        helper = new DBHelper(ctx);
        this.ctx = ctx;

    }

    public boolean insert(Student student){
        ContentValues cv = new ContentValues();
        cv.put("name", student.getName());
        cv.put("email", student.getEmail());
        cv.put("course", student.getCourse());
        cv.put("phone", student.getPhone());
        cv.put("tag", student.getPhone());
        cv.put("id_user", student.getId_user());

        this.helper.getWritableDatabase().insert("students", null, cv);
        this.helper.close();
        return true;
    }

    public ArrayList<Student> select(){
        String sql = "SELECT * FROM students;";
        Cursor c = helper.getReadableDatabase().rawQuery(sql,null);
        ArrayList<Student> students = new ArrayList<>();

        while(c.moveToNext()){
            Student student = new Student();

            student.setId(c.getInt(c.getColumnIndex("id")));
            student.setName(c.getString(c.getColumnIndex("name")));
            student.setEmail(c.getString(c.getColumnIndex("email")));
            student.setCourse(c.getString(c.getColumnIndex("course")));
            student.setPhone(c.getString(c.getColumnIndex("phone")));
            student.setTag(c.getString(c.getColumnIndex("tag")));

            students.add(student);
        }
        helper.close();
        return students;
    }

    public Boolean deleteAll(){
        return helper.getWritableDatabase().delete("students","id > 0",null) > 0;
    }
}
