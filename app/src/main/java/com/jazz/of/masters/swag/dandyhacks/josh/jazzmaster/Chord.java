package com.jazz.of.masters.swag.dandyhacks.josh.jazzmaster;

/**
 * Created by Josh on 4/3/2015.
 */
public class Chord {
    String[] mychord;
    public Chord(String[] mychord)
    {
        this.mychord= mychord;
    }
    public String toString()
    {
        String s = "";
        for (String t: mychord)
            s+=t;
        return s;
    }
}
