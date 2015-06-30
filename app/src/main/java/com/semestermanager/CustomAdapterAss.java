package com.semestermanager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by its_PKY on 3/5/2015.
 */
class CustomAdapterAss extends ArrayAdapter<String>{

    String ad1[],ad2[];

    CustomAdapterAss(Context context, String[] ss,String[] dates) {
        super(context,R.layout.ass_rows,ss);
        this.ad1=ss;
        this.ad2=dates;
    }
    int i=0;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li=LayoutInflater.from(getContext());
        View view= li.inflate(R.layout.ass_rows, parent, false);
        //String sst=getItem(position);
        TextView tv=(TextView)view.findViewById(R.id.textViewrowA);
        TextView tv2=(TextView)view.findViewById(R.id.textView7A);

       // Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(),"font1.ttf");
        //tv.setTypeface(custom_font);
//        if(!(position==(ad1.length-1))) {
            tv.setText(ad1[position]);
            String ch = "00";
            if (ad2[position].equals(ch)) tv2.setText(" ");
            else
                tv2.setText(ad2[position]);
       // }
//        Bitmap bi= BitmapFactory.decodeResource(view.getResources(),R.drawable.ad_bunk);
//        iv.setImageBitmap(bi);

      //  iv.setImageResource(R.drawable.ic_launcher);



        i++;


    return view;
    }
}
