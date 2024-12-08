package Persos.Ennemies;

import Persos.Brigand;
import Persos.Gangster;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GangsterTest {
    @Test
    void attaquer() {
        Gangster G1 = new Gangster("G1", 70, 15, 1, "poison boots");
        Brigand B1 = new Brigand("B1", 60, 7, 0, false);
        G1.attaquer(B1);
        assertTrue(B1.getPointsDeVie()<60);
    }
}