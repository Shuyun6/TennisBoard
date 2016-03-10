package com.example.shuyun.tennisboard;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import Data.AddMatchFragment;
import Data.AddPlayerFragment;
import MenuItem.PlayerFragment;

public class MainActivity extends Activity {

    TopBar topBar;
    TopMenu topMenu;
    AddView addView;
    android.app.FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private boolean isAddButtonOpen;
    private boolean isPlayerOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topBar = (TopBar) findViewById(R.id.id_topbar);
        topBar.setOnTopBarClickListener(new TopBar.topBarClickListener() {
            @Override
            public void rightClick() {
                AddButton();
            }

            @Override
            public void leftClick() {
                menuOpen(topMenu);
            }
        });

        topMenu = (TopMenu) findViewById(R.id.topmenu);
        topMenu.setVisibility(View.GONE);
        topMenu.setOnTopBarClickListener(new TopMenu.topMenuClickListener() {
            @Override
            public void menuClick() {
                menuClose(topMenu);
            }
        });

        addView = (AddView) findViewById(R.id.addview);
        addView.setVisibility(View.GONE);
        addView.setOnAddViewClickListener(new AddView.onAddViewClickListener() {
            @Override
            public void firstItem() {
                AddButton();
                openAddPlayerFragment();
            }

            @Override
            public void secondItem() {
                AddButton();
                openAddMatchFragment();
            }
        });

    }

    public void openPlayerData(View view) {
        isPlayerOpen = true;
        /**
         * Use isPlayerOpen to detect if is the state that close menu firstly and then show player fragment for menuClose()
         * */
        menuClose(topMenu);

    }

    public void openAddPlayerFragment() {
        /**
         * fragment
         * */
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AddPlayerFragment addPlayerFragment = new AddPlayerFragment();
        fragmentTransaction.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_left);
        fragmentTransaction.replace(android.R.id.content, addPlayerFragment, "");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void openAddMatchFragment(){
        /**
         * fragment
         * */
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AddMatchFragment addMatchFragment = new AddMatchFragment();
        fragmentTransaction.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_left);
        fragmentTransaction.replace(android.R.id.content, addMatchFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void menuOpen(View view) {

        if (isAddButtonOpen == true) {  //if the addbutton is open and close it firstly then open top menu
            AddButton();
        }
        topMenu.setVisibility(View.VISIBLE);
        topMenu.setClickable(true);
        RotateAnimation rotateAnimation = new RotateAnimation(-90, 0, (float) (topMenu.menuImageViewMarginLeft +
                0.5 * topMenu.menuImageViewWidth), (float) (topMenu.menuImageViewMarginTop + 0.5 * topMenu.menuImageViewHeight));
        rotateAnimation.setDuration(625);
        rotateAnimation.setFillAfter(false);
        rotateAnimation.setInterpolator(new CustomizedInterpolator());
        view.startAnimation(rotateAnimation);
    }

    public void menuClose(View view) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, -90, (float) (topMenu.menuImageViewMarginLeft +
                0.5 * topMenu.menuImageViewWidth), (float) (topMenu.menuImageViewMarginTop + 0.5 * topMenu.menuImageViewHeight));
        rotateAnimation.setDuration(625);
        rotateAnimation.setFillAfter(false);
        rotateAnimation.setInterpolator(new CustomizedInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (isPlayerOpen == true) {
                    /**
                     * fragment
                     * */
                    android.app.FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    PlayerFragment playerFragment = new PlayerFragment();
                    fragmentTransaction.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_left);
                    fragmentTransaction.replace(android.R.id.content, playerFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                isPlayerOpen = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        topMenu.startAnimation(rotateAnimation);
        topMenu.setVisibility(View.GONE);
    }

    /**
     * Show AddButton animation
     * */
    public void AddButton() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, (float) (topBar.rightImageView.getPivotX() + 0.5 * topBar.rightImageViewWidth),
                (float) (topBar.rightImageView.getPivotY() + 0.5 * topBar.rightImageViewHeight));
        rotateAnimation.setDuration(624);
        rotateAnimation.setFillAfter(false);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        topBar.rightImageView.startAnimation(rotateAnimation);
        /**
         * show visible
         * */
        TranslateAnimation translateAnimationOpen1 = new TranslateAnimation(addView.getPivotX(), addView.getPivotX(),
                -addView.addViewHeight - addView.textView1MarginTop, 0);
        translateAnimationOpen1.setDuration(312);
        translateAnimationOpen1.setFillAfter(false);
        translateAnimationOpen1.setInterpolator(new DecelerateInterpolator());

        TranslateAnimation translateAnimationOpen2 = new TranslateAnimation(addView.getPivotX(), addView.getPivotX(),
                -addView.addViewHeight - addView.textView2MarginTop, 0);
        translateAnimationOpen2.setDuration(624);
        translateAnimationOpen2.setFillAfter(false);
        translateAnimationOpen2.setInterpolator(new DecelerateInterpolator());
        /**
         * textView1 running away with textView2
         * */
        final TranslateAnimation translateAnimationClose1 = new TranslateAnimation(addView.getPivotX(), addView.getPivotX(),
                0, -addView.addViewHeight - addView.textView1MarginTop);
        translateAnimationClose1.setDuration(312);
        translateAnimationClose1.setFillAfter(false);
        translateAnimationClose1.setInterpolator(new DecelerateInterpolator(2));

        /**
         * textView2 running away with textView1
         * */
        final TranslateAnimation translateAnimationClose11 = new TranslateAnimation(addView.getPivotX(), addView.getPivotX(),
                -addView.textView2MarginTop + addView.textView1MarginTop, -addView.addViewHeight - addView.textView2MarginTop);
        translateAnimationClose11.setDuration(312);
        translateAnimationClose11.setFillAfter(false);
        translateAnimationClose11.setInterpolator(new DecelerateInterpolator(2));
        translateAnimationClose11.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                addView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        /**
         * textView2 move textView1's position firstly
         * */
        TranslateAnimation translateAnimationClose2 = new TranslateAnimation(addView.getPivotX(), addView.getPivotX(),
                0, -addView.textView2MarginTop + addView.textView1MarginTop);
        translateAnimationClose2.setDuration(312);
        translateAnimationClose2.setFillAfter(false);
        translateAnimationClose2.setInterpolator(new AccelerateInterpolator());
        translateAnimationClose2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                addView.textView1.startAnimation(translateAnimationClose1);
                addView.textView2.startAnimation(translateAnimationClose11);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        /**
         * different states depending on if the AddButton is opening
         * */
        if (isAddButtonOpen == true) {
            isAddButtonOpen = false;
            addView.textView1.clearAnimation();
            addView.textView2.clearAnimation();
            addView.textView2.startAnimation(translateAnimationClose2);
        } else {
            isAddButtonOpen = true;
            addView.textView1.clearAnimation();
            addView.textView2.clearAnimation();
            addView.textView1.startAnimation(translateAnimationOpen1);
            addView.textView2.startAnimation(translateAnimationOpen2);
            addView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Fragment fragment = getFragmentManager().findFragmentByTag("");   //fragment tagged by ""
        fragment.onActivityResult(requestCode, resultCode, intent);     //put the results to fragment ""
    }

    public void finish(View view) {
        finish();
    }

}
