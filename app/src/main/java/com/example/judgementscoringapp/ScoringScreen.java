package com.example.judgementscoringapp;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.judgementscoringapp.databinding.ActivityScrollingBinding;

import java.util.ArrayList;
import java.util.List;

public class ScoringScreen extends AppCompatActivity {

    private ActivityScrollingBinding binding;
    private String m_Text = "";

    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        doneButton = findViewById(R.id.scoreDoneButton);

        List<String> players = getIntent().getStringArrayListExtra("playersList");

        List<TextView> tableOutput = generateTable(players);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i= players.size()-1; i>=0; i--){
                    inputDialogue(players.get(i), tableOutput.get(i));
                }
            }
        });
    }

    private List<String> generateInitials(List<String> players){
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

    private List<TextView> generateTable(List<String> players){
        List<String> sirs = new ArrayList<>();
        sirs.add("N");
        sirs.add("K");
        sirs.add("C");
        sirs.add("F");
        sirs.add("L");

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
        List<TextView> allColumns = new ArrayList<>();

        for(int i = 0; i < numberOfGames; i++){
            TableRow row = new TableRow(this);
            TextView firstColumn = new TextView(this);
            String firstColumnString = "" + playerInitials.get(i%playerInitials.size()) + "    " + sirs.get(i%sirs.size()) + "    " + String.valueOf(new Integer(i+1));
            firstColumn.setText(firstColumnString);
            row.addView(firstColumn);
            for(int j = 1; j <= players.size(); j++){
                TextView column = new TextView(this);
                row.addView(column);
                allColumns.add(column);
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
                row.addView(column);
                allColumns.add(column);
            }
            scores.addView(row);
        }

        return allColumns;
    }

    private void inputDialogue(String player, TextView cell){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter number of hands for: " + player);

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cell.setText(input.getText().toString());
                dialog.cancel();
            }
        });
/*        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });*/

        builder.show();
    }
}