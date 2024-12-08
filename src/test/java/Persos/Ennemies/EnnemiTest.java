package Persos.Ennemies;

import Persos.Catcheur;
import Persos.Ennemi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnnemiTest {
    @Test
    void prendreDegats() {
        Ennemi E1 = new Catcheur("E1", 20, 10, 10, "sheeeeeesh");
        E1.prendreDegats(10);
        assertEquals(E1.getPointsDeVie(), 10);
    }
}