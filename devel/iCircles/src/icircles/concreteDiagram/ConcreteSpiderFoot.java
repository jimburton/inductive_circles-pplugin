package icircles.concreteDiagram;

import java.awt.geom.Ellipse2D;

public class ConcreteSpiderFoot {

    public static final double FOOT_RADIUS = 4;
    private double x;
    private double y;
    private ConcreteSpider spider;

    public ConcreteSpiderFoot(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Ellipse2D.Double getBlob() {
        double rad = FOOT_RADIUS;
        return new Ellipse2D.Double(getX() - rad, getY() - rad, 2 * rad, 2 * rad);
    }

    /**
     * Puts the coordinates and dimensions of this foot into the given ellipse.
     * @param outBlob this ellipse will contain the coordinates and dimensions
     * of this foot.
     */
    public void getBlob(Ellipse2D.Double outBlob) {
        outBlob.x = getX() - FOOT_RADIUS;
        outBlob.y = getY() - FOOT_RADIUS;
        outBlob.width = 2 * FOOT_RADIUS;
        outBlob.height = 2 * FOOT_RADIUS;
    }

    // TODO: Maybe you should use 'hashCode' instead of 'checksum'?
    public double checksum() {
        return getX() + 1.02 * getY();
    }

    /**
     * Returns the x coordinate of the centre of this foot.
     * @return the x coordinate of the centre of this foot.
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x coordinate of the centre of this foot.
     * @param x the new x coordinate of the centre of this foot.
     */
    void setX(double x) {
        this.x = x;
    }

    /**
     * Returns the y coordinate of the centre of this foot.
     * @return the y coordinate of the centre of this foot.
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y coordinate of the centre of this foot.
     * @param y the new y coordinate of the centre of this foot.
     */
    void setY(double y) {
        this.y = y;
    }

    /**
     * Returns the spider to which this foot belongs.
     * @return the spider to which this foot belongs.
     */
    public ConcreteSpider getSpider() {
        return spider;
    }

    void setSpider(ConcreteSpider spider) {
        this.spider = spider;
    }
}
