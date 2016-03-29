package CustomizedView;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shuyun.tennisboard.DensityUtil;
import com.example.shuyun.tennisboard.R;

import Data.Match;

/**
 * Created by Shuyun on 3/25/2016/025.
 */
public class MatchDataShowView extends RelativeLayout {

    private LayoutInflater layoutInflater;
    private LayoutParams layoutParams;
    private View view;
    private TextView set1, set2, set3, set4, set5, all;
    private TextView set1Score, set2Score, set3Score, set4Score, set5Score;
    private TextView name1, aces1, doubleFault1, firstServe1, secondServe1, servePoint1, breakPoint1,serviceGame1, 
            firstServeWinner1, secondServeWinner1, netPoint1, leftNet1, highNet1, rightNet1, basePoint1, leftBase1, highBase1, rightBase1;
    private TextView name2, aces2, doubleFault2, firstServe2, secondServe2, servePoint2, breakPoint2,serviceGame2,
            firstServeWinner2, secondServeWinner2, netPoint2, leftNet2, highNet2, rightNet2, basePoint2, leftBase2, highBase2, rightBase2;
    public Button exitButton;
    private ImageView exitImageView;
    private LayoutParams exitbuttonParams, exitImageViewParams;
    private Match match;
    public Activity activity;

    public void setMatch(Match match){
        this.match=match;
        set1Score.setText(match.set1[0]+((match.set1[1]>=match.set1[3])?"":("("+String.valueOf(match.set1[1])+")"))
                +"-"+match.set1[2]+((match.set1[1]<=match.set1[3])?"":("("+String.valueOf(match.set1[3])+")")));
        set2Score.setText(match.set2[0]+((match.set2[1]>=match.set2[3])?"":("("+String.valueOf(match.set2[1])+")"))
                +"-"+match.set2[2]+((match.set2[1]<=match.set2[3])?"":("("+String.valueOf(match.set2[3])+")")));
        set3Score.setText(match.set3[0]+((match.set3[1]>=match.set3[3])?"":("("+String.valueOf(match.set3[1])+")"))
                +"-"+match.set3[2]+((match.set3[1]<=match.set3[3])?"":("("+String.valueOf(match.set3[3])+")")));
        set4Score.setText(match.set4[0]+((match.set4[1]>=match.set4[3])?"":("("+String.valueOf(match.set4[1])+")"))
                +"-"+match.set4[2]+((match.set4[1]<=match.set4[3])?"":("("+String.valueOf(match.set4[3])+")")));
        set5Score.setText(match.set5[0]+((match.set5[1]>=match.set5[3])?"":("("+String.valueOf(match.set5[1])+")"))
                +"-"+match.set5[2]+((match.set5[1]<=match.set5[3])?"":("("+String.valueOf(match.set5[3])+")")));
    }
    
    private void setData(int set){        
        name1.setText(match.player1.name);
        aces1.setText(String.valueOf(match.ace1[set]));
        doubleFault1.setText(String.valueOf(match.doubleFault1[set]));
        firstServe1.setText((match.serveCount1[set] != 0) ? String.valueOf(1 - match.firstServeFault1[set] / match.serveCount1[set]) : "0");
        secondServe1.setText((match.serveCount1[set] != 0) ? String.valueOf(1 - match.doubleFault1[set] / match.serveCount1[set]) : "0");
        servePoint1.setText(match.servePointWin1[set]+"/"+(match.servePoints1[set]));
        breakPoint1.setText(match.breakPointWin1[set]+"/"+(match.breakPoints1[set]));
        serviceGame1.setText(String.valueOf(match.serveCount1[set]));
        firstServeWinner1.setText(String.valueOf(match.firstServeWin1[set]));
        secondServeWinner1.setText(String.valueOf(match.secondServeWin1[set]));
        netPoint1.setText("(W/L) "+(match.leftNetWin1[set]+match.highNetWin1[set]+match.rightNetWin1[set])
                +"/"+(match.leftNetFault1[set]+match.highNetFault1[set]+match.rightNetFault1[set]));
        leftNet1.setText("(W/L) "+match.leftNetWin1[set]+"/"+match.leftNetFault1[set]);
        highNet1.setText("(W/L) "+match.highNetWin1[set]+"/"+match.highNetFault1[set]);
        rightNet1.setText("(W/L) "+match.rightNetWin1[set]+"/"+match.rightNetFault1[set]);
        basePoint1.setText("(W/L) "+(match.leftBaseWin1[set]+match.highBaseWin1[set]+match.rightBaseWin1[set])
                +"/"+(match.leftBaseFault1[set]+match.highBaseFault1[set]+match.rightBaseFault1[set]));
        leftBase1.setText("(W/L) "+match.leftBaseWin1[set]+"/"+match.leftBaseFault1[set]);
        highBase1.setText("(W/L) "+match.highBaseWin1[set]+"/"+match.highBaseFault1[set]);
        rightBase1.setText("(W/L) "+match.rightBaseWin1[set]+"/"+match.rightBaseFault1[set]);

        name2.setText(match.player2.name);
        aces2.setText(String.valueOf(match.ace2[set]));
        doubleFault2.setText(String.valueOf(match.doubleFault2[set]));
        firstServe2.setText((match.serveCount2[set] != 0) ? String.valueOf(1 - match.firstServeFault2[set] / match.serveCount2[set]) : "0");
        secondServe2.setText((match.serveCount2[set] != 0) ? String.valueOf(1 - match.doubleFault2[set] / match.serveCount2[set]) : "0");
        servePoint2.setText(match.servePointWin2[set]+"/"+(match.servePoints2[set]));
        breakPoint2.setText(match.breakPointWin2[set]+"/"+(match.breakPoints2[set]));
        serviceGame2.setText(String.valueOf(match.serveCount2[set]));
        firstServeWinner2.setText(String.valueOf(match.firstServeWin2[set]));
        secondServeWinner2.setText(String.valueOf(match.secondServeWin2[set]));
        netPoint2.setText("(W/L) "+(match.leftNetWin2[set]+match.highNetWin2[set]+match.rightNetWin2[set])
                +"/"+(match.leftNetFault2[set]+match.highNetFault2[set]+match.rightNetFault2[set]));
        leftNet2.setText("(W/L) "+match.leftNetWin2[set]+"/"+match.leftNetFault2[set]);
        highNet2.setText("(W/L) "+match.highNetWin2[set]+"/"+match.highNetFault2[set]);
        rightNet2.setText("(W/L) "+match.rightNetWin2[set]+"/"+match.rightNetFault2[set]);
        basePoint2.setText("(W/L) "+(match.leftBaseWin2[set]+match.highBaseWin2[set]+match.rightBaseWin2[set])
                +"/"+(match.leftBaseFault2[set]+match.highBaseFault2[set]+match.rightBaseFault2[set]));
        leftBase2.setText("(W/L) "+match.leftBaseWin2[set]+"/"+match.leftBaseFault2[set]);
        highBase2.setText("(W/L) "+match.highBaseWin2[set]+"/"+match.highBaseFault2[set]);
        rightBase2.setText("(W/L) "+match.rightBaseWin2[set]+"/"+match.rightBaseFault2[set]);

    }

    public MatchDataShowView(final Context context) {
        super(context);

        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.matchdatashowview_layout, null);
        layoutParams=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(view, layoutParams);

        exitButton=new Button(context);
        exitButton.setBackgroundColor(getResources().getColor(R.color.colorBlueGrey));
        exitbuttonParams=new LayoutParams(DensityUtil.dip2px(context, 48), DensityUtil.dip2px(context, 48));
        exitbuttonParams.setMargins(DensityUtil.dip2px(context, 4), DensityUtil.dip2px(context, 4), 0, 0);
        addView(exitButton, exitbuttonParams);

        exitButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    exitButton.setBackgroundColor(getResources().getColor(R.color.colorLightBlueGrey));
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    exitButton.setBackgroundColor(getResources().getColor(R.color.colorBlueGrey));
                    activity.finish();
                }
                return false;
            }
        });

        exitImageView=new ImageView(context);
        exitImageView.setBackground(getResources().getDrawable(R.drawable.ic_leftarrow));
        exitImageViewParams=new LayoutParams(DensityUtil.dip2px(context, 24), DensityUtil.dip2px(context, 24));
        exitImageViewParams.setMargins(DensityUtil.dip2px(context, 16), DensityUtil.dip2px(context, 16), 0, 0);
        addView(exitImageView, exitImageViewParams);
        
        set1= (TextView) view.findViewById(R.id.set1);
        set2= (TextView) view.findViewById(R.id.set2);
        set3= (TextView) view.findViewById(R.id.set3);
        set4= (TextView) view.findViewById(R.id.set4);
        set5= (TextView) view.findViewById(R.id.set5);
        all= (TextView) view.findViewById(R.id.all);

        set1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(0);
            }
        });

        set2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(1);
            }
        });

        set3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(2);
            }
        });

        set4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(3);
            }
        });

        set5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setData(4);
            }
        });

        set1Score= (TextView) view.findViewById(R.id.set1score);
        set2Score= (TextView) view.findViewById(R.id.set2score);
        set3Score= (TextView) view.findViewById(R.id.set3score);
        set4Score= (TextView) view.findViewById(R.id.set4score);
        set5Score= (TextView) view.findViewById(R.id.set5score);
        
        name1= (TextView) view.findViewById(R.id.name1);
        aces1= (TextView) view.findViewById(R.id.aces1);
        doubleFault1= (TextView) view.findViewById(R.id.doublefault1);
        firstServe1= (TextView) view.findViewById(R.id.firstserve1);
        secondServe1= (TextView) view.findViewById(R.id.secondserve1);
        servePoint1= (TextView) view.findViewById(R.id.servepoint1);
        breakPoint1= (TextView) view.findViewById(R.id.breakpoint1);
        serviceGame1= (TextView) view.findViewById(R.id.servicegames1);
        firstServeWinner1= (TextView) view.findViewById(R.id.firstservewinner1);
        secondServeWinner1= (TextView) view.findViewById(R.id.secondservewinner1);
        netPoint1= (TextView) view.findViewById(R.id.netpoint1);
        leftNet1= (TextView) view.findViewById(R.id.leftnet1);
        highNet1= (TextView) view.findViewById(R.id.highnet1);
        rightNet1= (TextView) view.findViewById(R.id.rightnet1);
        basePoint1= (TextView) view.findViewById(R.id.basepoint1);
        leftBase1= (TextView) view.findViewById(R.id.leftbase1);
        highBase1= (TextView) view.findViewById(R.id.highbase1);
        rightBase1= (TextView) view.findViewById(R.id.rightbase1);

        name2= (TextView) view.findViewById(R.id.name2);
        aces2= (TextView) view.findViewById(R.id.aces2);
        doubleFault2= (TextView) view.findViewById(R.id.doublefault2);
        firstServe2= (TextView) view.findViewById(R.id.firstserve2);
        secondServe2= (TextView) view.findViewById(R.id.secondserve2);
        servePoint2= (TextView) view.findViewById(R.id.servepoint2);
        breakPoint2= (TextView) view.findViewById(R.id.breakpoint2);
        serviceGame2= (TextView) view.findViewById(R.id.servicegames2);
        firstServeWinner2= (TextView) view.findViewById(R.id.firstservewinner2);
        secondServeWinner2= (TextView) view.findViewById(R.id.secondservewinner2);
        netPoint2= (TextView) view.findViewById(R.id.netpoint2);
        leftNet2= (TextView) view.findViewById(R.id.leftnet2);
        highNet2= (TextView) view.findViewById(R.id.highnet2);
        rightNet2= (TextView) view.findViewById(R.id.rightnet2);
        basePoint2= (TextView) view.findViewById(R.id.basepoint2);
        leftBase2= (TextView) view.findViewById(R.id.leftbase2);
        highBase2= (TextView) view.findViewById(R.id.highbase2);
        rightBase2= (TextView) view.findViewById(R.id.rightbase2);

    }

}
