package CustomizedView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.shuyun.tennisboard.R;

import Data.Match;
import Data.ScoreCalculator;

/**
 * Created by Shuyun on 3/18/2016/018.
 */
public class SelectPointsButtonView extends RelativeLayout {

    private Context context;
    public boolean style;  //detect win style or lose style // true is win, false is lose
    private Button button11, button12, button13, button21, button22,
            button23, button31, button32, button33;
    private MatchBoardView matchBoardView;
    public static ScoreCalculator scoreCalculator=new ScoreCalculator();
    private int whoWin=0;
    private String[] scoreShow=new String[2];
    private int[] setShow={0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static Match match;

    private LayoutInflater layoutInflater;
    private View view;

    public void setMatchBoardView(MatchBoardView matchBoardView){
        this.matchBoardView=matchBoardView;
    }

    public SelectPointsButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initial();
    }

    public SelectPointsButtonView(Context context) {
        super(context);
        this.context=context;
        initial();
    }

    public void initial(){
        scoreCalculator.match=this.match;

        setBackground(getResources().getDrawable(R.drawable.lightbluegreyshape));
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.selectbuttongroup, null);
        addView(view);
        button11= (Button) view.findViewById(R.id.button11);
        button12= (Button) view.findViewById(R.id.button12);
        button13= (Button) view.findViewById(R.id.button13);
        button21= (Button) view.findViewById(R.id.button21);
        button22= (Button) view.findViewById(R.id.button22);
        button23= (Button) view.findViewById(R.id.button23);
        button31= (Button) view.findViewById(R.id.button31);
        button32= (Button) view.findViewById(R.id.button32);
        button33= (Button) view.findViewById(R.id.button33);

        button11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Need to know: 1,who win or lose; 2,which set
                 * */
                if(style==true) {
                    match.addFirstServeWin(whoWin, scoreCalculator.setState);
                    setMatchBoardView();
                }else {
                    match.addFirstServeFault((whoWin==0)?1:0, scoreCalculator.setState); /** first serve fault just need to record, not need to show score */
                }
            }
        });
        button12.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setMatchBoardView();
                if(style==true) {
                    match.addSecondServeWin(whoWin, scoreCalculator.setState);
                }else {
                    match.addDoubleFault(whoWin, scoreCalculator.setState);
                }
            }
        });
        button13.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setMatchBoardView();
                if(style==true) {
                    match.addAce(whoWin, scoreCalculator.setState);
                }else {
                }
            }
        });
        button21.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setMatchBoardView();
                if(style==true) {
                    match.addRightNetWin(whoWin, scoreCalculator.setState);
                }else {
                    match.addRightNetFault(whoWin, scoreCalculator.setState);
                }
            }
        });
        button22.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setMatchBoardView();
                if(style==true) {
                    match.addHighNetWin(whoWin, scoreCalculator.setState);
                }else {
                    match.addHighNetFault(whoWin, scoreCalculator.setState);
                }
            }
        });
        button23.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setMatchBoardView();
                if(style==true) {
                    match.addLeftNetWin(whoWin, scoreCalculator.setState);
                }else {
                    match.addLeftNetFault(whoWin, scoreCalculator.setState);
                }
            }
        });
        button31.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setMatchBoardView();
                if(style==true) {
                    match.addRightBaseWin(whoWin, scoreCalculator.setState);
                }else {
                    match.addRightBaseFault(whoWin, scoreCalculator.setState);
                }
            }
        });
        button32.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setMatchBoardView();
                if(style==true) {
                    match.addHighBaseWin(whoWin, scoreCalculator.setState);
                }else {
                    match.addHighBaseFault(whoWin, scoreCalculator.setState);
                }
            }
        });
        button33.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setMatchBoardView();
                if(style==true) {
                    match.addLeftBaseWin(whoWin, scoreCalculator.setState);
                }else {
                    match.addLeftBaseFault(whoWin, scoreCalculator.setState);
                }
            }
        });


    }

    public void getData(){ //for activity resume
        scoreShow=scoreCalculator.getData();
        setShow=scoreCalculator.getSetData();
        matchBoardView.tvScore1.setText(scoreShow[0]);
        matchBoardView.tvScore2.setText(scoreShow[1]);
        for(int i=0; i<scoreCalculator.setState; i++){
            switch (i+1){
                case 1:
                    matchBoardView.tvSet11.setText((matchBoardView.tvScore1.getText().equals(""))?"":String.valueOf(setShow[0]));
                    matchBoardView.tvSet12.setText((matchBoardView.tvScore1.getText().equals(""))?"":String.valueOf(setShow[1]));
                    break;
                case 2:
                    matchBoardView.tvSet21.setText(String.valueOf(setShow[2]));
                    matchBoardView.tvSet22.setText(String.valueOf(setShow[3]));
                    break;
                case 3:
                    matchBoardView.tvSet31.setText(String.valueOf(setShow[4]));
                    matchBoardView.tvSet32.setText(String.valueOf(setShow[5]));
                    break;
                case 4:
                    matchBoardView.tvSet41.setText(String.valueOf(setShow[6]));
                    matchBoardView.tvSet42.setText(String.valueOf(setShow[7]));
                    break;
                case 5:
                    matchBoardView.tvSet51.setText(String.valueOf(setShow[8]));
                    matchBoardView.tvSet52.setText(String.valueOf(setShow[9]));
                    break;
            }
        }
    }

    private void setMatchBoardView(){
        setVisibility(GONE);
        scoreCalculator.setMatchData(matchBoardView.set, matchBoardView.game, matchBoardView.ad);
        scoreCalculator.putData(whoWin);
        scoreCalculator.calculate();
        scoreShow=scoreCalculator.getData();
        setShow=scoreCalculator.getSetData();
        /** when match board view's serve state is different to score calculators',
         * then change the serve state visible and whichOneServe value */
        if(matchBoardView.whichOneServe!=scoreCalculator.isServe()){
            matchBoardView.changeServePointShowState();
            matchBoardView.whichOneServe=(matchBoardView.whichOneServe==0)?1:0;
        }

        matchBoardView.tvScore1.setText(scoreShow[0]);
        matchBoardView.tvScore2.setText(scoreShow[1]);

        switch (setShow[10]){
            case 1:
                matchBoardView.tvSet11.setText(String.valueOf(setShow[0]));
                matchBoardView.tvSet12.setText(String.valueOf(setShow[1]));
                break;
            case 2:
                matchBoardView.tvSet21.setText(String.valueOf(setShow[2]));
                matchBoardView.tvSet22.setText(String.valueOf(setShow[3]));
                break;
            case 3:
                matchBoardView.tvSet31.setText(String.valueOf(setShow[4]));
                matchBoardView.tvSet32.setText(String.valueOf(setShow[5]));
                break;
            case 4:
                matchBoardView.tvSet41.setText(String.valueOf(setShow[6]));
                matchBoardView.tvSet42.setText(String.valueOf(setShow[7]));
                break;
            case 5:
                matchBoardView.tvSet51.setText(String.valueOf(setShow[8]));
                matchBoardView.tvSet52.setText(String.valueOf(setShow[9]));
                break;
        }

    }

    public void styleChange(boolean style, int whoWin){ //true is win, fault is lose, whoWin: 0 is player1, 1 is player2
        this.style=style;
        this.whoWin=whoWin;
        if(style==true){
            button11.setText("1st");
            button12.setText("2nd");
            button13.setText("ace");
            button21.setText("L-net");
            button22.setText("H-net");
            button23.setText("R-net");
            button31.setText("L-base");
            button32.setText("H-base");
            button33.setText("R-base");

        }else{
            button11.setText("1st");
            button12.setText("2nd");
            button13.setText("X");
            button21.setText("L-net");
            button22.setText("H-net");
            button23.setText("R-net");
            button31.setText("L-base");
            button32.setText("H-base");
            button33.setText("R-base");

        }

    }

}
