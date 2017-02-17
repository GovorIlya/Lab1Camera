package com.example.ilya.myapplication;
import java.io.File;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

    File directory;
    final int TYPE_PHOTO = 1;
    final int TYPE_VIDEO = 2;

    final int REQUEST_CODE_PHOTO = 1;
    final int REQUEST_CODE_VIDEO = 2;

    final String TAG = "myLogs";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDirectory(); //созадем папку для наших файлов

    }

    public void onClickPhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri(TYPE_PHOTO));
        startActivityForResult(intent, REQUEST_CODE_PHOTO);

    }

    public void onClickVideo(View view) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri(TYPE_VIDEO));
        startActivityForResult(intent, REQUEST_CODE_VIDEO);
    }


    private Uri generateFileUri(int type) {      //генерирует путь к файлугенерирует путь к файлу
        File file = null;
        switch (type) {
            case TYPE_PHOTO:
                file = new File(directory.getPath() + "/" + "photo_" + System.currentTimeMillis() + ".jpg");
                break;
            case TYPE_VIDEO:
                file = new File(directory.getPath() + "/" + "video_" + System.currentTimeMillis() + ".mp4");
                break;
        }
        Log.d(TAG, "fileName = " + file);
        return Uri.fromFile(file);
    }

    private void createDirectory() {
        directory = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MyFolder");
        if (!directory.exists())
            directory.mkdirs();
    }

}