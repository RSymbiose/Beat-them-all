package Persos.Ennemies;

import Persos.Brigand;

import static org.junit.jupiter.api.Assertions.*;

class BrigandTest {
    @org.junit.jupiter.api.Test
    void testToString() {
        Brigand B1 = new Brigand("B1", 60, 7, 0, false);
        assertEquals(B1.toString(), "Brigand{nom: B1, PV: 60, attaque: 7, defense: 1, armee: false}");
    }
}