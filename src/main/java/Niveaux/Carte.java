package Niveaux;

import java.util.ArrayList;

public class Carte {
    private double difficulty;

    public Carte(){
        this.difficulty=0;
    }

    public Carte(ArrayList<Object> map, double difficulty){
        this.Map=map;
        this.difficulty=difficulty;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }
}