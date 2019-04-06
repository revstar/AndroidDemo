package com.revstar.cutout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

public class CutActivity extends AppCompatActivity {
    private Canvas mCanvas;
    private boolean mCutFinFlag = false;
    private ImageView mCutImv = null;
    private Bitmap mCutImvBmp = null;
    private Bitmap mCutOutBmp = null;
    private boolean mExact = false;
    private Paint mPaint;
    private Path mPath = null;
    private RectF mPathRectF = null;
    private String mPicToBeCutPath = "";
    private Bitmap mPitTobeCutBmp = null;
    private int offsetX = 0;
    private int offsetY = 0;
    private float scaleFactor = 0.0f;
    private float touchDownX = 0.0f;
    private float touchDownY = 0.0f;
    private float touchUpX = 0.0f;
    private float touchUpY = 0.0f;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        Log.d("CutActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cut);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mCutImv = (ImageView) findViewById(R.id.imageView_image_cut);
        Button mBtnConfirm = (Button) findViewById(R.id.button_next_cut);
        Button mBtnCancel = (Button) findViewById(R.id.button_back_cut);
        Intent toCutIntent = getIntent();
        if (toCutIntent != null) {
            this.mPicToBeCutPath = toCutIntent.getStringExtra("PicToBeCut");
            System.out.println("CutActivity:  PicToBeCutPath = " + this.mPicToBeCutPath);
        }
        ((RelativeLayout) findViewById(R.id.relativeLayout_cut)).post(new Runnable() {
            public void run() {
                if (mPicToBeCutPath != null) {
                    Options bitmapOptions = new Options();
                    bitmapOptions.inSampleSize = 2;
                    Bitmap tempBmp = BitmapFactory.decodeFile(mPicToBeCutPath, bitmapOptions);
                    float degree = readPictureDegree(mPicToBeCutPath);
                    System.out.println("degree=" + degree);
                    mPitTobeCutBmp = rotatingImageView(degree, tempBmp);
                    int srcBmpW = mPitTobeCutBmp.getWidth();
                    int srcBmpH = mPitTobeCutBmp.getHeight();
                      mCutImvBmp = Bitmap.createBitmap(srcBmpW, srcBmpH,   mPitTobeCutBmp.getConfig());
                      mCanvas = new Canvas(  mCutImvBmp);
                      mPaint = new Paint();
                      mPaint.setColor(Color.rgb(18, 181, 176));
                      mPaint.setStrokeWidth(100.0f);
                      mPaint.setStyle(Style.STROKE);
                      mPaint.setPathEffect(new DashPathEffect(new float[]{50.0f, 30.0f, 50.0f, 30.0f}, 30.0f));
                    int imageViewW =   mCutImv.getWidth();
                    int imageViewH =   mCutImv.getHeight();
                    if (((float) imageViewH) / ((float) srcBmpH) < ((float) imageViewW) / ((float) srcBmpW)) {
                          scaleFactor = ((float) imageViewH) / ((float) srcBmpH);
                          offsetX = (int) Math.abs(((((float) srcBmpW) *   scaleFactor) - ((float) imageViewW)) / 2.0f);
                          offsetY = 0;
                    } else {
                          scaleFactor = ((float) imageViewW) / ((float) srcBmpW);
                          offsetX = 0;
                          offsetY = (int) Math.abs(((((float) srcBmpH) *   scaleFactor) - ((float) imageViewH)) / 2.0f);
                    }
                      mPaint.setStrokeWidth(5.0f /   scaleFactor);
                    System.out.println("ScaleFactor = " +   scaleFactor);
                    System.out.println("OffsetX = " +   offsetX + " ; OffsetY = " +   offsetY);
                      mCanvas.save();
                      mCanvas.drawBitmap(  mPitTobeCutBmp, 0.0f, 0.0f, null);
                      mCanvas.restore();
                      mCutImv.setScaleType(ScaleType.FIT_CENTER);
                      mCutImv.setImageBitmap(  mCutImvBmp);
                    System.out.println(  mCanvas.isHardwareAccelerated());
                }
            }
        });
        this.mCutImv.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (!  mCutFinFlag) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                              mPath = new Path();
                              touchDownX = (event.getX() - ((float)   offsetX)) /   scaleFactor;
                              touchDownY = (event.getY() - ((float)   offsetY)) /   scaleFactor;
                              mPath.moveTo(  touchDownX,   touchDownY);
                              mCutFinFlag = false;
                            break;
                        case MotionEvent.ACTION_UP:
                              touchUpX = (event.getX() - ((float)   offsetX)) /   scaleFactor;
                              touchUpY = (event.getY() - ((float)   offsetY)) /   scaleFactor;
                              mPath.lineTo(  touchUpX,   touchUpY);
                              mPath.close();
                              mCutFinFlag = true;
                              mCanvas.drawPath(  mPath,   mPaint);
                              mCutImv.invalidate();
                            break;
                        case MotionEvent.ACTION_MOVE:
                              touchUpX = (event.getX() - ((float)   offsetX)) /   scaleFactor;
                              touchUpY = (event.getY() - ((float)   offsetY)) /   scaleFactor;
                            System.out.println("touchUpX = " +   touchUpX);
                            System.out.println("touchUpY = " +   touchUpY);
                              mPath.lineTo(  touchUpX,   touchUpY);
                              mCanvas.drawPath(  mPath,   mPaint);
                              mCutImv.invalidate();
                              touchDownX =   touchUpX;
                              touchDownY =   touchUpY;
                              mCutFinFlag = false;
                            break;
                    }
                    if (  mCutFinFlag) {
                          mPathRectF = new RectF();
                          mPath.computeBounds(  mPathRectF,   mExact);
                          mPaint.setStyle(Style.FILL);
                          mCanvas.drawColor(0, Mode.CLEAR);
                          mCanvas.drawPath(  mPath,   mPaint);
                          mPaint.setXfermode(new PorterDuffXfermode(Mode.SRC_OUT));
                          mCanvas.drawBitmap(  mPitTobeCutBmp, 0.0f, 0.0f,   mPaint);
                          mCutOutBmp = Bitmap.createBitmap((int)   mPathRectF.width(), (int)   mPathRectF.height(),   mCutImvBmp.getConfig());
                        Canvas mCanvasCut = new Canvas(  mCutOutBmp);
//                        mCanvasCut.drawColor(0, Mode.CLEAR);
                        mCanvasCut.drawBitmap(  mCutImvBmp, new Rect((int)   mPathRectF.left, (int)   mPathRectF.top, ((int)   mPathRectF.left) + ((int)   mPathRectF.width()), ((int)   mPathRectF.top) + ((int)   mPathRectF.height())), new RectF(0.0f, 0.0f, (float)   mCutOutBmp.getWidth(), (float)   mCutOutBmp.getHeight()), null);
                          mCutImv.setImageBitmap(  mCutImvBmp);

                    }
                }
                return true;
            }
        });
        mBtnConfirm.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (  mCutFinFlag) {
                    String mSaveFilePath =   bmp2PngFile(  mCutOutBmp);
//                    Intent Cut2CopyIntent = new Intent(CutActivity.this, CopyActivity.class);
                    Intent Cut2CopyIntent = new Intent();
                    Cut2CopyIntent.putExtra("PicCutOut", mSaveFilePath);
                    Cut2CopyIntent.putExtra("PictoBeCut",   mPicToBeCutPath);
                      finish();
                      startActivity(Cut2CopyIntent);
                    return;
                }
                Toast.makeText(  getApplicationContext(), R.string.warning_cut, Toast.LENGTH_SHORT).show();
            }
        });
        mBtnCancel.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
//                  showAdI();
                if (  mCutFinFlag) {
                      mCanvas.drawBitmap(  mPitTobeCutBmp, 0.0f, 0.0f, null);
                      mCutImv.invalidate();
                      mPaint.setStyle(Style.STROKE);
                      mPaint.setXfermode(new PorterDuffXfermode(Mode.SRC));
                      mCutFinFlag = false;
                }
            }
        });
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
//            Intent Cut2MainIntent = new Intent(this, MainActivity.class);
            finish();
//            startActivity(Cut2MainIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    private float readPictureDegree(String path) {
        try {
            switch (new ExifInterface(path).getAttributeInt("Orientation", 1)) {
                case 3:
                    return 180.0f;
                case 6:
                    return 90.0f;
                case 8:
                    return 270.0f;
                default:
                    return 0.0f;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    private Bitmap rotatingImageView(float angle, Bitmap bitmap_src) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        Bitmap rotateBmp = Bitmap.createBitmap(bitmap_src, 0, 0, bitmap_src.getWidth(), bitmap_src.getHeight(), matrix, true);
        System.out.println("Rotation Finished!");
        return rotateBmp;
    }

    private String bmp2PngFile(Bitmap bmp) {
        String path = Environment.getExternalStorageDirectory().toString();
        System.out.println(path);
        File dir = new File(path + "/Cutout/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(path + "/Cutout/", "Cutout_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".png");
        String filePath = file.getAbsolutePath();
        System.out.println("CutActivity: CutOut Part's Path = " + filePath);
        try {
            OutputStream fOutputStream = new FileOutputStream(file);
            bmp.compress(CompressFormat.PNG, 70, fOutputStream);
            fOutputStream.flush();
            fOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Save Failed", Toast.LENGTH_SHORT).show();
        }
        return filePath;
    }
}