package com.example.shuyun.tennisboard;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import CustomizedView.MatchBoardView;
import CustomizedView.SelectPointsButtonView;

/**
 * Created by Shuyun on 3/13/2016/013.
 */
public class MatchActivity extends Activity {

    private String name1, name2, sex1, sex2, bitmapString1, bitmapString2, set, game, ad;
    private MatchBoardView matchBoardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchacitivty_layout);

        Bundle bundle=getIntent().getExtras();
        name1=bundle.getString("name1");
        name2=bundle.getString("name2");
        sex1=bundle.getString("sex1");
        sex2=bundle.getString("sex2");
        bitmapString1=bundle.getString("bitmapString1");
        bitmapString2=bundle.getString("bitmapString2");
        set=bundle.getString("set");
        game=bundle.getString("game");
        ad=bundle.getString("ad");

        matchBoardView= (MatchBoardView) findViewById(R.id.matchboardview);
        matchBoardView.putPlayerData(name1, sex1, name2, sex2, bitmapString1, bitmapString2);
        matchBoardView.putMatchData(set, game, ad);

    }

    @Override
    protected void onResume() {
        if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }
}
