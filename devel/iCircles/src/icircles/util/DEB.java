package icircles.util;

import icircles.gui.CirclesPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DEB {

    public static int level = 0; // DO NOT CHANGE HERE - change in code with main e.g. test harness
    
    private static ArrayList<CirclesPanel> filmStripShots = new ArrayList<CirclesPanel>();
    public static void addFilmStripShot(CirclesPanel cp)
    {
    	filmStripShots.add(cp);
    }
    public static void showFilmStrip()
    {
    	if(filmStripShots.size()==0)
    		return;
    	
    	JFrame viewingFrame = new JFrame("frame to hold a CirclesPanel");
    	JScrollPane scrollPane = new JScrollPane();
    	JPanel filmPanel = new JPanel();
    	for(CirclesPanel cp : filmStripShots)
    	{
    		filmPanel.add(cp);
    	}
    	scrollPane.add(filmPanel);
    			
    	viewingFrame.getContentPane().setPreferredSize(new Dimension(200, 200));
    	viewingFrame.getContentPane().add(scrollPane);
    	viewingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	viewingFrame.pack();
    	viewingFrame.setVisible(true);    	
    }
    public void clearFilmStrip()
    {
    	filmStripShots.clear();
    }
    
    public static void assertCondition(boolean condition, String messageIfFail) {
        if (!condition) {
            System.out.println("!! assert failure !! " + messageIfFail);
            throw new Error();
        }
    }

    public static void out(int this_level, String message) {
        if (this_level <= level) {
            System.out.println(message);
        }
    }

    public static void show(int deb_level, Shape s, String desc) {
    	if(deb_level > DEB.level)
    		return;
    	
        JFrame jf = new JFrame(desc);
        jf.getContentPane().add(new ShapePanel(s));
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //jf.setSize(s.getBounds().width, s.getBounds().height);
        jf.setBounds(0, 0, 800, 800);
        jf.setVisible(true);
    }
}

class ShapePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    Shape m_s;

    ShapePanel(Shape s) {
        super();
        m_s = s;
        this.setBounds(s.getBounds());
        this.setMinimumSize(new Dimension(s.getBounds().width, s.getBounds().height));
    }

    public void paint(Graphics g) {
        super.paint(g);
        ((Graphics2D) g).fill(m_s);
    }
}
