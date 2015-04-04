package com.jazz.of.masters.swag.dandyhacks.josh.jazzmaster;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by Josh on 4/3/2015.
 */
public class MusicTools {
    String[] n ={"C","C#","D","D#","E","F","F#","G","G#","A","A#","B","C","C#","D","D#","E","F","F#","G","G#","A","A#","B","C","C#","D","D#","E","F","F#","G","G#","A","A#","B"};
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
    int[] maj9 = {4,7,14};
    int[] min9 = {3,7,13};
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
        chords.put("maj",maj);
        chords.put("min",min);
        chords.put("dim",dim);
        chords.put("sus2",sus2);
        chords.put("sus4",sus4);
        chords.put("aug",aug);
        chords.put("maj6",maj6);
        chords.put("maj7",maj7);
        chords.put("maj9",maj9);
        chords.put("min6",min6);
        chords.put("min7",min7);
        chords.put("min9",min9);
    }

    public Chord getchord(String root, String chord)
    {
        ArrayList<String> ch= new ArrayList<>();
        int[] intervals = chords.get(chord);
        ch.add(root);
        for (int i = 0; i<intervals.length;i++){
            int j = notes.indexOf(root);
            j += intervals[i];
            ch.add(notes.get(j));
            }
        Chord c = new Chord((String[])ch.toArray());
        return c;
    }
}
