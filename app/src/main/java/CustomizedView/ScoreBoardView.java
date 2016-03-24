package CustomizedView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shuyun.tennisboard.R;

/**
 * Created by Shuyun on 3/17/2016/017.
 */
public class ScoreBoardView extends RelativeLayout {

    public TextView score1, score2;
    public TextView name1, name2;
    public TextView set11, set12, set21, set22, set31, set32, set41, set42,set51, set52;
    private LayoutParams scoreBoardViewParams;
    private View scoreBoardView;

    public ScoreBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater1= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        scoreBoardView=inflater1.inflate(R.layout.scoreboardview, null);
        scoreBoardViewParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        scoreBoardViewParams.addRule(CENTER_HORIZONTAL);
        addView(scoreBoardView, scoreBoardViewParams);

//        score1=new TextView(context);
//        score2=new TextView(context);
//        name1=new TextView(context);
//        name2=new TextView(context);
//        set11=new TextView(context);
//        set12=new TextView(context);
//        set21=new TextView(context);
//        set22=new TextView(context);
//        set31=new TextView(context);
//        set32=new TextView(context);
//        set41=new TextView(context);
//        set42=new TextView(context);
//        set51=new TextView(context);
//        set52=new TextView(context);


    }

    public ScoreBoardView(Context context) {
        super(context);
    }
}
