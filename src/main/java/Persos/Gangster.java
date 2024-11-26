// Classe Gangster
package Persos;

public class Gangster extends Ennemi {
    private String arme;

    public Gangster(String nom) {
        super(nom, 70, 12, 4);  // PV:70, Attaque:12, Défense:4
        this.arme = "MISE A MORT";
    }

    public Gangster(String nom, int pointsDeVie, int attaque, int defense, String arme) {
        super(nom, pointsDeVie, attaque, defense);
        this.arme = arme;
    }

    public String getArme() {
        return arme;
    }

    public void setArme(String arme) {
        this.arme = arme;
    }

    @Override
    public void attaquer(Personnages cible) {
        if (arme.equals("MISE A MORT")) {
            System.out.println(this.getNom() + " tire une rafale avec sa MISE A MORT !");
            int attaqueBonus = 5;
            this.setAttaque(this.getAttaque() + attaqueBonus);
            super.attaquer(cible);
            this.setAttaque(this.getAttaque() - attaqueBonus);
        } else {
            super.attaquer(cible);
        }
    }
}