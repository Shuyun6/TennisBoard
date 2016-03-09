package CustomizedView;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shuyun.tennisboard.R;

import java.io.IOException;
import java.sql.SQLException;

import Data.Database;

/**
 * Created by Shuyun on 3/3/2016/003.
 */
public class PlayerInfoShowView extends RelativeLayout {

    private Context context;
    private View view;
    private String nameData, sexData;
    private Database database;
    private Bitmap bitmap;
    private Cursor cursor;
    private ImageView imageView;
    private TextView name, sex, birth, email, phone, height, weight;
    private LayoutParams nameParmas, sexParams, birthParams, emailParams, phoneParams,
            heightParams, weightParams;

    public PlayerInfoShowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initial();
    }

    public PlayerInfoShowView(Context context) {
        super(context);
        this.context = context;
        initial();
    }

    /**
     * Get data to show a view
     */
    public void getData(String namedata, String sexdata) {
        this.name.setText(namedata);
        this.sex.setText(sexdata);

        database = new Database(context);
        try {
            database.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            cursor = database.getPlayer(namedata, sexdata);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cursor.moveToFirst();

        Uri uri = Uri.parse(cursor.getString(0));
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);
        this.height.setText(cursor.getString(7));
        this.weight.setText(cursor.getString(6));
        this.email.setText(cursor.getString(4));
        this.phone.setText(cursor.getString(5));
        this.birth.setText(cursor.getString(3));
        database.close();
    }

    public void initial() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.playerinfoshow_layout, null);
        addView(view);

        imageView = (ImageView) view.findViewById(R.id.imageview);
        name = (TextView) view.findViewById(R.id.dataname);
        sex = (TextView) view.findViewById(R.id.datasex);
        birth = (TextView) view.findViewById(R.id.databirth);
        email = (TextView) view.findViewById(R.id.dataemail);
        phone = (TextView) view.findViewById(R.id.dataphone);
        weight = (TextView) view.findViewById(R.id.dataweight);
        height = (TextView) view.findViewById(R.id.dataheight);

    }

}
