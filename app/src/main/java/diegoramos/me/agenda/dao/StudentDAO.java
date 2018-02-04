package diegoramos.me.agenda.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
