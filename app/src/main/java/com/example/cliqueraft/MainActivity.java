package com.example.cliqueraft;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText text1;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn1);
        button.setOnClickListener(v -> {
            text1=(EditText) findViewById(R.id.textview1);
            value = text1.getText().toString();
            Toast.makeText(MainActivity.this, "Your Text Massage : "+value, Toast.LENGTH_SHORT).show();
        });
    }

    public void Gchange(View view) {
        text1=(EditText) findViewById(R.id.textview1);
        text1.setText("COMPLETED");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Gnotification(View view){
        text1=(EditText) findViewById(R.id.textview1);
        value = text1.getText().toString();
        addNotification(value);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addNotification(String value) {
        Intent intent=new Intent(this,MainActivity.class);

        String CHANNEL_ID="MYCHANNEL";
        NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,"name",NotificationManager.IMPORTANCE_LOW);

        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);

        Notification notification=new Notification.Builder(getApplicationContext(),CHANNEL_ID)
                .setContentText("Notification has been generated from cliqueRaft")
                .setContentTitle(" Your text is :} :}")
                .setSubText(value)
                .setContentIntent(pendingIntent)
                .addAction(android.R.drawable.sym_action_chat,"Title",pendingIntent)
                .setChannelId(CHANNEL_ID)
//                .addAction(android.R.drawable.ic_menu_close_clear_cancel, "Dismiss", pendingIntent)
                .setSmallIcon(android.R.drawable.sym_action_chat)
                .setOnlyAlertOnce(true)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify(1,notification);
    }

    public void GModel(View view){
        text1=(EditText) findViewById(R.id.textview1);
        value = text1.getText().toString();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Your text is : "+value);
        alert.setMessage("Message");

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                closeContextMenu();
            }
        });

        alert.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                closeContextMenu();
            }
        });

        alert.show();
    }

}