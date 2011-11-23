package icircles.gui;

import icircles.gui.CirclesPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import icircles.abstractDescription.AbstractDescription;

import icircles.recomposition.RecompositionStrategy;
import icircles.test.TestData;
import icircles.util.CannotDrawException;
import icircles.util.DEB;
import icircles.concreteDiagram.ConcreteDiagram;
import icircles.concreteDiagram.DiagramCreator;
import icircles.decomposition.DecompositionStrategy;

public class CirclesFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    final InputPanel inputPanel = new InputPanel();
    final ResultPanel resultPanel = new ResultPanel();
    final SettingsPanel settingsPanel = new SettingsPanel();
    private int SIZE = 200;
    boolean useColors = true;

    CirclesFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        populate_frame();
        pack();
        setVisible(true);
        //Debug.level = 4;
        //draw("a b cd f eg st fes");
        //draw("a b c d e f g h i j k l");
        drawTest(63);
        getContentPane().addComponentListener(new ComponentListener() {

            public void componentHidden(ComponentEvent e) {
            }

            public void componentMoved(ComponentEvent e) {
            }

            public void componentResized(ComponentEvent e) {
                respondToResize();
            }

            public void componentShown(ComponentEvent e) {
            }
        });
        respondToResize();
    }

    void respondToResize() {
        SIZE = Math.min(getContentPane().getHeight()
                - settingsPanel.getPanel().getHeight()
                - inputPanel.getPanel().getHeight(), // allow for buttons etc

                getContentPane().getWidth()) - 30;

        DEB.out(3, "new size is " + getContentPane().getHeight() + "," + getContentPane().getWidth());
        DEB.out(3, "SIZE is " + SIZE);
        redraw();
    }

    void draw(String s) {
        inputPanel.setInput(s);
        redraw();
    }

    void populate_frame() {
        getContentPane().add(inputPanel.getPanel(), BorderLayout.NORTH);
        getContentPane().add(resultPanel.getPanel(), BorderLayout.CENTER);
        getContentPane().add(settingsPanel.getPanel(), BorderLayout.SOUTH);
        draw("");
    }

    void drawTest(int test_num) {
        draw(TestData.test_data[test_num - 1].description);
    }

    private void goDraw(String description, 
    		            int decomp_strategy, int recomp_strategy) {

        ConcreteDiagram cd = null;
        String failureMessage = null;
        try {
            AbstractDescription adr = AbstractDescription.makeForTesting(description);
            DEB.out(1,  "draw "+adr.debug());
            DiagramCreator dc = new DiagramCreator(adr, 
            		DecompositionStrategy.getStrategy(decomp_strategy), 
            		RecompositionStrategy.getStrategy(recomp_strategy));
            cd = dc.createDiagram(SIZE);
        } catch (CannotDrawException x) {
            failureMessage = x.message;
        }
        resultPanel.show(description, failureMessage, cd, SIZE, useColors);
    }

    class InputPanel {

        final JTextField inputJTF = new JTextField();
        final JPanel p = new JPanel();
        final static String ESCAPE_ACTION = "cancel-typing";
        final static String ENTER_ACTION = "go-draw";

        InputPanel() {
            p.setLayout(new BorderLayout());
            p.add(inputJTF, BorderLayout.CENTER);

            InputMap im = inputJTF.getInputMap(JComponent.WHEN_FOCUSED);
            ActionMap am = inputJTF.getActionMap();
            im.put(KeyStroke.getKeyStroke("ESCAPE"), ESCAPE_ACTION);
            am.put(ESCAPE_ACTION, new EscapeAction());

            im.put(KeyStroke.getKeyStroke("ENTER"), ENTER_ACTION);
            am.put(ENTER_ACTION, new RedrawListener());

            im.put(KeyStroke.getKeyStroke("ESCAPE"), ESCAPE_ACTION);
            am.put(ESCAPE_ACTION, new EscapeAction());

            im.put(KeyStroke.getKeyStroke("ENTER"), ENTER_ACTION);
            am.put(ENTER_ACTION, new RedrawListener());
        }

        String getCurrentDescription() {
            return inputJTF.getText();
        }

        void setInput(String s) {
            inputJTF.setText(s);
        }

        void clear() {
            inputJTF.setText("");
        }

        JPanel getPanel() {
            return p;
        }
    }

    class EscapeAction extends AbstractAction {
    	
        private static final long serialVersionUID = 1L;

        public void actionPerformed(ActionEvent ev) {
            inputPanel.clear();
        }
    }

    class ResultPanel {

        JPanel p = new JPanel();

        ResultPanel() {
            p.setLayout(new BorderLayout());
        }

        JPanel getPanel() {
            return p;
        }

        void show(String description,
        		String failureMessage,
                ConcreteDiagram cd,
                int SIZE,
                boolean useColors) {
            JPanel jp = new CirclesPanel(description, failureMessage, cd, useColors);
            p.removeAll();
            p.invalidate();
            p.add(jp);
            p.revalidate();
            getContentPane().repaint();

        }
    }

    class SettingsPanel {

        private int test_num = 0;
        String[] decompStrings = DecompositionStrategy.getDecompStrings();
        String[] recompStrings = RecompositionStrategy.getRecompStrings();
        final JComboBox decompList = new JComboBox(decompStrings);
        final JComboBox recompList = new JComboBox(recompStrings);
        final JTextField testJTF = new JTextField("");
        final static String ENTER_ACTION = "go-draw-test";
        final JPanel p = new JPanel();

        SettingsPanel() {
            //Construct a GridLayout with 1 columns and an unspecified number of rows.
            //p.setLayout(new GridLayout(0,1));
            p.setLayout(new BorderLayout());

            JPanel topPanel = new JPanel();
            topPanel.setBorder(BorderFactory.createTitledBorder("settings"));
            topPanel.setLayout(new GridLayout(0, 1));
            p.add(topPanel, BorderLayout.NORTH);

            final JCheckBox jcb = new JCheckBox("Show contours with colours", true);
            jcb.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    useColors = jcb.isSelected();
                    redraw();
                }
            });
            topPanel.add(jcb);

            decompList.setSelectedIndex(decompStrings.length - 1);
            decompList.addActionListener(new RedrawListener());
            topPanel.add(decompList);

            recompList.setSelectedIndex(recompStrings.length - 1);
            recompList.addActionListener(new RedrawListener());
            topPanel.add(recompList);

            JPanel examplePanel = new JPanel();
            examplePanel.setBorder(BorderFactory.createTitledBorder("examples"));
            examplePanel.setLayout(new GridLayout(0, 1));
            p.add(examplePanel, BorderLayout.CENTER);

            JButton v3 = new JButton("draw Venn 3");
            v3.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    draw("a b c ab ac bc abc");
                }
            });
            examplePanel.add(v3);

            final JLabel testLabel = new JLabel("draw test index:");
            examplePanel.add(testLabel);
            InputMap im = testJTF.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap am = testJTF.getActionMap();
            im.put(KeyStroke.getKeyStroke("ENTER"), ENTER_ACTION);
            am.put(ENTER_ACTION, new TestListener());
            examplePanel.add(testJTF);

            JButton next = new JButton("draw next test case");
            next.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                	if (testJTF.getText().length() == 0) {
                        test_num = 0;
                    } else {
                        test_num += 1;
                        if (test_num > TestData.test_data.length - 1) {
                            test_num = 0;
                        }
                    }
                    testJTF.setText("" + (test_num + 1));
                    drawTest(test_num + 1);
                }
            });
            examplePanel.add(next);
            JButton prev = new JButton("draw previous test case");
            prev.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                	if (testJTF.getText().length() == 0) {
                        test_num = 0;
                    } else {
                        test_num -= 1;
                        if (test_num < 0) {
                            test_num = TestData.test_data.length - 1;
                        }
                    }
                    testJTF.setText("" + (test_num + 1));
                    drawTest(test_num + 1);
                }
            });
            examplePanel.add(prev);
        }

        void setDecompStrategy(int i) {
            decompList.setSelectedIndex(i);
        }

        int getDecompStrategy() {
            return decompList.getSelectedIndex();
        }

        String getDecompString() {
            return decompStrings[getDecompStrategy()];
        }

        void setRecompStrategy(int i) {
            recompList.setSelectedIndex(i);
        }

        int getRecompStrategy() {
            return recompList.getSelectedIndex();
        }

        String getRecompString() {
            return recompStrings[getRecompStrategy()];
        }

        JPanel getPanel() {
            return p;
        }

        class TestListener extends AbstractAction {

            private static final long serialVersionUID = 1L;

            public void actionPerformed(ActionEvent ev) {
                try {
                    int i = Integer.parseInt(testJTF.getText());
                    if (i < 1 || i > TestData.test_data.length) {
                        JOptionPane.showMessageDialog(null, "test number should be between 1 and " + TestData.test_data.length);
                        return;
                    }
                    test_num = i - 1;
                    drawTest(test_num + 1);
                } catch (NumberFormatException x) {
                    JOptionPane.showMessageDialog(null, "type an integer between 1 and " + TestData.test_data.length);
                    return;
                }
            }
        }
    }

    void redraw() {
        goDraw(inputPanel.getCurrentDescription(),
                settingsPanel.getDecompStrategy(),
                settingsPanel.getRecompStrategy());
    }

    class RedrawListener extends AbstractAction {

        private static final long serialVersionUID = 1L;

        public void actionPerformed(ActionEvent ev) {
            redraw();
        }
    }
}
