package com.example.arturmusayelyan.backnavigation;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button notifyButton, goToDeepStackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notifyButton = findViewById(R.id.notify_me_button);
        goToDeepStackButton = findViewById(R.id.go_to_deep_stack_button);
        notifyButton.setOnClickListener(this);
        goToDeepStackButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notify_me_button:
                notifyMe();
                break;
            case R.id.go_to_deep_stack_button:
                goToThirdActivity();
                break;
        }
    }

    private void notifyMe() {
        Intent goToThirdActivityIntent = new Intent(this, ThirdActivity.class);
        PendingIntent pendingIntent = TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(goToThirdActivityIntent)
                .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.pets);
        builder.setContentTitle("Title");
        builder.setContentText("Notification text");
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }

    private void goToThirdActivity() {
        Intent goToThirdActivityIntent = new Intent(this, ThirdActivity.class);
        Intent intent[] = TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(goToThirdActivityIntent)
                .getIntents();
        startActivities(intent);
    }
}
