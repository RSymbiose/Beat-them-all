package Niveaux;

import java.util.ArrayList;

public class Carte {
    private double difficulty;
    private ArrayList<Object> Map; // Déclaration de la variable Map

    public Carte() {
        this.difficulty = 0;
        this.Map = new ArrayList<>(); // Initialisation de Map
    }

    public Carte(ArrayList<Object> map, double difficulty) {
        this.Map = map;
        this.difficulty = difficulty;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public ArrayList<Object> getMap() {
        return Map;
    }

    public void setMap(ArrayList<Object> map) {
        this.Map = map;
    }
}
