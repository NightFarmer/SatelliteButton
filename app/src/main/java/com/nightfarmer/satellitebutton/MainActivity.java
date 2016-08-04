package com.nightfarmer.satellitebutton;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {
    SatelliteView satelliteView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SatelliteView satelliteView = (SatelliteView) findViewById(R.id.satelliteView);
//        satelliteView.setRadius(250);
//        satelliteView.setAdapter(new SatelliteAdapter() {
//            @Override
//            public View createMenuItem(ViewGroup parent, int index) {
//                return LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_item, parent, false);
//            }
//
//            @Override
//            public View createToggleItem(ViewGroup parent) {
//                return LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_toggle, parent, false);
//            }
//
//            @Override
//            public int getCount() {
//                return 10;
//            }
//        });

//        satelliteView = (SatelliteView) findViewById(R.id.satelliteView2);
//        satelliteView.setRadius(230);
//        satelliteView.setAdapter(getAdapter());
//        satelliteView = (SatelliteView) findViewById(R.id.satelliteView3);
//        satelliteView.setRadius(230);
//        satelliteView.setAdapter(getAdapter());
//        satelliteView = (SatelliteView) findViewById(R.id.satelliteView4);
//        satelliteView.setRadius(230);
//        satelliteView.setAdapter(getAdapter());
//        satelliteView = (SatelliteView) findViewById(R.id.satelliteView5);
//        satelliteView.setRadius(230);
//        satelliteView.setAdapter(getAdapter());

        satelliteView = (SatelliteView) findViewById(R.id.satelliteView6);
        satelliteView.setAdapter(getAdapter2());
        satelliteView.setRadius(200);
        satelliteView = (SatelliteView) findViewById(R.id.satelliteView7);
        satelliteView.setAdapter(getAdapter2());
        satelliteView.setRadius(200);
        satelliteView = (SatelliteView) findViewById(R.id.satelliteView8);
        satelliteView.setAdapter(getAdapter2());
        satelliteView.setRadius(200);
        satelliteView = (SatelliteView) findViewById(R.id.satelliteView9);
        satelliteView.setAdapter(getAdapter2());
        satelliteView.setRadius(200);
    }

    @NonNull
    private SatelliteAdapter getAdapter() {
        return new SatelliteAdapter() {
            @Override
            public View createMenuItem(ViewGroup parent, int index) {
                return LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_item, parent, false);
            }

            @Override
            public View createToggleItem(ViewGroup parent) {
                return LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_toggle, parent, false);
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @NonNull
    private SatelliteAdapter getAdapter2() {
        return new SatelliteAdapter() {
            @Override
            public View createMenuItem(ViewGroup parent, int index) {
                return LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_item, parent, false);
            }

            @Override
            public View createToggleItem(ViewGroup parent) {
                return LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_toggle, parent, false);
            }

            @Override
            public int getCount() {
                return 6;
            }
        };
    }
}
