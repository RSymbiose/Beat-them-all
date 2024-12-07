package Main;
import Persos.*;
import Niveaux.*;
import java.util.Scanner;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Par Romain Jacovetti et Lola Schweyer.");
        Scanner scanner = new Scanner(System.in);
        Hero joueur = null;
        Carte niveau = null;

        // Séléction du héros
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

        // Création du héros
        System.out.print("Entrez le nom de votre héros : ");
        scanner.nextLine(); // Fix: consume the leftover newline
        String nomHero = scanner.nextLine();
        
        switch (choixHero) {
            case 1:
                joueur = new Succube(nomHero);
                break;
            case 2:
                joueur = new ChevalierSaint(nomHero);
                break;
            case 3:
                joueur = new Elfe(nomHero);
                break;
        }

        // Sélection du niveau
        System.out.println("\nChoisissez votre niveau: ");
        System.out.println("1. Jungle");
        // Mettre les autres niveaux ici

        int choixNiveau = 0;
        while (choixNiveau != 1) { //ce qui sera à modifier
            System.out.print("Votre choix (1) : ");
            choixNiveau = scanner.nextInt();
        }

        // Sélection de la difficulté
        System.out.println("\nChoisissez la difficulté (1-3) :");
        System.out.println("1 = Facile, 2 = Normal, 3 = Difficile");

        int difficulte = 0;
        while (difficulte < 1 || difficulte > 5) {
            System.out.print("Votre choix (1-5) : ");
            difficulte = scanner.nextInt();
        }

        // Création et initialisation du niveau
        switch (choixNiveau) {
            case 1:
                niveau = new Jungle();
                ((Jungle)niveau).Difficulte(difficulte);
                break;
        }

        // Début du jeu
        System.out.println("\n=== Le jeu commence ===");
        System.out.println("Héros : " + joueur.getNom());
        System.out.println("Niveau : Jungle");
        System.out.println("Difficulté : " + difficulte);

        // Création des ennemis selon la difficulté
        ArrayList<Ennemi> ennemis = new ArrayList<>();
        for (int i = 0; i < difficulte; i++) {
            int typeEnnemi = (int)(Math.random() * 3) + 1;
            String nomEnnemi = "Ennemi " + (i + 1);

            switch (typeEnnemi) {
                case 1:
                    ennemis.add(new Brigand(nomEnnemi));
                    break;
                case 2:
                    ennemis.add(new Gangster(nomEnnemi));
                    break;
                case 3:
                    ennemis.add(new Catcheur(nomEnnemi));
                    break;
            }
        }

        // Boucle de jeu
        boolean partieEnCours = true;
        while (partieEnCours) {
            // Tour du joueur
            System.out.println("\n=== Tour de " + joueur.getNom() + " ===");
            System.out.println("PV: " + joueur.getPointsDeVie());
            System.out.println("\nEnnemis restants :");

            for (int i = 0; i < ennemis.size(); i++) {
                if (ennemis.get(i).estVivant()) {
                    System.out.println((i + 1) + ". " + ennemis.get(i).getNom() +
                            " (PV: " + ennemis.get(i).getPointsDeVie() + ")");
                }
            }

            System.out.println("\nActions disponibles :");
            System.out.println("1. Attaquer");
            System.out.println("2. Utiliser capacité spéciale");
            System.out.println("3. Quitter la partie");

            int action = scanner.nextInt();

            if (action == 3) {
                partieEnCours = false;
                continue;
            }

            // Sélection de la cible
            System.out.println("Choisissez une cible (1-" + ennemis.size() + ") :");
            int cible = scanner.nextInt() - 1;

            if (cible >= 0 && cible < ennemis.size()) {
                if (action == 1) {
                    joueur.attaquer(ennemis.get(cible));
                } else if (action == 2) {
                    joueur.utiliserCapaciteSpeciale(ennemis.get(cible));
                }
            }

            // Tour des ennemis
            for (Ennemi ennemi : ennemis) {
                if (ennemi.estVivant()) {
                    // Vérifie si l'ennemi n'est pas charmé (pour la Succube)
                    if (joueur instanceof Succube && ((Succube)joueur).estCharme(ennemi)) {
                        System.out.println(ennemi.getNom() + " est charmé et ne peut pas attaquer !");
                        continue;
                    }
                    ennemi.attaquer(joueur);
                }
            }

            // Vérifie les conditions de fin
            if (!joueur.estVivant()) {
                System.out.println("\n=== Game Over ===");
                System.out.println(joueur.getNom() + " a été vaincu !");
                partieEnCours = false;
            }

            boolean tousEnnemisVaincus = true;
            for (Ennemi ennemi : ennemis) {
                if (ennemi.estVivant()) {
                    tousEnnemisVaincus = false;
                    break;
                }
            }

            if (tousEnnemisVaincus) {
                System.out.println("\n=== Victoire ! ===");
                System.out.println("Tous les ennemis ont été vaincus !");
                partieEnCours = false;
            }
        }

        scanner.close();
        System.out.println("\nFin du jeu. Merci d'avoir joué !");
    }
}
