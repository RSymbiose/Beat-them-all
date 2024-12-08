package Niveaux;

import java.util.ArrayList;

public class Carte {
    private double difficulty;
    private ArrayList<Object> map; // Déclaration de la carte
    private String nom;            // Nouveau champ : nom de la carte
    private int longueur;          // Nouveau champ : longueur de la carte

    // Constructeurs
    public Carte() {
        this.difficulty = 0;
        this.map = new ArrayList<>();
        this.nom = "Carte"; // Nom par défaut
        this.longueur = 0;   // Longueur par défaut
    }

    public Carte(ArrayList<Object> map, double difficulty, String nom, int longueur) {
        this.map = map;
        this.difficulty = difficulty;
        this.nom = nom;
        this.longueur = longueur;
    }

    // Getter et setter pour la difficulté
    public double getDifficulty() {
        return difficulty;
    }

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

    // Getter et setter pour la longueur
    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }
}
