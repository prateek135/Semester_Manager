package com.semestermanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Introductory extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        setTitle("Welcome");
        EditText edittext=(EditText)findViewById(R.id.editText);
        edittext.setHint("Name");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_introductory, menu);
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
    public void clickedd(View view)
    {

        SharedPreferences sharedpref=getSharedPreferences("MyData", 0);
        SharedPreferences.Editor editor=sharedpref.edit();
        EditText edittext=(EditText)findViewById(R.id.editText);
       // EditText edittext2=(EditText)findViewById(R.id.editText2);
        String gh="";
        if(edittext.getText().toString().equals(gh)){
            Toast.makeText(this,"You should have a name !!",Toast.LENGTH_SHORT).show();
        }
        else {
            editor.putString("name", edittext.getText().toString());
         //   editor.putString("email", edittext2.getText().toString());
            editor.commit();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

}
