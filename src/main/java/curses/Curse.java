package curses;

import prng.psuedoRandomNumberGenerator;

//a super class to manage all the curses
public class Curse {
    String text;
    int number;
    psuedoRandomNumberGenerator prng;
    Curse (){
        text = "No curses to be had...";
        number = 0;
    }
    public String getMinorCurse(){

        return text;
    }
    public String getMajorCurse(){

        return text;
    }
}
