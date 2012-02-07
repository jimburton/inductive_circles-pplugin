package icircles.concreteDiagram;

import java.awt.geom.Ellipse2D;

public class ConcreteSpiderFoot {

    // TODO: make some data private
    public static double FOOT_RADIUS = 4;
    public double x;
    public double y;

    public Ellipse2D.Double getBlob() {
        double rad = FOOT_RADIUS;
        return new Ellipse2D.Double(x - rad, y - rad, 2 * rad, 2 * rad);
    }

    public void getBlob(Ellipse2D.Double outBlob) {
        outBlob.x = x - FOOT_RADIUS;
        outBlob.y = y - FOOT_RADIUS;
        outBlob.width = 2 * FOOT_RADIUS;
        outBlob.height = 2 * FOOT_RADIUS;
    }

    public double checksum() {
        return x + 1.02 * y;
    }

    public static void set_foot_size(int size) {
        FOOT_RADIUS = size;
    }
}
