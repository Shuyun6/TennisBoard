package com.example.shuyun.tennisboard;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Shuyun on 2/4/2016/004.
 */
public class AddView extends RelativeLayout {

    public TextView textView1, textView2;
    public LayoutParams textView1Params, textView2params;
    public int addViewWidth, addViewHeight;
    public int textView1MarginLeft, textView1MarginTop, textView1MarginRight, textView1MarginBottom, textView2MarginLeft, textView2MarginTop, textView2MarginRight, textView2MarginBottom;

    public onAddViewClickListener listener;

    public AddView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AddView);
        addViewWidth = (int) typedArray.getDimension(R.styleable.AddView_addViewWidth, 0);
        addViewHeight = (int) typedArray.getDimension(R.styleable.AddView_addViewHeight, 0);
        textView1MarginLeft = (int) typedArray.getDimension(R.styleable.AddView_textView1MarginLeft, 0);
        textView1MarginTop = (int) typedArray.getDimension(R.styleable.AddView_textView1MarginTop, 0);
        textView1MarginRight = (int) typedArray.getDimension(R.styleable.AddView_textView1MarginRight, 0);
        textView1MarginBottom = (int) typedArray.getDimension(R.styleable.AddView_textView1MarginBottom, 0);

        textView2MarginLeft = (int) typedArray.getDimension(R.styleable.AddView_textView2MarginLeft, 0);
        textView2MarginTop = (int) typedArray.getDimension(R.styleable.AddView_textView2MarginTop, 0);
        textView2MarginRight = (int) typedArray.getDimension(R.styleable.AddView_textView2MarginRight, 0);
        textView2MarginBottom = (int) typedArray.getDimension(R.styleable.AddView_textView2MarginBottom, 0);

        textView1 = new TextView(context);
        textView2 = new TextView(context);

        textView2.setText(R.string.addview2);
        textView2.setTextColor(getResources().getColor(R.color.colorTopMenuText));
        textView2.setClickable(true);
        textView2.setGravity(Gravity.CENTER);
        textView2.setBackground(getResources().getDrawable(R.drawable.shape));
        textView2params = new LayoutParams(addViewWidth, addViewHeight);
        textView2params.addRule(ALIGN_PARENT_RIGHT, TRUE);
        textView2params.setMargins(textView2MarginLeft, textView2MarginTop, textView2MarginRight, textView2MarginBottom);
        addView(textView2, textView2params);

        textView1.setText(R.string.addview1);
        textView1.setTextColor(getResources().getColor(R.color.colorTopMenuText));
        textView1.setClickable(true);
        textView1.setGravity(Gravity.CENTER);
        textView1.setBackgroundColor(getResources().getColor(R.color.colorBlueGrey));
        textView1Params = new LayoutParams(addViewWidth, addViewHeight);
        textView1Params.addRule(ALIGN_PARENT_RIGHT, TRUE);
        textView1Params.setMargins(textView1MarginLeft, textView1MarginTop, textView1MarginRight, textView1MarginBottom);
        addView(textView1, textView1Params);

        textView1.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    v.setBackgroundColor(getResources().getColor(R.color.colorLightBlueGrey));
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setBackgroundColor(getResources().getColor(R.color.colorBlueGrey));
                    listener.firstItem();
                }
                return false;
            }
        });

        textView2.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    v.setBackgroundColor(getResources().getColor(R.color.colorLightBlueGrey));
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setBackgroundColor(getResources().getColor(R.color.colorBlueGrey));
                    textView2.setBackground(getResources().getDrawable(R.drawable.shape));
                    listener.secondItem();
                }
                return false;
            }
        });

    }

    public void setOnAddViewClickListener(onAddViewClickListener listener) {
        this.listener = listener;
    }

    public interface onAddViewClickListener {
        void firstItem();

        void secondItem();
    }
}
