package com.serviq.serviq;

import android.content.res.Resources;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class Main2Activity extends AppCompatActivity {
    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        inicializarTabs();


        /*//Tab 1
        th.setup();
        TabSpec ts1 = th.newTabSpec("Tab 1");
        ts1.setIndicator("Bienvenido");
        ts1.setContent(R.id.tabFav);
        th.addTab(ts1);

        //Tab 2
        th.setup();
        TabSpec ts2 = th.newTabSpec("Tab 2");
        ts1.setIndicator("Contenido");
        ts1.setContent(R.id.tabMenu);
        th.addTab(ts2);

        //Tab 3
        th.setup();
        TabSpec ts3 = th.newTabSpec("Tab 3");
        ts1.setIndicator("Despedida");
        ts1.setContent(R.id.tabCart);
        th.addTab(ts3);
*/
    }

    private void inicializarTabs(){
        tabHost = (FragmentTabHost)findViewById(R.id.tabHost);
        tabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);

        Resources res = getResources();

        TabHost.TabSpec tab1 =  tabHost.newTabSpec("tab1");
        TabHost.TabSpec tab2 =  tabHost.newTabSpec("tab2");
        TabHost.TabSpec tab3 =  tabHost.newTabSpec("tab3");

        tab1.setIndicator(res.getString(R.string.tabFav));
        tab2.setIndicator(res.getString(R.string.tabMenu));
        tab3.setIndicator(res.getString(R.string.tabCart));

        tabHost.addTab(tab1, FragFavoritos.class,null);
        tabHost.addTab(tab2, FragMenu.class,null);
        tabHost.addTab(tab3, FragCart.class,null);

    }
}
