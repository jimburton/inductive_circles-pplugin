package icircles.concreteDiagram;

import icircles.abstractDescription.AbstractDescription;
import icircles.gui.CirclesPanel;
import icircles.util.CannotDrawException;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

public class ConcreteDiagram {

    Rectangle2D.Double box;
    ArrayList<CircleContour> circles;
    ArrayList<ConcreteZone> shadedZones;
    ArrayList<ConcreteSpider> spiders;

    public ConcreteDiagram(Rectangle2D.Double box,
            ArrayList<CircleContour> circles,
            ArrayList<ConcreteZone> shadedZones,
            ArrayList<ConcreteSpider> spiders) {
        this.box = box;
        this.circles = circles;
        this.shadedZones = shadedZones;
        this.spiders = spiders;
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
            result += c.cx * 0.345 + c.cy * 0.456 + c.radius * 0.567 + c.ac.checksum() * 0.555;
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

    /**
     * This can be used to obtain a drawing of an abstract diagram.
     * @param ad the description to be drawn
     * @param size the size of the drawing panel
     * @return
     * @throws CannotDrawException
     */
    public static ConcreteDiagram makeConcreteDiagram(AbstractDescription ad, int size) throws CannotDrawException
    {
    	// TODO
    	if(!ad.checks_ok())
    	{
    		// not drawable
    		throw new CannotDrawException("badly formed diagram spec");
    	}
        DiagramCreator dc = new DiagramCreator(ad);
        ConcreteDiagram cd = dc.createDiagram(size);
        return cd;
    }
    
    public static void main(String[] args)
    {
    	//DEB.level = 3;
    	AbstractDescription ad = AbstractDescription.makeForTesting("a ab b c", 
    			true); // randomised shading

    	String failuremessage = "no failure";
    	ConcreteDiagram cd = null;
    	try
    	{
    	cd = ConcreteDiagram.makeConcreteDiagram(ad, 300);
    	}
    	catch(CannotDrawException ex)
    	{
    		failuremessage = ex.message;
    	}

    	CirclesPanel cp = new CirclesPanel("a sample CirclesPanel", failuremessage, cd, 100, 
    			true); // do use colors
    	
    	JFrame viewingFrame = new JFrame("frame to hold a CirclesPanel");
    	viewingFrame.getContentPane().add(cp);
    	viewingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	viewingFrame.pack();
    	viewingFrame.setVisible(true);
    }

	public ArrayList<ConcreteSpider> getSpiders() {
		return spiders;
	}

}
