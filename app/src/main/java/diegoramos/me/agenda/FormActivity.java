package diegoramos.me.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }

    public void handleRegister(View v) {
        Toast.makeText(FormActivity.this, "Clicked!", Toast.LENGTH_LONG).show();
        finish();
    }
}
