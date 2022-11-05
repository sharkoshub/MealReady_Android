package fr.sharkos.mealready;

import androidx.appcompat.app.AppCompatActivity;

import static android.content.ContentValues.TAG;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

    private static final String URL = "https://mealready.herokuapp.com/plats";
    private List<Plat> platList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        platList = new ArrayList<>();

        this.getPlat();
    }

    public void getPlat() {

        TextView listViewTitle = (TextView) findViewById(R.id.listViewTitle);
        listViewTitle.setText("Quel plat vous fait rêver ?\n\n");

        TextView listView = (TextView) findViewById(R.id.listView);

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i=0;i < array.length();i++) {
                                JSONObject object = array.getJSONObject(i);

                                Plat plat = new Plat(
                                        object.getInt("id"),
                                        object.getString("nom"),
                                        object.getString("description"),
                                        object.getDouble("prixUnitaire"),
                                        object.getInt("nbPlat"),
                                        object.getString("photo")
                                );

                                platList.add(plat);

                                // Initiatialiser mon TextView
                                Log.i(TAG, "nom du plat = "+plat.getNom());
                                listView.append(plat.getNom()+
                                        "\n\n"+ plat.getDescription()+
                                        "\n\n Nombre de plats disponibles : " +plat.getNbPlat()+
                                        "\n\n Prix unitaire : " +plat.getPrixUnitaire()+" € \n\n\n"
                                );

                            }
                            Log.i(TAG, "taille de la response = "+platList.size());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, "Error Response = "+e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //listView.setText("That didn't work!");
                Log.e("api", "error response : "+ error.getLocalizedMessage());
            }
        });

        queue.add(stringRequest);
    }

}