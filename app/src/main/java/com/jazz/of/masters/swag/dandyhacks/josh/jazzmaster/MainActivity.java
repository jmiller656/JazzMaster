package com.jazz.of.masters.swag.dandyhacks.josh.jazzmaster;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private int[] whitekeys = {0,2,4,5,7,9,11,12,14,16,17,19,21,23};
    private int[] blackkeys = {1,3,6,8,10,13,15,18,20,22};
    private ArrayList<Integer> w,b;
    private ArrayList<ImageButton> keys = new ArrayList<>();
    private RadioGroup n,c;
    private TextView current,next,prev;
    private MusicTools tools= new MusicTools();
    private ArrayList<String> progression = new ArrayList<>();
    private int pos = 0;
    private MediaPlayer mp= new MediaPlayer();
    private ArrayList<File> files = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mp.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mp.setLooping(false);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        w = new ArrayList<>();
        b = new ArrayList<>();
        for(int i: whitekeys){
            w.add(i);
        }
        for(int i: blackkeys){
            b.add(i);
        }
        setContentView(R.layout.activity_main);
        n = (RadioGroup) findViewById(R.id.notes);
        c = (RadioGroup) findViewById(R.id.chords);
        current = (TextView) findViewById(R.id.currentChord);
        prev = (TextView) findViewById(R.id.Previous);
        next = (TextView) findViewById(R.id.Next);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        getKeys();
        makeFiles();
    }

    public void makeFiles(){
        for(int i = 0; i<24; i++){
            int[] j = {i};
            files.add(MidiTask.makeMidiFile(getApplicationContext(), j, i + ""));
        }
        int j;
        for(j = 0; j<24;j++){
            final int finalJ = j;
            keys.get(j).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp = MediaPlayer.create(getApplicationContext(),Uri.fromFile(files.get(finalJ)));
                    MusicThread m = new MusicThread(mp);
                    m.start();
                }
            });
        }
    }

    public void getKeys(){
        keys.add((ImageButton)findViewById(R.id.key1));
        keys.add((ImageButton)findViewById(R.id.key2));
        keys.add((ImageButton)findViewById(R.id.key3));
        keys.add((ImageButton)findViewById(R.id.key4));
        keys.add((ImageButton)findViewById(R.id.key5));
        keys.add((ImageButton)findViewById(R.id.key6));
        keys.add((ImageButton)findViewById(R.id.key7));
        keys.add((ImageButton)findViewById(R.id.key8));
        keys.add((ImageButton)findViewById(R.id.key9));
        keys.add((ImageButton)findViewById(R.id.key10));
        keys.add((ImageButton)findViewById(R.id.key11));
        keys.add((ImageButton)findViewById(R.id.key12));
        keys.add((ImageButton)findViewById(R.id.key13));
        keys.add((ImageButton)findViewById(R.id.key14));
        keys.add((ImageButton)findViewById(R.id.key15));
        keys.add((ImageButton)findViewById(R.id.key16));
        keys.add((ImageButton)findViewById(R.id.key17));
        keys.add((ImageButton)findViewById(R.id.key18));
        keys.add((ImageButton)findViewById(R.id.key19));
        keys.add((ImageButton)findViewById(R.id.key20));
        keys.add((ImageButton)findViewById(R.id.key21));
        keys.add((ImageButton)findViewById(R.id.key22));
        keys.add((ImageButton)findViewById(R.id.key23));
        keys.add((ImageButton)findViewById(R.id.key24));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            mp = MediaPlayer.create(this,R.raw.darudesandstorm);
            MusicThread m = new MusicThread(mp);
            m.start();
            return true;
        }
        else if(id == R.id.action_progression){
            for(String s: progression){
                playNote(tools.getchord(s.split(" ")[0], s.split(" ")[1]));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        else if(id == R.id.action_danger){
            mp = MediaPlayer.create(this,R.raw.danger);
            MusicThread m = new MusicThread(mp);
            m.start();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void add(View view){
        String root;
        String chord;
        resetKeys();
        int id = n.getCheckedRadioButtonId();
        if (id == -1){
            Toast.makeText(getApplicationContext(),"No note selected",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            RadioButton r=(RadioButton) findViewById(id);
            root = r.getText().toString();
        }
        id = c.getCheckedRadioButtonId();
        if (id == -1){
            Toast.makeText(getApplicationContext(),"No chord type selected",Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            RadioButton r=(RadioButton) findViewById(id);
            chord = r.getText().toString();
        }
        int[] chd = tools.getchord(root,chord);
        current.setText(root+" " +chord);
        progression.add(root+" " +chord);
        for(int i:chd){
            highlightKey(i);
        }
        pos = progression.size()-1;
        if(pos-1>=0){
            prev.setText(progression.get(pos-1));
        }
        playNote(chd);
       }

    public void highlightKey(int i)
    {
        keys.get(i).setBackgroundColor(Color.parseColor("#0080FF"));
    }

    public void resetKeys(){
        for(int i = 0; i<keys.size();i++){
            if(w.contains(i)){
                keys.get(i).setBackground(getResources().getDrawable(R.drawable.white_selector));
            }
            else{
                keys.get(i).setBackgroundColor(Color.parseColor("#000000"));
            }
        }
    }

    public void remove(View view){
        if(progression.size()>0){
        progression.remove(pos);
        resetKeys();
        if(progression.size()==0){
            current.setText("");
        }
        else{
            if (progression.size()>0){
                if (pos != 0)
                    current.setText(progression.get(pos-1));
                else
                    current.setText(progression.get(pos));
                String s = current.getText().toString();
                String [] rc = s.split(" ");
                int[] chd = tools.getchord(rc[0],rc[1]);
                for(int i:chd){
                    highlightKey(i);
                }
                if(pos -1 >0){
                    prev.setText(progression.get(pos-1));
                }
                else{
                    prev.setText("");
                }
                if(progression.size()-pos>=2){
                    next.setText(progression.get(pos+1));
                }
                else{
                    next.setText("");
                }
            }
            else{
                current.setText("");
                prev.setText("");
                next.setText("");
            }
            if(pos == progression.size()&& pos>=1)
                pos --;
        }
        }
        else{
            Toast.makeText(getApplicationContext(),"No chords to remove", Toast.LENGTH_SHORT).show();
        }
    }

    public void previous(View view){
        if(pos-1 >= 0){
            resetKeys();
            pos = pos-1;
            current.setText(progression.get(pos));
            String s = current.getText().toString();
            String [] rc = s.split(" ");
            int[] chd = tools.getchord(rc[0],rc[1]);
            for(int i:chd){
                highlightKey(i);
            }
            next.setText(progression.get(pos+1));
            if(pos -1 >=0){
                prev.setText(progression.get(pos-1));
            }
            else{
                prev.setText("");
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"No previous chord exists",Toast.LENGTH_SHORT).show();
        }
    }

    public void next(View view){
        if(progression.size()-pos>=2){
            resetKeys();
            pos = pos+1;
            current.setText(progression.get(pos));
            prev.setText(progression.get(pos-1));
            String s = current.getText().toString();
            String [] rc = s.split(" ");
            int[] chd = tools.getchord(rc[0],rc[1]);
            for(int i:chd){
                highlightKey(i);
            }
            if(progression.size()-pos>=2){
                next.setText(progression.get(pos+1));
            }
            else{
                next.setText("");
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"No next chord exists",Toast.LENGTH_SHORT).show();
        }
    }

    public void playNote(int[] chord){
        mp = MediaPlayer.create(this,Uri.fromFile(MidiTask.makeMidi(getApplicationContext(),chord)));
        MusicThread m = new MusicThread(mp);
        m.start();
    }

}
