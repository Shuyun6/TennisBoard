package MenuItem;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.shuyun.tennisboard.DensityUtil;
import com.example.shuyun.tennisboard.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Data.Database;
import Data.ItemBean;
import Data.PlayerItemAdapter;

/**
 * Created by Shuyun on 2/20/2016/020.
 */

/**
 * Get players' data from database and show them with ListView
 * */

public class PlayerDataView extends RelativeLayout {

    private Database database;
    private Cursor cursor;
    private int count;
    public View v;
    public ListView listView;
    private Context context;
    private List<ItemBean> itemBeanList;
    private PlayerItemAdapter playerItemAdapter;
    private boolean isMale=true;
    private Button sexButtton;
    private LayoutParams sexButtonParams;
    private Point point=new Point();

    public PlayerDataView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;

        WindowManager windowManager=(WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
//        int screenWidth=windowManager.getDefaultDisplay().getWidth();
        int screenHeight=windowManager.getDefaultDisplay().getHeight();

        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v=layoutInflater.inflate(R.layout.playerdataview_layout, null); //Include ListView
        addView(v);

        sexButtton=new Button(context);
        sexButtton.setBackground(getResources().getDrawable(R.drawable.bluecircle));
        sexButtton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMale==true){
                    isMale=false;
                    sexButtton.setBackground(getResources().getDrawable(R.drawable.pinkcircle));
                    getData();
                }else if(isMale==false){
                    isMale=true;
                    sexButtton.setBackground(getResources().getDrawable(R.drawable.bluecircle));
                    getData();
                }
            }
        });

        sexButtonParams=new RelativeLayout.LayoutParams(DensityUtil.dip2px(context, 64), DensityUtil.dip2px(context, 64));
        sexButtonParams.addRule(ALIGN_PARENT_RIGHT);
        sexButtonParams.setMargins(0, screenHeight - DensityUtil.dip2px(context, 168), DensityUtil.dip2px(context, 32), 0);
        addView(sexButtton, sexButtonParams);

        getData();

    }

    public void getData(){
        database=new Database(context.getApplicationContext());
        try {
            database.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(isMale==true) {
            cursor = database.getAllPlayers(1);
        }else if(isMale==false){
            cursor = database.getAllPlayers(2);
        }
        count=cursor.getCount();

        itemBeanList=new ArrayList<>();
        if(cursor!=null){
            cursor.moveToFirst();
            for(int i=0; i<count;i++, cursor.moveToNext()){
                itemBeanList.add(new ItemBean(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(7),
                        cursor.getString(6),
                        cursor.getString(5),
                        cursor.getString(2)
                ));
            }
            listView= (ListView) v.findViewById(R.id.listview);
            playerItemAdapter=new PlayerItemAdapter(context, itemBeanList);
            listView.setAdapter(playerItemAdapter);

        }
        database.close();
    }

    public void open(){

    }

}
