package icircles.concreteDiagram;

import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import icircles.util.DEB;

import icircles.abstractDescription.AbstractCurve;

public class CircleContour {

    Ellipse2D.Double circle;
    double cx;
    double cy;
    double radius;
    double nudge = 0.1;
//    Area bigInterior;
//    Area smallInterior; 
    // TODO this caching of the smallInterior somehow became out of date (for the second 1-piercing in test 206)
    // I have just suppressed it, which slow things down. but we need to understand why and reinstate the cache.

    public AbstractCurve ac;

    public CircleContour(double cx,
            double cy, double radius, 
            AbstractCurve ac) {
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.ac = ac;
        circle = makeEllipse(cx, cy, radius);
    }

    public CircleContour(CircleContour c) {
		this.cx = c.cx;
		this.cy = c.cy;
		this.radius = c.radius;
		this.ac = c.ac;
		circle = makeEllipse(cx, cy, radius);
	}

	private void shift(double x, double y) {
        cx += x;
        cy += y;
        circle = makeEllipse(cx, cy, radius);
//        bigInterior = null;
//        smallInterior = null;
    }

    private void scaleAboutZero(double scale) {
        cx *= scale;
        cy *= scale;
        radius *= scale;
        circle = makeEllipse(cx, cy, radius);
//        bigInterior = null;
//        smallInterior = null;
    }

    private Ellipse2D.Double makeEllipse(double x, double y, double r) {
        return new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
    }

    public Ellipse2D.Double getCircle() {
        return circle;
    }

    public Area getBigInterior() {
//        if (bigInterior == null) {
          Area  bigInterior = new Area(makeEllipse(cx, cy, radius + nudge));
//        }
        return bigInterior;
    }

    public Area getSmallInterior() {
        //if (smallInterior == null) {
        Area    smallInterior = new Area(makeEllipse(cx, cy, radius - nudge));
        //}
        return smallInterior;
    }

    public String debug() {
        if (DEB.level > 2) {
            return "circle " + ac.getLabel().debug() + " at (" + cx + "," + cy + ") rad " + radius;
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
    static void fitCirclesToSize(ArrayList<CircleContour> circles, int size)
    {
        // work out a suitable size
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (CircleContour cc : circles) {
            if (cc.getMinX() < minX) {
                minX = cc.getMinX();
            }
            if (cc.getMinY() < minY) {
                minY = cc.getMinY();
            }
            if (cc.getMaxX() > maxX) {
                maxX = cc.getMaxX();
            }
            if (cc.getMaxY() > maxY) {
                maxY = cc.getMaxY();
            }
        }

        double midX = (minX + maxX) * 0.5;
        double midY = (minY + maxY) * 0.5;
        for (CircleContour cc : circles) {
            cc.shift(-midX, -midY);
        }

        double width = maxX - minX;
        double height = maxY - minY;
        double biggest_HW = Math.max(height, width);
        double scale = (size * 0.95) / biggest_HW;
        for (CircleContour cc : circles) {
            cc.scaleAboutZero(scale);
        }

        for (CircleContour cc : circles) {
            cc.shift(size * 0.5, size * 0.5);
        }
    }
    static Rectangle2D.Double makeBigOuterBox(ArrayList<CircleContour> circles)
    {
    	if(circles.size()==0)
    		return new Rectangle2D.Double(0, 0, 1000, 1000);
    	
        // work out a suitable size
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (CircleContour cc : circles) {
            if (cc.getMinX() < minX) {
                minX = cc.getMinX();
            }
            if (cc.getMinY() < minY) {
                minY = cc.getMinY();
            }
            if (cc.getMaxX() > maxX) {
                maxX = cc.getMaxX();
            }
            if (cc.getMaxY() > maxY) {
                maxY = cc.getMaxY();
            }
        }
        int width = maxX - minX;
        int height = maxY - minX;
        
        return new Rectangle2D.Double((int)(minX - 2*width), (int)(minY - 2*height), 5*width, 5*height);
    }
}
