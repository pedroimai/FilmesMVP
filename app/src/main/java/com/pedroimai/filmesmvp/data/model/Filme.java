package com.pedroimai.filmesmvp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pedro on 24/01/2017.
 */

public class Filme {
    @SerializedName("imdbID")
    public String id;

    @SerializedName("Title")
    public String titulo;

    @SerializedName("Year")
    public String ano ;

    @SerializedName("Poster")
    public String posterUrl;

    public Filme() {
    }

    public Filme(String id, String titulo, String ano, String posterUrl) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.posterUrl = posterUrl;
    }
}
