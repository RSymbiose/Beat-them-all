package Niveaux;

import Persos.Brigand;
import Persos.Catcheur;
import Persos.Gangster;
import java.util.Collections;
import java.util.ArrayList;

public class Jungle extends Carte {
    private ArrayList<Object> map; // Liste pour représenter les éléments de la carte
    private int longueur;         // Longueur du niveau
    private String nom;           // Nom du niveau

    // Constructeur par défaut
    public Jungle() {
        super();
        this.nom = "Jungle";
        this.longueur = 9;  // Valeur par défaut
        this.map = new ArrayList<>(longueur);
    }

    // Constructeur avec nom et longueur
    public Jungle(String nom, int longueur) {
        super();
        this.nom = nom;
        this.longueur = longueur;
        this.map = new ArrayList<>(longueur);
    }

    public void difficulte(int diff) {
        super.setDifficulty(diff);

        // Liste des endroits sur la carte où vont apparaître les ennemis
        ArrayList<Integer> spawnPlaces = new ArrayList<>(diff);

        // Choisir aléatoirement les emplacements pour les ennemis
        for (int i = 0; i < diff; i++) {
            int indice = (int) (Math.random() * longueur); // Aléatoire dans la longueur de la carte
            while (spawnPlaces.contains(indice)) {
                indice = (indice + 1) % longueur; // Évite les doublons
            }
            spawnPlaces.add(indice);
        }

        // Si la carte n'est pas encore initialisée, initialise-la correctement
        if (map == null || map.isEmpty()) {
            map = new ArrayList<>(Collections.nCopies(longueur, null));
        }

        // Peupler la carte avec des ennemis en fonction de la difficulté
        for (int place : spawnPlaces) {
            int typeEnnemi = (int) (Math.random() * 3); // 0: Brigand, 1: Catcheur, 2: autre ennemi
            switch (typeEnnemi) {
                case 0 -> map.set(place, new Brigand("Brigand_" + place));
                case 1 -> map.set(place, new Catcheur("Catcheur_" + place));
                default -> map.set(place, new Gangster("Gangster" + place));
            }
        }
    }


    // Getters et Setters
    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Object> getMap() {
        return map;
    }

    public void setMap(ArrayList<Object> map) {
        this.map = map;
    }
}