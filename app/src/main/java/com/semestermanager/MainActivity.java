package com.semestermanager;

import android.content.Context;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    String cov[]={"Assignments","Notes","Bunk Calculator"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));

        SharedPreferences sharedpref=getSharedPreferences("MyData", 0);
        SharedPreferences sharedPreferences=getSharedPreferences("MyDataass",0);
        sharedPreferences.edit().putInt("ass_no",101).commit();
        sharedPreferences.edit().putInt("not_no",201).commit();
        SharedPreferences.Editor editor=sharedpref.edit();
        if(sharedpref.getBoolean("firsttime",true)){
            sharedpref.edit().putBoolean("firsttime", false).commit();

            Intent intent=new Intent(this,Introductory.class);startActivity(intent);
            sharedpref.edit().putInt("no_of_notes",0).commit();

            FileOutputStream fileOutputStream;
            try {
                fileOutputStream=openFileOutput("Addss.txt", Context.MODE_PRIVATE);
                fileOutputStream=openFileOutput("assignment.txt", Context.MODE_PRIVATE);
                fileOutputStream=openFileOutput("ass_dates.txt", Context.MODE_PRIVATE);
                fileOutputStream=openFileOutput("desc_notes.txt", Context.MODE_PRIVATE);
                fileOutputStream=openFileOutput("topic_notes.txt", Context.MODE_PRIVATE);
                fileOutputStream=openFileOutput("notes_list.txt", Context.MODE_PRIVATE);
                for(int p=0;p<26;p++)
                fileOutputStream=openFileOutput(p+".txt", Context.MODE_PRIVATE);
                for(int p=101;p<201;p++)fileOutputStream=openFileOutput(p+".txt", Context.MODE_PRIVATE);;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

       //  Toast.makeText(getApplicationContext(),"Welcome "+sharedpref.getString("name","New User").toString()+" !!",Toast.LENGTH_SHORT).show();
        final String sss="pky";

        TextView tv=(TextView)findViewById(R.id.textViewM);
        tv.setText("Hi "+sharedpref.getString("name","New User").toString());


//        // String str=sharedpref.getString("checkintro","first").toString();
//        //  Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
//        final ArrayList<String> list =new ArrayList<String>();
//        for(int i=0;i<2;i++)list.add(cov[i]);
//
//        ListView lv=(ListView)findViewById(R.id.listView);
//        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
//        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(position==0)
//                {
//                    Toast.makeText(getApplicationContext(),position,Toast.LENGTH_SHORT).show();
//    //                  Intent intent=new Intent(getApplicationContext(),.class);
//  //                    startActivity(intent);
//                }
//                if(position==1)
//                {
//                    Toast.makeText(getApplicationContext(),position,Toast.LENGTH_SHORT).show();sharedpref.getString("name","New User").toString()
////                    startActivity(new Intent(getApplicationContext(),.class));
//                }
//
//            }
//        });
//
//
        ListAdapter adapter=new CustomAdapter(this,cov);
        ListView lv=(ListView)findViewById(R.id.listView1);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    Intent intent=new Intent(getApplicationContext(),Ass2.class);
                    startActivity(intent);
                }
                if(position==1)
                {
                 startActivity(new Intent(getApplicationContext(),Notes2.class));
                }
                if(position==2)
                {
                    startActivity(new Intent(getApplicationContext(),Bunk.class));
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //

    public boolean onKeyDown(int keyCode,KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            //Toast.makeText(this,"BACK PRESSED",Toast.LENGTH_SHORT).show();
            finish();
            moveTaskToBack(true);
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
            Intent intent=new Intent(this,myself.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}