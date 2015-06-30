package com.semestermanager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class Notes23 extends android.support.v4.app.Fragment implements  View.OnClickListener {
    int no_of_notes;
    public Uri fileUri;
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    ImageView imgPreview;
    String ss[];
    Button button;
    View view;
    private GridView gridView;
    private GridViewAdapter gridAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      view = inflater.inflate(R.layout.fragment_notes23, container, false);
        String hh="h11",gett="";

        if ((getArguments().getString("key2").equals(hh)))
        {
            SharedPreferences sharedPreferences=getActivity().getSharedPreferences("MyData",0);
            no_of_notes=sharedPreferences.getInt("no_of_notes",0);
        }
        else {
            no_of_notes = Integer.parseInt(getArguments().getString("key2") + "")+0;

        }

           imgPreview = (ImageView)view.findViewById(R.id.imageViewN);

        button = (Button) view.findViewById(R.id.cambutton);
        button.setOnClickListener(this);
        gridView = (GridView) view.findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(getActivity(), R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                //Create intent
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("title", item.getTitle());
                intent.putExtra("image", item.getImage());

                //Start details activity
                startActivity(intent);
            }
        });

        return view;
    }

    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
//        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
//        for (int i = 0; i < imgs.length(); i++) {
            FileInputStream fileInputStream= null;
            try {
                fileInputStream = getActivity().openFileInput(no_of_notes+".txt");
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
            ss=sbf.toString().split(" ");
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
                imageItems.add(new ImageItem(bitmap, "Image#" + i));
            }
//        }
        return imageItems;
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(1);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(intent, 100);
        // Toast.makeText(getActivity(),"pky",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        if (requestCode == 100) {
            if (resultCode == getActivity().RESULT_OK) {
//                Toast.makeText(getActivity(),
//                        "Captured", Toast.LENGTH_SHORT)
//                        .show();
                previewCapturedImage();
                gridAdapter = new GridViewAdapter(getActivity(), R.layout.grid_item_layout, getData());
                gridView.setAdapter(gridAdapter);

            }
            // successfully captured the image
            // display it in image view
            //            previewCapturedImage();
        } else if (resultCode == getActivity().RESULT_CANCELED) {
            // user cancelled Image capture
            Toast.makeText(getActivity(),
                    "User cancelled image capture", Toast.LENGTH_SHORT)
                    .show();
        } else {
            // failed to capture image
            Toast.makeText(getActivity(),
                    "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                    .show();
        }
    }


    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    public static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == 1) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        }
         else {
            return null;
        }

        return mediaFile;
    }
    public void previewCapturedImage() {
        FileOutputStream fileOutputStream=null;
        try {
            // hide video preview
          //  videoPreview.setVisibility(View.GONE);

       //     imgPreview.setVisibility(View.VISIBLE);

            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;
            File file=getActivity().getFilesDir();
         //   File nn=new File("Adds1.txt");
            fileOutputStream=getActivity().openFileOutput(no_of_notes+".txt",Context.MODE_APPEND);
            fileOutputStream.write((fileUri.toString() + " ").getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);

            imgPreview.setImageBitmap(bitmap);
//            Toast.makeText(getActivity(),"Got Here "+fileUri.toString(),Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(),"2 "+file.toString(),Toast.LENGTH_SHORT).show();

 }
        catch (NullPointerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // TODO Auto-generated method stub
//        super.onActivityResult(requestCode, resultCode, data);
//        Bitmap bp = (Bitmap) data.getExtras().get("data");
//        imgFavorite.setImageBitmap(bp);
//    }




}