package entites;

import java.util.List;

import outils.Omnicient;

public class EarthBrute extends Enemi {
    
    public EarthBrute() {
        this.PV = 30;
        this.ATK = 5;
        this.ATKSpeed = 1;
        this.Range = 3;
        this.element = Element.EARTH;
        this.Speed = 1;
        this.Reward = 3;
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
                    cible.setPV(cible.getPV() - this.ATK);
                    afficheattaque(cible);
                }
            }
        }
    }
}