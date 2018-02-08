package diegoramos.me.agenda.helpers;

import android.widget.EditText;
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
    private Student student;

    public FormHelper(FormActivity formActivity) {
        studentName = formActivity.findViewById(R.id.form_student_name);
        studentTelephone = formActivity.findViewById(R.id.form_student_telephone);
        studentAddress = formActivity.findViewById(R.id.form_student_address);
        studentSite = formActivity.findViewById(R.id.form_student_site);
        studentRate = formActivity.findViewById(R.id.form_student_rate);
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
          studentRate.getProgress()
        );
    }
}
