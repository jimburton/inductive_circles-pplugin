package icircles.concreteDiagram;

import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import icircles.util.DEB;

import icircles.abstractDescription.CurveLabel;

public class CircleContour {

    Ellipse2D.Double circle;
    double cx;
    double cy;
    double radius;
    double nudge = 0.1;
    Area bigInterior;
    Area smallInterior;
    public CurveLabel l;

    public CircleContour(double cx,
            double cy, double radius, CurveLabel l) {
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.l = l;
        circle = makeEllipse(cx, cy, radius);
    }

    public void shift(double x, double y) {
        cx += x;
        cy += y;
        circle = makeEllipse(cx, cy, radius);
        bigInterior = null;
        smallInterior = null;
    }

    public void scaleAboutZero(double scale) {
        cx *= scale;
        cy *= scale;
        radius *= scale;
        circle = makeEllipse(cx, cy, radius);
        bigInterior = null;
        smallInterior = null;
    }

    private Ellipse2D.Double makeEllipse(double x, double y, double r) {
        return new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
    }

    public Ellipse2D.Double getCircle() {
        return circle;
    }

    public Area getBigInterior() {
        if (bigInterior == null) {
            bigInterior = new Area(makeEllipse(cx, cy, radius + nudge));
        }
        return bigInterior;
    }

    public Area getSmallInterior() {
        if (smallInterior == null) {
            smallInterior = new Area(makeEllipse(cx, cy, radius - nudge));
        }
        return smallInterior;
    }

    public String debug() {
        if (DEB.level > 2) {
            return "circle " + l.debug() + " at (" + cx + "," + cy + ") rad " + radius;
        } else {
            return "";
        }
    }

    public Shape getFatInterior(double fatter) {
        return new Area(makeEllipse(cx, cy, radius + fatter));
    }

    public double getLabelXPosition() {
        return cx + 0.8 * radius;
    }

    public double getLabelYPosition() {
        return cy - 0.8 * radius;
    }

    public int getMinX() {
        return (int) (cx - radius);
    }

    public int getMaxX() {
        return (int) (cx + radius) + 1;
    }

    public int getMinY() {
        return (int) (cy - radius);
    }

    public int getMaxY() {
        return (int) (cy + radius) + 1;
    }
}
