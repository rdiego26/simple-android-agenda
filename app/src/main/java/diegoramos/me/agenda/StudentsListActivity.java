package diegoramos.me.agenda;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import diegoramos.me.agenda.dao.StudentDAO;
import diegoramos.me.agenda.models.Student;

public class StudentsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);
    }

    private void getDataAndHandleToView() {
        StudentDAO studentDAO = new StudentDAO(this);
        List<Student> studentList = studentDAO.getAllStudents();

        ListView studentsListVw = findViewById(R.id.studentsList);
        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this,
                android.R.layout.simple_list_item_1, studentList);
        studentsListVw.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataAndHandleToView();
    }

    public void goToForm(View v) {
        Intent goToFormIntent = new Intent(StudentsListActivity.this,
                FormActivity.class);
        startActivity(goToFormIntent);
    }
}
