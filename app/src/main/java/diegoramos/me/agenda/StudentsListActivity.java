package diegoramos.me.agenda;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import diegoramos.me.agenda.dao.StudentDAO;
import diegoramos.me.agenda.models.Student;

public class StudentsListActivity extends AppCompatActivity {

    private ListView studentsListVw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        studentsListVw = findViewById(R.id.studentsList);

        registerForContextMenu(studentsListVw);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataAndHandleToView();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem deleteMenuItem = menu.add("Deletar");

        deleteMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Student student = (Student) studentsListVw.getItemAtPosition(info.position);
                StudentDAO studentDAO = new StudentDAO(StudentsListActivity.this);
                studentDAO.delete(student);
                return false;
            }
        });
    }

    private void getDataAndHandleToView() {
        StudentDAO studentDAO = new StudentDAO(this);
        List<Student> studentList = studentDAO.getAllStudents();

        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this,
                android.R.layout.simple_list_item_1, studentList);
        studentsListVw.setAdapter(adapter);

    }

    public void goToForm(View v) {
        Intent goToFormIntent = new Intent(StudentsListActivity.this,
                FormActivity.class);
        startActivity(goToFormIntent);
    }
}
