package diegoramos.me.agenda;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

        attachOnItemClickListener();
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
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Student student = (Student) studentsListVw.getItemAtPosition(info.position);

        MenuItem visitSiteMenuItem = menu.add("Visitar Site");
        listenVisitMenu(visitSiteMenuItem, student);


        MenuItem deleteMenuItem = menu.add("Deletar");
        deleteMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                StudentDAO studentDAO = new StudentDAO(StudentsListActivity.this);
                studentDAO.delete(student);
                getDataAndHandleToView();
                return false;
            }
        });

    }

    private void listenVisitMenu(MenuItem visitSiteMenuItem, Student student) {
        Intent intentSite = new Intent(Intent.ACTION_VIEW);
        String site = student.getSite();

        if(!site.startsWith("http://")) {
            site = "http://" + site;
        }

        intentSite.setData(Uri.parse(site));
        visitSiteMenuItem.setIntent(intentSite);
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

    private void attachOnItemClickListener() {

        studentsListVw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = (Student) studentsListVw.getItemAtPosition(position);
                Intent intentOpenForm = new Intent(StudentsListActivity.this,
                        FormActivity.class);

                intentOpenForm.putExtra("STUDENT", student);
                startActivity(intentOpenForm);
            }
        });

    }
}
