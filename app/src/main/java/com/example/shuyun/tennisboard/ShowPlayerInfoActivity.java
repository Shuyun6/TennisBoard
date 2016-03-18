package com.example.shuyun.tennisboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import CustomizedView.PlayerInfoEditView;
import CustomizedView.PlayerInfoShowView;
import Data.Database;
import Data.FileTools;
import Data.ImageSelectTools;
import Data.Player;

/**
 * Created by Shuyun on 3/2/2016/002.
 */
public class ShowPlayerInfoActivity extends Activity {
    boolean isImageChanged;
    private TopBar topBar;
    private PlayerInfoShowView playerInfoShowView;
    private PlayerInfoEditView playerInfoEditView;
    private String dataname, datasex;
    private Uri photoUri = null;
    private Bitmap bitmap;
    private ImageView imageView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showplayerinfoactivity_layout);
        context = this;

        Bundle bundle = getIntent().getExtras();
        dataname = bundle.getString("name");
        datasex = bundle.getString("sex");

        topBar = (TopBar) findViewById(R.id.id_topbarinshowfragment);
        topBar.setOnTopBarClickListener(new TopBar.topBarClickListener() {
            @Override
            public void leftClick() {
                finish();
            }

            @Override
            public void rightClick() {
                playerInfoEditView.setVisibility(View.VISIBLE);
                playerInfoEditView.getData(dataname, datasex);
            }
        });

        playerInfoShowView = (PlayerInfoShowView) findViewById(R.id.playerinfoshowview);
        playerInfoShowView.getData(dataname, datasex);

        playerInfoEditView = (PlayerInfoEditView) findViewById(R.id.playerinfoeditview);
        imageView = playerInfoEditView.imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });

        playerInfoEditView.setOnPlayerInfoEditViewClickListener(new PlayerInfoEditView.OnPlayerInfoEditViewClickListener() {
            @Override
            public void finish() {
                String name = playerInfoEditView.editTextName.getText().toString();
                String birth = playerInfoEditView.editTextBirth.getText().toString();
                String height = playerInfoEditView.editTextHeight.getText().toString();
                String weight = playerInfoEditView.editTextWeight.getText().toString();
                String email = playerInfoEditView.editTextEmail.getText().toString();
                String phone = playerInfoEditView.editTextPhone.getText().toString();
                String sex = playerInfoEditView.sexString;
                String imageUriStr = null;
                Database database = new Database(context);
                try {
                    database.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    if (database.ifExistPlayer(name, sex)) {
                        Toast.makeText(context, "The name is exist !!!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (isImageChanged) {
                            Uri uri = saveBitmaptoFile(bitmap, name);
                            imageUriStr = uri.toString();
                        } else {
                            Uri uri = saveBitmaptoFile(playerInfoEditView.bitmap, name);
                            imageUriStr = uri.toString();
                        }
                        database.deletePlayer(dataname, datasex);
                        Player player = new Player(imageUriStr, name, sex, birth, email, phone, height, weight);
                        database.insertPlayer(player);
                        database.close();
                        playerInfoEditView.setVisibility(View.GONE);
                        playerInfoShowView.getData(name, sex);

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == -1)  //while choose to pic a photo
                    ImageSelectTools.startPhotoZoom(this, photoUri, 600);
                else
                    return;
                break;
            case 2:
                if (data == null)
                    return;
                ImageSelectTools.startPhotoZoom(this, data.getData(), 600);
                break;
            case 3:
                if (resultCode == 0)   //while choose to edit
                    return;
                bitmap = data.getExtras().getParcelable("data");
                if (bitmap == null) {
                    Toast.makeText(this, "No image have been selected !", Toast.LENGTH_SHORT).show();
                } else {
                    imageView.setImageBitmap(bitmap);
                    isImageChanged = true;
                }
                break;
        }
    }

    public void click() {
        if (!FileTools.hasSDcard()) {
            Toast.makeText(this, "Cannot find SDcard, please check again !", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            photoUri = FileTools.getUriByFileDirAndFileName("/tbp", "player.jpeg");
        } catch (IOException e) {
            Toast.makeText(this, "failed to create a file", Toast.LENGTH_SHORT).show();
            return;
        }
        ImageSelectTools.openDialog(this, photoUri);
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
