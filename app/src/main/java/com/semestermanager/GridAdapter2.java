package com.semestermanager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by its_PKY on 5/29/2015.
 */
public class GridAdapter2 extends ArrayAdapter {
    private Context context;

    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public GridAdapter2(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder2 holder = null;


        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder2();
            //holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image2 = (ImageView) row.findViewById(R.id.image000);
            row.setTag(holder);
        } else {
            holder = (ViewHolder2) row.getTag();
        }

        ImageItem2 item = (ImageItem2) data.get(position);
      //
      //  holder.imageTitle.setText(item.getTitle());
        holder.image2.setImageBitmap(item.getImage());
        return row;
    }

    static class ViewHolder2 {
    //    TextView imageTitle;
        ImageView image2;
    }

}
class ImageItem2 {
    private Bitmap image;
   // private String title;

    public ImageItem2(Bitmap image) {
        super();
        this.image = image;
      //  this.title = title;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

//    public String getTitle() {
//        return title;
//    }

   // public void setTitle(String title) {
     //   this.title = title;

}

