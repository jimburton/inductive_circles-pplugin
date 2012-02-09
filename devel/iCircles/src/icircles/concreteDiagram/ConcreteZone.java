package icircles.concreteDiagram;

import icircles.abstractDescription.AbstractBasicRegion;
import java.awt.Color;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

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

    public ArrayList<CircleContour> getContainingContours() {
        return containingCircles;
    }

    public ArrayList<CircleContour> getExcludingContours() {
        return excludingCircles;
    }

    public AbstractBasicRegion getAbstractBasicRegion() {
        return abr;
    }
    Color col;

    public void setColor(Color c) {
        col = c;
    }

    public Color getColor() {
        return col;
    }
}
