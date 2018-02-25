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
        super(context, "Agenda", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE students(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL, " +
                "telephone TEXT, " +
                "address TEXT, " +
                "site TEXT, " +
                "grade REAL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql;
        // `break` purposely removed
        switch(oldVersion) {
            case 1:
                sql = "ALTER TABLE students ADD COLUMN photoPath TEXT;";
                db.execSQL(sql);
        }

    }

    private ContentValues contentValueFromStudent(Student student) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", student.getName());
        contentValues.put("telephone", student.getTelephone());
        contentValues.put("address", student.getAddress());
        contentValues.put("site", student.getSite());
        contentValues.put("grade", student.getGrade());
        contentValues.put("photoPath", student.getPhotoPath());
        return contentValues;
    }

    public void insert(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = contentValueFromStudent(student);

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
                    cursor.getLong(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("telephone")),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("site")),
                    cursor.getDouble(cursor.getColumnIndex("grade")),
                    cursor.getString(cursor.getColumnIndex("photoPath"))
            );
            students.add(student);
        }
        cursor.close();
        db.close();
        return students;
    }

    public void delete(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {student.getId().toString()};

        db.delete("students", "id = ?", params);
        db.close();
    }

    public void update(Student student) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = contentValueFromStudent(student);
        String[] params = {student.getId().toString()};

        db.update("students", contentValues, "id = ?", params);

    }
}
