package entites;

import Gestion.Joueur;
import Librairies.Point;
import Librairies.StdDraw;
import Map.Case;
import static Map.DetectionSouris.*;
import java.awt.Color;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import outils.Omnicient;




/**
 * La classe abstraite Tour représente une entité tour dans le jeu. 
 * Elle fournit les méthodes et propriétés de base pour toutes les tours.
 */
public abstract class Tour extends Entite {
    protected int Cost;

    
    /**
     * Constructeur de la classe Tour.
     * 
     * @param position La position initiale de la tour.
     */
    public Tour(int PV, int ATK, double ATKSpeed, double Range, Element Element, Point position, int cost) {
        super(PV, ATK, ATKSpeed,Range, Element, position);
        this.Cost = cost;
        
    }

    // Getters et setters
    public int getCost() {
        return Cost;
    }

    public Point getPosition(){
        return position;
    }


    /**
     * Place une tour sur une case constructible en fonction des interactions de l'utilisateur.
     * @param  joueur Le joueur qui place la tour.
     */

   

    public static void PlacerTour(Joueur Joueur) {
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();
    
        if (StdDraw.isMousePressed()) {
            // Archer
            if ((x < 849 && x > 721) && (y < 596 && y > 546) && Joueur.getArgent() >= 20) {
                System.out.println("Archer sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new Archer(c.getCenterCase());
                    Joueur.depenserArgent(t.getCost());
                    Omnicient.SavetoOmni(t);
                    System.out.println("Tour placée : Archer à " + t.getPosition());
                }
            }
            // WindCaster
            else if ((x < 987 && x > 859) && (y < 596 && y > 546) && Joueur.getArgent() >= 50) {
                System.out.println("WindCaster sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new WindCaster(c.getCenterCase());
                    Joueur.depenserArgent(t.getCost());
                    Omnicient.SavetoOmni(t);
                    System.out.println("Tour placée : WindCaster à " + t.getPosition());
                }
            }
            // WaterCaster
            else if ((x < 849 && x > 721) && (y < 536 && y > 486) && Joueur.getArgent() >= 50) {
                System.out.println("WaterCaster sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new WaterCaster(c.getCenterCase());
                    Joueur.depenserArgent(t.getCost());
                    Omnicient.SavetoOmni(t);
                    System.out.println("Tour placée : WaterCaster à " + t.getPosition());
                }
            }
            // EarthCaster
            else if ((x < 987 && x > 859) && (y < 536 && y > 486) && Joueur.getArgent() >= 100) {
                System.out.println("EarthCaster sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new EarthCaster(c.getCenterCase());
                    Joueur.depenserArgent(t.getCost());
                    Omnicient.SavetoOmni(t);
                    System.out.println("Tour placée : EarthCaster à " + t.getPosition());
                }
            }
            // FireCaster
            else if ((x < 849 && x > 721) && (y < 476 && y > 426) && Joueur.getArgent() >= 100) {
                System.out.println("FireCaster sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new FireCaster(c.getCenterCase());
                    Joueur.depenserArgent(t.getCost());
                    Omnicient.SavetoOmni(t);
                    System.out.println("Tour placée : FireCaster à " + t.getPosition());
                }
            }
            // IceCaster
            else if ((x < 849 && x > 721) && (y < 416 && y > 366) && Joueur.getArgent() >= 70) {
                System.out.println("IceCaster sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new IceCaster(c.getCenterCase());
                    Joueur.depenserArgent(t.getCost());
                    Omnicient.SavetoOmni(t);
                    System.out.println("Tour placée : IceCaster à " + t.getPosition());
                }
            }
            // PoisonCaster
            else if ((x < 987 && x > 859) && (y < 416 && y > 366) && Joueur.getArgent() >= 80) {
                System.out.println("PoisonCaster sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new PoisonCaster(c.getCenterCase());
                    Joueur.depenserArgent(t.getCost());
                    Omnicient.SavetoOmni(t);
                    System.out.println("Tour placée : PoisonCaster à " + t.getPosition());
                }
            }
            // GoldDigger
            else if ((x < 849 && x > 721) && (y < 356 && y > 306) && Joueur.getArgent() >= 20) {
                System.out.println("GoldDigger sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new GoldDigger(c.getCenterCase());
                    Joueur.depenserArgent(t.getCost());
                    Omnicient.SavetoOmni(t);
                    System.out.println("Tour placée : GoldDigger à " + t.getPosition());
                }
            }
            /* // Railgun
            else if ((x < 849 && x > 721) && (y < 356 && y > 306) && Joueur.getArgent() >= 150) {
                System.out.println("Railgun sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new Railgun(c.getCenterCase());
                    Joueur.depenserArgent(t.getCost());
                    Omnicient.SavetoOmni(t);
                    System.out.println("Tour placée : Railgun à " + t.getPosition());
                }
            } */
        }
    } 

     /**
     * fonction qui Attend qu'une case constructible soit sélectionnée par l'utilisateur.
     * 
     * @return une instance de Case représentant la case sélectionnée.
     */
    

    private static Case attendreCaseConstructible() {
        Case c = null;
        while (c == null) {
            if (StdDraw.isMousePressed()) {
                c = DetectionSourisCase(StdDraw.mouseX(), StdDraw.mouseY());
                if (c != null && c.getType() == Case.Casetype.CONSTRUCTIBLE && caseDispo(c)) {
                    return c;
                }
            }
        }
        return null;
    }

    public static boolean caseDispo(Case c ){
        List<Tour> tours = Omnicient.getPositionTours();
        for(Tour tour : tours ){
            if (tour.getPosition().equals(c.getCenterCase())) return false;
        }
        return true;
    }
    
 /**
     * Affiche la tour sur l'interface graphique.
     * 
     * @param tailleCase La taille de la case pour ajuster la taille de la tour.
     */

    public void afficheTour(double tailleCase) {
        // Dessine la tour
        Color tourColor = this.color;
        StdDraw.setPenColor(tourColor);
        StdDraw.filledCircle(position.getX(), position.getY(), tailleCase / 4.0); // Ajuster la taille selon besoin
    
        this.afficherVieT();
    }

    public void afficherVieT() {
        if (this.getPosition() == null || this.PVmax <= 0) {
            System.err.println("Erreur : position ou PV max invalide pour " + this);
            return;
        }

        double largeurActuelle = Math.max(0, (double) this.getPV() / this.PVmax * 50);
        double x = this.getPosition().getX();
        double y = this.getPosition().getY() - 25;

        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.filledRectangle(x, y, 50 / 2, 7 / 2);

        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledRectangle(x - (50 - largeurActuelle) / 2, y, largeurActuelle / 2, 7 / 2);

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(x, y, 50 / 2, 7 / 2);

        StdDraw.show();
    }

    public abstract Color getColor();


    protected double tempsDepuisDerniereAttaque = 0.0;
    protected LocalTime derniereAttaque = LocalTime.now();

    /**
     * Renvoie les ennemis à portée de la tour.
     * 
     * @param monstres La liste des ennemis.
     * @param portee   La portée de la tour.
     * @return Liste des ennemis à portée.
     */

    public List<Enemi> MonstreAportee(List<Enemi> monstres, double portee) {
        if (monstres == null || monstres.isEmpty()) {
            return new ArrayList<>();
        }
        List<Enemi> monstresAportee = new ArrayList<>();
        for (Enemi m : monstres) {
            if (m.getPosition().distance(this.position) / Omnicient.getSize() <= portee) {
                monstresAportee.add(m);
            }
        }
        return monstresAportee;

    /**
     * Renvoie l'ennemi avec le plus de points de vie à portée.
     * 
     * @param monstres La liste des ennemis.
     * @return Ennemi avec le plus de PV.
     */
    }

    public Enemi PlusDePV(List<Enemi> monstres) {
        if (!monstres.isEmpty()) {
            Enemi plusDePV = monstres.get(0);
            for (Enemi m : monstres) {
                if (m.getPV() > plusDePV.getPV()) {
                    plusDePV = m;
                }
            }
            return plusDePV;
        } else {
            return null;
        }
    }

    /**
     * Renvoie l'ennemi le plus avance  sur le chemin.
     * 
     * @param monstres La liste des ennemis.
     * @return Ennemi le plus avance.
     */

    public Enemi PlusAvancer(List<Enemi> monstres) {
        if (!monstres.isEmpty()) {
            Enemi plusAvancer = monstres.get(0);
            for (Enemi m : monstres) {
                if (m.getCurrentIndex() > plusAvancer.getCurrentIndex()) {
                    plusAvancer = m;
                } else if (m.getCurrentIndex() == plusAvancer.getCurrentIndex()) {
                    List<Case> chemin = Omnicient.getChemin();
                    if (m.getPosition().distance(chemin.get(m.getCurrentIndex() + 1).getCenterCase()) <
                        plusAvancer.getPosition().distance(chemin.get(plusAvancer.getCurrentIndex() + 1).getCenterCase())) {
                        plusAvancer = m;
                    }
                }
            }
            return plusAvancer;
        } else {
            return null;
        }
    }
    /**
     * Renvoie l'ennemi le plus proche de la tour
     * 
     * @param monstres La liste des ennemis.
     * @return Ennemi le plus proche.
     */

    public Enemi PlusProche(List<Enemi> monstres) {
        if (!monstres.isEmpty()) {
            Enemi plusProche = monstres.get(0);
            for (Enemi m : monstres) {
                if (m.getPosition().distance(this.position) < plusProche.getPosition().distance(this.position)) {
                    plusProche = m;
                }
            }
            return plusProche;
        } else {
            return null;
        }
    }


/**
 * Affiche une ligne entre la position de la tour et celle de l'ennemi attaqué pour representer une attaque simple.
 *
 * @param e L'ennemi attaqué,  Si l'ennemi est null, aucune ligne ne s' affiche.
 */

    public void afficheattaque(Enemi e) {
        if (e == null) {
            return;
        }
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.line(this.position.getX(), this.position.getY(), e.getPosition().getX(), e.getPosition().getY());
    }


/**
 * la methode effectue une attaque simple sur un ennemi cible,
 * Diminue les points de vie de l'ennemi par la valeur d'attaque de la tour
 * puis affiche une ligne pour représenter l'attaque.
 *
 * @param cible  L'ennemi ciblé par l'attaque.
 * @param Joueur Le joueur qui contrôle la tour.
 */
    protected void attaqueSimple(Enemi cible, Joueur Joueur) {
        if (cible != null) {
            cible.setPV(cible.getPV() - this.ATK);
            afficheattaque(cible);
        }
    }

  /**
 * la methode effectue une attaque collaterale sur les ennemis proches de la cible,
 * Les ennemis situes dans un rayon donne  autour de la cible subissent des dégâts,
 * Affiche des lignes entre les ennemis affectés pour représenter l'attaque collaterale.
 *
 * @param t        L'ennemi cible de l'attaque collatérale.
 * @param distance Le rayon d'effet de l'attaque collatérale.
 * @param Joueur   Le joueur qui contrôle la tour.
 */

    public void attaqueCollateral(Enemi t, double distance, Joueur Joueur) {
        for (Enemi tour : Omnicient.getPositionMonstre()) {
            if (tour.getPosition().distance(t.getPosition()) <= distance) {
                tour.setPV(tour.getPV() - this.ATK);
                afficheAattaqueCollateral(tour, t);
            }
        }
    
    }


    /**
 * Affiche une ligne entre la position d'un ennemi affecte par une attaque collatérale
 * et celle de l'ennemi cible pour representer les dégâts infligés.
 *
 * @param t     L'ennemi affecté par l'attaque collatérale.
 * @param cible L'ennemi cible initial de l'attaque collatérale.
 */

    public void afficheAattaqueCollateral(Enemi t, Enemi cible) {
        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.line(t.getPosition().getX(), t.getPosition().getY(), cible.getPosition().getX(), cible.getPosition().getY());
    }


    public void attaquer(Joueur joueur){}
}
     
