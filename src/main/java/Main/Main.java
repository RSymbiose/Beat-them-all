package Main;

import Persos.*;
import Niveaux.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
            System.out.println("2. Desert");
            System.out.println("3. Chateau");
            System.out.print("Votre choix (1-3) : ");
            int choixNiveau = scanner.nextInt();

            if (choixNiveau == 1) {
                niveau = new Jungle();
            }else if (choixNiveau == 2){
                niveau = new Desert();
            }else if (choixNiveau == 3){
                niveau = new Chateau();
            }

            logFile.write("Carte choisie : " + niveau.getNom() + "\n");

            System.out.println("\n=== Le jeu commence ===");
            logFile.write("Le jeu commence...\n");

            // Choix de la difficulté
            System.out.println("Choisissez la difficulté:");
            System.out.println("1. Facile");
            System.out.println("2. Moyen");
            System.out.println("3. Difficile");
            System.out.print("Votre choix (1-3) : ");
            int choixDifficulte = scanner.nextInt();

            niveau.difficulte(choixDifficulte);

            boolean partieEnCours = true;
            int position = 0;

            while (partieEnCours && joueur.estVivant() && position < niveau.getLongueur()) {
                System.out.println("\n=== Position actuelle : " + position + "/" + niveau.getLongueur() + " ===");
                logFile.write("Le héros est à la position " + position + ".\n");

                ArrayList<Object> ennemisActuels = niveau.getEnemiesAtPosition(position);
                ennemisActuels.removeIf(ennemi -> !(ennemi instanceof Ennemi) || !((Ennemi) ennemi).estVivant()); // Nettoyage initial des ennemis morts

                if (!ennemisActuels.isEmpty()) {
                    System.out.println("Vous rencontrez des ennemis !");
                    logFile.write("Rencontre avec des ennemis.\n");

                    boolean combatTermine = false;
                    while (!ennemisActuels.isEmpty() && joueur.estVivant() && !combatTermine) {
                        System.out.println("\n=== Tour de combat ===");
                        for (Object monstre : ennemisActuels) {
                            System.out.println(((Ennemi) monstre).toString());
                        }

                        System.out.println("\nActions disponibles :");
                        System.out.println("1. Attaquer");
                        System.out.println("2. Utiliser capacité spéciale");
                        System.out.print("Votre choix : ");
                        int action = scanner.nextInt();

                        if (action == 1) {
                            Ennemi monstre = (Ennemi) ennemisActuels.get(0);
                            joueur.attaquer(monstre);
                            logFile.write(joueur.getNom() + " attaque " + monstre.getNom() + ".\n");

                            if (!monstre.estVivant()) {
                                logFile.write(monstre.getNom() + " est vaincu.\n");
                                System.out.println("L'adversaire est vaincu !");
                                ennemisActuels.remove(0);
                                niveau.clearEnemiesAtPosition(position); // Suppression du monstre de la position
                            }
                        } else if (action == 2) {
                            if (joueur instanceof Succube) {
                                ((Succube) joueur).utiliserCapaciteSpeciale((Ennemi) ennemisActuels.get(0));
                                logFile.write(joueur.getNom() + " utilise sa capacité spéciale.\n");
                            } else if (joueur instanceof ChevalierSaint) {
                                ((ChevalierSaint) joueur).utiliserCapaciteSpeciale((Ennemi) ennemisActuels.get(0));
                                logFile.write(joueur.getNom() + " utilise sa capacité spéciale.\n");
                            } else if (joueur instanceof Elfe) {
                                // Conversion du tableau en array de type Ennemi
                                Ennemi[] cibles = ennemisActuels.toArray(new Ennemi[0]);
                                ((Elfe) joueur).tirRapideMultiple(cibles);
                                logFile.write(joueur.getNom() + " utilise sa capacité spéciale.\n");
                            } else {
                                System.out.println("Action invalide ou capacité déjà utilisée.");
                            }
                        } else {
                            System.out.println("Action invalide ou capacité déjà utilisée.");
                        }

                        if (ennemisActuels.isEmpty()) {
                            combatTermine = true;
                            System.out.println("Tous les ennemis de cette salle ont été vaincus !");
                        } else {
                            // Si les ennemis sont encore là après le tour du joueur, ils attaquent
                            for (Object monstre : ennemisActuels) {
                                ((Ennemi) monstre).attaquer(joueur);
                                logFile.write(((Ennemi) monstre).getNom() + " attaque " + joueur.getNom() + ".\n");
                                System.out.println(((Ennemi) monstre).getNom() + " attaque " + joueur.getNom() + ".\n");
                            }
                        }
                        // Réinitialisation de la capacité spéciale après chaque tour
                        if (joueur instanceof Succube) {
                            ((Succube) joueur).finCharme();
                        }
                    }
                }

                if (ennemisActuels.isEmpty()) { // Si plus d'ennemis, proposer le déplacement
                    System.out.println("\nActions disponibles :");
                    System.out.println("1. Avancer");
                    System.out.println("2. Reculer");
                    System.out.print("Votre choix : ");
                    int action = scanner.nextInt();

                    if (action == 1) {
                        position++;
                    } else if (action == 2 && position > 0) {
                        position--;
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

        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture du fichier de log : " + e.getMessage());
        }

        scanner.close();
    }
}
