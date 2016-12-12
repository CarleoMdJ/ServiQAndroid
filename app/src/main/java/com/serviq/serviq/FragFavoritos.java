package com.serviq.serviq;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 12/12/2016.
 */

public class FragFavoritos extends Fragment {
    private ComidaDataSource dataSource;
    private RecyclerView recyclerView;
    private ComidaAdapter adapter;
    private List<Comida> comidaList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_favoritos, container,false);

        TextView textView = (TextView) view.findViewById(R.id.textView);
        Resources res = getResources();
        textView.setText("Estás en favoritos!");

        return view;
    }

}


