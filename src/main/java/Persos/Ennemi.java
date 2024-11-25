package Persos;

public abstract class Ennemi extends Personnages {
    protected int or;  // Quantité d'or que l'ennemi possède
    protected boolean agressif;  // État d'agressivité de l'ennemi
    protected int experienceDonnee;  // Points d'expérience donnés quand vaincu

    public Ennemi(String nom, int pointsDeVie, int attaque, int defense, int or) {
        super(nom, pointsDeVie, attaque, defense);
        this.or = or;
        this.agressif = true;
        this.experienceDonnee = pointsDeVie / 5;  // L'expérience donnée est basée sur les PV
    }

    // Getters
    public int getOr() {
        return or;
    }

    public boolean isAgressif() {
        return agressif;
    }

    public int getExperienceDonnee() {
        return experienceDonnee;
    }

    // Setters
    public void setOr(int or) {
        this.or = or;
    }

    public void setAgressif(boolean agressif) {
        this.agressif = agressif;
    }

    public void setExperienceDonnee(int experienceDonnee) {
        this.experienceDonnee = experienceDonnee;
    }

    // Méthodes spécifiques aux ennemis
    @Override
    public void prendreDegats(int degats) {
        super.prendreDegats(degats);
        // L'ennemi devient plus agressif quand il est blessé
        if (this.pointsDeVie < this.pointsDeVie / 2) {
            this.agressif = true;
            System.out.println(this.nom + " devient plus agressif !");
        }
    }

    // Méthode pour lâcher le butin quand vaincu
    public void lacherButin() {
        if (!this.estVivant()) {
            System.out.println(this.nom + " laisse tomber " + this.or + " pièces d'or.");
            System.out.println("Vous gagnez " + this.experienceDonnee + " points d'expérience.");
        }
    }

    // Méthode pour fuir le combat
    public void fuir() {
        if (this.pointsDeVie < this.pointsDeVie / 4) {  // Fuit si moins de 25% PV
            System.out.println(this.nom + " tente de fuir le combat !");
            double chanceDeFuite = 0.4;  // 40% de chance de fuir
            if (Math.random() < chanceDeFuite) {
                System.out.println(this.nom + " a réussi à s'enfuir !");
                this.or = this.or / 2;  // Perd la moitié de son or en fuyant
            } else {
                System.out.println(this.nom + " n'a pas réussi à s'enfuir !");
            }
        }
    }

    // Méthode pour intimider l'adversaire
    public void intimider(Personnages cible) {
        if (this.agressif) {
            System.out.println(this.nom + " tente d'intimider " + cible.getNom() + " !");
            // Peut être utilisé par les classes filles pour réduire la défense de la cible
        }
    }

    @Override
    public void attaquer(Personnages cible) {
        // Si l'ennemi est agressif, il a une chance de faire un coup critique
        if (this.agressif && Math.random() < 0.2) {  // 20% de chance de coup critique
            int attaqueInitiale = this.attaque;
            System.out.println(this.nom + " prépare une attaque critique !");
            this.attaque *= 1.5;  // Augmente les dégâts de 50%
            super.attaquer(cible);
            this.attaque = attaqueInitiale;  // Restaure l'attaque normale
        } else {
            super.attaquer(cible);
        }

        // Possibilité de fuir après l'attaque
        this.fuir();
    }
}