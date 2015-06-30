package com.semestermanager;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

/**
 * Created by its_PKY on 6/3/2015.
 */
public class SelectDialog extends android.support.v4.app.DialogFragment{

//    public  SelectDialog()
//    {
//        String ss33[]={"Modify","Delete","Cancel"};
//        ListView listView=(ListView)getActivity().findViewById(R.id.listViewdialog);
//        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, ss33);
//
//        listView.setAdapter(adapter);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstance)
    {
        View view=inflater.inflate(R.layout.select_dialog,container,false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    String ss33[]={"Modify","Delete","Cancel"};
        ListView listView=(ListView)view.findViewById(R.id.listViewdialog);
        ArrayAdapter adapter = new ArrayAdapter(view.getContext(), R.layout.support_simple_spinner_dropdown_item, ss33);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               if(position==0)
               {

                   SharedPreferences sharedPreferences=getActivity().getSharedPreferences("MyData", 0);
                   int pos=Integer.parseInt(sharedPreferences.getString("poo",101+""));
                   Intent intent = new Intent(getActivity(), Ass.class);
                   intent.putExtra("check", "here");
                   intent.putExtra("key", pos);
                   startActivity(intent);
                   dismiss();
               }
                if(position==1)
                {
                    //
                    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("MyData", 0);
                    int pos=Integer.parseInt(sharedPreferences.getString("poo",101+""));
                    FileInputStream fileInputStream = null;
                    try {
                        fileInputStream = getActivity().openFileInput("assignment.txt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    int read = -1;
                    StringBuffer sbf = new StringBuffer("");
                    try {
                        while ((read = fileInputStream.read()) != -1) {
                            sbf.append((char) read);
                        }
                    } catch (IOException e) {

                    }
                    String ss2[] = sbf.toString().split("`");
                    Toast.makeText(getActivity(),""+ss2[pos],Toast.LENGTH_SHORT).show();

                    String deletion=ss2[pos];
               //     String newss2[]=new String[ss2.length-1];
                    StringBuffer newsbf=new StringBuffer("");
                    for(int i=0;i<(ss2.length);i++)//abcd
                    {
                        if(!(ss2[i].equals(deletion)))
                            newsbf.append(ss2[i]+"`");
                    }

//
//                    for (int i=0;i<newss2.length;i++)
//                    {
//                        newsbf.append(newss2[i]);
//                    }
                    try {
                        Toast.makeText(getActivity(),newsbf.toString(),Toast.LENGTH_SHORT).show();
//                        PrintWriter pw=new PrintWriter("assignment.txt");
//                        pw.close();


                        //new RandomAccessFile("assignment.txt").setLength(0);
                        File f=new File("assignment.txt");
                        f.delete();
                        FileOutputStream fileOutputStream;

                        fileOutputStream = getActivity().openFileOutput("assignment.txt", Context.MODE_PRIVATE);
                        fileOutputStream = getActivity().openFileOutput("assignment.txt", Context.MODE_APPEND);
                        fileOutputStream.write(newsbf.toString().getBytes());

                        fileOutputStream.flush();
                        Toast.makeText(getActivity(),"YAHA PE",Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }



                }
                if(position==2)
                {
                    dismiss();
                }
            }});
               return view;
    }

}
