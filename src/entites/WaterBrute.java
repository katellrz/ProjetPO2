package entites;

import java.util.List;

import outils.Omnicient;

public class WaterBrute extends Enemi {
    
    public WaterBrute() {
        this.PV = 30;
        this.ATK = 5;
        this.ATKSpeed = 1;
        this.Range = 3;
        this.element = Element.WATER;
        this.Speed = 1;
        this.Reward = 3;

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
                Tour cible = MoinsDePV(cibles);
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
