package caseNumberOneCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used as view Craps class.
 *  @autor Ervin-Caravali-I-ID:1925648 ervin.caravali@correounivalle.edu.co
 *  @version @version v.1.0.0 date:06/12/2021
 */
public class GUI extends JFrame {
    public static final String MASSAGE_START="Welcome to Craps \n"
            +  "please push the button(hit) to  launch   and  you start the game"
            +  "\n If you launch of exit is 7 or 11, you win with natural"
            +  "\n If you launch of exit is 2, 3 or 12, you fail with craps"
            +  "\n If you get any other  value, you establish point"
            +  "\n The state in points, you can continue launching craps"
            +  "\n But now you will win if you newly get  the point value"
            +  "\n without you previously have obtained 7 " ;

    private Header headerProject;
    private JLabel dice1,dice2;
    private JButton hit;
    private JPanel panelDices,panelResults;
    private ImageIcon imageDice;
    private JTextArea results;
    private Escucha escucha;
    private ModelCraps modelCraps;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Game Craps");
        //this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object or Control Object
        escucha= new Escucha();
        modelCraps= new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Table Game Craps", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH);

        imageDice= new ImageIcon(getClass().getResource("/resource/dado.png"));
        dice1= new JLabel(imageDice);
        dice2= new JLabel(imageDice);

        hit=new JButton("hit");
        hit.addActionListener(escucha);

        panelDices= new JPanel();
        panelDices.setPreferredSize(new Dimension(300,180) );
        panelDices.setBorder(BorderFactory.createTitledBorder("your Craps"));
        panelDices.add(dice1);
        panelDices.add(dice2);
        panelDices.add(hit);
        this.add(panelDices,BorderLayout.CENTER);

        results= new JTextArea(7,31);
        results.setText(MASSAGE_START);
        results.setBorder(BorderFactory.createTitledBorder("what you have to do"));
        JScrollPane scroll = new  JScrollPane(results);
        this.add(scroll,BorderLayout.EAST);








    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           modelCraps.CalculateHit();
           int [ ] faces=modelCraps.getFaces();
           imageDice= new ImageIcon(getClass().getResource("/resource/" + faces [0] + ".png"));
           dice1.setIcon(imageDice);
           imageDice= new ImageIcon(getClass().getResource("/resource/" + faces [1] + ".png"));
           dice2.setIcon(imageDice);



           modelCraps.DetermineStatusOfGame();
           results.setText(modelCraps.getStateToString());
        }
    }
}

