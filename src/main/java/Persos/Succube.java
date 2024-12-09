package Persos;

public class Succube extends Hero {
    private Personnages personnageCharme;
    public boolean charmeActif;

    public Succube(String nom) {
        super(nom, 120, 50, 5);
        this.charmeActif = false;
        this.personnageCharme = null;
    }

    @Override
    public void utiliserCapaciteSpeciale(Personnages cible) {
        if (!isCapaciteSpecialeUtilisee() && !charmeActif) {
            this.personnageCharme = cible;
            this.charmeActif = true;
            setCapaciteSpecialeUtilisee(true);
            System.out.println(getNom() + " charme " + cible.getNom() + " !");
        } else if (charmeActif) {
            System.out.println("La capacité spéciale est déjà active !");
        } else {
            System.out.println("La capacité spéciale a déjà été utilisée cette tour !");
        }
    }

    // Méthode pour vérifier si un personnage est charmé
    public boolean estCharme(Personnages personnage) {
        return charmeActif && personnageCharme == personnage;
    }

    // Réinitialiser la capacité spéciale après chaque tour
    public void finCharme() {
        this.charmeActif = false;
        this.personnageCharme = null;
    }

    @Override
    public void attaquer(Personnages cible) {
        // Si l'ennemi est charmé, on ne fait rien
        if (estCharme(cible)) {
            System.out.println(getNom() + " ne peut pas attaquer " + cible.getNom() + " car il est charmé.");
        } else {
            super.attaquer(cible);
        }
    }
}
