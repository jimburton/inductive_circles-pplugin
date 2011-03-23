package icircles.concreteDiagram;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

public class ConcreteDiagram {

    Rectangle2D.Double box;
    ArrayList<CircleContour> circles;
    ArrayList<ConcreteZone> shadedZones;

    public ConcreteDiagram(Rectangle2D.Double box,
            ArrayList<CircleContour> circles,
            ArrayList<ConcreteZone> shadedZones) {
        this.box = box;
        this.circles = circles;
        this.shadedZones = shadedZones;
    }

    public ArrayList<CircleContour> getCircles() {
        return circles;
    }

    public static double checksum(ArrayList<CircleContour> circles) {

        double result = 0.0;
        if (circles == null) {
            return result;
        }

        Iterator<CircleContour> cIt = circles.iterator();
        while (cIt.hasNext()) {
            CircleContour c = cIt.next();
            result += c.cx * 0.345 + c.cy * 0.456 + c.radius * 0.567 + c.l.checksum() * 0.555;
            result *= 1.2;
        }
        return result;
    }

    public ArrayList<ConcreteZone> getShadedZones() {
        return shadedZones;
    }

    public Rectangle2D.Double getBox() {
        return box;
    }
}
