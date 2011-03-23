package icircles.concreteDiagram;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import icircles.abstractDescription.AbstractBasicRegion;

public class ConcreteZone {

    AbstractBasicRegion abr;
    ArrayList<CircleContour> containingCircles;
    ArrayList<CircleContour> excludingCircles;
    Area shape;

    public ConcreteZone(AbstractBasicRegion abr,
            ArrayList<CircleContour> containingCircles,
            ArrayList<CircleContour> excludingCircles) {
        this.abr = abr;
        this.containingCircles = containingCircles;
        this.excludingCircles = excludingCircles;
        shape = null;
    }

    public Area getShape(Rectangle2D.Double box) {
        if (shape != null) {
            return shape;
        }

        Area a = new Area(box);
        for (CircleContour c : containingCircles) {
            a.intersect(c.getBigInterior());
        }
        for (CircleContour c : excludingCircles) {
            a.subtract(c.getSmallInterior());
        }
        shape = a;
        return a;
    }
}
