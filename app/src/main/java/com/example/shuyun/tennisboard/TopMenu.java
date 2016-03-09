package com.example.shuyun.tennisboard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Shuyun on 2/2/2016/002.
 */
public class TopMenu extends RelativeLayout {

    public Button menuButton;
    public int menuButtonWidth, menuButtonHeight, menuImageViewWidth, menuImageViewHeight,
            menuButtonMarginLeft, menuButtonMarginTop, menuButtonMarginRight, menuButtonMarginBottom,
            menuImageViewMarginLeft, menuImageViewMarginTop, menuImageViewMarginRight, menuImageViewMarginBottom;
    private ImageView menuImageView;
    private Drawable backgroundDrawable;
    private int backgroundColor;
    private int menuButtonColor, menuButtonClickColor;
    private LayoutParams menuButtonParams, menuImageViewParams;

    private LayoutInflater inflater;
    private View view;

    private topMenuClickListener listener;

    public TopMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopMenu);
        backgroundDrawable = typedArray.getDrawable(R.styleable.TopMenu_backgroundDrawable);
        backgroundColor = typedArray.getColor(R.styleable.TopMenu_backgroundColor, 0);

        menuButtonColor = typedArray.getColor(R.styleable.TopMenu_menuButtonColor, 0);
        menuButtonClickColor = typedArray.getColor(R.styleable.TopMenu_menuButtonClickColor, 0);

        menuButtonWidth = (int) typedArray.getDimension(R.styleable.TopMenu_menuButtonWidth, 0);
        menuButtonHeight = (int) typedArray.getDimension(R.styleable.TopMenu_menuButtonHeight, 0);
        menuImageViewWidth = (int) typedArray.getDimension(R.styleable.TopMenu_menuImageViewWidth, 0);
        menuImageViewHeight = (int) typedArray.getDimension(R.styleable.TopMenu_menuImageViewHeight, 0);

        menuButtonMarginLeft = (int) typedArray.getDimension(R.styleable.TopMenu_menuButtonMarginLeft, 0);
        menuButtonMarginTop = (int) typedArray.getDimension(R.styleable.TopMenu_menuButtonMarginTop, 0);
        menuButtonMarginRight = (int) typedArray.getDimension(R.styleable.TopMenu_menuButtonMarginRight, 0);
        menuButtonMarginBottom = (int) typedArray.getDimension(R.styleable.TopMenu_menuButtonMarginBottom, 0);

        menuImageViewMarginLeft = (int) typedArray.getDimension(R.styleable.TopMenu_menuImageViewMarginLeft, 0);
        menuImageViewMarginTop = (int) typedArray.getDimension(R.styleable.TopMenu_menuImageViewMarginTop, 0);
        menuImageViewMarginRight = (int) typedArray.getDimension(R.styleable.TopMenu_menuImageViewMarginRight, 0);
        menuImageViewMarginBottom = (int) typedArray.getDimension(R.styleable.TopMenu_menuImageViewMarginBottom, 0);

        menuButton = new Button(context);
        menuImageView = new ImageView(context);

        setBackgroundColor(backgroundColor);

        menuButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    v.setBackgroundColor(menuButtonClickColor);
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setBackgroundColor(menuButtonColor);
                    listener.menuClick();
                }
                return false;
            }
        });

        menuButton.setBackgroundColor(menuButtonColor);
        menuButtonParams = new LayoutParams(menuButtonWidth, menuButtonHeight);
        menuButtonParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        menuButtonParams.setMargins(menuButtonMarginLeft, menuButtonMarginTop, menuButtonMarginRight, menuButtonMarginBottom);
        addView(menuButton, menuButtonParams);

        menuImageView.setBackground(backgroundDrawable);
        menuImageViewParams = new LayoutParams(menuImageViewWidth, menuImageViewHeight);
        menuImageViewParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        menuImageViewParams.setMargins(menuImageViewMarginLeft, menuImageViewMarginTop, menuImageViewMarginRight, menuImageViewMarginBottom);
        addView(menuImageView, menuImageViewParams);

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.topmenu_layout, null);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        layoutParams.setMargins(0, menuButtonHeight, 0, 0);
        addView(view, layoutParams);

    }

    public void setOnTopBarClickListener(topMenuClickListener listener) {
        this.listener = listener;
    }

    public interface topMenuClickListener {
        void menuClick();
    }

}
