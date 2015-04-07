package com.joshmiller656.jazzmaster;

import android.content.Context;

import com.leff.midi.MidiFile;
import com.leff.midi.MidiTrack;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import com.leff.midi.event.meta.TimeSignature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Josh on 4/4/2015.
 */
public class MidiTask {
    //private static MusicTools tools= new MusicTools();
    public static File makeMidi(Context context, int[]chord){
        MidiTrack tempo = new MidiTrack();
        int c = 0;
        int v = 100;
        ArrayList<MidiTrack> midiTracks = new ArrayList<>();
        //MAKE TEMPO
        TimeSignature ts = new TimeSignature();
        ts.setTimeSignature(4, 4, TimeSignature.DEFAULT_METER, TimeSignature.DEFAULT_DIVISION);
        Tempo t = new Tempo();
        t.setBpm(120);
        tempo.insertEvent(ts);
        tempo.insertEvent(t);
        midiTracks.add(tempo);
        //ADD OTHER TRACKS
        for(int i=0; i<chord.length;i++){
            MidiTrack midiTrack = new MidiTrack();
            NoteOn noteOn= new NoteOn(480,c,60+chord[i],v);
            NoteOff noteOff = new NoteOff(480 + 480*3, c,60+chord[i],0);
            midiTrack.insertEvent(noteOn);
            midiTrack.insertEvent(noteOff);
            midiTracks.add(midiTrack);
        }
        MidiFile midi = new MidiFile(MidiFile.DEFAULT_RESOLUTION, midiTracks);
        File output = new File(context.getFilesDir()+"m.mid");
        try
        {
            midi.writeToFile(output);
        }
        catch(IOException e)
        {
            System.err.println(e);
        }
        return output;
    }
    public static File makeMidiFile(Context context, int[]chord,String name){
        MidiTrack tempo = new MidiTrack();
        int c = 0;
        int v = 80;
        ArrayList<MidiTrack> midiTracks = new ArrayList<>();
        //MAKE TEMPO
        TimeSignature ts = new TimeSignature();
        ts.setTimeSignature(4, 4, TimeSignature.DEFAULT_METER, TimeSignature.DEFAULT_DIVISION);
        Tempo t = new Tempo();
        t.setBpm(120);
        tempo.insertEvent(ts);
        tempo.insertEvent(t);
        midiTracks.add(tempo);
        //ADD OTHER TRACKS
        for(int i=0; i<chord.length;i++){
            MidiTrack midiTrack = new MidiTrack();
            NoteOn noteOn= new NoteOn(480,c,60+chord[i],v);
            NoteOff noteOff = new NoteOff(480+ 300,c,60+chord[i],0);
            midiTrack.insertEvent(noteOn);
            midiTrack.insertEvent(noteOff);
            midiTracks.add(midiTrack);
        }
        MidiFile midi = new MidiFile(MidiFile.DEFAULT_RESOLUTION, midiTracks);
        File output = new File(context.getFilesDir()+name);
        try
        {
            midi.writeToFile(output);
        }
        catch(IOException e)
        {
            System.err.println(e);
        }
        return output;
    }
}
