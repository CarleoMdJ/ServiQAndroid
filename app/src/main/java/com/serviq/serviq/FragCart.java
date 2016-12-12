package com.serviq.serviq;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by hp on 12/12/2016.
 */

public class FragCart extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_cart, container,false);

        TextView textView = (TextView) view.findViewById(R.id.textView);
        Resources res = getResources();
        textView.setText("Estás en el carrito");

        return view;


    }
}
