package diegoramos.me.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import diegoramos.me.agenda.dao.StudentDAO;
import diegoramos.me.agenda.helpers.FormHelper;
import diegoramos.me.agenda.models.Student;

public class FormActivity extends AppCompatActivity {

    private FormHelper formHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        formHelper = new FormHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.form_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.form_menu_ok:
                Student student = formHelper.getStudent();
                StudentDAO studentDAO = new StudentDAO(this);

                studentDAO.insert(student);

                Toast.makeText(FormActivity.this, "Clicked!" + student.getName(), Toast.LENGTH_LONG).show();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
