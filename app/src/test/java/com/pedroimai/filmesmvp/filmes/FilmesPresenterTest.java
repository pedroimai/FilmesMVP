/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pedroimai.filmesmvp.filmes;


import com.pedroimai.filmesmvp.data.FilmeServiceApi;
import com.pedroimai.filmesmvp.data.model.Filme;
import com.pedroimai.filmesmvp.data.model.FilmeResultadoBusca;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class FilmesPresenterTest {

    private static ArrayList<Filme> FILMES = new ArrayList<Filme>(){{
        add(new Filme("1","FILME A","2015",""));
        add(new Filme("2","FILME B","2014",""));
        add(new Filme("3","FILME C","2013",""));
    }};

    private static FilmeResultadoBusca FILME_RESULTA_BUSCA = new FilmeResultadoBusca(FILMES);

    //Classes MOCKADAS devem utilizar a notação @MOCK
    @Mock
    private FakeFilmeServiceImpl mApi;

    @Mock
    private FilmesContract.View mFilmesView;

    //IRÁ SIMULAR O COMPORTAMENTO DE UM CALLBACK
    @Captor
    private ArgumentCaptor<FilmeServiceApi.FilmeServiceCallback> mCarregaFilmesCallbackCaptor;

    private FilmesPresenter mFilmesPresenter;

    //IRÁ EXECUTAR ANTES DE COMEÇAR OS TESTES
    @Before
    public void setupFilmesPresenter() {
        //inicializa as classes mockadas
        MockitoAnnotations.initMocks(this);

        mFilmesPresenter = new FilmesPresenter(mApi, mFilmesView);
    }

    @Test
    public void carregaFilmesDaApiEPopulaATela(){
        //carrega filmes
        mFilmesPresenter.carregarFilmes();

        //callback é capturado e chamado com valores fake
        verify(mApi).getFilmes(mCarregaFilmesCallbackCaptor.capture());
        mCarregaFilmesCallbackCaptor.getValue().onLoaded(FILME_RESULTA_BUSCA);

        //o progress é escondido e os filmes são exibidos na tela
        verify(mFilmesView).setCarregando(false);
        verify(mFilmesView).exibirFilmes(FILME_RESULTA_BUSCA.filmes);
    }

}
