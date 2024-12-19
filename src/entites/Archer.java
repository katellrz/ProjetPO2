package entites;

import Librairies.Point;

public class Archer extends Tour{

    public Archer(Point position){
        super(position);
        this.PV=30;
        this.ATK=5;
        this.ATKSpeed=1;
        this.Range=2;
        this.element=Element.NONE;
        this.Cost=20;       
    }

    @Override
    public int getMaxPV() {
        return MaxPV;
    }




}