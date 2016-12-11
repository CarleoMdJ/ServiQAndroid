package com.serviq.serviq;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ComidaDataSource dataSource;
    private RecyclerView recyclerView;
    private ComidaAdapter adapter;
    private List<Comida> comidaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * CardViews.
         */
        initCollapsingToolbar();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        comidaList = new ArrayList<>();
        adapter = new ComidaAdapter(this, comidaList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        /**
         * Prueba de Database.
         */
        comidas();
        dataSource = new ComidaDataSource(this);
        dataSource.open();
        List<Comida> comidas = dataSource.findAll();
        if (comidas.size() == 0) {
            createData();
            comidas = dataSource.findAll();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     * MAPEADO PAPU
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataSource.open();
    }

    @Override
    protected void onPause() {
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

