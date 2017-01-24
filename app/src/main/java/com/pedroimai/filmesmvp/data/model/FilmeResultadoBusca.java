package com.pedroimai.filmesmvp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Pedro on 24/01/2017.
 */

public class FilmeResultadoBusca {
    @SerializedName("Search")
    public List<Filme> filmes;

    public FilmeResultadoBusca(){}

    public FilmeResultadoBusca(List<Filme> filmes) {
        this.filmes = filmes;
    }
}
