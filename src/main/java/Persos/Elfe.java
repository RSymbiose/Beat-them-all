package Persos;

public class Elfe extends Hero {
    //Avec son arc rapide, l'elfe peut tirer sur les 3 prochaines cases, mettant tous ses adversaires à -50% hp
    private static final int PORTEE_TIRS = 3;
    private boolean tirRapideActif;

    public Elfe(String nom) {
        super(nom, 160, 40, 10);
        this.tirRapideActif = false;
    }

    @Override
    public void utiliserCapaciteSpeciale(Personnages cible) {
        if (!isCapaciteSpecialeUtilisee()) {
            this.tirRapideActif = true;
            setCapaciteSpecialeUtilisee(true);

            // Simule le tir sur plusieurs cibles
            System.out.println(getNom() + " utilise Tir Rapide et vise les " + PORTEE_TIRS + " prochaines cases !");

            // Inflige les dégâts
            int degats = cible.getPointsDeVie() / 2;
            cible.prendreDegats(degats);
            System.out.println(cible.getNom() + " perd la moitié de ses points de vie !");

        } else {
            System.out.println("La capacité spéciale a déjà été utilisée !");
        }
    }

    // Méthode pour utiliser la capacité sur plusieurs cibles
    public void tirRapideMultiple(Personnages[] cibles) {
        if (!isCapaciteSpecialeUtilisee() && cibles != null) {
            setCapaciteSpecialeUtilisee(true);
            System.out.println(getNom() + " décoche une pluie de flèches !");

            // Ne prend en compte que les PORTEE_TIRS premières cibles
            int nbCibles = Math.min(cibles.length, PORTEE_TIRS);

            for (int i = 0; i < nbCibles; i++) {
                if (cibles[i] != null && cibles[i].estVivant()) {
                    int degats = cibles[i].getPointsDeVie() / 2;
                    cibles[i].prendreDegats(degats);
                    System.out.println(cibles[i].getNom() + " est touché par une flèche et perd la moitié de ses points de vie !");
                }
            }
            this.tirRapideActif = false;
        } else {
            System.out.println("La capacité spéciale a déjà été utilisée !");
        }
    }

    public boolean isTirRapideActif() {
        return tirRapideActif;
    }
}
