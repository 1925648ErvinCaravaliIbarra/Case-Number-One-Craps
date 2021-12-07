package caseNumberOneCraps;
/**
 * Model Craps applies craps rules.
 * state =1 natural winner.
 * state =2 craps failed.
 * state =3 Establish point.
 * state =4 point winner.
 * state =5 point failed.
 * @autor Ervin-Caravali-I-ID:1925648 ervin.caravali@correounivalle.edu.co
 * @version @version v.1.0.0 date:06/12/2021
 */
public class ModelCraps {
    private Dice dice1,dice2;
    private int  hit,point,state,flag;
    private String stateToString;
    private int [] faces;

    /**
     * Class constructor.
     */

    public ModelCraps() {
        dice1= new Dice();
        dice2= new Dice();
        faces= new int [2]; flag=0;
    }


    /**
     * This method Establishes hit value according to each dice.
     */

    public void CalculateHit(){
        faces [0] = dice1.getFace();
        faces [1]= dice2.getFace();
        hit= faces [0]+faces [1];
    }

    /**
     * This method Establishes the game state, this according to state value attribute.
      * state =1 natural winner.
      * state =2 craps failed.
      * state =3 Establish point.
     */

    public void DetermineStatusOfGame(){
        if (flag==0){
            if(hit==7 || hit==11){
                state=1;
            }else {
                if(hit==3|| hit==2 ||hit==12){
                    state=2;
                }else{
                    state=3;
                    point=hit;
                    flag=1;
                }
            }
        }else {
            RoundPoint();
        }
    }

    /**
     * This method Establishes the game state, this according to state value attribute.
     * state =4 point winner.
     * state =5 point failed.
     */
    private void RoundPoint()  {
        if(hit==point) {
            state=4;
            flag=0;
        }if(hit==7){
            state=5;
            flag=0;


        }



    }

    public int getHit() {
        return hit;





    }


    public int getPoint() {
        return point;





    }

    /**
     * This method Establishes a message with the game state, according to state value attribute.
     * @return message for view class.
     */
    public String getStateToString() {
        switch(state){
            case 1:stateToString= "you have gotten  a natural number, you have won";
                break;
            case 2:stateToString= "you have gotten craps,you have failed";
                break;
            case 3:stateToString= "you have established point in"+point+"you have to continue hitting craps"+
                    "\n but if you get 7 before "+point+" you will fail";
                break;
            case 4:stateToString= "if you got again"+point+"you will win";
                break;
            case 5:stateToString= "you have gotten 7 before "+point+" you have failed";
                break;
        }return stateToString;
    }

    public int[] getFaces() {
        return faces;
    }
}