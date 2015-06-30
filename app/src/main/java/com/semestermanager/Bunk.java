package com.semestermanager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Bunk extends ActionBarActivity {
    EditText editext,editText2,editText3;
    TextView textView4,textview6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunk);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
        setTitle("Bunk Calculator");
        editext=(EditText)findViewById(R.id.editTextB1);
        editext.setBackgroundResource(R.drawable.backtext);
        editext.setHint("for eg. 14");
        editText2=(EditText)findViewById(R.id.editText3);
        editText2.setBackgroundResource(R.drawable.backtext);
        editText3=(EditText)findViewById(R.id.editText4);
        editText2.setHint("for eg. 19");
        editText3.setHint("for eg. 95");
        editText3.setBackgroundResource(R.drawable.backtext);
        textView4=(TextView)findViewById(R.id.textView4);
        textview6=(TextView)findViewById(R.id.textView6);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bunk, menu);
        return true;
    }
    public void BunkClick(View view)
    {
        InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),0);
        float i1=0,i2=0,i3=0;
        try {
            i1 = Float.parseFloat(editext.getText().toString());
            i2 = Float.parseFloat(editText2.getText().toString());
            i3 = Float.parseFloat(editText3.getText().toString());
        }
        catch (Exception e)
        {
            textView4.setText("Incorrect Values");
            textview6.setText(" ");
            Toast.makeText(this,"Incorrect Values! \nPlease Check Again",Toast.LENGTH_LONG).show();
        }
        if(i1<=0||i1>=9999||i1>i2||i2<=0||i2>=9999||i3<=0||i3>99.99)
        {
            Toast.makeText(this,"Incorrect Values! \nPlease Check Again",Toast.LENGTH_LONG).show();
        }
        else
        {
            float temp=(i1/i2)*100;
                     textView4.setText((int)temp+"%");

            if(temp<=i3){
                float tempp=i3/100;
                ImageView imageView=(ImageView)findViewById(R.id.imageView4);
                imageView.setImageResource(R.drawable.sad_1);
                int ii=(int)((i1-tempp*i2)/(tempp-1));
                if(ii==0||ii==1)
                    textview6.setText("You cannot bunk right now!! Stay back and attend 1 more class.");
                else
                textview6.setText("You cannot bunk right now!! Stay back and attend "+ii+" more classes.");
            //hi...................i have done awesome.............(p/100)*i2

            }
            else {
                ImageView imageView=(ImageView)findViewById(R.id.imageView4);
                imageView.setImageResource(R.drawable.hap_1);
                textview6.setText("Booya!! You can bunk right now!!");

            }
        }
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