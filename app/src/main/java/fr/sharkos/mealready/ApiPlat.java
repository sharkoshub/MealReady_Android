package fr.sharkos.mealready;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiPlat {

    @GET("/plats")
    Call<Plat> getPlat();
}
