// Classe Gangster
package Persos;

public class Gangster extends Ennemi {
    private String arme;

    public Gangster(String nom) {
        // Valeurs par défaut pour un gangster
        super(nom, 70, 12, 4, 50);  // PV:70, Attaque:12, Défense:4, Or:50
        this.arme = "Tommy Gun";
    }

    public Gangster(String nom, int pointsDeVie, int attaque, int defense, int or, String arme) {
        super(nom, pointsDeVie, attaque, defense, or);
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
        if (arme.equals("Tommy Gun")) {
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