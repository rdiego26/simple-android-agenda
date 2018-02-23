package diegoramos.me.agenda.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import diegoramos.me.agenda.FormActivity;
import diegoramos.me.agenda.R;
import diegoramos.me.agenda.models.Student;

public class FormHelper {

    private final EditText studentName;
    private final EditText studentTelephone;
    private final EditText studentAddress;
    private final EditText studentSite;
    private final RatingBar studentRate;
    private final ImageView studentPhoto;

    private Student student;

    public FormHelper(FormActivity formActivity) {
        studentName = formActivity.findViewById(R.id.form_student_name);
        studentTelephone = formActivity.findViewById(R.id.form_student_telephone);
        studentAddress = formActivity.findViewById(R.id.form_student_address);
        studentSite = formActivity.findViewById(R.id.form_student_site);
        studentRate = formActivity.findViewById(R.id.form_student_rate);
        studentPhoto = formActivity.findViewById(R.id.form_image);
        student = new Student();
    }

    public void setFormValues(Student student) {
        studentName.setText(student.getName());
        studentTelephone.setText(student.getTelephone());
        studentAddress.setText(student.getAddress());
        studentSite.setText(student.getSite());
        studentRate.setProgress(Double.valueOf(student.getGrade()).intValue());
        this.student = student;
    }

    public Student getStudent() {
        return new Student(
          this.student.getId(),
          studentName.getText().toString(),
          studentTelephone.getText().toString(),
          studentAddress.getText().toString(),
          studentSite.getText().toString(),
          studentRate.getProgress(),
          studentPhoto.getTag().toString()
        );
    }

    public void loadImage(String photoPath) {
        if (photoPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
            Bitmap reducedBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true);

            studentPhoto.setImageBitmap(reducedBitmap);
            studentPhoto.setScaleType(ImageView.ScaleType.FIT_XY);

            studentPhoto.setTag(photoPath);
        }

    }
}
