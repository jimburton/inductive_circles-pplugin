package icircles.gui;

import icircles.concreteDiagram.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

public class ConcreteDiagramPointQuery {

    static int INSIDE = 0;
    static int COINCIDENT = 1;
    static int OUTSIDE = 2;
    private ConcreteDiagram cd;
    private HashMap<CircleContour, Integer> containmentInfo;
    private ConcreteSpider s;
    private ConcreteSpiderFoot foot;

    ConcreteDiagramPointQuery(ConcreteDiagram cd, Point2D.Double p, double tol) {
        this.cd = cd;
        containmentInfo = new HashMap<CircleContour, Integer>();
        for (CircleContour cc : cd.getCircles()) {
            double dist = Math.sqrt((p.x - cc.get_cx()) * (p.x - cc.get_cx())
                    + (p.y - cc.get_cy()) * (p.y - cc.get_cy()));
            if (dist < cc.get_radius() - tol) {
                containmentInfo.put(cc, INSIDE);
            } else if (dist > cc.get_radius() + tol) {
                containmentInfo.put(cc, OUTSIDE);
            } else {
                containmentInfo.put(cc, COINCIDENT);
            }
        }
        boolean done = false;
        for (ConcreteSpider s : cd.getSpiders()) {
            for (ConcreteSpiderFoot f : s.feet) {
                double dist = Math.sqrt((p.x - f.getX()) * (p.x - f.getX())
                        + (p.y - f.getY()) * (p.y - f.getY()));
                if (dist < ConcreteSpiderFoot.FOOT_RADIUS + tol) {
                    this.s = s;
                    this.foot = f;
                    done = true;
                    break;
                }
            }
            if (done) {
                break;
            }
        }
    }

    ArrayList<CircleContour> getContours() {
        ArrayList<CircleContour> result = new ArrayList<CircleContour>();
        for (CircleContour cc : cd.getCircles()) {
            Integer i = containmentInfo.get(cc);
            if (i.intValue() == COINCIDENT) {
                result.add(cc);
            }
        }
        return result;
    }

    private ConcreteZone selectZoneFromList(ArrayList<ConcreteZone> list) {
        for (ConcreteZone cz : list) {
            boolean containment_ok = true;
            for (CircleContour cc : cz.getContainingContours()) {
                Integer i = containmentInfo.get(cc);
                if (i.intValue() != INSIDE) {
                    containment_ok = false;
                    break;
                }
            }
            if (containment_ok) {
                for (CircleContour cc : cz.getExcludingContours()) {
                    Integer i = containmentInfo.get(cc);
                    if (i.intValue() != OUTSIDE) {
                        containment_ok = false;
                        break;
                    }
                }
            }
            if (containment_ok) {
                return cz;
            }
        }
        return null;
    }

    public ConcreteZone getConcreteZone() {
        ConcreteZone z = selectZoneFromList(cd.getShadedZones());
        if (z != null) {
            return z;
        }
        z = selectZoneFromList(cd.getUnshadedZones());
        return z;
    }

    public ConcreteSpider getSpider() {
        return s;
    }

    public ConcreteSpiderFoot getSpiderFoot() {
        return foot;
    }
}
