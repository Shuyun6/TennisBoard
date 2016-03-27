package com.example.shuyun.tennisboard;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import CustomizedView.MatchBoardView;
import CustomizedView.SelectPointsButtonView;
import Data.Match;
import Data.Player;

/**
 * Created by Shuyun on 3/13/2016/013.
 */
public class MatchActivity extends Activity {

    private String name1, name2, sex1, sex2, bitmapString1, bitmapString2, set, game, ad;
    private MatchBoardView matchBoardView;
    private Player player1, player2;
    private static Match match=new Match();
    private static int point1=0, point2=0;
    private int pointFlage=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchacitivty_layout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

        player1=new Player(name1, sex1);
        player2=new Player(name2, sex2);

        matchBoardView= (MatchBoardView) findViewById(R.id.matchboardview);
        matchBoardView.putPlayerData(name1, sex1, name2, sex2, bitmapString1, bitmapString2);
        matchBoardView.putMatchData(set, game, ad);
        matchBoardView.activity=this;
        match.setPlayers(player1, player2);
        matchBoardView.selectPointsButtonView.match=match;

        /** After return from pausing, here is to rebuild the serve points' visible */
        if(savedInstanceState!=null){
            point1=savedInstanceState.getInt("point1");
            point2=savedInstanceState.getInt("point2");
            matchBoardView.tvPoint1.setVisibility((point1==1)?View.VISIBLE:View.GONE);
            matchBoardView.tvPoint2.setVisibility((point2==1)?View.VISIBLE:View.GONE);

        }


    }

    @Override
    protected void onResume() {
        if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        matchBoardView.selectPointsButtonView.getData();

        super.onResume();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        point1=(matchBoardView.tvPoint1.getVisibility()== View.VISIBLE)?1:0;
        point2=(matchBoardView.tvPoint2.getVisibility()== View.VISIBLE)?1:0;
        outState.putInt("point1", point1);
        outState.putInt("point2", point2);

    }
}
