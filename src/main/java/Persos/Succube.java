package Persos;

public class Succube extends Hero {
    //La succube peut charmer un ennemi, celui-ci ne pourra pas l'attaquer durant un tour et peut passer à traver la défense adverse
    private Personnages personnageCharme;
    private boolean charmeActif;

    public Succube(String nom){
        super(nom, 60, 50, 5);
        this.charmeActif = false;
        this.personnageCharme = null;
    }

    @Override
    public void utiliserCapaciteSpeciale(Personnages cible) {
        if (!isCapaciteSpecialeUtilisee()) {
            this.personnageCharme = cible;
            this.charmeActif = true;
            setCapaciteSpecialeUtilisee(true);
            System.out.println(getNom() + " charme " + cible.getNom() + " !");
        } else {
            System.out.println("La capacité spéciale a déjà été utilisée !");
        }
    }

    // Méthode pour vérifier si un personnage est charmé
    public boolean estCharme(Personnages personnage) {
        return charmeActif && personnageCharme == personnage;
    }

    // Méthode pour réinitialiser le charme au début d'un nouveau tour
    public void finCharme() {
        this.charmeActif = false;
        this.personnageCharme = null;
    }

}