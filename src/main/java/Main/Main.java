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
        Scanner.nextLine();
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
        While (choixNiveau != 1) { //ce qui sera à modifier
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

    }
}
