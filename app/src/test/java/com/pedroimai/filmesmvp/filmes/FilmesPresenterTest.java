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

    private static List<Filme> VAZIO = new ArrayList<>(0);

    @Mock
    private FakeFilmeServiceImpl mApi;

    @Mock
    private FilmesContract.View mFilmesView;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<FilmeServiceApi.FilmeServiceCallback> mCarregaFilmesCallbackCaptor;

    private FilmesPresenter mFilmesPresenter;

    @Before
    public void setupNotesPresenter() {
        MockitoAnnotations.initMocks(this);

        mFilmesPresenter = new FilmesPresenter(mApi, mFilmesView);
    }

    @Test
    public void carregaFilmesDaApiEPopulaATela(){
        mFilmesPresenter.carregarFilmes(true);

        verify(mApi).getFilmes(mCarregaFilmesCallbackCaptor.capture());
        mCarregaFilmesCallbackCaptor.getValue().onLoaded(FILME_RESULTA_BUSCA);

        verify(mFilmesView).setCarregando(false);
        verify(mFilmesView).exibirFilmes(FILME_RESULTA_BUSCA.filmes);
    }

//
//    @Test
//    public void loadNotesFromRepositoryAndLoadIntoView() {
//        // Given an initialized NotesPresenter with initialized notes
//        // When loading of Notes is requested
//        mFilmesPresenter.loadNotes(true);
//
//        // Callback is captured and invoked with stubbed notes
//        verify(mNotesRepository).getNotes(mCarregaFilmesCallbackCaptor.capture());
//        mCarregaFilmesCallbackCaptor.getValue().onNotesLoaded(NOTES);
//
//        // Then progress indicator is hidden and notes are shown in UI
//        verify(mFilmesView).setProgressIndicator(false);
//        verify(mFilmesView).showNotes(NOTES);
//    }
//
//    @Test
//    public void clickOnFab_ShowsAddsNoteUi() {
//        // When adding a new note
//        mFilmesPresenter.addNewNote();
//
//        // Then add note UI is shown
//        verify(mFilmesView).showAddNote();
//    }
//
//    @Test
//    public void clickOnNote_ShowsDetailUi() {
//        // Given a stubbed note
//        Note requestedNote = new Note("Details Requested", "For this note");
//
//        // When open note details is requested
//        mFilmesPresenter.openNoteDetails(requestedNote);
//
//        // Then note detail UI is shown
//        verify(mFilmesView).showNoteDetailUi(any(String.class));
//    }
}
