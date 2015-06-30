package com.semestermanager;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Notes22 extends android.support.v4.app.Fragment {

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_notes22, container, false);
        String hh="h11",gett="";
        EditText tv1=(EditText)view.findViewById(R.id.editTextN1);
        if ((getArguments().getString("key1").equals(hh)))
        {

        }
        else {
            gett = getArguments().getString("key1") + "";
            tv1.setText(gett);
        }

        EditText tv2=(EditText)view.findViewById(R.id.editTextN2);
        tv1.setHint("Topic");

        tv2.setHint("Description");
        return view;
    }

    public String test11()
    {
        EditText tv1=(EditText)view.findViewById(R.id.editTextN1);
        EditText tv2=(EditText)view.findViewById(R.id.editTextN2);
        return tv1.getText().toString()+"332223"+tv2.getText().toString();
    }
}
