package com.danifitrianto.puzzlegamedani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private int eX = 3, eY = 3;
    private RelativeLayout patternGroup;
    private Button[][] button;
    private Character[] letters = {'a','b','c','d','e','f','g',
            'h','i','j','k','l','m','n','o'};

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        startGame();

        button[eX][eY].setText("");
        button[eX][eY].setBackgroundColor(ContextCompat.getColor(this, R.color.white));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.optionmenu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                startGame();
                return true;
            case R.id.menu2:
                this.finishAffinity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void rearrangePat(View view) {
        Button a = (Button) view;
        int x = a.getTag().toString().charAt(0)-'0';
        int y = a.getTag().toString().charAt(1)-'0';
        if ((Math.abs(eX - x) == 1 && eY == y) || (Math.abs(eY - y) == 1 && eX == x)) {
            button[eX][eY].setText(a.getText().toString());
            button[eX][eY].setBackgroundResource(R.drawable.white_button);
            a.setText("");
            a.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
            eX = x;
            eY = y;
            judges();
        }
    }

    private void startGame() {
        List<Character> list = Arrays.asList(letters);
        Collections.shuffle(list);
        list.toArray(letters);

        patternGroup = findViewById(R.id.patternWords);
        button = new Button[4][4];
        for (int i = 0; i < patternGroup.getChildCount(); i++) {
            button[i/4][i%4] = (Button) patternGroup.getChildAt(i);
        }

        for(int i = 0; i < patternGroup.getChildCount()-1; i++) {
            button[i/4][i%4].setText(String.valueOf(letters[i]));
        }
    }

    private void judges() {
        if(eX==3 && eY==3) {
            for(int i = 0; i < patternGroup.getChildCount() - 1; i++) {
                if (button[i / 4][i % 4].getText().toString()
                        .equals(String.valueOf(letters[i]))) {
                    Toast toast = Toast.makeText(this, "You Win!", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    break;
                }
            }
        }
    }
}