package com.example.judgementscoringapp;

import java.util.ArrayList;
import java.util.List;

public class ScoreScreenPreparer {
    MainActivity mainActivity;

    private List<String> playerInitials;

    public List<String> generateInitials(){
        playerInitials = new ArrayList<>();
        mainActivity = new MainActivity();
        List<String> players = mainActivity.getPlayers();

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
