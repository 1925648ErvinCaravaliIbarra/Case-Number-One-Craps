package caseNumberOneCraps;

import java.util.Random;

/**
 * This class dice allow to know the value of visible dice face.The value's dice have to be between 1 and 6.
 * @autor Ervin-Caravali-I-ID:1925648 ervin.caravali@correounivalle.edu.co
 * @version @version v.1.0.0 date:06/12/2021
 */
public class Dice {
    private int face;
    /**
     * This method generate a random number  for  dice face .
     * @return a number between (1,6).
     */

    public int getFace() {
        Random random = new Random();
        face=random.nextInt(6)+1;
        return face;
    }
}
