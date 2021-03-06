package com.example.judgementscoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> players;
    private EditText nameInput;
    private TextView mainView;

    private Button submitButton, doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainView = (TextView) findViewById(R.id.main_title);

        players = new ArrayList<>();
        nameInput = (EditText) findViewById(R.id.editTextTextPersonName);

        submitButton = (Button) findViewById(R.id.button);
        doneButton = (Button) findViewById(R.id.button2);

        nameInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                boolean handled = false;
                if (i == EditorInfo.IME_ACTION_NEXT){
                    String name = nameInput.getText().toString().trim();
                    if (TextUtils.isEmpty(name)){
                        nameInput.setError("Please Enter a Name!");
                    }else {
                        players.add(nameInput.getText().toString());
                        nameInput.setText("");
                    }
                }
                return handled;
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    nameInput.setError("Please Enter a Name!");
                }else {
                    players.add(nameInput.getText().toString());
                    nameInput.setText("");
                }
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainView.setText(players.toString());
                Intent intent = new Intent(MainActivity.this, ScoringScreen.class);
                intent.putStringArrayListExtra("playersList", (ArrayList<String>) players);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }
}