package com.ups.sekunden.game;

import java.util.Date;

/**
 * Created by julien on 15/02/14.
 */
public class Score {
    public int score;
    public String author;
    public Date date ;

    public Score(int pscore, String pauthor, Date pdate){
        author = pauthor ;
        score = pscore ;
        date = pdate ;
    }

}
