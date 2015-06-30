package com.semestermanager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Notes2 extends ActionBarActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes2);
        setTitle("Notes");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        FileInputStream fileInputStream= null;
        try {
            fileInputStream = openFileInput("topic_notes.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int read=-1;StringBuffer sbf=new StringBuffer();
        try {
            while((read=fileInputStream.read())!=-1)
            {
                sbf.append((char)read);
            }
        } catch (IOException e) {

        }

        sbf.append("Add More...332223");


        String ss2[]=sbf.toString().split("332223");
        if(!(ss2.length==1)) {
            listView = (ListView) findViewById(R.id.listViewNotes);
            int y=ss2.length;
            String ss33[]=new String[y-1];
            for(int t=0;t<y-1;t++)
            {

                ss33[t]=ss2[t];
            }
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, ss33);

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String hh = "Add More...";
                    if (listView.getItemAtPosition(position).toString().equals(hh)) {
                        Intent intent = new Intent(getApplicationContext(), Notes3.class);

                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), Notes3.class);
                        intent.putExtra("pos", position + "");
                        startActivity(intent);
                    }
                }
            });
        }
    }
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            //Toast.makeText(this,"BACK PRESSED",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notes2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(this,Notes3.class);
        intent.putExtra("pos","nahi");
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}