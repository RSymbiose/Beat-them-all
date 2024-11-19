package Niveaux;

import java.util.ArrayList;

public class Carte {
    private ArrayList<Object> Map;
    private double difficulty;

    public Carte(){
        this.Map=null;
        this.difficulty=0;
    }

    public Carte(ArrayList<Object> map, double difficulty){
        this.Map=map;
        this.difficulty=difficulty;
    }
}