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
public class GUIGridBagLayout extends JFrame {
    public static final String MASSAGE_START = "Bienvenido a Craps \n"
            + "Pulse boton (hit) para tirar y empezar el juego"
            + "\n Si tu tiro de salida  es 7 o 11, tu ganas con natural"
            + "\n Si tu tiro de salida  es 2, 3 o 12, tu pierdes con craps"
            + "\n Si tu sacas cualquier otro valor , tu estableces punto"
            + "\n con el estado en los puntos,tu puedes siguir tirando los dados"
            + "\n Pero tu ganaras si tu nuevamente consigues el valor punto"
            + "\n sin que tu  previamente hayas obtenido 7 ";

    private Header headerProject;
    private JLabel dice1, dice2;
    private JButton hit, ayuda, salir;
    private JPanel panelDices;
    private ImageIcon imageDice;
    private JTextArea outputMassages, diceResults;
    private Escucha escucha;
    private ModelCraps modelCraps;

    /**
     * Constructor of GUI class
     */
    public GUIGridBagLayout(){
        initGUI();
        //Default JFrame configuration
        this.setTitle("Game Craps");
        this.setUndecorated(true);
        this.setBackground(new Color(255,255,255,0));
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout((LayoutManager) new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Create Listener Object or Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Mesa Juego Craps", Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject, constraints);

        ayuda = new JButton("   ?   ");
        ayuda.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(ayuda, constraints);

        salir = new JButton("Salir");
        salir.addActionListener(escucha);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;
        this.add(salir, constraints);

        imageDice= new ImageIcon(getClass().getResource("/resource/dado.png"));
        dice1= new JLabel(imageDice);
        dice2= new JLabel(imageDice);



        panelDices= new JPanel();
        panelDices.setPreferredSize(new Dimension(300,180) );
        panelDices.setBorder(BorderFactory.createTitledBorder("Tus dados"));
        panelDices.add(dice1);
        panelDices.add(dice2);

        //headerProject = new Header("Mesa Juego Craps", Color.BLACK);
        imageDice= new ImageIcon(getClass().getResource("/resource/dado.png"));

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        add(panelDices, constraints);

        diceResults= new JTextArea(4,31);
        diceResults.setEditable(false);
        diceResults.setBorder(BorderFactory.createTitledBorder("Resultados"));
        diceResults.setText("Debes lanzar los dados");

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        add(diceResults, constraints);



        hit=new JButton("hit");
        hit.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        add(hit, constraints);

        outputMassages= new JTextArea(4,31);
        outputMassages.setText("Usa el boton ayuda (?) para ver la reglas del juego");
        outputMassages.setBorder(BorderFactory.createTitledBorder("Mensajes"));
        outputMassages.setEditable(false);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        add(outputMassages, constraints);


    }
    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }





    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==hit) {
                modelCraps.CalculateHit();
                int[] faces = modelCraps.getFaces();
                imageDice = new ImageIcon(getClass().getResource("/resource/" + faces[0] + ".png"));
                dice1.setIcon(imageDice);
                imageDice = new ImageIcon(getClass().getResource("/resource/" + faces[1] + ".png"));
                dice2.setIcon(imageDice);
                modelCraps.DetermineStatusOfGame();

                diceResults.setText(modelCraps.getStateToString()[0]);

                outputMassages.setText(modelCraps.getStateToString()[1]);
            }else{
                if(e.getSource()==ayuda){
                    JOptionPane.showMessageDialog(null,MASSAGE_START);
                } else{
                    System.exit(0);
                }
            }

        }
    }


}
