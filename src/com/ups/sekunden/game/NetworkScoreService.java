package com.ups.sekunden.game;

import android.util.Log;
import com.ups.sekunden.domain.Score;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by julien on 15/02/14.
 */
public class NetworkScoreService {
    private static final String SERVEUR_ADRESS = "http://192.168.1.10"; //because i have bad routes in my livebox
    private static final int SERVEUR_PORT = 8000;
    private static final String SUFFIX_URL = "/ws-score/";
    /*private static final String SERVEUR_ADRESS = "http://jouradain.no-ip.org";
    private static final int SERVEUR_PORT = 14800;
    private static final String SUFFIX_URL = "/ws-score/";*/


    private static final String ADD_METHOD = "add_score";
    private static final String GET_SCORE_METHOD = "get_all";

    /**
     * adding score into db
     * @param author
     * @param score
     * @return
     */
    public static boolean add_score(String author, int score){
        URL url = null;
        try {
            url = new URL(SERVEUR_ADRESS + ':' + SERVEUR_PORT + SUFFIX_URL + GET_SCORE_METHOD+"?author="+author+"&score="+score);
            String str = conToString(url);
            return str.equals("1");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * adding score into db
     * @param score
     * @return
     */
    public static boolean add_score(Score score){
        URL url = null;
        try {
            url = new URL(SERVEUR_ADRESS + ':' + SERVEUR_PORT + SUFFIX_URL + ADD_METHOD+"?author="+score.author+"&score="+score.score);
            String str = conToString(url);
            return str.equals("1");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * to order by desc, just write '-score' instead of 'score'
     * @param order possible value :{author, score, date}
     * @return all score
     */
    public static List<Score> get_scores(String order)  {
        List<Score> ret_arr = null;
        try {
            URL url = null;
            String str = null;
            if (order != null && (order.equals("date") || order.equals("-date") ||
                                order.equals("author") || order.equals("-author") ||
                                order.equals("score") || order.equals("-score")  )){
                url = new URL(SERVEUR_ADRESS + ':' + SERVEUR_PORT + SUFFIX_URL + GET_SCORE_METHOD+"?order="+order);
            }else{
                url = new URL(SERVEUR_ADRESS + ':' + SERVEUR_PORT + SUFFIX_URL + GET_SCORE_METHOD);
            }
            str = conToString(url);


            //parse json
            JSONArray json = null;
            ret_arr = new ArrayList();
            json = new JSONArray(str);
            for (int i=0; i < json.length(); i++){
                JSONObject jsonScore = json.getJSONObject(i);
                // Pulling items from the array
                int value = jsonScore.getInt("score");
                String author = jsonScore.getString("author");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = df.parse(jsonScore.getString("date"));
                Score score = new Score(value,author,date);
                ret_arr.add(score);
            }
            return ret_arr;
        } catch (Exception e) {
            e.printStackTrace();
            return ret_arr;
        }
    }

    /**
     * @return all score
     */
    public static List<Score> get_scores() {
        return get_scores(null);
    }

    /**
     * convert to string, return null on error
     * @param url complete HTTP GET url
     * @return
     */
    private static String conToString(URL url){
        URLConnection con = null;
        String ret = null;
        try {
            con = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            ret = "";
            String tmp;
            while ((tmp = in.readLine()) != null)
                ret += tmp;
            in.close();
            return ret;

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("HTTPRETURN", e.getStackTrace().toString());
            return ret;
        }
    }
}
