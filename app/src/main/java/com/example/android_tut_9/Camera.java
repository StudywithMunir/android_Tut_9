package com.example.android_tut_9;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.io.IOException;

public class Camera extends Activity implements View.OnClickListener {

    Button btn;
    ImageView iv;
    ImageButton ib;

    Intent i;

    final static int cameraData= 0;

    Bitmap bmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        initializer();
    }

    public void initializer(){
        btn=(Button) findViewById(R.id.setWallPaper);
        iv=(ImageView) findViewById(R.id.ivReturnPic);
        ib=(ImageButton) findViewById(R.id.ibTakepic);

        //btn for setting wallpaper
        btn.setOnClickListener(this);

        //ib for taking pic
        ib.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            //for setting wallpaper
            case R.id.setWallPaper:

                try {
                    getApplicationContext().setWallpaper(bmp);

                    //IO=input output
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case R.id.ibTakepic:

                //Taking pic through the method image capture that already in the manifest and store in i intent variable
                i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //cameraData variable will collect data
                //using startActivityForResult bcz we want to return some data
                startActivityForResult(i,cameraData);

                break;


        }

    }

    //this method allow us to get the data and we set our imageview within that method


    //resultcode=information gave it
    //data=label

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //checking if operation is suceeded
        //if information is true
        //RESULT_OK is a method 
        if (resultCode == RESULT_OK){

            //when start the camera activity and when it closes it put extras
            Bundle extras=data.getExtras();

            //setting bitmap based of those extras
            //data is key value inside get
            bmp=(Bitmap) extras.get("data");

            //for imageView
            iv.setImageBitmap(bmp);

        }


    }
}
