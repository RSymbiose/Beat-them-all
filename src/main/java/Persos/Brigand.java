package Persos;

public class Brigand extends Ennemi {
    //Peut posséder une arme, si armé -> alors il fait plus de dégâts
    private boolean armee;

    public Brigand(String nom) {
        super(nom, 50, 8, 3);  // PV:50, Attaque:8, Défense:3
        this.armee = true;
    }

    public Brigand(String nom, int pointsDeVie, int attaque, int defense, boolean armee) {
        super(nom, pointsDeVie, attaque, defense);
        this.armee = armee;
    }

    public boolean isArmee() {
        return armee;
    }

    public void setArmee(boolean armee) {
        this.armee = armee;
    }

    @Override
    public void attaquer(Personnages cible) {
        if (armee) {
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