package com.pedroimai.filmesmvp.filmes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.pedroimai.filmesmvp.R;

public class FilmesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filmes_activity);

        if (null == savedInstanceState) {
            initFragment(FilmesFragment.newInstance());
        }
    }

    private void initFragment(Fragment filmesFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content, filmesFragment);
        transaction.commit();
    }
}
