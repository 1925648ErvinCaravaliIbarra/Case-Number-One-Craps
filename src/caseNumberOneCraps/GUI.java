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
    public static final String MASSAGE_START="Bienvenido a Craps \n"
            +  "Pulse boton (hit) para tirar y empezar el juego"
            +  "\n Si tu tiro de salida  es 7 o 11, tu ganas con natural"
            +  "\n Si tu tiro de salida  es 2, 3 o 12, tu pierdes con craps"
            +  "\n Si tu sacas cualquier otro valor , tu estableces punto"
            +  "\n con el estado en los puntos,tu puedes siguir tirando los dados"
            +  "\n Pero tu ganaras si tu nuevamente consigues el valor punto"
            +  "\n sin que tu  previamente hayas obtenido 7 " ;

    private Header headerProject;
    private JLabel dice1,dice2;
    private JButton hit;
    private JPanel panelDices,panelResults;
    private ImageIcon imageDice;
    private JTextArea outputMassages, diceResults;
    private Escucha escucha;
    private ModelCraps modelCraps;
    private JSeparator separator;

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
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH);

        imageDice= new ImageIcon(getClass().getResource("/resource/dado.png"));
        dice1= new JLabel(imageDice);
        dice2= new JLabel(imageDice);

        hit=new JButton("hit");
        hit.addActionListener(escucha);

        panelDices= new JPanel();
        panelDices.setPreferredSize(new Dimension(300,180) );
        panelDices.setBorder(BorderFactory.createTitledBorder("Tus dados"));
        panelDices.add(dice1);
        panelDices.add(dice2);
        panelDices.add(hit);
        this.add(panelDices,BorderLayout.CENTER);

        outputMassages= new JTextArea(7,31);
        outputMassages.setText(MASSAGE_START);
        //outputMassages.setBorder(BorderFactory.createTitledBorder("what you have to do"));
        JScrollPane scroll = new  JScrollPane(outputMassages);
        panelResults= new JPanel();
        panelResults.setBorder(BorderFactory.createTitledBorder("Lo que debes hacer"));
        panelResults.add(scroll);
        panelResults.setPreferredSize(new Dimension(370 ,180));
        this.add(panelResults,BorderLayout.EAST);





       diceResults= new JTextArea(4,31);
       separator= new JSeparator();
       separator.setPreferredSize(new Dimension(320,7));
       separator.setBackground(Color.BLUE);


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
           panelResults.removeAll();
           panelResults.setBorder(BorderFactory.createTitledBorder("Resultados"));
           panelResults.add(diceResults);
           panelResults.add(separator);
           panelResults.add(outputMassages);
           diceResults.setText(modelCraps.getStateToString()[0]);
           outputMassages.setRows(4);
           outputMassages.setText(modelCraps.getStateToString()[1]);
           revalidate();
           repaint();
            //outputMassages.setText(modelCraps.getStateToString());
        }
    }
}

