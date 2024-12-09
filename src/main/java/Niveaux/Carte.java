package Niveaux;

import java.util.ArrayList;
import java.util.Map;

public abstract class Carte {
    private double difficulty;
    private int longueur;
    private ArrayList<Object> map; // Déclaration de la carte
    private String nom;            // Nouveau champ : nom de la carte

    // Constructeurs
    public Carte() {
        this.difficulty = 0;
        this.map = new ArrayList<>();
        this.nom = "Carte"; // Nom par défaut
    }

    public Carte(ArrayList<Object> map, double difficulty, String nom) {
        this.map = map;
        this.difficulty = difficulty;
        this.nom = nom;
    }

    public ArrayList<Object> getEnemiesAtPosition(int position) {
        ArrayList<Object> enemies = new ArrayList<>();
        return enemies;
    }

    /**
     * Supprime tous les ennemis d'une position donnée.
     */
    public void clearEnemiesAtPosition(int position) {   }

    // Getter et setter pour la difficulté
    public double getDifficulty() {
        return difficulty;
    }

    public abstract void difficulte(int diff);

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    // Getter et setter pour la carte
    public ArrayList<Object> getMap() {
        return map;
    }

    public void setMap(ArrayList<Object> map) {
        this.map = map;
    }

    // Getter et setter pour le nom
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }
}
