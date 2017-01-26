package com.pedroimai.filmesmvp.filmes;

import android.support.annotation.NonNull;

import com.pedroimai.filmesmvp.data.model.Filme;

import java.util.List;

/**
 * Created by Pedro on 24/01/2017.
 */

public interface FilmesContract {

    interface View {
        void setCarregando(boolean isAtivo);

        void exibirFilmes(List<Filme> filme);

        void exibirDetalhesUI (String filmeId);
    }

    interface UserActionsListener {

        void carregarFilmes();

        void abrirDetalhes(@NonNull Filme filme);
    }
}
