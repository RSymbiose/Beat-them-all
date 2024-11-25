package Persos;

public class Brigand extends Ennemi {
    private boolean armé;  // Indique si le brigand possède une arme

    public Brigand(String nom) {
        // Valeurs par défaut pour un brigand
        super(nom, 50, 8, 3, 20);  // PV:50, Attaque:8, Défense:3, Or:20
        this.armé = true;
    }

    public Brigand(String nom, int pointsDeVie, int attaque, int defense, int or, boolean armé) {
        super(nom, pointsDeVie, attaque, defense, or);
        this.armé = armé;
    }

    public boolean isArmé() {
        return armé;
    }

    public void setArmé(boolean armé) {
        this.armé = armé;
    }

    @Override
    public void attaquer(Personnages cible) {
        if (armé) {
            // Si le brigand est armé, il inflige plus de dégâts
            int attaqueBonus = 2;
            this.setAttaque(this.getAttaque() + attaqueBonus);
            super.attaquer(cible);
            this.setAttaque(this.getAttaque() - attaqueBonus);
        } else {
            super.attaquer(cible);
        }
    }
}