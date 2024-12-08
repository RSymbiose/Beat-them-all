package Persos;

public interface HeroInterface {

    boolean capaciteSpecialeUtilisee = false;
    public abstract void utiliserCapaciteSpeciale(Personnages cible);
    public boolean isCapaciteSpecialeUtilisee();
    public void setCapaciteSpecialeUtilisee(boolean capaciteSpecialeUtilisee);
}
