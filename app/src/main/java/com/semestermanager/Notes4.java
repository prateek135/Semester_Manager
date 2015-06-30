package com.semestermanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Notes4 extends ActionBarActivity {
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes4);
        setTitle("Notes");
            position = Integer.parseInt(getIntent().getStringExtra("pos"));
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = openFileInput("topic_notes.txt");
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

            sbf.append("Add More...332223");

            String ss2[] = sbf.toString().split("332223");

        try {
            fileInputStream = openFileInput("desc_notes.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        read = -1;
        StringBuffer sbf2 = new StringBuffer();
        try {
            while ((read = fileInputStream.read()) != -1) {
                sbf2.append((char) read);
            }
        } catch (IOException e) {

        }

        sbf2.append("Add More...332223");

        String ss22[] = sbf2.toString().split("332223");
            TextView tv1 = (TextView) findViewById(R.id.textView11);
            TextView tv2 = (TextView) findViewById(R.id.textView13);
            tv1.setText(ss2[position]);tv2.setText(ss22[position]);
            GridView gridView = (GridView) findViewById(R.id.gridViewNN);
            GridViewAdapter gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData());
            gridView.setAdapter(gridAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                //James Bond...Awesome Movies...
                //Create intent
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("image", item.getImage());
                //Start details activity
                startActivity(intent);
            }
        });


    }

//    private ArrayList<ImageItem2> getData() {
//
//            final ArrayList<ImageItem2> imageItems = new ArrayList<>();try {
////        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
////        for (int i = 0; i < imgs.length(); i++) {
//            FileInputStream fileInputStream = null;
//            try {
//                fileInputStream = openFileInput(position + ".txt");
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            int read = -1;
//            StringBuffer sbf = new StringBuffer();
//            try {
//                while ((read = fileInputStream.read()) != -1) {
//                    sbf.append((char) read);
//                }
//            } catch (IOException e) {
//
//            }
//            //Toast.makeText(getActivity(),"3 "+sbf.toString(),Toast.LENGTH_SHORT).show();
//            String ss[] = sbf.toString().split(" ");
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inSampleSize = 8;
//            for (int i = 0; i < ss.length; i++) {
//                Uri newUri = Uri.parse(ss[i]);
//                final Bitmap bitmap = BitmapFactory.decodeFile(newUri.getPath(),
//                        options);
//                //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
//                imageItems.add(new ImageItem2(bitmap));
//            }
////        }
//        }
//        catch (Exception e){
//            Toast.makeText(this,(imageItems==null)?"Yahoooogadbad":"",Toast.LENGTH_SHORT).show();
//        }
//            return imageItems;
//
//
//    }
    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
//        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
//        for (int i = 0; i < imgs.length(); i++) {
        FileInputStream fileInputStream= null;
        try {
            fileInputStream = openFileInput(position+".txt");
            } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int read=-1;StringBuffer sbf=new StringBuffer();
        try {
            while((read=fileInputStream.read())!=-1)
            {
                sbf.append((char    )read);
            }
        } catch (IOException e) {

        }
    //Toast.makeText(getActivity(),"3 "+sbf.toString(),Toast.LENGTH_SHORT).show();
    String ss[]=sbf.toString().split(" ");
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inSampleSize = 8;
    for(int i=0;i<ss.length;i++) {
        Uri newUri=Uri.parse(ss[i]);
        final Bitmap bitmap = BitmapFactory.decodeFile(newUri.getPath(),
                options);
        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
        String gh="";
        if(ss[0].equals(gh))imageItems.add(new ImageItem(bitmap, " "));
        else
        imageItems.add(new ImageItem(bitmap, "Note#" + i));
    }
//        }
    return imageItems;
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notes4, menu);
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
      //      Toast.makeText(this, "BACK PRESSED", Toast.LENGTH_SHORT).show();
//            Intent intent=new Intent(this,Notes2.class);
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