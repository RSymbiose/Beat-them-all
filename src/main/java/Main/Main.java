package Main;

import Persos.*;  // Assurez-vous d'importer toutes les classes de la package Persos
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
        Jungle niveau = null; // Utilisation de Jungle pour la carte
        ArrayList<Object> monstres = null; // Liste pour stocker les monstres de la carte

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

            // Demander la difficulté
            System.out.println("Choisissez la difficulté:");
            System.out.println("1. Facile");
            System.out.println("2. Moyen");
            System.out.println("3. Difficile");
            System.out.print("Votre choix (1-3) : ");
            int choixDifficulte = scanner.nextInt();

            niveau.difficulte(choixDifficulte); // Mise à jour de la difficulté de la carte
            monstres = niveau.getMap(); // Obtention de la liste des monstres sur la carte

            boolean partieEnCours = true;
            int position = 0;

            while (partieEnCours && joueur.estVivant() && position < niveau.getLongueur()) {
                System.out.println("\n=== Position actuelle : " + position + "/" + niveau.getLongueur() + " ===");
                logFile.write("Le héros est à la position " + position + ".\n");

                // Vérifier si la carte contient encore des monstres
                if (!monstres.isEmpty()) {
                    System.out.println("Vous rencontrez des ennemis !");
                    logFile.write("Rencontre avec des ennemis.\n");

                    // Combattre les ennemis
                    while (!monstres.isEmpty() && joueur.estVivant()) {
                        System.out.println("\n=== Tour de combat ===");
                        for (Object monstre : monstres) {
                            System.out.println(monstre); // Affiche le nom et les caractéristiques du monstre
                        }

                        System.out.println("\nActions disponibles :");
                        System.out.println("1. Attaquer");
                        System.out.println("2. Utiliser capacité spéciale");
                        System.out.print("Votre choix : ");
                        int action = scanner.nextInt();

                        if (action == 1) {
                            for (int i = 0; i < new Random().nextInt(5) + 1 && !monstres.isEmpty(); i++) {
                                Object monstre = monstres.get(0);
                                joueur.attaquer((Ennemi) monstre); // Cast pour correspondre à l'objet attendu
                                logFile.write(joueur.getNom() + " attaque " + ((Ennemi) monstre).getNom() + ".\n");
                                if (!((Ennemi) monstre).estVivant()) {
                                    logFile.write(((Ennemi) monstre).getNom() + " est vaincu.\n");
                                    monstres.remove(monstre); // Retire le monstre de la liste
                                }
                            }
                        } else if (action == 2 && !joueur.isCapaciteSpecialeUtilisee()) {
                            if (joueur instanceof Elfe) {
                                ((Elfe) joueur).tirRapideMultiple(monstres.toArray(new Ennemi[0]));
                                logFile.write(joueur.getNom() + " utilise sa capacité spéciale.\n");
                            } else {
                                joueur.utiliserCapaciteSpeciale((Ennemi) monstres.get(0));
                                logFile.write(joueur.getNom() + " utilise sa capacité spéciale sur " + ((Ennemi) monstres.get(0)).getNom() + ".\n");
                            }
                            joueur.setCapaciteSpecialeUtilisee(true);
                        } else {
                            System.out.println("Action invalide ou capacité déjà utilisée.");
                        }

                        // Riposte des ennemis
                        for (Object monstre : monstres) {
                            ((Ennemi) monstre).attaquer(joueur);
                            logFile.write(((Ennemi) monstre).getNom() + " attaque " + joueur.getNom() + ".\n");
                            logFile.write(joueur.getNom() + " a maintenant " + joueur.getPointsDeVie() + " PV.\n");
                        }

                        // Vérification pour savoir si le joueur peut continuer
                        if (joueur.estVivant() && monstres.isEmpty()) {
                            break; // Fin du combat si tous les ennemis sont vaincus
                        }
                    }

                    // Si le joueur n'a plus de points de vie ou s'il reste encore des monstres
                    if (!joueur.estVivant() || !monstres.isEmpty()) {
                        position = 0; // Réinitialise la position si le joueur est vaincu ou des monstres restent
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
                    } else if (action == 2 && position > 0) {
                        position--; // Reculer
                    } else {
                        System.out.println("Action invalide ou position déjà à zéro.");
                    }
                }

                logFile.write("Position actuelle après action : " + position + ".\n");

                // Vérifier si le joueur a atteint la fin de la carte
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

        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture du fichier de log : " + e.getMessage());
        }

        scanner.close();
    }
}
