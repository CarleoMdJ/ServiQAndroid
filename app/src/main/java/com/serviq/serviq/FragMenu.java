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

public class FragMenu extends Fragment {
    private ComidaDataSource dataSource;
    private RecyclerView recyclerView;
    private ComidaAdapter adapter;
    private List<Comida> comidaList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_menu, container,false);

//        TextView textView = (TextView) view.findViewById(R.id.textView);
//        Resources res = getResources();
//        textView.setText("Estás en favoritos");

        /**
         * CardViews.
         */
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        comidaList = new ArrayList<>();
        adapter = new ComidaAdapter(getContext(), comidaList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        /**
         * Prueba de Database.
         */
        comidas();
        dataSource = new ComidaDataSource(getContext());
        dataSource.open();
        List<Comida> comidas = dataSource.findAll();
        if (comidas.size() == 0) {
            createData();
            comidas = dataSource.findAll();
        }

        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void comidas() {
        int[] portadas = new int[]{
                R.drawable.pizza,
                R.drawable.hotcakes,
                R.drawable.tacos
        };

        Comida c = new Comida("Pizza", 25.2, "Goood shit esa madre", 15, portadas[0]);
        comidaList.add(c);

        c = new Comida("Hotcakes", 35.2, "Goood shit esa madre", 30, portadas[1]);
        comidaList.add(c);

        c = new Comida("Tacos", 15.2, "Goood shit esa madre", 5, portadas[2]);
        comidaList.add(c);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        dataSource.open();
    }

    @Override
    public void onPause() {
        super.onPause();
        dataSource.close();
    }

    private void createData()
    {
        Comida comida = new Comida("Pizza", 19.8, "Good shit ;D", 20, "una imagen");
        comida = dataSource.create(comida);
        Log.i("My app", "" + comida.getId());

        Comida comida1 = new Comida("Hamburgesa", 29.8, "Good shit ;D", 40, "una imagen");
        comida = dataSource.create(comida1);
        Log.i("My app", "" + comida.getId());

    }

}