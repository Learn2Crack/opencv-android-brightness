package com.learn2crack.opencvdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;


public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    
    private static final String TAG = "MainActivity";
    
    static {
        if(!OpenCVLoader.initDebug()){
            Log.d(TAG, "OpenCV not loaded");
        } else {
            Log.d(TAG, "OpenCV loaded");
        }
    }

    private ImageView iv_image;
    private AppCompatSeekBar sb_brightness;
    private Bitmap image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        iv_image = (ImageView)findViewById(R.id.iv_image);
        sb_brightness = (AppCompatSeekBar)findViewById(R.id.sb_brightness);
        image = BitmapFactory.decodeResource(getResources(),R.drawable.learn2crack);
        iv_image.setImageBitmap(image);
        sb_brightness.setOnSeekBarChangeListener(this);
    }

    private Bitmap increaseBrightness(Bitmap bitmap, int value){

        Mat src = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
        Utils.bitmapToMat(bitmap,src);
        src.convertTo(src,-1,1,value);
        Bitmap result = Bitmap.createBitmap(src.cols(),src.rows(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(src,result);
        return result;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        Bitmap edited = increaseBrightness(image,progress);
        iv_image.setImageBitmap(edited);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
