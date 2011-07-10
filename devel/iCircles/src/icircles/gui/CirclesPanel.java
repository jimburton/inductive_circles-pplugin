package icircles.gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import icircles.abstractDescription.AbstractDescription;
import icircles.abstractDescription.CurveLabel;

import icircles.concreteDiagram.CircleContour;
import icircles.concreteDiagram.ConcreteDiagram;
import icircles.concreteDiagram.ConcreteZone;
import icircles.util.CannotDrawException;
import icircles.util.DEB;

public class CirclesPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final HashMap<CurveLabel, Color> labelsToColours =
            new HashMap<CurveLabel, Color>();

    public CirclesPanel(String desc, String failureMessage, ConcreteDiagram cd, int size,
            boolean useColors) 
    	{
        labelsToColours.put(CurveLabel.get("a"), new Color(0, 100, 0)); // dark green
        labelsToColours.put(CurveLabel.get("b"), Color.red);
        labelsToColours.put(CurveLabel.get("c"), Color.blue);
        labelsToColours.put(CurveLabel.get("d"), new Color(150, 50, 0));
        labelsToColours.put(CurveLabel.get("e"), new Color(0, 50, 150));
        labelsToColours.put(CurveLabel.get("f"), new Color(100, 0, 100));
        labelsToColours.put(CurveLabel.get("g"), new Color(0, 100, 0)); // dark green
        labelsToColours.put(CurveLabel.get("h"), Color.red);
        labelsToColours.put(CurveLabel.get("i"), Color.blue);
        labelsToColours.put(CurveLabel.get("j"), new Color(150, 50, 0));
        labelsToColours.put(CurveLabel.get("k"), new Color(0, 50, 150));
        labelsToColours.put(CurveLabel.get("l"), new Color(100, 0, 100));
        labelsToColours.put(CurveLabel.get("m"), new Color(0, 100, 0)); // dark green
        labelsToColours.put(CurveLabel.get("n"), Color.red);
        labelsToColours.put(CurveLabel.get("o"), Color.blue);
        labelsToColours.put(CurveLabel.get("p"), new Color(150, 50, 0));
        labelsToColours.put(CurveLabel.get("q"), new Color(0, 50, 150));
        labelsToColours.put(CurveLabel.get("r"), new Color(100, 0, 100));
        labelsToColours.put(CurveLabel.get("s"), new Color(0, 100, 0)); // dark green
        labelsToColours.put(CurveLabel.get("t"), Color.red);
        labelsToColours.put(CurveLabel.get("u"), Color.blue);
        labelsToColours.put(CurveLabel.get("v"), new Color(150, 50, 0));
        labelsToColours.put(CurveLabel.get("w"), new Color(0, 50, 150));
        labelsToColours.put(CurveLabel.get("x"), new Color(100, 0, 100));
        labelsToColours.put(CurveLabel.get("y"), new Color(0, 100, 0)); // dark green
        labelsToColours.put(CurveLabel.get("z"), Color.red);

        //setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new BorderLayout());
        JLabel jl = new JLabel(desc);
        Font f = new Font("Dialog", Font.PLAIN, 12);
        if (desc.length() > 24) {
            f = new Font("Dialog", Font.PLAIN, 8);
        } else if (desc.length() > 16) {
            f = new Font("Dialog", Font.PLAIN, 8);
        } else if (desc.length() > 14) {
            f = new Font("Dialog", Font.PLAIN, 10);
        }
        jl.setFont(f);
        jl.setHorizontalAlignment(JLabel.CENTER);
        add(jl, BorderLayout.NORTH);

        int padding = 5;
        DiagramPanel dp = new DiagramPanel(cd, failureMessage, useColors);
        //dp.setBorder(BorderFactory.createLineBorder(Color.black));

        if (cd == null) {
            dp.setPreferredSize(new Dimension(size + padding, size + padding));
            dp.setMinimumSize(new Dimension(size + padding, size + padding));
            dp.setMaximumSize(new Dimension(size + padding, size + padding));
        }

        JPanel containsDiag = new JPanel();
        containsDiag.setLayout(new FlowLayout());
        containsDiag.add(dp);
        //containsDiag.setBackground(Color.orange);

        //containsDiag.setPreferredSize(new Dimension(size+2*padding, size+2*padding));
        //containsDiag.setMinimumSize(  new Dimension(size+2*padding, size+2*padding));
        //containsDiag.setMaximumSize(  new Dimension(size+2*padding, size+2*padding));
        //containsDiag.setBorder(BorderFactory.createLineBorder(Color.blue));

        //setPreferredSize(new Dimension(size+3*padding, size+3*padding + 20));
        //setMinimumSize(  new Dimension(size+3*padding, size+3*padding + 20));
        //setMaximumSize(  new Dimension(size+3*padding, size+3*padding + 20));

        add(containsDiag, BorderLayout.CENTER);
    }

    static class DiagramPanel extends JPanel {

        private static final long serialVersionUID = 1L;
        ConcreteDiagram diagram;
        String failureMessage;
        private boolean useColors;

        DiagramPanel(ConcreteDiagram diagram, String failureMessage, boolean useColors) {
            setBackground(Color.white);
            this.diagram = diagram;
            this.failureMessage = failureMessage;
            this.useColors = useColors;
            if (diagram != null) {
                setPreferredSize(new Dimension((int) (diagram.getBox().width) + 5,
                        (int) (diagram.getBox().height) + 5));
            }
            //setBackground(Color.yellow);
        }

        public void paint(Graphics g) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (diagram == null) {
                this.setBackground(Color.red);
                super.paint(g);
                if (failureMessage != null) {
                    g.drawString(failureMessage, 0, (int) (this.getHeight() * 0.5));
                }
                return;
            }
            // draw the diagram
            super.paint(g);

            // shaded zones
            g.setColor(Color.lightGray);
            ArrayList<ConcreteZone> zones = diagram.getShadedZones();
            for (ConcreteZone z : zones) {
                ((Graphics2D) g).fill(z.getShape(diagram.getBox()));
            }
            ((Graphics2D) g).setStroke(new BasicStroke(2));
            ArrayList<CircleContour> circles = diagram.getCircles();
            for (CircleContour cc : circles) {
                if (useColors) {
                    Color col = labelsToColours.get(cc.ac.getLabel());
                    if (col == null) {
                        col = Color.black;
                    }
                    g.setColor(col);
                } else {
                    g.setColor(Color.black);
                }
                ;

                ((Graphics2D) g).draw(cc.getCircle());
            }
            for (CircleContour cc : circles) {
            	if( cc.ac.getLabel() == null )
            		continue;
                if (useColors) {
                    Color col = labelsToColours.get(cc.ac.getLabel());
                    if (col == null) {
                        col = Color.black;
                    }
                    g.setColor(col);
                } else {
                    g.setColor(Color.black);
                }
                ((Graphics2D) g).drawString(cc.ac.getLabel().getLabel(),
                        (int) cc.getLabelXPosition(),
                        (int) cc.getLabelYPosition());
            }
        }
    }
    /**
     * This can be used to obtain a drawing of an abstract diagram.
     * @param ad the description to be drawn
     * @param size the size of the drawing panel
     * @return
     * @throws CannotDrawException
     */
    public static CirclesPanel makeCirclesPanel(AbstractDescription ad, 
    		                                    String diagText,
    		                                    int size)
    {
    	String failuremessage = "no failure";
    	ConcreteDiagram cd = null;
    	try
    	{
    	cd = ConcreteDiagram.makeConcreteDiagram(ad, size);
    	}
    	catch(CannotDrawException ex)
    	{
    		failuremessage = ex.message;
    	}

    	CirclesPanel cp = new CirclesPanel(diagText, failuremessage, cd, size, 
    			true); // do use colors
    	
    	return cp;
    }
    public static void main(String[] args)
    {
    	// See the implementation of makeForTesting to see how to make an 
    	// AbstractDescription from scratch.
    	AbstractDescription ad = AbstractDescription.makeForTesting(
    			//"qh h fh ih ik kh b ab ac de bd  abc bfg fc bj l lc al m mn nc bc bco bo boj bp bop cq cqb rs ra s t");
    			"qh h fh ih ik kh b ab ac de bd  abc bfg fc bj l lc al m mn nc bc bco bo boj bp bop cq cqb rs ra s");
    			//"a ab b c");

    	DEB.level = 3; // generates intermediate frames
    	
    	int size = 600;
    	
    	CirclesPanel cp = CirclesPanel.makeCirclesPanel(ad, "a sample diagram", size);
    	
    	JFrame viewingFrame = new JFrame("frame to hold a CirclesPanel");
    	JScrollPane scrollPane = new JScrollPane(cp);
    	viewingFrame.getContentPane().setPreferredSize(new Dimension(Math.min(size,  800), Math.min(size,  800)));
    	viewingFrame.getContentPane().add(scrollPane);
    	viewingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	viewingFrame.pack();
    	viewingFrame.setVisible(true);
    }

}
