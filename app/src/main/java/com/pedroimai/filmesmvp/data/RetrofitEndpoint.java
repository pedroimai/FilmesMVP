package com.pedroimai.filmesmvp.data;

import com.pedroimai.filmesmvp.data.model.FilmeResultadoBusca;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Pedro on 24/01/2017.
 */

public interface RetrofitEndpoint {
    @GET("./")
    Call<FilmeResultadoBusca> busca(@Query("s") String q, @Query("r") String format);
}
