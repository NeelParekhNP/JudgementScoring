package com.example.judgementscoringapp;

import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.judgementscoringapp.databinding.ActivityScrollingBinding;

import java.util.ArrayList;
import java.util.List;

public class ScoringScreen extends AppCompatActivity {

    private ActivityScrollingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        List<String> sirs = new ArrayList<>();
        sirs.add("N");
        sirs.add("K");
        sirs.add("C");
        sirs.add("F");
        sirs.add("L");

        List<String> players = getIntent().getStringArrayListExtra("playersList");
        List<String> playerInitials = generateInitials(players);

        TableLayout scores = (TableLayout)findViewById(R.id.score_table);
        scores.setStretchAllColumns(true);
        scores.bringToFront();
        TableRow tr =  new TableRow(this);
        TextView columnOne = new TextView(this);;
        columnOne.setText("Guide");
        tr.addView(columnOne);
        for(String player : playerInitials){
            TextView column = new TextView(this);
            column.setText(player);
            tr.addView(column);
        }
        scores.addView(tr);

        int numberOfGames = 52/players.size();

        for(int i = 0; i < numberOfGames; i++){
            TableRow row = new TableRow(this);
            TextView firstColumn = new TextView(this);
            String firstColumnString = "" + playerInitials.get(i%playerInitials.size()) + "    " + sirs.get(i%sirs.size()) + "    " + String.valueOf(new Integer(i+1));
            firstColumn.setText(firstColumnString);
            row.addView(firstColumn);
            for(int j = 1; j <= players.size(); j++){
                TextView column = new TextView(this);
                column.setText(String.valueOf(new Integer(i +1)));
                row.addView(column);
            }
            scores.addView(row);
        }

        for(int i = numberOfGames; i >= 1; i--){
            TableRow row = new TableRow(this);
            TextView firstColumn = new TextView(this);
            String firstColumnString = "" + playerInitials.get(i%playerInitials.size()) + "    " + sirs.get(i%sirs.size()) + "    " + String.valueOf(new Integer(i));
            firstColumn.setText(firstColumnString);
            row.addView(firstColumn);
            for(int j = 1; j <= players.size(); j++){
                TextView column = new TextView(this);
                column.setText(String.valueOf(new Integer(i)));
                row.addView(column);
            }
            scores.addView(row);
        }
    }

    public List<String> generateInitials(List<String> players){
        List<String> playerInitials = new ArrayList<>();

        for(String player : players){
            String initial = player.substring(0,1);
            if(playerInitials.contains(initial)){
                playerInitials.add(player.substring(0,2));
            }
            else{
                playerInitials.add(initial);
            }
        }
        return playerInitials;
    }
}