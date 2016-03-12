package MenuItem;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shuyun.tennisboard.DensityUtil;
import com.example.shuyun.tennisboard.MainActivity;
import com.example.shuyun.tennisboard.R;
import com.example.shuyun.tennisboard.ShowPlayerInfoActivity;

import Data.AddMatchFragment;

/**
 * Created by Shuyun on 2/26/2016/026.
 */

/**
 * The customized view to show items in ListView
 */

public class PlayerDataItemView extends HorizontalScrollView {

    public View view;
    public ImageView imageView;
    public TextView textViewName, textViewHeight, textViewWeight, textViewPhoneNumber, textViewSex;
    public Button buttonEdit, buttonDelete;
    public LayoutInflater layoutInflater;
    public int flage;
    public FragmentManager fragmentManager;
    private Context context;
    private RelativeLayout relativeLayout;
    private boolean isScrolled;
    private RelativeLayout.LayoutParams textViewEditParams, textViewDeleteParams, viewParams;

    private deleteClickListener listener;

    public PlayerDataItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initialized();
    }

    public PlayerDataItemView(Context context) {
        super(context);
        this.context = context;
        initialized();

    }

    public void setOnDeleteClickListener(deleteClickListener listener) {
        this.listener = listener;
    }

    public void initialized() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        int screenHeight = windowManager.getDefaultDisplay().getHeight();

        relativeLayout = new RelativeLayout(context);
        addView(relativeLayout);

        /**
         * Use flage to detect the state which from playerdataview that come for reading data
         * or from addMatchFragment that come for choosing item for selecting player to match
         * */
        relativeLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flage==0) {
                    if (isScrolled == true) {
                        smoothScrollTo(0, 0);
                        isScrolled = false;
                    } else {
                        startShowPlayerInfoActivity();
                    }
                }else if(flage==1){
                    AddMatchFragment addMatchFragment = (AddMatchFragment) fragmentManager.findFragmentByTag("addMatchFragment");
                    addMatchFragment.name1=textViewName.getText().toString();
                    addMatchFragment.sex1=textViewSex.getText().toString();
                    fragmentManager.popBackStack();

                }else if(flage==2){
                    AddMatchFragment addMatchFragment = (AddMatchFragment) fragmentManager.findFragmentByTag("addMatchFragment");
                    addMatchFragment.name2=textViewName.getText().toString();
                    addMatchFragment.sex2=textViewSex.getText().toString();
                    fragmentManager.popBackStack();
                }
            }
        });

        layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.playerdata, null);

        imageView = (ImageView) view.findViewById(R.id.head);
        textViewName = (TextView) view.findViewById(R.id.playername);
        textViewHeight = (TextView) view.findViewById(R.id.height2);
        textViewWeight = (TextView) view.findViewById(R.id.weight2);
        textViewPhoneNumber = (TextView) view.findViewById(R.id.phonenumber);
        textViewSex = (TextView) view.findViewById(R.id.id_sex);

        viewParams = new RelativeLayout.LayoutParams(screenWidth, DensityUtil.dip2px(context, 72));
        viewParams.setMargins(0, 0, 0, 0);
        relativeLayout.addView(view, viewParams);

        buttonEdit = new Button(context);
        buttonDelete = new Button(context);
        setHorizontalScrollBarEnabled(false);

        buttonDelete.setText("Delete");
        buttonDelete.setTextColor(getResources().getColor(R.color.colorTopMenuText));
        buttonDelete.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonDelete.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    buttonDelete.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    closeItem();
                    listener.delete();
                }
                return false;
            }
        });

        buttonEdit.setText("Edit");

        buttonEdit.setTextColor(getResources().getColor(R.color.colorTopMenuText));
        buttonEdit.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    buttonEdit.setBackgroundColor(getResources().getColor(R.color.colorDeepGreen));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    buttonEdit.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                    closeItem();
                    startShowPlayerInfoActivity();
                }
                return false;
            }
        });

        textViewEditParams = new RelativeLayout.LayoutParams(DensityUtil.dip2px(context, 81), DensityUtil.dip2px(context, 72));
        textViewEditParams.setMargins(screenWidth, 0, 0, 0);
        buttonEdit.setBackgroundColor(getResources().getColor(R.color.colorOrange));
        relativeLayout.addView(buttonEdit, textViewEditParams);

        textViewDeleteParams = new RelativeLayout.LayoutParams(DensityUtil.dip2px(context, 81), DensityUtil.dip2px(context, 72));
        textViewDeleteParams.setMargins(screenWidth + DensityUtil.dip2px(context, 81), 0, 0, 0);
        buttonDelete.setBackgroundColor(getResources().getColor(R.color.colorRed));
        relativeLayout.addView(buttonDelete, textViewDeleteParams);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(0, 0);    //keep closed when intialized the view
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if (scrollX >= DensityUtil.dip2px(context, 72)) {
                    this.smoothScrollTo(DensityUtil.dip2px(context, 162), 0);
                    isScrolled = true;
                } else {
                    this.smoothScrollTo(0, 0);
                }
                return true;
        }

        return super.onTouchEvent(ev);
    }

    public void closeItem() {
        this.smoothScrollTo(0, 0);
    }

    public void startShowPlayerInfoActivity() {
        Bundle bundle = new Bundle();
        bundle.putString("name", textViewName.getText().toString());
        bundle.putString("sex", textViewSex.getText().toString());
        Intent intent = new Intent(context, ShowPlayerInfoActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    public interface deleteClickListener {
        void delete();
    }

}
