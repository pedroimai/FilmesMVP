package com.pedroimai.filmesmvp.filmes;

import android.support.annotation.NonNull;

import com.pedroimai.filmesmvp.data.FilmeServiceApi;
import com.pedroimai.filmesmvp.data.model.Filme;
import com.pedroimai.filmesmvp.data.model.FilmeResultadoBusca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pedro on 24/01/2017.
 */

public class FilmesPresenter implements FilmesContract.UserActionsListener {
    private final FilmeServiceApi mApi;
    private final FilmesContract.View mFilmesView;

    public FilmesPresenter(FilmeServiceApi api, FilmesContract.View filmesView) {
        mApi = api;
        mFilmesView = filmesView;
    }

    @Override
    public void carregarFilmes(boolean forceUpdate) {
        mFilmesView.setCarregando(true);
        if (forceUpdate) {
            //mNotesRepository.refreshData();
        }

        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
        //EspressoIdlingResource.increment(); // App is busy until further notice


        mApi.getFilmes(new FilmeServiceApi.FilmeServiceCallback<FilmeResultadoBusca>(){
            @Override
            public void onLoaded(FilmeResultadoBusca resultadoBusca) {
                mFilmesView.setCarregando(false);
                mFilmesView.exibirFilmes(resultadoBusca.filmes);
            }
        });
    }

    @Override
    public void abrirDetalhes(@NonNull Filme filme) {

    }
}
