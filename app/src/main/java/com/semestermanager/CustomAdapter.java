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
import android.widget.Toast;


/**
 * Created by its_PKY on 3/5/2015.
 */
class CustomAdapter extends ArrayAdapter<String>{

    CustomAdapter(Context context, String[] ss) {
        super(context,R.layout.row_layout, ss);
    }
    int i=0;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li=LayoutInflater.from(getContext());
        View view= li.inflate(R.layout.row_layout, parent, false);
        String sst=getItem(position);
        TextView tv=(TextView)view.findViewById(R.id.textView5);
        Typeface custom_font = Typeface.createFromAsset(getContext().getAssets(),"font1.ttf");
        tv.setTypeface(custom_font);

        tv.setText(sst);
        ImageView iv=(ImageView)view.findViewById(R.id.imageViewrow);

//        Bitmap bi= BitmapFactory.decodeResource(view.getResources(),R.drawable.ad_bunk);
//        iv.setImageBitmap(bi);
        tv.setText(sst);
      //  iv.setImageResource(R.drawable.ic_launcher);

        switch (position)
        {
            case 0:
                //iv.setImageResource(R.drawable.ic_launcher);
                Bitmap bi= BitmapFactory.decodeResource(view.getResources(),R.drawable.ad_ass);
                iv.setImageBitmap(bi);

                break;
            case 1:
                Bitmap bi2= BitmapFactory.decodeResource(view.getResources(),R.drawable.ad_notes);
                iv.setImageBitmap(bi2);

                //iv.setImageResource(R.drawable.ad_notes);
                break;
            case 2:
                Bitmap bi3= BitmapFactory.decodeResource(view.getResources(),R.drawable.ad_bunk);
                iv.setImageBitmap(bi3);

                //iv.setImageResource(R.drawable.ad_bunk);
                break;
        }

        i++;


    return view;
    }
}
