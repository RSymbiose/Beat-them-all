package Persos.Heros;

import Persos.Brigand;
import Persos.ChevalierSaint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChevalierSaintTest {
    @Test
    void Attaquer() {
        //Note: le test de one shot d'un ennemi par le héro est obligatoire, mais la quantité de dégats infligé est fait de manière random dans notre programme
        // -> Test effectué en deux parties: le héro inflige des dégats, puis l'ennemi se prend assez de dégats pour être oneshot.
        ChevalierSaint CS1 = new ChevalierSaint("Henri");
        Brigand B1 = new Brigand("Dalton");
        CS1.attaquer(B1);
        int degats = B1.getPointsDeVie();
        B1.prendreDegats(degats);
        assertEquals(B1.getPointsDeVie(), 0);
    }
}