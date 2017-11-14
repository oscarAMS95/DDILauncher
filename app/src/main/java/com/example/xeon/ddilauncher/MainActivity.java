package com.example.xeon.ddilauncher;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Message;
import android.provider.AlarmClock;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton botonMain, phoneButton, messageButton, newMessage, calendarButton, calculatorButton, contactosButton, cameraButton, galleryButton, clockButton, newContact;
    private static final String CALCULATOR_PACKAGE_NAME = "com.android.calculator2";
    private static final String CALCULATOR_CLASS_NAME = "com.android.calculator2.Calculator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonMain = (ImageButton)findViewById(R.id.botonMain);
        phoneButton = (ImageButton)findViewById(R.id.phoneButton);
        messageButton = (ImageButton)findViewById(R.id.messageButton);
        newMessage = (ImageButton)findViewById(R.id.newMessage);
        calendarButton = (ImageButton)findViewById(R.id.calendarButton);
        calculatorButton = (ImageButton)findViewById(R.id.calculatorButton);
        contactosButton = (ImageButton)findViewById(R.id.contactosButton);
        cameraButton = (ImageButton)findViewById(R.id.cameraButton);
        galleryButton = (ImageButton)findViewById(R.id.galleryButton);
        clockButton = (ImageButton)findViewById(R.id.clockButton);
        newContact = (ImageButton)findViewById(R.id.addButton);

        botonMain.setOnClickListener(new View.OnClickListener(){
            @Override
           public void onClick(View v){
               Intent intent = new Intent(getApplicationContext(),AppsListActivity.class);
               startActivity(intent);
           }
        });

        newContact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(),NuevoContactoActivity.class);
                startActivity(intent);
            }
        });

        phoneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                startActivity(intent);
            }
        });

        newMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:"));
                startActivity(intent);
            }
        });

        contactosButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI);
                startActivity(intent);
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setType("vnd.android-dir/mms-sms");
                startActivity(intent);
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_CALENDAR);
                startActivity(intent);
            }
        });

        galleryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_GALLERY);
                startActivity(intent);
            }
        });

        clockButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
                //intent.putExtra(AlarmClock.EXTRA_MESSAGE,"Nueva Alarma");
                startActivity(intent);
            }
        });


        cameraButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
                startActivity(intent);
            }
        });

        calculatorButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setComponent(new ComponentName(CALCULATOR_PACKAGE_NAME, CALCULATOR_CLASS_NAME));
                try {
                    startActivity(intent);
                }
                catch(ActivityNotFoundException e){}
            }
        });
    }

    public void onBackPressed(){
        //Nothing//
    }
}
