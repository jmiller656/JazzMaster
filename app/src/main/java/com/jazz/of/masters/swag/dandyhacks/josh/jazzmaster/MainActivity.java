package com.jazz.of.masters.swag.dandyhacks.josh.jazzmaster;

import android.app.ActionBar;
import android.content.ClipData;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private int[] whitekeys = {0,2,4,5,7,9,11,12,14,16,17,19,21,23};
    private int[] blackkeys = {1,3,6,8,10,13,15,18,20,22};
    private ArrayList<ImageButton> keys = new ArrayList<>();
    private RadioGroup n,c;
    private TextView current,next,prev;
    private MusicTools tools= new MusicTools();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        n = (RadioGroup) findViewById(R.id.notes);
        c = (RadioGroup) findViewById(R.id.chords);
        current = (TextView) findViewById(R.id.currentChord);
        prev = (TextView) findViewById(R.id.Previous);
        next = (TextView) findViewById(R.id.Next);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        getKeys();
    }

    public void getKeys()
    {
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void add(View view){
        String root;
        String chord;
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
        current.setText(root+chord);
        //Toast.makeText(getApplicationContext(),"Root: " + root + " Chord: " + chord, Toast.LENGTH_SHORT).show();
    }
    public void remove(View view){}
    public void previous(View view){}
    public void next(View view){}
}
