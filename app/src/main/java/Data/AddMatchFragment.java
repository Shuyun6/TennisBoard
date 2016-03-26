package Data;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shuyun.tennisboard.MatchActivity;
import com.example.shuyun.tennisboard.R;
import com.example.shuyun.tennisboard.TopBar;

import java.io.IOException;
import java.sql.SQLException;

import MenuItem.PlayerFragment;

import static com.example.shuyun.tennisboard.R.layout.addmatchfragment_layout;

/**
 * Created by Shuyun on 3/9/2016/009.
 */
public class AddMatchFragment extends Fragment {

    private TopBar topBar;
    private String set="1", game="6", ad="YES"; //default value
    public String name1=null, name2=null, sex1, sex2;
    public Bitmap bitmap1, bitmap2;
    private Uri uri1, uri2;
    private View detailView, addmatchplayersview;
    private RadioGroup setRadioGroup, gameRadioGroup, adRadioGroup;
    private View view1, view2;
    private Button nextButton, startButton;
    private ImageView imageView1, imageView2;
    private TextView textView1, textView2;
    private boolean isClick;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(addmatchfragment_layout, container, false);

        topBar= (TopBar) view.findViewById(R.id.id_topbar);
        detailView=view.findViewById(R.id.detailCheckView);
        addmatchplayersview=view.findViewById(R.id.addmatchplayersview);
        setRadioGroup = (RadioGroup) view.findViewById(R.id.setRadioGroup);
        gameRadioGroup = (RadioGroup) view.findViewById(R.id.gameRadioGroup);
        adRadioGroup = (RadioGroup) view.findViewById(R.id.adRadioGroup);
        nextButton= (Button) view.findViewById(R.id.next);
        view1=view.findViewById(R.id.view1);
        view2=view.findViewById(R.id.view2);
        startButton= (Button) view.findViewById(R.id.start);
        imageView1= (ImageView) view.findViewById(R.id.player1);
        imageView2= (ImageView) view.findViewById(R.id.player2);
        textView1= (TextView) view.findViewById(R.id.playername1);
        textView2= (TextView) view.findViewById(R.id.playername2);

        topBar.setOnTopBarClickListener(new TopBar.topBarClickListener() {
            @Override
            public void leftClick() {
                getFragmentManager().popBackStack();
            }

            @Override
            public void rightClick() {

            }
        });

        setRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) getActivity().findViewById(radioButtonId);
                set = radioButton.getText().toString();
            }
        });

        gameRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) getActivity().findViewById(radioButtonId);
                game = radioButton.getText().toString();
            }
        });

        adRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) getActivity().findViewById(radioButtonId);
                ad = radioButton.getText().toString();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addmatchplayersview.setVisibility(View.VISIBLE);
                detailView.setVisibility(View.GONE);
            }
        });

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClick=true;
                PlayerFragment playerFragment=new PlayerFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("Flage", 1);
                playerFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_left);
                fragmentTransaction.replace(android.R.id.content, playerFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name1 == null) {
                    Toast.makeText(getActivity(), "Choose PLAYER1 firstly !", Toast.LENGTH_SHORT).show();
                } else {
                    isClick = true;
                    PlayerFragment playerFragment = new PlayerFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("Flage", 2);
                    playerFragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_left);
                    fragmentTransaction.replace(android.R.id.content, playerFragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name1!=null&&name2!=null){
                    Intent intent=new Intent(getActivity(), MatchActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("name1", name1);
                    bundle.putString("name2", name2);
                    bundle.putString("sex1", sex1);
                    bundle.putString("sex2", sex2);
                    bundle.putString("bitmapString1", uri1.toString());
                    bundle.putString("bitmapString2", uri2.toString());
                    bundle.putString("set", set);
                    bundle.putString("game", game);
                    bundle.putString("ad", ad);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    getFragmentManager().popBackStack();

                }
            }
        });

        return view;
    }

    public void getData(){
        Database database=new Database(getActivity());
        try {
            database.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor;
        if(!(name1==null)){
            try {
                cursor=database.getPlayer(name1, sex1);
                cursor.moveToFirst();
                uri1=Uri.parse(cursor.getString(0));
                try {
                    bitmap1 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri1);
                    imageView1.setImageBitmap(bitmap1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textView1.setText(name1);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if(!(name2==null)) {
            if (name1.equals(name2) && sex1.equals(sex2)) {
                Toast.makeText(getActivity(), "It's the same player, try again !", Toast.LENGTH_SHORT).show();
                name2=null;
                sex2=null;
            } else {
                try {
                    cursor = database.getPlayer(name2, sex2);
                    cursor.moveToFirst();
                     uri2 = Uri.parse(cursor.getString(0));
                    try {
                        bitmap2 = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri2);
                        imageView2.setImageBitmap(bitmap2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    textView2.setText(name2);

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            database.close();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if(isClick==true) {
            getData();
            detailView.setVisibility(View.GONE);
            addmatchplayersview.setVisibility(View.VISIBLE);
        }
    }
}
