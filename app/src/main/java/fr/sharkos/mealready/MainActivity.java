package fr.sharkos.mealready;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static android.content.ContentValues.TAG;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button clickToEat = findViewById(R.id.clickToEat);
        Button clickNotif= findViewById(R.id.clickNotif);

        // Bouton pour appeler l'API
        clickToEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Manger ! \uD83D\uDE0B", Toast.LENGTH_SHORT).show();
                Intent listPlatIntent = new Intent(MainActivity.this, ListPlatActivity.class);
                startActivity(listPlatIntent);
            }
        });

        // Bouton pour afficher la notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("MealReady Notification", "MealReady Notification",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        clickNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Nouveauté \uD83D\uDC9B", Toast.LENGTH_SHORT).show();
                addNotification();
            }
        });
    }

    private void addNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "MealReady Notification");
        builder.setContentTitle("Nouveauté");
        builder.setContentText("Frite party chez MealReady !!!");
        builder.setSmallIcon(R.drawable.logo);
        builder.setAutoCancel(false);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
        managerCompat.notify(1, builder.build());

    }

}