package Data;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.example.shuyun.tennisboard.R.layout.showandeditplayerdatafragment_layout;

/**
 * Created by Shuyun on 3/1/2016/001.
 */
public class ShowAndEditPlayerDataFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(showandeditplayerdatafragment_layout, container, false);


        return view;
    }
}
