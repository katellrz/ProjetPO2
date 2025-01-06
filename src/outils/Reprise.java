package outils;

import Gestion.Joueur;

public class Reprise {

    private int MoneyJoueur;
    private int VieJoueur;

    public Reprise(int Money, int Vie){
        this.MoneyJoueur = Money;
        this.VieJoueur=Vie;
    }

    public void Repprendre(Joueur joueur){
        joueur.setVie(this.VieJoueur);
        joueur.setArgent(this.MoneyJoueur);
    }
}