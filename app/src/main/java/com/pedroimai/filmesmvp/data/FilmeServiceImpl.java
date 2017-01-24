package com.pedroimai.filmesmvp.data;

import com.pedroimai.filmesmvp.data.model.Filme;
import com.pedroimai.filmesmvp.data.model.FilmeResultadoBusca;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pedro on 24/01/2017.
 */

public class FilmeServiceImpl implements FilmeServiceApi {
    RetrofitEndpoint mRetrofit;

    public FilmeServiceImpl(){
        mRetrofit = RetrofitClient.getClient().create(RetrofitEndpoint.class);
    }

    @Override
    public void getFilmes(final FilmeServiceCallback<FilmeResultadoBusca> callback) {
        Call<FilmeResultadoBusca> callFilme = mRetrofit.busca("star wars","json");
        callFilme.enqueue(new Callback<FilmeResultadoBusca>() {
            @Override
            public void onResponse(Call<FilmeResultadoBusca> call, Response<FilmeResultadoBusca> response) {
                if(response.code()==200){
                    FilmeResultadoBusca resultadoBusca = response.body();
                    callback.onLoaded(resultadoBusca);
                }
            }

            @Override
            public void onFailure(Call<FilmeResultadoBusca> call, Throwable t) {

            }
        });

    }

    @Override
    public void getFilme(final String filmeId, FilmeServiceCallback<Filme> callback) {

    }
}
