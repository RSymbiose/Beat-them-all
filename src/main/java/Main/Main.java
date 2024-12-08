package Main;

import Persos.*;
import Niveaux.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Par Romain Jacovetti et Lola Schweyer.");

        Scanner scanner = new Scanner(System.in);
        Hero joueur = null;
        Carte niveau = null;
        ArrayList<Integer> sallesNettoyees; // Nouvelle liste pour stocker l'état des salles

        try (FileWriter logFile = new FileWriter("game_log.txt")) {
            logFile.write("Début de la partie\n");

            // Choix du héros
            System.out.println("=== Bienvenue dans le jeu ===");
            System.out.println("Choisissez votre héros");
            System.out.println("1. Succube (PV:120, ATT:50, DEF:5) - Peut charmer un ennemi");
            System.out.println("2. Chevalier Saint (PV:200, ATT:30, DEF:20) - Peut purifier un ennemi");
            System.out.println("3. Elfe (PV:160, ATT:40, DEF:10) - Peut tirer sur plusieurs ennemis");

            int choixHero = 0;
            while (choixHero < 1 || choixHero > 3) {
                System.out.print("Votre choix (1-3) : ");
                choixHero = scanner.nextInt();
            }

            System.out.print("Entrez votre pseudo en tant que joueur : ");
            scanner.nextLine();
            String nomHero = scanner.nextLine();

            switch (choixHero) {
                case 1 -> joueur = new Succube(nomHero);
                case 2 -> joueur = new ChevalierSaint(nomHero);
                case 3 -> joueur = new Elfe(nomHero);
            }

            logFile.write("Héros choisi : " + joueur.getNom() + "\n");

            // Choix de la carte
            System.out.println("\nChoisissez votre niveau:");
            System.out.println("1. Jungle");
            System.out.print("Votre choix (1) : ");
            int choixNiveau = scanner.nextInt();

            // Saisie de la difficulté
            System.out.println("\nChoisissez la difficulté:");
            System.out.println("1. Facile");
            System.out.println("2. Moyen");
            System.out.println("3. Difficile");
            System.out.print("Votre choix (1-3) : ");
            int choixDifficulte = scanner.nextInt();
            double difficulty = 0;
            switch (choixDifficulte) {
                case 1 -> difficulty = 3; // Facile
                case 2 -> difficulty = 5; // Moyen
                case 3 -> difficulty = 8; // Difficile
                default -> {
                    System.out.println("Choix de difficulté invalide.");
                    return;
                }
            }

            // Création de la carte avec la difficulté choisie
            if (choixNiveau == 1) {
                niveau = new Jungle("Jungle Mystérieuse", 10); // Longueur par défaut
                ((Jungle) niveau).difficulte((int) difficulty); // Casting de Carte à Jungle avant d'accéder à la méthode difficulte()
            }

            logFile.write("Carte choisie : " + niveau.getNom() + " avec difficulté " + difficulty + "\n");

            // Début de la partie
            System.out.println("\n=== Le jeu commence ===");
            logFile.write("Le jeu commence...\n");

            sallesNettoyees = new ArrayList<>(Collections.nCopies(niveau.getLongueur(), 0)); // Initialise une liste de longueur égale au niveau

            boolean partieEnCours = true;
            int position = 0;

            while (partieEnCours && joueur.estVivant() && position < niveau.getLongueur()) {
                System.out.println("\n=== Position actuelle : " + position + "/" + niveau.getLongueur() + " ===");
                logFile.write("Le héros est à la position " + position + ".\n");

                // Vérifier si la salle est nettoyée
                if (sallesNettoyees.get(position) == 0) {
                    System.out.println("Vous rencontrez des ennemis !");
                    logFile.write("Rencontre avec des ennemis.\n");

                    ArrayList<Ennemi> ennemis = creerRencontreAleatoire();

                    // Combat
                    while (!ennemis.isEmpty() && joueur.estVivant()) {
                        System.out.println("\n=== Tour de combat ===");
                        for (Ennemi ennemi : ennemis) {
                            System.out.println(ennemi.getNom() + " (PV: " + ennemi.getPointsDeVie() + ")");
                        }

                        System.out.println("\nActions disponibles :");
                        System.out.println("1. Attaquer");
                        System.out.println("2. Utiliser capacité spéciale");
                        System.out.print("Votre choix : ");
                        int action = scanner.nextInt();

                        if (action == 1) {
                            for (int i = 0; i < new Random().nextInt(5) + 1 && !ennemis.isEmpty(); i++) {
                                Ennemi cible = ennemis.get(0);
                                joueur.attaquer(cible);
                                logFile.write(joueur.getNom() + " attaque " + cible.getNom() + ".\n");
                                if (!cible.estVivant()) {
                                    logFile.write(cible.getNom() + " est vaincu.\n");
                                    ennemis.remove(cible);
                                }
                            }
                        } else if (action == 2 && !joueur.isCapaciteSpecialeUtilisee()) {
                            if (joueur instanceof Elfe) {
                                // Utiliser la méthode de tir multiple de l'elfe
                                ((Elfe) joueur).tirRapideMultiple(ennemis.toArray(new Ennemi[0]));  // Remplace Personnage par Ennemi
                                logFile.write(joueur.getNom() + " utilise sa capacité spéciale.\n");
                            } else {
                                joueur.utiliserCapaciteSpeciale(ennemis.get(0)); // Capacité spéciale pour Succube ou Chevalier Saint
                                logFile.write(joueur.getNom() + " utilise sa capacité spéciale sur " + ennemis.get(0).getNom() + ".\n");
                            }
                            joueur.setCapaciteSpecialeUtilisee(true);
                        } else {
                            System.out.println("Action invalide ou capacité déjà utilisée.");
                        }

                        // Riposte des ennemis
                        for (Ennemi ennemi : ennemis) {
                            if (ennemi.estVivant()) {
                                ennemi.attaquer(joueur);
                                logFile.write(ennemi.getNom() + " attaque " + joueur.getNom() + ".\n");
                                logFile.write(joueur.getNom() + " a maintenant " + joueur.getPointsDeVie() + " PV.\n");
                            }
                        }

                        // Check if the player can proceed
                        if (joueur.estVivant() && ennemis.isEmpty()) {
                            sallesNettoyees.set(position, 1); // Marque la salle comme nettoyée
                            break;  // Break out of combat loop if all enemies are defeated
                        }
                    }

                    // After combat, check if we need to fight again in the same position
                    if (!joueur.estVivant() || !ennemis.isEmpty()) {
                        position = 0;  // Reset position if there are still enemies
                    }
                } else {
                    System.out.println("Aucun ennemi ici. Vous avancez ou reculez.");
                    logFile.write("Le héros avance ou recule sans rencontrer d'ennemi.\n");

                    // Menu déplacement
                    System.out.println("\nActions disponibles :");
                    System.out.println("1. Avancer");
                    System.out.println("2. Reculer");
                    System.out.print("Votre choix : ");
                    int action = scanner.nextInt();

                    if (action == 1) {
                        position++; // Avancer
                    } else if (action == 2 && position > 0 && sallesNettoyees.get(position - 1) == 1) {
                        position--; // Reculer uniquement si la salle précédente est nettoyée
                    } else {
                        System.out.println("Action invalide ou position déjà à zéro.");
                    }
                }

                logFile.write("Position actuelle après action : " + position + ".\n");

                if (joueur.estVivant() && position >= niveau.getLongueur()) {
                    System.out.println("=== Victoire ! Vous avez terminé le parcours ! ===");
                    logFile.write("Victoire : Le héros a terminé le parcours.\n");
                    partieEnCours = false;
                }
            }

            if (!joueur.estVivant()) {
                System.out.println("=== Défaite ! Vous avez été vaincu. ===");
                logFile.write("Défaite : Le héros a été vaincu.\n");
            }

            logFile.write("Fin de la partie.\n");
            scanner.close();
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture du journal de jeu : " + e.getMessage());
        }
    }

    private static ArrayList<Ennemi> creerRencontreAleatoire() {
        ArrayList<Ennemi> ennemis = new ArrayList<>();
        Random rand = new Random();
        int nombreEnnemis = rand.nextInt(3) + 1; // Entre 1 et 3 ennemis par rencontre

        for (int i = 0; i < nombreEnnemis; i++) {
            switch (rand.nextInt(3)) {
                case 0 -> ennemis.add(new Brigand("Brigand_" + i));
                case 1 -> ennemis.add(new Catcheur("Catcheur_" + i));
                default -> ennemis.add(new Gangster("Gangster_" + i));
            }
        }

        return ennemis;
    }
}
