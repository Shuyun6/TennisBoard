package CustomizedView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shuyun.tennisboard.DensityUtil;
import com.example.shuyun.tennisboard.R;

import java.io.IOException;

/**
 * Created by Shuyun on 3/16/2016/016.
 */
public class MatchBoardView extends RelativeLayout {

    public String name1, name2, sex1, sex2, bitmapString1, bitmapString2, set, game, ad;
    private View pic_name1, pic_name2;
    public View scoreBoardView, leftCornerButton, rightCornerButton;
    public SelectPointsButtonView selectPointsButtonView;
    public Button finishButton, startButton;

    private Bitmap bitmap1, bitmap2;
    private LayoutParams pic_name1Params, pic_name2Params, scoreBoardViewParams, leftCornerButtonParams,
            rightCornerButtonParams, selectPointsButtonViewParams, finishButtonParams, startButtonParams;
    private Uri uri1, uri2;
    private Context context;
    public TextView tvScore1, tvScore2, tvName1, tvName2, tvSet11, tvSet12, tvSet21, tvSet22,
            tvSet31, tvSet32, tvSet41, tvSet42, tvSet51, tvSet52, tvPoint1, tvPoint2;

    private ImageView imageView1, imageView2;
    private TextView textViewName1, textViewName2;
    private Button leftWinButton, leftLoseButton, rightWinButton, rightLoseButton;

    private MatchDataShowView matchDataShowView;
    private LayoutParams matchDataShowViewParams;

    private WindowManager windowManager;
    public Activity activity;
    private int width, height;
    public int whichOneServe=0;

    public void putMatchData(String set, String game, String ad){
        this.set=set;
        this.game=game;
        this.ad=ad;
    }

    public void putPlayerData(String name1, String sex1, String name2, String sex2,
                              String bitmapString1, String bitmapString2){
        this.name1=name1;
        this.name2=name2;
        this.sex1=sex1;
        this.sex2=sex2;
        this.bitmapString1=bitmapString1;
        this.bitmapString2=bitmapString2;

        uri1=Uri.parse(bitmapString1);
        uri2=Uri.parse(bitmapString2);
        try {
            bitmap1 = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri1);
            bitmap2 = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView1.setImageBitmap(bitmap1);
        imageView2.setImageBitmap(bitmap2);
        textViewName1.setText(name1);
        textViewName2.setText(name2);

        tvName1.setText(name1);
        tvName2.setText(name2);

    }

    public MatchBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        windowManager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width=windowManager.getDefaultDisplay().getWidth();

        /** Click to disappear the select points button
         * */
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPointsButtonView.setVisibility(GONE);

            }
        });

        /** Add finishButton and startButton
         * */
        finishButton=new Button(context);
        finishButton.setBackground(getResources().getDrawable(R.drawable.buttonshape));
        finishButton.setText("FINISH");
        finishButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPointsButtonView.scoreCalculator.finishMatch();
                matchDataShowView.setVisibility(VISIBLE);
                matchDataShowView.activity=activity;
                matchDataShowView.setMatch(selectPointsButtonView.match);

            }
        });
        startButton=new Button(context);
        startButton.setBackground(getResources().getDrawable(R.drawable.buttonshape));
        startButton.setText("START");
        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setText((startButton.getText().equals("PAUSE"))?"START":"PAUSE");
                if(startButton.getText().equals("PAUSE")){
                    if(tvScore1.getText().equals("")&&tvScore2.getText().equals("")){ // when score text views haven't initialized(the text is empty), give them initial values
                        /** detect which one to serve */
                        whoServeDialog();
                        /** start the game and initial the scores */
                        tvScore1.setText("0");
                        tvScore2.setText("0");
                        tvSet11.setText("0");
                        tvSet12.setText("0");
                    }
                }
            }
        });

        finishButtonParams=new LayoutParams(DensityUtil.dip2px(context, 120), DensityUtil.dip2px(context, 32));
        finishButtonParams.addRule(CENTER_IN_PARENT);
        finishButtonParams.addRule(ALIGN_PARENT_BOTTOM);
        finishButtonParams.setMargins(0, 0, 0, DensityUtil.dip2px(context, 16));
        addView(finishButton, finishButtonParams);

        startButtonParams=new LayoutParams(DensityUtil.dip2px(context, 120), DensityUtil.dip2px(context, 32));
        startButtonParams.addRule(CENTER_IN_PARENT);
        startButtonParams.addRule(ALIGN_PARENT_BOTTOM);
        startButtonParams.setMargins(0, 0, 0, DensityUtil.dip2px(context, 64));
        addView(startButton, startButtonParams);

        /** Add pic_name view
         * */
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        pic_name1=inflater.inflate(R.layout.pic_name, null);
        pic_name2=inflater.inflate(R.layout.pic_name, null);
        imageView1= (ImageView) pic_name1.findViewById(R.id.image);
        imageView2= (ImageView) pic_name2.findViewById(R.id.image);
        textViewName1= (TextView) pic_name1.findViewById(R.id.text);
        textViewName2= (TextView) pic_name2.findViewById(R.id.text);

        pic_name1Params=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pic_name1Params.addRule(ALIGN_PARENT_LEFT);
        pic_name1Params.setMargins(0, 0, 0, 0);
        addView(pic_name1, pic_name1Params);

        pic_name2Params=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pic_name2Params.addRule(ALIGN_PARENT_RIGHT);
        pic_name2Params.setMargins(0, 0, 0, 0);
        addView(pic_name2, pic_name2Params);

        /** Add score board view with layout inflater
         * */
        LayoutInflater inflater1= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        scoreBoardView=inflater1.inflate(R.layout.scoreboardview, null);
        scoreBoardViewParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        scoreBoardViewParams.addRule(CENTER_HORIZONTAL);
        addView(scoreBoardView, scoreBoardViewParams);

        tvScore1= (TextView) scoreBoardView.findViewById(R.id.score1);
        tvScore2= (TextView) scoreBoardView.findViewById(R.id.score2);
        tvName1= (TextView) scoreBoardView.findViewById(R.id.name1);
        tvName2= (TextView) scoreBoardView.findViewById(R.id.name2);
        tvSet11= (TextView) scoreBoardView.findViewById(R.id.set11);
        tvSet12= (TextView) scoreBoardView.findViewById(R.id.set12);
        tvSet21= (TextView) scoreBoardView.findViewById(R.id.set21);
        tvSet22= (TextView) scoreBoardView.findViewById(R.id.set22);
        tvSet31= (TextView) scoreBoardView.findViewById(R.id.set31);
        tvSet32= (TextView) scoreBoardView.findViewById(R.id.set32);
        tvSet41= (TextView) scoreBoardView.findViewById(R.id.set41);
        tvSet42= (TextView) scoreBoardView.findViewById(R.id.set42);
        tvSet51= (TextView) scoreBoardView.findViewById(R.id.set51);
        tvSet52= (TextView) scoreBoardView.findViewById(R.id.set52);
        tvPoint1= (TextView) scoreBoardView.findViewById(R.id.leftservepoint);
        tvPoint2= (TextView) scoreBoardView.findViewById(R.id.rightservepoint);

        /** Add left corner button
         * */
        LayoutInflater inflater2= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        leftCornerButton=inflater2.inflate(R.layout.leftcornerbutton, null);
        leftCornerButtonParams=new LayoutParams(DensityUtil.dip2px(context, 176), DensityUtil.dip2px(context, 176));
        leftCornerButtonParams.addRule(ALIGN_PARENT_LEFT);
        leftCornerButtonParams.addRule(ALIGN_PARENT_BOTTOM);
        addView(leftCornerButton, leftCornerButtonParams);

        leftWinButton= (Button) leftCornerButton.findViewById(R.id.leftwin);
        leftWinButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPointsButtonView.styleChange(true, 0);
                SelectViewPositionChanged(true);
            }
        });

        leftLoseButton= (Button) leftCornerButton.findViewById(R.id.leftlose);
        leftLoseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPointsButtonView.styleChange(false, 1);
                SelectViewPositionChanged(true);
            }
        });

        /** Add right corner button
         * */
        LayoutInflater inflater3= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rightCornerButton=inflater3.inflate(R.layout.rightcornerbutton, null);
        rightCornerButtonParams=new LayoutParams(DensityUtil.dip2px(context, 176), DensityUtil.dip2px(context, 176));
        rightCornerButtonParams.addRule(ALIGN_PARENT_RIGHT);
        rightCornerButtonParams.addRule(ALIGN_PARENT_BOTTOM);
        addView(rightCornerButton, rightCornerButtonParams);

        rightWinButton= (Button) rightCornerButton.findViewById(R.id.rightwin);
        rightWinButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPointsButtonView.styleChange(true, 1);
                SelectViewPositionChanged(false);
            }
        });

        rightLoseButton= (Button) rightCornerButton.findViewById(R.id.rightlose);
        rightLoseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPointsButtonView.styleChange(false, 0);
                SelectViewPositionChanged(false);
            }
        });

        /** Add select points button group
         * */
        selectPointsButtonView=new SelectPointsButtonView(context);
        selectPointsButtonView.setVisibility(INVISIBLE);
        selectPointsButtonView.setMatchBoardView(this);
        selectPointsButtonViewParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        selectPointsButtonViewParams.addRule(ALIGN_PARENT_LEFT);
        selectPointsButtonViewParams.addRule(ALIGN_PARENT_BOTTOM);
        addView(selectPointsButtonView, selectPointsButtonViewParams);

        /** Add match data show view
         * */
        matchDataShowView=new MatchDataShowView(context);
        matchDataShowView.setVisibility(GONE);
        matchDataShowViewParams=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        matchDataShowViewParams.addRule(CENTER_IN_PARENT);
        addView(matchDataShowView, matchDataShowViewParams);

    }

    private void SelectViewPositionChanged(boolean position){
        if(position==true){
            selectPointsButtonView.setVisibility(GONE);
            selectPointsButtonViewParams.setMargins(0, 0, 0, 0);
            selectPointsButtonView.setVisibility(VISIBLE);

        }else {
            selectPointsButtonView.setVisibility(GONE);
            selectPointsButtonViewParams.setMargins(width-selectPointsButtonView.getWidth(), 0, 0, 0);
            selectPointsButtonView.setVisibility(VISIBLE);
        }
    }

    private void whoServeDialog(){
        String items[]={name1, name2};
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Which one SERVE ?");
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                whichOneServe=which;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                selectPointsButtonView.scoreCalculator.isServe=whichOneServe;
                if(whichOneServe==0){
                    tvPoint1.setVisibility(VISIBLE);
                    tvPoint2.setVisibility(GONE);
                }
                if(whichOneServe==1){
                    tvPoint2.setVisibility(VISIBLE);
                    tvPoint1.setVisibility(GONE);
                }
            }
        });
        builder.create().show();

    }

    /** change serve state's visible */
    public void changeServePointShowState(){
        if(tvPoint1.getVisibility()==VISIBLE){
            tvPoint1.setVisibility(GONE);
            tvPoint2.setVisibility(VISIBLE);

        }else {
            tvPoint1.setVisibility(VISIBLE);
            tvPoint2.setVisibility(GONE);

        }

    }

}
