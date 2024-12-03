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
    }
}
