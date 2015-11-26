package com.example.alex.incafeandroidapp1.utils;

import android.util.Log;

import com.example.alex.incafeandroidapp1.IncafeApplication;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.File;

/**
 * Created by Alex on 19.11.2015.
 */
public class FileUtil {

    private static final String LOG_TAG = FileUtil.class.getSimpleName();

    public static String savePhotoByUrl(String imageUrl) {
        String fileName = getFileNameFromUrl(imageUrl);
        String filePath = getAbsoluteLocalPath(fileName);
        //   String filePath = fileStoregPath+fileName;

        Ion.with(IncafeApplication.getInstance()).load(imageUrl).write(new File(filePath)).setCallback(new FutureCallback<File>() {
            @Override
            public void onCompleted(Exception e, File result) {

                Log.d(LOG_TAG, "file loaded " + e + " " + result);
            }
        });
        return filePath;
    }

    public static String getFileNameFromUrl(String url) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = url.length() - 1; i > 0; i--) {
            char ch = url.charAt(i);
            if (ch != '%') {
                stringBuilder.insert(0, ch);
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }

    public static String getAbsoluteLocalPath(String fileName) {
        File localFile = new File(IncafeApplication.getInstance().getFilesDir(), fileName);
        return localFile.getAbsolutePath();
    }

    public static boolean isFileExist(String fileName) {
        File localFile = new File(IncafeApplication.getInstance().getFilesDir(), fileName);
        return localFile.exists();
    }
}
