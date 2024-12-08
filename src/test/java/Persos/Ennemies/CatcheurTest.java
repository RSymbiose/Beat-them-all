package Persos.Ennemies;

import Persos.Catcheur;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatcheurTest {

    @Test
    void getPriseSignature() {
        Catcheur C1 = new Catcheur("C1");
        assertEquals(C1.getPriseSignature(), "Suplex suuuuuuu");
    }

    @Test
    void isEnColere() {
        Catcheur C1 = new Catcheur("C1", 60, 7, 1, "Coup de pied retourné");
        assertFalse(C1.isEnColere());
        C1.setEnColere(true);
        assertTrue(C1.isEnColere());
    }

    @Test
    void prendreDegats() {
        Catcheur C1 = new Catcheur("C1", 60, 7, 1, "Coup de pied retourné");
        C1.prendreDegats(10);
        assertEquals(C1.getPointsDeVie(), 50);
        C1.prendreDegats(50);
        assertEquals(C1.getPointsDeVie(), 0);
    }
}