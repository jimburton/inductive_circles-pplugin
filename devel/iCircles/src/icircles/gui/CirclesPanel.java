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
import java.awt.Stroke;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import icircles.abstractDescription.AbstractDescription;

import icircles.concreteDiagram.CircleContour;
import icircles.concreteDiagram.ConcreteDiagram;
import icircles.concreteDiagram.ConcreteSpider;
import icircles.concreteDiagram.ConcreteSpiderFoot;
import icircles.concreteDiagram.ConcreteSpiderLeg;
import icircles.concreteDiagram.ConcreteZone;
import icircles.util.CannotDrawException;
import icircles.util.DEB;

public class CirclesPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ConcreteDiagram cd;

    ConcreteDiagram getDiagram(){return cd;}
    public CirclesPanel(String desc, String failureMessage, ConcreteDiagram cd, int size,
            boolean useColors) 
    	{
    	this.cd = cd;
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
            	if(z.getColor() != null)
            		g.setColor(z.getColor());
            	else
            		g.setColor(Color.lightGray);

            	((Graphics2D) g).fill(z.getShape(diagram.getBox()));
            }
            ((Graphics2D) g).setStroke(new BasicStroke(2));
            ArrayList<CircleContour> circles = diagram.getCircles();
            for (CircleContour cc : circles) {
                if (useColors) {
                	Color col = cc.color();
                    if (col == null) {
                        col = Color.black;
                    }
                    g.setColor(col);
                } else {
                    g.setColor(Color.black);
                }
                ((Graphics2D) g).draw(cc.getCircle());
            	if( cc.ac.getLabel() == null )
            		continue;
                if (useColors) {
                	Color col = cc.color();
                    if (col == null) {
                        col = Color.black;
                    }
                    g.setColor(col);
                } else {
                    g.setColor(Color.black);
                }
                if(cc.stroke() != null)
                	((Graphics2D) g).setStroke(cc.stroke());
                else
                	((Graphics2D) g).setStroke(new BasicStroke(2));
                // TODO a proper way to place labels - it can't be a method in CircleContour,
                // we need the context in the ConcreteDiagram
                ((Graphics2D) g).drawString(cc.ac.getLabel().getLabel(),
                        (int) cc.getLabelXPosition(),
                        (int) cc.getLabelYPosition());
            }
            g.setColor(Color.black);
            for (ConcreteSpider s : diagram.getSpiders())
            {
            	for (ConcreteSpiderFoot foot : s.feet)
            	{
            		((Graphics2D) g).fill(foot.getBlob());
            	}
            	for (ConcreteSpiderLeg leg : s.legs)
            	{
                    ((Graphics2D) g).drawLine((int)leg.from.x, (int)leg.from.y, (int)leg.to.x, (int)leg.to.y );
            	}
            	if( s.as.get_name() == null )
            		continue;
                // TODO a proper way to place labels - it can't be a method in ConcreteSpider,
                // we need the context in the ConcreteDiagram
                ((Graphics2D) g).drawString(s.as.get_name(),
                		(int)(s.feet.get(0).x + 5),
        				(int)(s.feet.get(0).y - 5));
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
    			"qh h fh ih ik kh b ab ac de bd  abc bfg fc bj l lc al m mn nc bc bco bo boj bp bop cq cqb rs ra s",
    			true); // randomised shading
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

    ArrayList<CircleContour> getAllCircles()
    {
    	return cd.getCircles();
    }
    void setColor(CircleContour cc, Color c)
    {
    	cc.setColor(c);
    	repaint();
    }
    void setStroke(CircleContour cc, Stroke s)
    {
    	cc.setStroke(s);
    	repaint();
    }

}
