package com.pedroimai.filmesmvp.filmes;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pedroimai.filmesmvp.R;
import com.pedroimai.filmesmvp.data.FilmeServiceImpl;
import com.pedroimai.filmesmvp.data.model.Filme;

import java.util.ArrayList;
import java.util.List;

public class FilmesFragment extends Fragment implements FilmesContract.View {

    private FilmesContract.UserActionsListener mActionsListener;

    private FilmesAdapter mListAdapter;

    public FilmesFragment() {
    }

    public static FilmesFragment newInstance() {
        return new FilmesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new FilmesAdapter(new ArrayList<Filme>(0), mItemListener);
        mActionsListener = new FilmesPresenter(new FilmeServiceImpl(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mActionsListener.carregarFilmes();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.filmes_fragment, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.filmes_list);
        recyclerView.setAdapter(mListAdapter);

        int numColumns = 2;

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

        SwipeRefreshLayout swipeRefreshLayout =
                (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mActionsListener.carregarFilmes();
            }
        });
        return root;
    }

    @Override
    public void setCarregando(final boolean ativo) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(ativo);
            }
        });
    }

    @Override
    public void exibirFilmes(List<Filme> filmes) {
        mListAdapter.replaceData(filmes);
    }

    @Override
    public void exibirDetalhesUI(String filmeId) {

    }

    ItemListener mItemListener = new ItemListener() {
        @Override
        public void onFilmeClick(Filme filme) {
            mActionsListener.abrirDetalhes(filme);
        }
    };

    private static class FilmesAdapter extends RecyclerView.Adapter<FilmesAdapter.ViewHolder> {

        private List<Filme> mFilmes;
        private ItemListener mItemListener;

        public FilmesAdapter(List<Filme> filmes, ItemListener itemListener) {
            setList(filmes);
            mItemListener = itemListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View noteView = inflater.inflate(R.layout.filme_item, parent, false);

            return new ViewHolder(noteView, mItemListener);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            Filme filme = mFilmes.get(position);

            viewHolder.titulo.setText(filme.titulo);
        }

        public void replaceData(List<Filme> notes) {
            setList(notes);
            notifyDataSetChanged();
        }

        private void setList(List<Filme> notes) {
            mFilmes = notes;
        }

        @Override
        public int getItemCount() {
            return mFilmes.size();
        }

        public Filme getItem(int position) {
            return mFilmes.get(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public TextView titulo;
            private ItemListener mItemListener;

            public ViewHolder(View itemView, ItemListener listener) {
                super(itemView);
                mItemListener = listener;
                titulo = (TextView) itemView.findViewById(R.id.filme_titulo);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                Filme filme = getItem(position);
                mItemListener.onFilmeClick(filme);

            }
        }
    }

    public interface ItemListener {

        void onFilmeClick(Filme clickedNote);
    }
}
