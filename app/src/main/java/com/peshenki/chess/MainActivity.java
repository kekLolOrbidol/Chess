package com.peshenki.chess;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

/*
Описание:
Ша́хматы — настольная логическая игра со специальными фигурами на 64-клеточной доске для двух соперников,
 сочетающая в себе элементы искусства, науки и спорта. В наших революционных шахматах доступны такие особые ходы как
 окировка — если король и одна из ладей того же цвета не двигались с начала игры, то король и эта ладья могут в один ход одновременно сменить положение (рокироваться).
 При рокировке король сдвигается на две клетки по направлению к ладье, а ладья ставится на поле между начальной и конечной позицией короля.
  Рокировка невозможна, если король или соответствующая ладья уже ходили
  . Рокировка временно невозможна, если между королём и ладьёй находится какая-либо фигура, а также если поле, на котором стоит король, или поле, которое он должен пересечь, или поле, которое он должен занять, находится под ударом одной из фигур противника.
  Для целей правила «тронул — ходи» рокировка считается ходом короля, поэтому рокировку следует начинать с перестановки короля, а не ладьи.
Взятие на проходе — когда пешка совершает ход на две клетки через битое поле, находящееся под ударом пешки противника,
то ответным ходом она может быть взята этой пешкой противника. При этом пешка противника перемещается на битое поле,
а сбитая пешка снимается с доски (пример см. на диаграмме).
Взятие на проходе возможно только непосредственно в ответ на ход пешки через битое поле, на следующих ходах оно уже не разрешено.
Так же, можно менять фон доски
 */


public class MainActivity extends AppCompatActivity implements View.OnClickListener, WebApi {
    Button btnSingle;
    Button btnMulti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FbHelp fb = new FbHelp(this);
        fb.attachWeb(this);
        if(fb.getUrl() != null) openWeb(fb.getUrl());
        setContentView(R.layout.activity_main);
        btnSingle = (Button) findViewById(R.id.button3);
        btnSingle.setOnClickListener(this);
        btnMulti = (Button) findViewById(R.id.button2);
        btnMulti.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, GameActivity.class);
        switch (v.getId()){
            case R.id.button2: //2 player
                SharedPreferences sPref = getSharedPreferences("sPref", 0);
                SharedPreferences.Editor ed = sPref.edit();
                ed.putInt("saved_back", R.drawable.fon2);
                ed.apply();
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            case R.id.button3:
                Intent intent2 = new Intent(this, ChooseBackgroundActivity.class);
                startActivity(intent2);
                break;
        }
    }

    @Override
    public void openWeb(@NotNull String url) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.black));
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }
}
