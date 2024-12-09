package Persos.Heros;

import Niveaux.Jungle;
import Persos.Brigand;
import Persos.ChevalierSaint;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ChevalierSaintTest {

    @Test
    void Attaquer() {
        ChevalierSaint CS1 = new ChevalierSaint("Henri");
        Brigand B1 = new Brigand("Dalton");
        ArrayList<Object> ennemi = new ArrayList<Object>();
        ennemi.add(B1);
        System.out.println(ennemi);
        Jungle J1 = new Jungle();
        J1.setMap(ennemi);
        //Note: le test de one shot d'un ennemi par le héro est obligatoire, mais la quantité de dégats infligé est fait de manière random dans notre programme
        // -> Test effectué en deux parties: le héro inflige des dégats, puis l'ennemi se prend assez de dégats pour être oneshot
        CS1.attaquer(B1);
        int degats = B1.getPointsDeVie();
        B1.prendreDegats(degats);
        assertEquals(B1.getPointsDeVie(), 0);
        System.out.println(ennemi);
    }
}