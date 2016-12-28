package com.matthewregis.randomquiz.data.models;

import io.realm.RealmObject;

/**
 * Created by reg on 30/11/2016.
 */

public class ScoreModel extends RealmObject{

    String Id;
    int Score;
    String Name;
    String GameMode;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGameMode() {
        return GameMode;
    }

    public void setGameMode(String gameMode) {
        GameMode = gameMode;
    }


}
