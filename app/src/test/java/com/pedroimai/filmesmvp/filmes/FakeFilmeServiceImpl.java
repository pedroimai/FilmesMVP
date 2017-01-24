package com.pedroimai.filmesmvp.filmes;

import com.pedroimai.filmesmvp.data.FilmeServiceApi;
import com.pedroimai.filmesmvp.data.model.Filme;
import com.pedroimai.filmesmvp.data.model.FilmeResultadoBusca;

/**
 * Created by Pedro on 24/01/2017.
 */

public class FakeFilmeServiceImpl implements FilmeServiceApi {

    private static final FilmeResultadoBusca FILMES_SERVICE_DATA = new FilmeResultadoBusca();
    private static final Filme SINGLE_FILME_SERVICE_DATA = new Filme();


    @Override
    public void getFilmes(final FilmeServiceCallback<FilmeResultadoBusca> callback) {
        callback.onLoaded(FILMES_SERVICE_DATA);
    }

    @Override
    public void getFilme(final String filmeId, FilmeServiceCallback<Filme> callback) {
        callback.onLoaded(SINGLE_FILME_SERVICE_DATA);
    }
}
