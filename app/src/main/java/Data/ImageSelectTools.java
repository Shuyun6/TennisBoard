package Data;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by Shuyun on 2/14/2016/014.
 */
public class ImageSelectTools {
    public static void openDialog(final Activity activity, final Uri uri) {
        new ImageSelectDialog(activity)
                .builder()
//                .setTitle("Image")
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addItem("TakePhoto", ImageSelectDialog.ItemColor.Red, new ImageSelectDialog.OnItemClickListener() {
                    @Override
                    public void onClick(int click) {
                        startCamearPicCut(activity, uri);
                    }
                })
                .addItem("Gallery", ImageSelectDialog.ItemColor.Red, new ImageSelectDialog.OnItemClickListener() {
                    @Override
                    public void onClick(int click) {
                        startImageCaptrue(activity);
                    }
                })
                .show();
    }

    private static void startCamearPicCut(Activity context, Uri uri) {
        // 调用系统的拍照功能
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        intent.putExtra("camerasensortype", 2);// 调用前置摄像头
        intent.putExtra("autofocus", true);// 自动对焦
        intent.putExtra("fullScreen", true);// 全屏
        intent.putExtra("showActionIcons", false);
        // 指定调用相机拍照后照片的储存路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        context.startActivityForResult(intent, 1);
    }

    private static void startImageCaptrue(Activity context) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        context.startActivityForResult(intent, 2);
    }

    public static void startPhotoZoom(Activity context, Uri uri, int size) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", size);
        intent.putExtra("outputY", size);
        intent.putExtra("return-data", true);

        context.startActivityForResult(intent, 3);
    }


}
