package Main;

import Persos.*;  // Assurez-vous d'importer toutes les classes de la package Persos
import Niveaux.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Par Romain Jacovetti et Lola Schweyer.");

        Scanner scanner = new Scanner(System.in);
        Hero joueur = null;
        Carte niveau = null;

        try (FileWriter logFile = new FileWriter("game_log.txt")) {
            logFile.write("Début de la partie\n");

            // Choix du héros
            System.out.println("=== Bienvenue dans le jeu ===");
            System.out.println("Choisissez votre héros");
            System.out.println("1. Succube (PV:60, ATT:50, DEF:5) - Peut charmer un ennemi");
            System.out.println("2. Chevalier Saint (PV:100, ATT:30, DEF:20) - Peut purifier un ennemi");
            System.out.println("3. Elfe (PV:80, ATT:40, DEF:10) - Peut tirer sur plusieurs ennemis");

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

            // Création de la carte
            if (choixNiveau == 1) {
                niveau = new Jungle("Jungle Mystérieuse", 10);
            }

            logFile.write("Carte choisie : " + niveau.getNom() + "\n");

            // Début de la partie
            System.out.println("\n=== Le jeu commence ===");
            logFile.write("Le jeu commence...\n");

            boolean partieEnCours = true;
            int position = 0;

            while (partieEnCours && joueur.estVivant() && position < niveau.getLongueur()) {
                System.out.println("\n=== Position actuelle : " + position + "/" + niveau.getLongueur() + " ===");
                logFile.write("Le héros est à la position " + position + ".\n");

                // Rencontre aléatoire
                if (Math.random() < 0.5) {
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
                    }
                } else {
                    System.out.println("Aucun ennemi ici. Vous avancez.");
                    logFile.write("Le héros avance sans rencontrer d'ennemi.\n");
                }

                position++;
            }

            if (joueur.estVivant() && position >= niveau.getLongueur()) {
                System.out.println("=== Victoire ! Vous avez terminé le parcours ! ===");
                logFile.write("Victoire : Le héros a terminé le parcours.\n");
            } else if (!joueur.estVivant()) {
                System.out.println("=== Défaite ! Vous avez été vaincu. ===");
                logFile.write("Défaite : Le héros a été vaincu.\n");
            }

        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture du fichier de log : " + e.getMessage());
        }

        scanner.close();
        System.out.println("\nFin du jeu. Merci d'avoir joué !");
    }

    private static ArrayList<Ennemi> creerRencontreAleatoire() {
        ArrayList<Ennemi> ennemis = new ArrayList<>();
        Random random = new Random();
        int nbEnnemis = random.nextInt(3) + 1;

        for (int i = 0; i < nbEnnemis; i++) {
            switch (random.nextInt(3)) {
                case 0 -> ennemis.add(new Brigand("Brigand " + (i + 1)));
                case 1 -> ennemis.add(new Gangster("Gangster " + (i + 1)));
                case 2 -> ennemis.add(new Catcheur("Catcheur " + (i + 1)));
            }
        }

        return ennemis;
    }
}
