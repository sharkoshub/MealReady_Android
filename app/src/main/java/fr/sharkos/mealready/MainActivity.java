package fr.sharkos.mealready;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.ContentValues.TAG;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    static final String URL = "https://mealready.herokuapp.com";

    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView3 = findViewById(R.id.textView3);
        //textView2.setText(this.getPlat());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiPlat apiPlat = retrofit.create(ApiPlat.class);

        //Call<Plat> call = apiPlat.getPlats();
        /*
        call.enqueue(new Callback<Plat>() {
            @Override
            public void onResponse(Call<Plat> call, retrofit2.Response<Plat> response) {

                String nomPlat = response.body().getNom();
                textView2.append(nomPlat);
            }

            @Override
            public void onFailure(Call<Plat> call, Throwable t) {
                Log.e("Fail mon pote",t.toString());
            }
        });*/

        apiPlat.getPlat().enqueue(new Callback<Plat>() {
            @Override
            public void onResponse(Call<Plat> call, Response<Plat> response) {
                String nomPlat = response.body().getNom();
                textView3.setText(nomPlat);
            }

            @Override
            public void onFailure(Call<Plat> call, Throwable t) {
                Log.e(TAG, t.toString());
                textView3.setText("Fail !!!");
            }
        });
    }

}