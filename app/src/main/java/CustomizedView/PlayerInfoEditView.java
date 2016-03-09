package CustomizedView;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.shuyun.tennisboard.R;

import java.io.IOException;
import java.sql.SQLException;

import Data.Database;

/**
 * Created by Shuyun on 3/6/2016/006.
 */
public class PlayerInfoEditView extends RelativeLayout {
    public ImageView imageView;
    public Bitmap bitmap;
    public EditText editTextName, editTextHeight, editTextWeight, editTextPhone, editTextEmail, editTextSex, editTextBirth;
    public Button finishButton;
    public String sexString;
    public OnPlayerInfoEditViewClickListener listener;
    private Context context;
    private View view;
    private RadioGroup radioGroup;
    private RadioButton radioButtonMale, radioButtonFemale;
    private String name, sex;
    private Database database;
    private Cursor cursor;
    private Uri photoUri = null;

    public PlayerInfoEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initial();
    }

    public PlayerInfoEditView(Context context) {
        super(context);
        this.context = context;
        initial();
    }

    public void setOnPlayerInfoEditViewClickListener(OnPlayerInfoEditViewClickListener listener) {
        this.listener = listener;
    }

    public void getData(String name, String sex) {
        this.name = name;
        this.sex = sex;
        database = new Database(context);
        try {
            database.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            cursor = database.getPlayer(name, sex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cursor.moveToFirst();
        editTextName.setText(cursor.getString(1));
        editTextHeight.setText(cursor.getString(7));
        editTextWeight.setText(cursor.getString(6));
        editTextBirth.setText(cursor.getString(3));
        editTextEmail.setText(cursor.getString(4));
        editTextPhone.setText(cursor.getString(5));

        Uri uri = Uri.parse(cursor.getString(0));
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);
        String s = cursor.getString(2);
        if (s.equals("Male")) {
            radioButtonMale.setChecked(true);
            sexString = "Male";
        } else {
            radioButtonFemale.setChecked(true);
            sexString = "Female";
        }


    }

    public void initial() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.edit_layout, null);
        addView(view);

        radioGroup = (RadioGroup) view.findViewById(R.id.sexRadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) view.findViewById(radioButtonId);
                sexString = radioButton.getText().toString();
                if (sexString.equals("")) {
                    sexString = "Male";
                }
            }
        });
        radioButtonFemale = (RadioButton) findViewById(R.id.setRadioButtonFemale);
        radioButtonMale = (RadioButton) findViewById(R.id.setRadioButtonMale);

        imageView = (ImageView) view.findViewById(R.id.image);
        editTextName = (EditText) view.findViewById(R.id.editname);
        editTextBirth = (EditText) view.findViewById(R.id.editbirth);
        editTextHeight = (EditText) view.findViewById(R.id.editheight);
        editTextWeight = (EditText) view.findViewById(R.id.editweight);
        editTextEmail = (EditText) view.findViewById(R.id.editemail);
        editTextPhone = (EditText) view.findViewById(R.id.editphone);


        finishButton = (Button) findViewById(R.id.finish);
        finishButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.finish();

            }
        });

    }

    public interface OnPlayerInfoEditViewClickListener {
        void finish();
    }
}
