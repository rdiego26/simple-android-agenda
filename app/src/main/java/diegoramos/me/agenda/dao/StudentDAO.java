package diegoramos.me.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import diegoramos.me.agenda.models.Student;

public class StudentDAO extends SQLiteOpenHelper {

    public StudentDAO(Context context) {
        super(context, "Agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE students(id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL, " +
                "telephone TEXT, " +
                "address TEXT, " +
                "site TEXT, " +
                "grade REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS students;";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(Student student) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", student.getName());
        contentValues.put("telephone", student.getTelephone());
        contentValues.put("address", student.getAddress());
        contentValues.put("site", student.getSite());
        contentValues.put("grade", student.getGrade());

        db.insert("students", null, contentValues);

        db.close();
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students ORDER BY name;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Student> students = new ArrayList<>();
        while(cursor.moveToNext()) {
            Student student = new Student(
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("telephone")),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("site")),
                    cursor.getDouble(cursor.getColumnIndex("grade"))
            );
            students.add(student);
        }
        cursor.close();
        db.close();
        return students;
    }
}
