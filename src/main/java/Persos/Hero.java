package Persos;

public abstract class Hero extends Personnages implements HeroInterface {
    private boolean capaciteSpecialeUtilisee;

    public Hero(String nom, int pointsDeVie, int attaque, int defense) {
        super(nom, pointsDeVie, attaque, defense);
        this.capaciteSpecialeUtilisee = false;
    }

    public abstract void utiliserCapaciteSpeciale(Personnages cible);

    public boolean isCapaciteSpecialeUtilisee() {
        return capaciteSpecialeUtilisee;
    }

    public void setCapaciteSpecialeUtilisee(boolean capaciteSpecialeUtilisee) {
        this.capaciteSpecialeUtilisee = capaciteSpecialeUtilisee;
    }
}
