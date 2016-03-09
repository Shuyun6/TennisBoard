package Data;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;

/**
 * Created by Shuyun on 2/14/2016/014.
 */
public class FileTools {

    public static Uri getUriByFileDirAndFileName(String strFileDir, String strFileName) throws IOException {
        Uri uri = null;
        File fileDir = new File(Environment.getExternalStorageDirectory(), strFileDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File file = new File(fileDir, strFileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        uri = Uri.fromFile(file);
        return uri;

    }

    public static boolean hasSDcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public static Uri changeImageName(Uri uri, String newName) throws IOException {
        String path = uri.getPath();
        File file = new File(path);
        Uri newUri = getUriByFileDirAndFileName("/tbp", newName + ".jpeg");
        String newPath = newUri.getPath();
        file.renameTo(new File(newPath));

        return newUri;
    }

    public static File getFileByUri(Activity activity, Uri uri) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver contentResolver = activity.getContentResolver();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").
                        append("'" + path + "'").append(")");
                Cursor cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA},
                        stringBuffer.toString(), null, null);//query the suitable uri
                int index = 0;
                int dataIdx = 0;
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    index = cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cursor.getInt(index);
                    dataIdx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cursor.getString(dataIdx);
                }
                cursor.close();
                if (index == 0) {
                } else {
                    Uri uri1 = Uri.parse("content://media/external/images/media/" + index);
                    System.out.println("temp uri is :" + uri1);
                }
            } else if ("content".equals(uri.getScheme())) {
                String[] project = {MediaStore.Images.Media.DATA};
                Cursor cursor = activity.getContentResolver().query(uri, project, null, null, null);
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    path = cursor.getString(columnIndex);
                }
                cursor.close();
                return new File(path);
            }
        }
        return null;
    }

}
