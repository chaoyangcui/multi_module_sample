package io;
//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//               佛祖保佑         永无BUG


//         ┌─┐       ┌─┐
//      ┌──┘ ┴───────┘ ┴──┐
//      │                 │
//      │       ───       │
//      │  ─┬┘       └┬─  │
//      │                 │
//      │       ─┴─       │
//      │                 │
//      └───┐         ┌───┘
//          │         │
//          │         │
//          │         │
//          │         └──────────────┐
//          │                        │
//          │                        ├─┐
//          │                        ┌─┘
//          │                        │
//          └─┐  ┐  ┌───────┬──┐  ┌──┘
//            │ ─┤ ─┤       │ ─┤ ─┤
//            └──┴──┘       └──┴──┘
//                神兽保佑
//                代码无BUG!


//
//  ██████▒█    ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
// ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
// ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
// ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
// ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
//  ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
//  ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
//  ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
//           ░     ░ ░      ░  ░
//                 ░

/**
 * Created by IntelliJ IDEA.
 * GridLayoutDemo.java
 *
 * @author Eric
 * @date 2018/1/3 14:15
 * Description
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FindDiffWindow extends JFrame {
    static final String gapList[] = {"0", "10", "15", "20"};
    final static int maxGap = 20;
    JComboBox horGapComboBox;
    JComboBox verGapComboBox;
    JButton refreshButton = new JButton("Refresh");
    // GridLayout experimentLayout = new GridLayout(0, 2);
    GridLayout experimentLayout = new GridLayout(1, 1);

    private static final String PATH = new ImageTest().getCurrFilePath();

    public FindDiffWindow(String name) {
        super(name);
        setResizable(false);
    }

    public void initGaps() {
        horGapComboBox = new JComboBox(gapList);
        verGapComboBox = new JComboBox(gapList);
    }

    class ImagePanel extends JPanel {
        private String fileName = null;
        public void paint(Graphics g) {
            super.paint(g);
            ImageIcon icon = new ImageIcon(getFilePath());
            g.drawImage(icon.getImage(), 0, 0, FindDiffHelper.IMG_WIDTH, FindDiffHelper.IMG_HEIGHT, this);
        }

        public void addMouseClickListener() {
            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    double x = e.getPoint().getX() + FindDiffHelper.LEFT;
                    double y = e.getPoint().getY() + FindDiffHelper.TOP + FindDiffHelper.HEIGHT;
                    // double x = e.getPoint().getX();
                    // double y = e.getPoint().getY();

                    System.out.println(String.format("click x: %f, y: %f", x, y));
                    AdbHelper.tap((int) x, (int) y);
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }
                @Override
                public void mouseReleased(MouseEvent e) {

                }
                @Override
                public void mouseEntered(MouseEvent e) {

                }
                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }

        public ImagePanel() {
            this.addMouseClickListener();
        }
        public ImagePanel(String fileName) {
            this.fileName = fileName;
            this.addMouseClickListener();
        }

        private String getFilePath() {
            return PATH + (fileName == null ? "diff.png" : fileName);
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }

    public void addComponentsToPane(final Container pane) {
        initGaps();
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        JPanel controls = new JPanel();
        // controls.setLayout(new GridLayout(2, 3));
        controls.setLayout(new GridLayout(1, 1));

        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        // compsToExperiment.setPreferredSize(new Dimension((int) (buttonSize.getWidth() * 2.5) + maxGap,
        //         (int) (buttonSize.getHeight() * 3.5) + maxGap * 2));
        compsToExperiment.setPreferredSize(new Dimension(FindDiffHelper.IMG_WIDTH, FindDiffHelper.IMG_HEIGHT));


        //Add buttons to experiment with Grid Layout
        // compsToExperiment.add(new JButton("Button 1"));
        // compsToExperiment.add(new JButton("Button 2"));
        // compsToExperiment.add(new JButton("Button 3"));
        // compsToExperiment.add(new JButton("Long-Named Button 4"));
        // compsToExperiment.add(new JButton("5"));
        ImagePanel imgPanel = new ImagePanel();
        compsToExperiment.add(imgPanel);

        //Add controls to set up horizontal and vertical gaps
        // controls.add(new Label("Horizontal gap:"));
        // controls.add(new Label("Vertical gap:"));
        // controls.add(new Label(" "));
        // controls.add(horGapComboBox);
        // controls.add(verGapComboBox);
        controls.add(refreshButton);

        // Process the Apply gaps button press
        refreshButton.addActionListener(e -> {
            /*//Get the horizontal gap value
            String horGap = (String) horGapComboBox.getSelectedItem();
            //Get the vertical gap value
            String verGap = (String) verGapComboBox.getSelectedItem();
            //Set up the horizontal gap value
            experimentLayout.setHgap(Integer.parseInt(horGap));
            //Set up the vertical gap value
            experimentLayout.setVgap(Integer.parseInt(verGap));
            //Set up the layout of the buttons
            experimentLayout.layoutContainer(compsToExperiment);*/

            // android adb operation
            // adb shell screencap -p /sdcard/autojump.png
            // adb pull /sdcard/autojump.png .
            // Runtime.getRuntime().exec("adb shell screencap -p /sdcard/finddiff.png");
            // Runtime.getRuntime().exec("adb pull /sdcard/finddiff.png .");

            // 删除上一张图片
            FindDiffHelper.delOldImage(fileName);

            AdbHelper.screen();
            AdbHelper.pull();
            FindDiffHelper.findDiff();

            // String fileName = "_diff.png";
            imgPanel.setFileName(fileName);
            imgPanel.repaint();
            System.out.println("refresh finish.");
        });
        pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }

    public volatile static String fileName = "";

    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        FindDiffWindow frame = new FindDiffWindow("找茬");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        // Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowClosing(WindowEvent e) {
                FindDiffHelper.delOldImage(fileName);
            }

            @Override
            public void windowOpened(WindowEvent e) {

            }
            @Override
            public void windowClosed(WindowEvent e) {

            }
            @Override
            public void windowIconified(WindowEvent e) {

            }
            @Override
            public void windowDeiconified(WindowEvent e) {

            }
            @Override
            public void windowActivated(WindowEvent e) {

            }
            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}

