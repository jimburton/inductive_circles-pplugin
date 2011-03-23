package icircles.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DEB {

    public static int level = 0; // DO NOT CHANGE HERE - change in code with main e.g. test harness

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

    public static void show(Shape s) {
        JFrame jf = new JFrame();
        jf.getContentPane().add(new ShapePanel(s));
        jf.setBounds(0, 0, 200, 200);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setVisible(true);
    }
}

class ShapePanel extends JPanel {

    private static final long serialVersionUID = 1L;
    Shape m_s;

    ShapePanel(Shape s) {
        super();
        m_s = s;
    }

    public void paint(Graphics g) {
        super.paint(g);
        ((Graphics2D) g).fill(m_s);
    }
}
