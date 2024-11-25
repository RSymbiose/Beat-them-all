// Classe Gangster
package Persos;

public class Gangster extends Ennemi {
    private String arme;  // Type d'arme utilisée par le gangster
    private boolean enCostard;  // Si le gangster porte un costume

    public Gangster(String nom) {
        // Valeurs par défaut pour un gangster
        super(nom, 70, 12, 4, 50);  // PV:70, Attaque:12, Défense:4, Or:50
        this.arme = "Tommy Gun";
        this.enCostard = true;
    }

    public Gangster(String nom, int pointsDeVie, int attaque, int defense, int or, String arme, boolean enCostard) {
        super(nom, pointsDeVie, attaque, defense, or);
        this.arme = arme;
        this.enCostard = enCostard;
    }

    public String getArme() {
        return arme;
    }

    public void setArme(String arme) {
        this.arme = arme;
    }

    public boolean isEnCostard() {
        return enCostard;
    }

    public void setEnCostard(boolean enCostard) {
        this.enCostard = enCostard;
    }

    @Override
    public void attaquer(Personnages cible) {
        if (arme.equals("Tommy Gun")) {
            // Attaque spéciale avec Tommy Gun
            System.out.println(this.getNom() + " tire une rafale avec son Tommy Gun !");
            int attaqueBonus = 5;
            this.setAttaque(this.getAttaque() + attaqueBonus);
            super.attaquer(cible);
            this.setAttaque(this.getAttaque() - attaqueBonus);
        } else {
            super.attaquer(cible);
        }
    }
}