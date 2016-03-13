package MenuItem;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

import com.example.shuyun.tennisboard.MainActivity;
import com.example.shuyun.tennisboard.R;
import com.example.shuyun.tennisboard.TopBar;

import Data.AddPlayerFragment;

import static com.example.shuyun.tennisboard.R.layout.playerdata;
import static com.example.shuyun.tennisboard.R.layout.playerfragment_layout;

/**
 * Created by Shuyun on 2/29/2016/029.
 */
public class PlayerFragment extends Fragment {

    public PlayerDataView playerDataView;
    public TopBar topBar;
    private FragmentManager fragmentManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(playerfragment_layout, container, false);

        fragmentManager=getFragmentManager();

        int flage=getArguments().getInt("Flage");

        playerDataView = (PlayerDataView) view.findViewById(R.id.playerdataview);
        playerDataView.flage=flage;//translate the flage to PlayerDataItemView
        playerDataView.fragmentManager=getFragmentManager();

        topBar = (TopBar) view.findViewById(R.id.id_back);
        topBar.setOnTopBarClickListener(new TopBar.topBarClickListener() {
            @Override
            public void leftClick() {
                getFragmentManager().popBackStack();
            }

            @Override
            public void rightClick() {
                RotateAnimation rotateAnimation = new RotateAnimation(0, 360, (float) (topBar.rightImageView.getPivotX() + 0.5 * topBar.rightImageViewWidth),
                        (float) (topBar.rightImageView.getPivotY() + 0.5 * topBar.rightImageViewHeight));
                rotateAnimation.setDuration(624);
                rotateAnimation.setFillAfter(false);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                topBar.rightImageView.startAnimation(rotateAnimation);
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
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        playerDataView.getData();
    }
}

