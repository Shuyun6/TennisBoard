package Data;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.shuyun.tennisboard.R;
import com.example.shuyun.tennisboard.TopBar;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import static com.example.shuyun.tennisboard.R.layout.addplayerfragment_layout;

/**
 * Created by Shuyun on 2/4/2016/004.
 */
public class AddPlayerFragment extends Fragment {
    android.app.FragmentManager fragmentManager;
    android.app.FragmentTransaction fragmentTransaction;
    private TopBar topBar;
    private AfterAdding afterAdding;
    private Context context;
    private Uri photoUri = null;
    private ImageView imageView;
    private Database database;
    private Player player;
    private Bitmap bitmap;
    private String sexString;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(addplayerfragment_layout, container, false);

        /**
         * Click and add player's head image
         * */
        imageView = (ImageView) view.findViewById(R.id.image);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imageView.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    imageView.setBackgroundColor(getResources().getColor(R.color.colorTopMenuText));

                    if (!FileTools.hasSDcard()) {
                        Toast.makeText(getActivity(), "Cannot find SDcard, please check again !", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    try {
                        photoUri = FileTools.getUriByFileDirAndFileName("/tbp", "player.jpeg");
                    } catch (IOException e) {
                        Toast.makeText(getActivity(), "failed to create a file", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    ImageSelectTools.openDialog(getActivity(), photoUri);
                }
                return false;
            }
        });

        final EditText editTextName = (EditText) view.findViewById(R.id.editname);
        final EditText editTextBirth = (EditText) view.findViewById(R.id.editbirth);
        final EditText editTextEmail = (EditText) view.findViewById(R.id.editemail);
        final EditText editTextPhone = (EditText) view.findViewById(R.id.editphone);
        final EditText editTextHeight = (EditText) view.findViewById(R.id.editheight);
        final EditText editTextWeight = (EditText) view.findViewById(R.id.editweight);

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.sexRadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) getActivity().findViewById(radioButtonId);
                sexString = radioButton.getText().toString();

            }
        });

        final Button finishButtton = (Button) view.findViewById(R.id.finish);
        finishButtton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    finishButtton.setBackgroundColor(getResources().getColor(R.color.colorDeepGreen));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    finishButtton.setBackgroundColor(getResources().getColor(R.color.colorGreen));

                    database = new Database(getActivity().getApplicationContext());
                    try {
                        database.open();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if (sexString == null)
                        sexString = "Male";
                    String tempname = editTextName.getText().toString();    //to get the name from edittext
                    if (!tempname.equals("")) {     //while the enter name is not null
                        /***/
                        try {
                            if (database.ifExistPlayer(tempname, sexString)) {
                                Toast.makeText(getActivity(), "The Player is already EXIST !", Toast.LENGTH_LONG).show();
                            } else {
                                Uri uri = saveBitmaptoFile(bitmap, editTextName.getText().toString());  //save the bitmap from result
                                String uriStr = uri.toString(); //turn uri to String to save in Database
                                if (sexString == null)
                                    sexString = "Male";   //The initial value from RadioButton is Male but return a null, here to turn it to Male
                                player = new Player(uriStr, editTextName.getText().toString(), sexString, editTextBirth.getText().toString(),
                                        editTextEmail.getText().toString(), editTextPhone.getText().toString(),
                                        editTextHeight.getText().toString(), editTextWeight.getText().toString());

                                database.insertPlayer(player);
                                afterAdding.setVisibility(View.VISIBLE);    //after adding the player to database, turning to a new view

                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    } else {    //while enter a empty name
                        Toast.makeText(getActivity(), "Name is empty ! Try again !", Toast.LENGTH_SHORT).show();
                    }

                }
                return false;
            }
        });

        /**
         * Edit after adding view
         * */
        afterAdding = (AfterAdding) view.findViewById(R.id.afteradding);
        afterAdding.setOnAfterAddingButtonClickListener(new AfterAdding.afterAddingButtonClickListener() {
            @Override
            public void back() {
                //count the number of backstacks, and cancel all of it
//                int count=getFragmentManager().getBackStackEntryCount();
//                for(int i=0; i<count; i++) {
//                    getFragmentManager().popBackStack();
//                }
                getFragmentManager().popBackStack();
            }

            @Override
            public void add() {
                /**
                 * Create a new fragment after click addButton
                 * */
                getFragmentManager().popBackStack();
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(android.R.id.content, new AddPlayerFragment(), ""); //"" is a tag, the same as "XXX", to get data from Activity result
                fragmentTransaction.setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        /**
         * Insert Topbar
         * */
        topBar = (TopBar) view.findViewById(R.id.id_topbar);
        topBar.setOnTopBarClickListener(new TopBar.topBarClickListener() {
            @Override
            public void leftClick() {
                getFragmentManager().popBackStack();
            }

            @Override
            public void rightClick() {

            }
        });
        topBar.invalidate();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == -1)  //while choose to pic a photo
                    ImageSelectTools.startPhotoZoom(getActivity(), photoUri, 600);
                else
                    return;
                break;
            case 2:
                if (data == null)
                    return;
                ImageSelectTools.startPhotoZoom(getActivity(), data.getData(), 600);
                break;
            case 3:
                if (resultCode == 0)   //while choose to edit
                    return;
                bitmap = data.getExtras().getParcelable("data");
                if (bitmap == null) {
                    Toast.makeText(getActivity(), "No image have been selected !", Toast.LENGTH_SHORT).show();
                } else {
                    imageView.setImageBitmap(bitmap);
                }
                break;
        }
    }

    public Uri saveBitmaptoFile(Bitmap bitmap, String name) {
        Uri uri = null;

        //if user haven't select an image, give him a local image
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tennis);
        }

        File fileDir = new File(Environment.getExternalStorageDirectory(), "/tbp");
        File file = new File(fileDir, name + ".jpeg");
        //Create a new file and put the bitmap into it
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        uri = Uri.fromFile(file);

        return uri;
    }

}
