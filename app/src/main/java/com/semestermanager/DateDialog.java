package com.semestermanager;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
public class DateDialog extends DialogFragment implements View.OnClickListener{

	Button bt;
	public View onCreateView(LayoutInflater infaltor,ViewGroup Container,Bundle savedInstanceState)
	{
		View view=infaltor.inflate(R.layout.activity_date_dialog, null);
		bt=(Button)view.findViewById(R.id.button1);
		return view;
	
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
	//	Toast.makeText(getActivity(), "text", Toast.LENGTH_SHORT).show();
		dismiss();
	}
	
}
