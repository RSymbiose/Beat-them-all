package Persos;

public class ChevalierSaint extends Hero {
    //Peut purifier un ennemi, celui-ci inflige 50% de dégâts en moins
    private Personnages personnagePurifie;
    private boolean purificationActive;
    
    public ChevalierSaint(String nom) {
        super(nom, 200, 30, 20);
        this.purificationActive = false;
        this.personnagePurifie = null;
    }
    
    @Override
    public void utiliserCapaciteSpeciale(Personnages cible) {
        if (!isCapaciteSpecialeUtilisee()) {
            this.personnagePurifie = cible;
            this.purificationActive = true;
            setCapaciteSpecialeUtilisee(true);
            System.out.println(getNom() + " purifie " + cible.getNom() + " ! Ses dégâts sont réduits de moitié !");
            
            // Réduit l'attaque de la cible de moitié
            int nouvelleAttaque = cible.getAttaque() / 2;
            cible.setAttaque(nouvelleAttaque);
        } else {
            System.out.println("La capacité spéciale a déjà été utilisée !");
        }
    }
    
    public boolean estPurifie(Personnages personnage) {
        return purificationActive && personnagePurifie == personnage;
    }
    
    public void finPurification() {
        if (personnagePurifie != null && purificationActive) {
            // Restaure l'attaque originale
            personnagePurifie.setAttaque(personnagePurifie.getAttaque() * 2);
            this.purificationActive = false;
            this.personnagePurifie = null;
        }
    }
}
