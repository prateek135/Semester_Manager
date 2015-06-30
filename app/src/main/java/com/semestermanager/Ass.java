package com.semestermanager;

import android.app.DatePickerDialog;
import android.app.Dialog;

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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Calendar;


public class Ass extends ActionBarActivity {
    Button buttonA;
    private DatePicker datePicker;
    private Calendar calendar;
    int entered=0;
    int pos;
    //private TextView dateView;
    private int year, month, day;
   int ass_no;
    EditText edittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ass);
        setTitle("New Assignment");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        edittext = (EditText) findViewById(R.id.editTextA);
        buttonA = (Button) findViewById(R.id.buttonAA);
        Intent intent=getIntent();

        int pos=intent.getIntExtra("key",0);
        String ch="here";
        String hh=intent.getStringExtra("check")+"";
        try {
            if (hh.equals(ch)) {
                FileInputStream fileInputStream2 = null;
                try {
                    fileInputStream2 = openFileInput("assignment.txt");
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

                String ss2[] = sbf2.toString().split("`");


                int chh = Integer.parseInt(ss2[pos]);
                ass_no=chh;
                fileInputStream2 = null;
                try {
                    fileInputStream2 = openFileInput(chh + ".txt");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                read2 = -1;
                StringBuffer sbf3 = new StringBuffer();
                try {
                    while ((read2 = fileInputStream2.read()) != -1) {
                        sbf3.append((char) read2);
                    }
                } catch (IOException e) {

                }
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                String htemp[] = sbf3.toString().split("~");
                edittext.setText(htemp[0]);
                buttonA.setText(htemp[1]);
            } else {
                SharedPreferences sharedPreferences = getSharedPreferences("MyData", 0);
                ass_no = sharedPreferences.getInt("ass_no", 101);
entered=1;
//        Intent intent=getIntent();
//        String value=intent.getStringExtra("key");

                edittext.setHint("Topic");
                Toast.makeText(getApplicationContext(), pos+"  "+intent.getStringExtra("check")+"assno"+ass_no+" "+entered, Toast.LENGTH_SHORT).show();

                //dateView = (TextView) findViewById(R.id.textView3);
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                showDate(year, month + 1, day);
            }
        }
        catch (Exception E)
        {
                    Toast.makeText(getApplicationContext(), pos+"  "+intent.getStringExtra("check")+"assno"+ass_no+" "+entered, Toast.LENGTH_SHORT).show();
        }
}

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
//        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
//                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

private DatePickerDialog.OnDateSetListener myDateListener
        = new DatePickerDialog.OnDateSetListener() {

    @Override
    public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        // arg1 = year
        // arg2 = month
        // arg3 = day
        showDate(arg1, arg2+1, arg3);
    }
};

    private void showDate(int year, int month, int day) {
        buttonA.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ass, menu);
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
    //        Toast.makeText(this,"BACK PRESSED",Toast.LENGTH_SHORT).show();
//     Intent intent=new Intent(this,Ass.class);
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
Toast.makeText(this,ass_no+"",Toast.LENGTH_SHORT).show();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.hirg) {
            try {
                FileOutputStream fileOutputStream;
                String ch = "";
                if (edittext.getText().toString().equals(ch))

                    Toast.makeText(this, "Please Enter Topic", Toast.LENGTH_SHORT).show();
                else {
                    try {
                        String ss = edittext.getText().toString();
                      //  FileChannel fc=fileOutputStream.getChannel();

                        fileOutputStream = openFileOutput(ass_no + ".txt", Context.MODE_PRIVATE);
                        fileOutputStream.write((ss + "~").getBytes());
                        fileOutputStream.flush();
                        fileOutputStream = openFileOutput(ass_no + ".txt", Context.MODE_APPEND);
                        ss = buttonA.getText().toString();
                        fileOutputStream.write(ss.getBytes());
                        fileOutputStream.flush();
                        //reading assignment.txt to see of the tpic already exits or not
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
                        String assno=ass_no+"";boolean flag=true;
                        for(int i=0;i<ss2.length;i++){if(ss2[i].equals(assno)){flag=false;break;}}
                        if(flag){
                        fileOutputStream = openFileOutput("assignment.txt", Context.MODE_APPEND);
                        fileOutputStream.write((ass_no + "`").getBytes());
                        fileOutputStream.flush();}

                    } catch (FileNotFoundException e) {
                        Toast.makeText(this, "Galti Hogyi Mamu", Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        Toast.makeText(this, "Galti Hogyi Mamu", Toast.LENGTH_SHORT).show();
                    }
                    SharedPreferences sharedPreferences = getSharedPreferences("MyData", 0);
                    if(entered==1) {

                        int aa = ass_no + 1;

                        sharedPreferences.edit().putInt("ass_no", aa).commit();
                    }
                    Toast.makeText(this, sharedPreferences.getInt("ass_no", 0)+"", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, Ass2.class);
                    startActivity(intent);
                }
            } catch (Exception E) {
             //   SharedPreferences sharedPreferences = getSharedPreferences("MyData", MODE_PRIVATE);
                Toast.makeText(this, /*sharedPreferences.getInt("ass_no", 0)*/"hereeee", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public void add1(View view)
    {
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream2 = openFileInput("assignment.txt");
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
        Toast.makeText(this, sbf2.toString(), Toast.LENGTH_SHORT).show();

    }
}
