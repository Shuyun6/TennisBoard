package Data;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.shuyun.tennisboard.R;

/**
 * Created by Shuyun on 2/19/2016/019.
 */
public class AfterAdding extends RelativeLayout {
    Button backButton, addButton;
    private afterAddingButtonClickListener listener;

    public AfterAdding(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.afteradding, null);
        addView(v);
        addButton = (Button) v.findViewById(R.id.add);
        backButton = (Button) v.findViewById(R.id.back);

        addButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    addButton.setBackgroundColor(getResources().getColor(R.color.colorDeepGreen));
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    addButton.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    listener.add();
                }
                return false;
            }
        });

        backButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    backButton.setBackgroundColor(getResources().getColor(R.color.colorDeepGreen));
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    backButton.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                    listener.back();
                }
                return false;
            }
        });

    }

    public void setOnAfterAddingButtonClickListener(afterAddingButtonClickListener listener) {
        this.listener = listener;
    }

    public interface afterAddingButtonClickListener {
        void add();

        void back();
    }
}
