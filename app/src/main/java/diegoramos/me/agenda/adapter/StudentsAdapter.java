package diegoramos.me.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import diegoramos.me.agenda.R;
import diegoramos.me.agenda.models.Student;

public class StudentsAdapter extends BaseAdapter {

    private final List<Student> students;
    private final Context context;

    public StudentsAdapter(Context ctx, List<Student> studentList) {
        this.students = studentList;
        this.context = ctx;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        Student student = students.get(position);
        View listItemView = convertView;

        // Performance TIP
        if(convertView == null) {
            listItemView =  layoutInflater.inflate(R.layout.list_item, parent, false);
        }

        loadDataToView(student, listItemView);

        return listItemView;
    }

    private void loadDataToView(Student student, View listItemView) {
        TextView nameTextView = listItemView.findViewById(R.id.item_name);
        nameTextView.setText(student.getName());

        TextView phoneTextView = listItemView.findViewById(R.id.item_telephone);
        phoneTextView.setText(student.getTelephone());

        ImageView studentPhoto = listItemView.findViewById(R.id.item_photo);

        String photoPath = student.getPhotoPath();
        if (photoPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
            Bitmap reducedBitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, true);

            studentPhoto.setImageBitmap(reducedBitmap);
            studentPhoto.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }
}
