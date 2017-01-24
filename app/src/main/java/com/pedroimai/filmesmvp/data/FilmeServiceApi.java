package com.pedroimai.filmesmvp.data;

import com.pedroimai.filmesmvp.data.model.Filme;
import com.pedroimai.filmesmvp.data.model.FilmeResultadoBusca;

/**
 * Created by Pedro on 24/01/2017.
 */

public interface FilmeServiceApi {

    interface FilmeServiceCallback<T> {

        void onLoaded(T filmes);
    }

    void getFilmes(FilmeServiceCallback<FilmeResultadoBusca> callback);

    void getFilme(String filmeId, FilmeServiceCallback<Filme> callback);
}
