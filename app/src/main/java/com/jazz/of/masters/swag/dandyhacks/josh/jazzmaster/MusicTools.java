package com.jazz.of.masters.swag.dandyhacks.josh.jazzmaster;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by Josh on 4/3/2015.
 */
public class MusicTools {
    String[] n ={"C","C#","D","D#","E","F","F#","G","G#","A","A#","B","C","C#","D","D#","E","F","F#","G","G#","A","A#","B"};
    private ArrayList<String> notes =new ArrayList<>();
    int[] maj = {4,7};
    int[] min = {3,7};
    int[] dim= {3,6};
    int[] sus2= {2,7};
    int[] sus4= {5,7};
    int[] aug= {4,8};
    int[] maj6 = {4,7,9};
    int[] min6 = {3,7,8};
    int[] maj7= {4,7,11};
    int[] min7 = {3,7,11};
    HashMap<String,int[]> chords= new HashMap<>();
    public MusicTools()
    {
        for (String s: n)
            notes.add(s);
        make();
    }
    public ArrayList<String> getWhites()
    {
        ArrayList<String> whites = new ArrayList<>();
        for(int i = 0; i<n.length; i++){
            if (n[i].length()<2){
                whites.add(n[i]);
            }

        }
        return whites;
    }
    public ArrayList<String> getBlacks()
    {
        ArrayList<String> blacks = new ArrayList<>();
        for(int i = 0; i<n.length; i++){
            if (!(n[i].length()<2)){
                blacks.add(n[i]);
            }

        }
        return blacks;
    }
    public ArrayList<String> getNotes()
    {
        return notes;
    }
    public void make()
    {
        chords.put("Major",maj);
        chords.put("Minor",min);
        chords.put("Diminished",dim);
        chords.put("Sus2",sus2);
        chords.put("Sus4",sus4);
        chords.put("Augmented",aug);
        chords.put("Maj6",maj6);
        chords.put("Maj7",maj7);
        chords.put("Min6",min6);
        chords.put("Min7",min7);
    }

    public int[] getchord(String root, String chord)
    {
        int[] intervals = chords.get(chord);
        int[] c = new int[intervals.length+1];
        for (int i = 0; i<c.length;i++){
            if (i==0)
                c[i]=notes.indexOf(root);
            else
                c[i]=intervals[i-1];
        }
        return c;
    }
}
