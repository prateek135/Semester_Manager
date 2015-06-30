package com.semestermanager;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Ass2 extends ActionBarActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ass2);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        setTitle("Assignments");
        try {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = openFileInput("assignment.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            int read = -1;
            StringBuffer sbf = new StringBuffer();
            try {
                while ((read = fileInputStream.read()) != -1) {
                    sbf.append((char) read);
                }
            } catch (IOException e) {

            }

            sbf.append("Add More...`");
            String ss2[] = sbf.toString().split("`");
//        FileInputStream fileInputStream2= null;
//        try {
//            fileInputStream2 = openFileInput("ass_dates.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        int read2=-1;StringBuffer sbf2=new StringBuffer();
//        try {
//            while((read2=fileInputStream2.read())!=-1)
//            {
//                sbf2.append((char)read2);
//            }
//        } catch (IOException e) {
//
//        }
//        sbf2.append("00 ");
//


            if (!(ss2.length == 1)) {
                String ss22[] = new String[ss2.length - 1];
                String sd22[] = new String[ss2.length - 1];
                for (int p = 0; p < ss2.length - 1; p++) {
                    FileInputStream fileInputStream2 = null;
                    try {
                        fileInputStream2 = openFileInput(ss2[p] + ".txt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    int read2 = -1;
                    StringBuffer sbf2 = new StringBuffer();
                    try {
                        while ((read2 = fileInputStream2.read()) != -1) {
                            sbf2.append((char) read2);
                        }
                    } catch (IOException e) {

                    }
                    String htemp[] = sbf2.toString().split("~");
                    ss22[p] = htemp[0];
                    sd22[p] = htemp[1];

                }



//                List<String> ls=new ArrayList<String>();
//                List<String> ld=new ArrayList<String>();
//                Set<String> sets=new HashSet<String>();
//                Set<String> setd=new HashSet<String>();
//                StringBuffer stringBuffer=new StringBuffer("");
//                for(int q=0;q<ss22.length;q++)
//                {
//                    if(!(ls.contains(ss22[q]))){ls.add(ss22[q]);ld.add(sd22[q]);}
//                   // sets.add(ss22[q]);setd.add(sd22[q]);
//
//
//                }
//                Toast.makeText(this,ls.toString()+"",Toast.LENGTH_SHORT).show();
//                String sss[]=new String[ss22.length];
//                String ssd[]=new String[sd22.length];
//                //Toast.makeText(this,ss22.toString()+"",Toast.LENGTH_SHORT).show();
//int yy=0;
//                for(int q=0;q<ss22.length;q++) {
//                    boolean flag=false;
//                    outer:
//                    for (int r = 0; r < ss22.length; r++)
//                    {
//                        if(ss22[q]!=sss[r]){sss[yy]=ss22[q];yy++;break outer;}
//                    }
//                }
                listView = (ListView) findViewById(R.id.listViewAss);
                ArrayAdapter adapter = new CustomAdapterAss(this, ss22, sd22);

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String hh = "Add More...";
                        if (listView.getItemAtPosition(position).toString().equals(hh)) {
//                        Intent intent = new Intent(getApplicationContext(), Ass.class);

//                        startActivity(intent);
                        } else {
                            Intent intent = new Intent(getApplicationContext(), Ass.class);
                            intent.putExtra("check", "here");
                            intent.putExtra("key", position);
                            startActivity(intent);
                        }
                    }
                });

                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    //    Toast.makeText(getApplicationContext(), "finally here/...", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences=getSharedPreferences("MyData",0);
                        sharedPreferences.edit().putString("poo",position+"").commit();
                        android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();

                        SelectDialog sd=new SelectDialog();
                        sd.show(fragmentManager,"SelectDialog");


                        return false;
                    }
                });

            }
        }
        catch(Exception e)
        {
            SharedPreferences sharedPreferences = getSharedPreferences("MyData", 0);
            Toast.makeText(this,"Inside ass2"+ sharedPreferences.getInt("ass_no", 0), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ass2, menu);
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            //Toast.makeText(this, "BACK PRESSED", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        return false;
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
        if(id==R.id.hirgg){
            Intent intent=new Intent(this,Ass.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}