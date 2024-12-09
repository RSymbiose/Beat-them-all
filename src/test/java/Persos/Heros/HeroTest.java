package Persos.Heros;

import Persos.ChevalierSaint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @Test
    void estVivant() {
        ChevalierSaint CS1 = new ChevalierSaint("Jack");
        assertTrue(CS1.estVivant());
        CS1.prendreDegats(210);
        assertFalse(CS1.estVivant());
    }
}