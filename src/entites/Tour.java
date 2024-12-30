package entites;

import Librairies.Point;
import Librairies.StdDraw;
import Map.Case;

import static Map.DetectionSouris.*;

import java.awt.Color;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import outils.Omnicient;



public abstract class Tour extends Entite {
    protected int Cost;
    protected Point position;
    protected int MaxPV;

    // Constructeur de la classe TOURS
    public Tour(Point position) {
        super();
        this.position = position;
        // this.Cost = cost;
        // this.MaxPV = PV;
    }

    // Getters et setters
    public int getCost() {
        return Cost;
    }

    public Point getPosition(){
        return position;
    }

    @Override
    public void estMort(){
        if(this.PV <= 0){
            Omnicient.removeTour(this);
        }
    }



    public static void PlacerTour() {
        double x = StdDraw.mouseX();
        double y = StdDraw.mouseY();
    
        if (StdDraw.isMousePressed()) {
            if ((x < 849 && x > 721) && (y < 596 && y > 546)) { // Archer
                System.out.println("Archer sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new Archer(c.getCenterCase());
                    Omnicient.SavetoOmni(t);
                }
            } else if ((x < 987 && x > 859) && (y < 596 && y > 546)) { // WindCaster
                System.out.println("WindCaster sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new WindCaster(c.getCenterCase());
                    Omnicient.SavetoOmni(t);
                }
            } else if ((x < 849 && x > 721) && (y < 536 && y > 486)) { // WaterCaster
                System.out.println("WaterCaster sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new WaterCaster(c.getCenterCase());
                    Omnicient.SavetoOmni(t);
                }
            } else if ((x < 987 && x > 859) && (y < 536 && y > 486)) { // EarthCaster
                System.out.println("EarthCaster sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new EarthCaster(c.getCenterCase());
                    Omnicient.SavetoOmni(t);
                }
            } else if ((x < 849 && x > 721) && (y < 476 && y > 426)) { // FireCaster
                System.out.println("FireCaster sélectionné");
                Case c = attendreCaseConstructible();
                if (c != null) {
                    Tour t = new FireCaster(c.getCenterCase());
                    Omnicient.SavetoOmni(t);
                }
            }
        }
    }
    
    // Fonction pour attendre que l'utilisateur clique sur une case constructible
    private static Case attendreCaseConstructible() {
        Case c = null;
        while (c == null) {
            if (StdDraw.isMousePressed()) {
                c = DetectionSourisCase(StdDraw.mouseX(), StdDraw.mouseY());
                if (c != null && c.getType() == Case.Casetype.CONSTRUCTIBLE) {
                    return c;
                }
            }
        }
        return null;
    }
    


    public void afficheTour(double tailleCase) {
        // Dessine la tour
        Color tourColor = this.getColor();
        StdDraw.setPenColor(tourColor);
        StdDraw.filledCircle(position.getX(), position.getY(), tailleCase / 4.0); // Ajuster la taille selon besoin
    
        //this.afficherVieT();
    }

    public void afficherVieT() {

        // Calculer la largeur actuelle en fonction des points de vie
        double largeurActuelle = (double)this.getPV() /this.getMaxPV() * 50;

        // Dessiner le fond de la barre (gris)
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        StdDraw.filledRectangle(this.getPosition().getX(),this.getPosition().getY() - 25, 50 / 2, 7 / 2);

        // Dessiner la barre de vie (verte)
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledRectangle(this.getPosition().getX() - (50 - largeurActuelle) / 2,this.getPosition().getY() - 25,
                largeurActuelle / 2, 7 / 2);

        // Contour de la barre (noir)
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(this.getPosition().getX(),this.getPosition().getX() - 25, 50 / 2, 7 / 2);

        StdDraw.show();

    }

    public abstract int getMaxPV();

    public abstract Color getColor();

    protected double tempsDepuisDerniereAttaque = 0.0; // Chronométrage des attaques
    protected LocalTime derniereAttaque = LocalTime.now(); // Chronométrage des attaques

    public abstract void attaquer();

    protected boolean peutAttaquer() {
        Duration d = Duration.between(derniereAttaque, LocalTime.now());
        tempsDepuisDerniereAttaque = d.toMillis();
        if (tempsDepuisDerniereAttaque >= ATKSpeed) {
            tempsDepuisDerniereAttaque = 0.0;
            derniereAttaque = LocalTime.now();
            return true;
        }
        return false;
    }

    public List<Enemi> MonstreAportee(List<Enemi> monstres, double portee) {
        if (monstres == null || monstres.isEmpty()) {
            return new ArrayList<>(); // Retourne une liste vide si aucun monstre
        }
    
        List<Enemi> monstresAportee = new ArrayList<>();
        for (Enemi m : monstres) {
            if (m.getPosition().distance(this.position) / Omnicient.getSize() <= portee) {
                monstresAportee.add(m);
            }
        }
        return monstresAportee;
    }

    public Enemi PlusAvancer(List<Enemi> monstres) {
        if(!monstres.isEmpty()) {
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
        }else{
            return null;
        }
    }

    public Enemi PlusProche(List<Enemi> monstres) {
        if(!monstres.isEmpty()) {
            Enemi plusProche = monstres.get(0);
            for (Enemi m : monstres) {
                if (m.getPosition().distance(this.position) < plusProche.getPosition().distance(this.position)) {
                    plusProche = m;
                }
            }
            return plusProche;
        }else{
            return null;
        }
    }

    public void afficheattaque(Enemi e) {
        if(e == null){
            return;
        }else{
            StdDraw.setPenColor(Color.RED);
            StdDraw.line(this.position.getX(), this.position.getY(), e.getPosition().getX(), e.getPosition().getY());
        }
    }

    protected void attaqueSimple(Enemi cible) {
        System.out.println("Attaque simple");
        if (cible != null) {
            cible.setPV(cible.getPV() - this.ATK);
            System.out.println("PV de "+cible+" : " + cible.getPV());

            afficheattaque(cible);
        }
    }




     
}