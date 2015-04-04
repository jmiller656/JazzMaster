package com.jazz.of.masters.swag.dandyhacks.josh.jazzmaster;

import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MusicTools tools= new MusicTools();
        ListView whiteKeys = (ListView) findViewById(R.id.whites);
        ListView blackKeys = (ListView) findViewById(R.id.blacks);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        whiteKeys.setMinimumHeight(height/21);
        blackKeys.setMinimumHeight(height/21);
        ArrayAdapter<String> wAdapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,tools.getWhites());
        whiteKeys.setAdapter(wAdapter);
        ArrayAdapter<String> bAdapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,tools.getBlacks());
        blackKeys.setAdapter(bAdapter);
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
}
