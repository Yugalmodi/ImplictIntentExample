package com.techpalle.implicctintentexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button buttonCamera, buttonGallery, buttonDial, buttonBrowser, buttonMessage;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCamera = (Button) findViewById(R.id.button_camera);
        buttonGallery = (Button) findViewById(R.id.button_gallery);
        buttonDial = (Button) findViewById(R.id.button_dial);
        buttonMessage = (Button) findViewById(R.id.button_message);
        buttonBrowser = (Button) findViewById(R.id.button_browser);
        imageView = (ImageView) findViewById(R.id.image_view);

        buttonDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                Uri uri = Uri.parse("tel: 8109434645");
                intent.setData(uri);
                startActivity(intent);
            }
        });
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });
        buttonBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("http://skillgun.com/");
                intent.setData(uri);
                startActivity(intent);
            }
        });
        buttonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                Uri uri = Uri.parse("tel: 8109434645");
                intent.setData(uri);
                startActivity(intent);
            }
        });
        buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                //For open File Manager
                intent.setAction(Intent.ACTION_GET_CONTENT);

                //For Open Gallery
//                intent.setAction(Intent.ACTION_VIEW);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode ==1 && resultCode == RESULT_OK){
            Uri imagePath = data.getData();
            Bitmap image = null;
            try {
                image = MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView. setImageBitmap(image);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
