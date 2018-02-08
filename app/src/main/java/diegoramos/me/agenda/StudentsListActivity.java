package diegoramos.me.agenda;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
        handleVisitSiteMenu(visitSiteMenuItem, student);

        final MenuItem callMenuItem = menu.add("Ligar");
        callMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(ActivityCompat.checkSelfPermission(StudentsListActivity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    final String[] permissions = { Manifest.permission.CALL_PHONE };

                    ActivityCompat.requestPermissions(StudentsListActivity.this,
                            permissions, 123);
                } else {
                    handleCallMenu(callMenuItem, student);
                }

                return false;
            }
        });


        MenuItem sendSMSMenuItem = menu.add("Enviar SMS");
        handleSendSMSMenu(sendSMSMenuItem, student);

        MenuItem visitAddressMenuItem = menu.add("Ir para o endereço");
        handleMapMenu(visitAddressMenuItem, student);

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

    private void handleCallMenu(MenuItem menuItem, Student student) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        String phone = student.getTelephone();
        Uri uri = Uri.parse("tel:" + phone);

        intent.setData(uri);
        menuItem.setIntent(intent);
    }

    private void handleMapMenu(MenuItem menuItem, Student student) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String address = student.getAddress();
        Uri uri = Uri.parse("geo:0,0?q=" + address);

        intent.setData(uri);
        menuItem.setIntent(intent);
    }

    private void handleSendSMSMenu(MenuItem menuItem, Student student) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String phone = student.getTelephone();
        Uri uri = Uri.parse("sms:/" + phone);

        intent.setData(uri);
        menuItem.setIntent(intent);
    }

    private void handleVisitSiteMenu(MenuItem visitSiteMenuItem, Student student) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String site = student.getSite();

        if(!site.startsWith("http://")) {
            site = "http://" + site;
        }

        intent.setData(Uri.parse(site));
        visitSiteMenuItem.setIntent(intent);
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
