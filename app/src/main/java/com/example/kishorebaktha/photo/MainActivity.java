package com.example.kishorebaktha.photo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{
   static final int REQUEST_IMAGE_CAPTURE=1;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b=(Button)findViewById(R.id.button);
        imageView=(ImageView)findViewById(R.id.imageView);

        //Disable button if user has no camers
        if(!hasCamera())
            b.setEnabled(false);
    }
    //check if user has camera
    public boolean hasCamera()
    {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
//launching camera
    public void LaunchCamera(View v)
    {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Take pic and pass result to onActivityResult
        //startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);//RETURN RESULT
        startActivityForResult(
                Intent.createChooser(intent, "Complete action using"),
                REQUEST_IMAGE_CAPTURE);
    }

   //IF YOU WANT TO RETURN IMAGE TAKEN

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK)
        {
            //get photo
            Bundle extras=data.getExtras();
            Bitmap photo=(Bitmap) extras.get("data");
            imageView.setImageBitmap(photo);
            }
           // MediaStore.Images.Media.insertImage(getContentResolver(),photo,"title","description");
        }
        public void Send(View v)
        {
            Intent intent2=new Intent(this,Attachment.class);
            startActivity(intent2);
        }
    }

