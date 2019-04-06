package com.revstar.cutout;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    final int MY_PERMISSIONS_REQUEST_MULTIPLE = 3;
    final List<String> permissionsList = new ArrayList();
    private File photoFile = null;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        getSupportActionBar().hide();
        ImageView mBtnGallery = (ImageView) findViewById(R.id.imageView_gallery_main);
        ((ImageView) findViewById(R.id.imageView_camera_main)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent takePictureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                if (takePictureIntent.resolveActivity(MainActivity.this.getPackageManager()) != null) {
                    try {
                        MainActivity.this.photoFile = MainActivity.this.createImageFile();
                    } catch (IOException e) {
                        Log.e("CameraButtonClick", "Can't create the file!");
                    }
                    if (MainActivity.this.photoFile != null) {
                        takePictureIntent.putExtra("output", Uri.fromFile(MainActivity.this.photoFile));
                        MainActivity.this.startActivityForResult(takePictureIntent, 1);
                    }
                }
            }
        });
        mBtnGallery.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.startActivityForResult(new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI), 2);
            }
        });
        getAppPermissions();
        if (this.permissionsList.size() > 0) {
            ActivityCompat.requestPermissions(this, (String[]) this.permissionsList.toArray(new String[this.permissionsList.size()]), 3);
        }
    }

    private void getAppPermissions() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                System.out.println("shouldShowRequestPermissionRationale-ex");
                this.permissionsList.add("android.permission.WRITE_EXTERNAL_STORAGE");
            } else {
                System.out.println("No explanation needed");
                this.permissionsList.add("android.permission.WRITE_EXTERNAL_STORAGE");
            }
        }
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0) {
            return;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA")) {
            System.out.println("shouldShowRequestPermissionRationale-camera");
            this.permissionsList.add("android.permission.CAMERA");
            return;
        }
        System.out.println("No explanation needed");
        this.permissionsList.add("android.permission.CAMERA");
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 3:
                System.out.println("MY_PERMISSIONS_REQUEST_MULTIPLE");
                boolean allGranted = true;
                if (grantResults.length > 0) {
                    System.out.println("requests " + grantResults.length);
                    for (int i : grantResults) {
                        if (i != 0) {
                            allGranted = false;
                        }
                    }
                }
                if (allGranted) {
                    System.out.println("access permitted");
                    return;
                } else {
                    System.out.println("Some accesses denied");
                    return;
                }
            default:
                return;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) {
            return;
        }
        Intent Main2CutIntent;
        if (requestCode == 1) {
            try {
                String cameraPicPath = this.photoFile.getAbsolutePath();
                galleryAddPic(this, cameraPicPath);
                System.out.println("MainActivity: CameraPicPath = " + cameraPicPath);
                Main2CutIntent = new Intent(this, CutActivity.class);
                Main2CutIntent.putExtra("PicToBeCut", cameraPicPath);
                finish();
                startActivity(Main2CutIntent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == 2) {
            String[] filePath = new String[]{"_data"};
            Cursor c = getContentResolver().query(data.getData(), filePath, null, null, null);
            c.moveToFirst();
            String galleryPicPath = c.getString(c.getColumnIndex(filePath[0]));
            c.close();
            System.out.println("MainActivity: galleryPicPath = " + galleryPicPath);
            Main2CutIntent = new Intent(this, CutActivity.class);
            Main2CutIntent.putExtra("PicToBeCut", galleryPicPath);
            finish();
            startActivity(Main2CutIntent);
        }
    }

    private File createImageFile() throws IOException {
        return File.createTempFile("JPEG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_", ".jpg", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
    }

    private void galleryAddPic(Context ctx, String mPhotoPathName) {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        mediaScanIntent.setData(Uri.fromFile(new File(mPhotoPathName)));
        ctx.sendBroadcast(mediaScanIntent);
    }
}