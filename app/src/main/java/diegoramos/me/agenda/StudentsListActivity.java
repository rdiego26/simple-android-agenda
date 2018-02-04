package diegoramos.me.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class StudentsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_list);

        String[] studentsList = {};
        ListView studentsListVw = findViewById(R.id.studentsList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, studentsList);
        studentsListVw.setAdapter(adapter);

    }

    public void goToForm(View v) {
        Intent goToFormIntent = new Intent(StudentsListActivity.this,
                FormActivity.class);
        startActivity(goToFormIntent);
    }
}
