package com.peshenki.chess;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class ChooseBackgroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_background);

        List<BackgroundItem> items = new ArrayList<>();
        items.add(new BackgroundItem(R.drawable.fon));
        items.add(new BackgroundItem(R.drawable.fon2));
        items.add(new BackgroundItem(R.drawable.fon3));
        items.add(new BackgroundItem(R.drawable.fon5));
        items.add(new BackgroundItem(R.drawable.fon6));
        items.add(new BackgroundItem(R.drawable.fon7));
        items.add(new BackgroundItem(R.drawable.fon8));
        items.add(new BackgroundItem(R.drawable.back_main));

        final Intent intent = new Intent(this, GameActivity.class);

        final BackgroundAdapter backAdapter = new BackgroundAdapter(this, items, item -> {
            SharedPreferences sPref = getSharedPreferences("sPref", 0);
            SharedPreferences.Editor ed = sPref.edit();
            ed.putInt("saved_back", item.name);
            ed.apply();
            intent.putExtra("type", 2);
            startActivity(intent);
            //Log.e("kek2", "Callback");
        });
        RecyclerView recV = (RecyclerView) findViewById(R.id.recV);
        recV.setHasFixedSize(true);
        recV.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recV.setAdapter(backAdapter);

    }
}