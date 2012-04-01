package icircles.concreteDiagram;

import icircles.abstractDescription.AbstractSpider;
import icircles.util.DEB;

import java.util.ArrayList;

public class ConcreteSpider {

    // TODO: make some data private
    public int footRad = 1;
    public ArrayList<ConcreteSpiderFoot> feet;
    public ArrayList<ConcreteSpiderLeg> legs;
    public AbstractSpider as;

    public ConcreteSpider(AbstractSpider as) {
        this.as = as;
        feet = new ArrayList<ConcreteSpiderFoot>();
        legs = new ArrayList<ConcreteSpiderLeg>();
    }

    public double checksum() {
        double result = 0.0;
        for (ConcreteSpiderFoot foot : feet) {
            if (DEB.level >= 2) {
                System.out.println("build checksum for foot\n");
            }
            result += foot.checksum();
        }
        for (ConcreteSpiderLeg leg : legs) {
            if (DEB.level >= 2) {
                System.out.println("build checksum for leg\n");
            }
            result += leg.checksum();
        }
        // TODO Auto-generated method stub
        return result;
    }
}
