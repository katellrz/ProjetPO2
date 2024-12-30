package entites;

import java.util.List;

import outils.Omnicient;

public class FireGrognard extends Enemi {
    
    public FireGrognard() {
        this.PV = 1;
        this.ATK = 7;
        this.ATKSpeed = 2;
        this.Range = 3;
        this.element = Element.FIRE;
        this.Speed = 2;
        this.Reward = 1;
        this.PVmax = PV;
    }
    
    @Override
    public int getMaxPV() {
        return PVmax;
    }  

    @Override
    public void attaquer() {
        if (peutAttaquer()) {
            List<Tour> tours = Omnicient.getPositionTours();
            List<Tour> cibles = tours.stream()
                .filter(t -> t.getPosition().distance(this.position) <= this.Range)
                .toList();

            if (!cibles.isEmpty()) {
                Tour cible = PlusProche(cibles);
                if (cible != null) {
                    for (Tour t : cibles) {
                        if (t.getPosition().distance(cible.getPosition()) <= 1.5) {
                            t.setPV(t.getPV() - this.ATK);
                        }
                    }
                    afficheattaque(cible);
                }
            }
        }
    }
    
}