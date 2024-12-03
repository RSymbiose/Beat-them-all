package Niveaux;

import Persos.Brigand;
import Persos.Catcheur;

import java.util.ArrayList;

public class Jungle extends Carte{
    private ArrayList<Object> Map;

    public Jungle(){
        super();
        int longueur = 9;
        this.Map=new ArrayList<Object>(longueur);
    }

    public void Difficulte(int diff){
        super.setDifficulty(diff);
        // fait une liste des endroits sur la map où vont spawner les ennemis
        ArrayList<Integer> spawnPlaces = new ArrayList<Integer>(diff);
        // de manière random, choisit les endroits de spawn des ennemis et les enregistre dans la liste
        for(int i=0; i<diff;i++){
            int indice = (int)(Math.random()*diff+1);
            if (!spawnPlaces.contains(indice)){
                spawnPlaces.add(indice);}
            else{spawnPlaces.add(indice+1);}
        }
        // spawnability : determined by smthing
        for (int i=0; i<diff;i++){
            for (int y=0; y<3;y++){
                Brigand B1= new Brigand("Tim");
                Brigand B2= new Brigand("Tom");
                Catcheur C= new Catcheur("Tam");

            }
        }
    }
}