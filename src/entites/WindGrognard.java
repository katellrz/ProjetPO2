package entites;

import java.util.List;

import outils.Omnicient;

public class WindGrognard extends Enemi {
    
    public WindGrognard() {
        this.PV = 1;
        this.ATK = 7;
        this.ATKSpeed = 2;
        this.Range = 5;
        this.element = Element.WIND;
        this.Speed = 2;
        this.Reward = 1;

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
                    cible.setPV(cible.getPV() - this.ATK);
                    afficheattaque(cible);
                }
            }
        }
    }
}
