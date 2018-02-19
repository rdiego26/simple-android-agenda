package diegoramos.me.agenda;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import diegoramos.me.agenda.dao.StudentDAO;
import diegoramos.me.agenda.helpers.FormHelper;
import diegoramos.me.agenda.models.Student;

public class FormActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 567;

    private FormHelper formHelper;

    private String photoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        formHelper = new FormHelper(this);
        handleUpdateFlow();
        handleCameraButton();

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

                if (student.getId() == null) {
                    studentDAO.insert(student);
                } else {
                    studentDAO.update(student);
                }

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST_CODE) {
            ImageView imageView = findViewById(R.id.form_image);
            Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
            Bitmap reducedBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);

            imageView.setImageBitmap(reducedBitmap);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }

    private void handleCameraButton() {
        Button btnCamera = findViewById(R.id.form_btn_camera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                photoPath = getExternalFilesDir(null) + "/" + System.currentTimeMillis() +  ".jpg";
                File photo = new File(photoPath);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
                startActivityForResult(intent, CAMERA_REQUEST_CODE);
            }
        });
    }

    private void handleUpdateFlow() {
        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("STUDENT");

        if(student != null) {
            formHelper.setFormValues(student);
        }
    }

}
