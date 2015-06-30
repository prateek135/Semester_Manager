package com.semestermanager;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Ass3 extends ActionBarActivity {

    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ass3);
        try {
            Intent intent = getIntent();
            position = Integer.parseInt(intent.getStringExtra("key"));
            setTitle("Assignment");
            TextView tv1 = (TextView) findViewById(R.id.textView7);
            TextView tv2 = (TextView) findViewById(R.id.textView9);
//            FileInputStream fileInputStream = null;
//            try {
//                fileInputStream = openFileInput("assignment.txt");
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            int read = -1;
//            StringBuffer sbf = new StringBuffer();
//            int count2 = 0;
//            try {
//                while ((read = fileInputStream.read()) != -1) {
//
//                    if ((char) read == '~') {
//                        count2++;
//                    }
//                    if (count2 == position)
//                        sbf.append((char) read);
//                }
//            } catch (IOException e) {
//
//            }
//
//
//            FileInputStream fileInputStream2 = null;
//            try {
//                fileInputStream2 = openFileInput("ass_dates.txt");
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            int read2 = -1;
//            StringBuffer sbf2 = new StringBuffer();
//            int count = 0;
//            try {
//                while ((read2 = fileInputStream2.read()) != -1) {
//
//                    if ((char) read2 == ' ') {
//                        count++;
//                    }
//                    if (count == position)
//                        sbf2.append((char) read2);
//                }
//            } catch (IOException e) {
//
//            }
//
//            ss2 = sbf.toString();
//            ss22 = sbf2.toString();

            FileInputStream fileInputStream= null;
            try {
                fileInputStream = openFileInput("assignment.txt");
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

            sbf.append("Add More...~");
            FileInputStream fileInputStream2= null;
            try {
                fileInputStream2 = openFileInput("ass_dates.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            int read2=-1;StringBuffer sbf2=new StringBuffer();
            try {
                while((read2=fileInputStream2.read())!=-1)
                {
                    sbf2.append((char)read2);
                }
            } catch (IOException e) {

            }
            sbf2.append("00 ");
            String ss2[]=sbf.toString().split("~");
            String ss22[]=sbf2.toString().split(" ");

            tv1.setText(ss2[position]);
            tv2.setText(ss22[position]);
        }
        catch (Exception e){
            Toast.makeText(this,"Its here "+position,Toast.LENGTH_SHORT).show();}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ass3, menu);
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
     //       Toast.makeText(this,"BACK PRESSED",Toast.LENGTH_SHORT).show();
//            Intent intent=new Intent(this,Ass2.class);
//            startActivity(intent);
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

        return super.onOptionsItemSelected(item);
    }
}
