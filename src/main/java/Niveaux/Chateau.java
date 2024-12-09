package Niveaux;

import Persos.Brigand;
import Persos.Catcheur;
import Persos.Gangster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chateau extends Carte {
    private ArrayList<Object> map;
    private int longueur;
    private String nom;
    private Map<Object, Integer> enemyPositions;

    public Chateau() {
        super();
        this.nom = "Haut_Koenigsbourg";
        this.longueur = 9;
        this.map = new ArrayList<>();
        this.enemyPositions = new HashMap<>();
    }

    public Chateau(String nom, int longueur) {
        super();
        this.nom = nom;
        this.longueur = longueur;
        this.map = new ArrayList<>();
        this.enemyPositions = new HashMap<>();
    }

    @Override
    public void difficulte(int diff) {
        super.setDifficulty(diff);

        System.out.println("\n=== Génération de la carte ===");
        System.out.println("Difficulté : " + diff*3 + " ennemis à placer");
        System.out.println("Longueur de la carte : " + longueur + " salles\n");

        // Nettoyage de la carte précédente
        map.clear();
        enemyPositions.clear();

        // Choisir aléatoirement les emplacements pour les ennemis
        ArrayList<Integer> availablePositions = new ArrayList<>();
        for (int i = 0; i < longueur; i++) {
            availablePositions.add(i);
        }

        System.out.println("Placement des ennemis :");
        for (int i = 0; i < diff * 3; i++) { // Calculer le nombre d'ennemis à placer
            if (availablePositions.isEmpty()) break;

            int randomIndex = (int) (Math.random() * availablePositions.size());
            int position = availablePositions.remove(randomIndex);

            int typeEnnemi = (int) (Math.random() * 3);
            Object ennemi = switch (typeEnnemi) {
                case 0 -> new Brigand("Chevalier_" + position);
                case 1 -> new Catcheur("Jouteur_" + position);
                default -> new Gangster("Chasseur_" + position);
            };

            map.add(ennemi);
            enemyPositions.put(ennemi, position);

            System.out.println("- " + ennemi.getClass().getSimpleName() +
                    " placé dans la salle " + position);
        }

        // Afficher un résumé des positions
        System.out.println("\nRésumé des positions :");
        for (int i = 0; i < longueur; i++) {
            ArrayList<Object> enemiesAtPos = getEnemiesAtPosition(i);
            if (!enemiesAtPos.isEmpty()) {
                System.out.print("Salle " + i + " : ");
                for (Object enemy : enemiesAtPos) {
                    System.out.print(enemy.getClass().getSimpleName() + " ");
                }
                System.out.println();
            }
        }
        System.out.println("\nTotal ennemis placés : " + map.size());
        System.out.println("=== Fin de la génération ===\n");
    }

    @Override
    public ArrayList<Object> getEnemiesAtPosition(int position) {
        ArrayList<Object> enemies = new ArrayList<>();
        for (Map.Entry<Object, Integer> entry : enemyPositions.entrySet()) {
            if (entry.getValue() == position) {
                enemies.add(entry.getKey());
            }
        }
        return enemies;
    }

    /**
     * Supprime tous les ennemis d'une position donnée.
     */
    @Override
    public void clearEnemiesAtPosition(int position) {
        ArrayList<Object> toRemove = new ArrayList<>();
        for (Map.Entry<Object, Integer> entry : enemyPositions.entrySet()) {
            if (entry.getValue() == position) {
                toRemove.add(entry.getKey());
            }
        }
        for (Object enemy : toRemove) {
            enemyPositions.remove(enemy); // Supprime les ennemis de la position
            map.remove(enemy);           // Supprime les ennemis de la carte globale
        }
    }

    @Override
    public int getLongueur() {
        return longueur;
    }

    @Override
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
