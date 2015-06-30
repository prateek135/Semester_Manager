package com.semestermanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class Notes3 extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;
int poss;
    boolean is_intent=false;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes3);
setTitle("New Note");
        ActionBar actionBar2=getSupportActionBar();
        actionBar2.setBackgroundDrawable(new ColorDrawable(Color.BLUE));

        try {
            Intent intent = getIntent();
            //poss=Integer.parseInt(intent.getStringExtra("pos")+"");

            String ssa = "nahi";
            if ((intent.getStringExtra("pos")).equals(ssa)) {
                is_intent = false;
            } else {
                is_intent = true;
                poss = Integer.parseInt(intent.getStringExtra("pos") + "") + 0;
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,is_intent+"",Toast.LENGTH_SHORT).show();
        }
        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_notes3, menu);
        return true;
    }
    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event)
    {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
     //       Toast.makeText(this,"BACK PRESSED",Toast.LENGTH_SHORT).show();
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
        if(id==R.id.hirgnotesin) {
            EditText edittext,edittext2;
            edittext = (EditText) findViewById(R.id.editTextN1);
            edittext2 = (EditText) findViewById(R.id.editTextN2);
            String ch = "";
            if (edittext.getText().toString().equals(ch)) {
                Toast.makeText(this, "Please enter the Topic", Toast.LENGTH_SHORT).show();
            }
            else if(edittext2.getText().toString().equals(ch)){Toast.makeText(this, "Please enter the Description!!", Toast.LENGTH_SHORT).show();}
            else {
                if (is_intent) {
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
                    if (!(ss2.length == 1)) {

                        int y = ss2.length;
                        String ss33[] = new String[y - 1];
                        for (int t = 0; t < y - 1; t++) {

                            ss33[t] = ss2[t];
                        }


                        ss33[poss]=edittext.getText().toString();
                        //     String newss2[]=new String[ss2.length-1];
                        StringBuffer newsbf = new StringBuffer("");
                        for (int i = 0; i < (ss33.length); i++)//abcd
                        {

                                newsbf.append(ss33[i] + "332223");
                        }

//
//                    for (int i=0;i<newss2.length;i++)
//                    {
//                        newsbf.append(newss2[i]);
//                    }
                        try {
                            Toast.makeText(this, newsbf.toString(), Toast.LENGTH_SHORT).show();
//                        PrintWriter pw=new PrintWriter("assignment.txt");
//                        pw.close();


                            //new RandomAccessFile("assignment.txt").setLength(0);
                            File f = new File("topic_notes.txt");
                            f.delete();
                            FileOutputStream fileOutputStream;

                            fileOutputStream = openFileOutput("topic_notes.txt", Context.MODE_PRIVATE);
                            fileOutputStream = openFileOutput("topic_notes.txt", Context.MODE_APPEND);
                            fileOutputStream.write(newsbf.toString().getBytes());

                            fileOutputStream.flush();
                            Toast.makeText(this, "YAHA PE", Toast.LENGTH_SHORT).show();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

                else {
                    //   Toast.makeText(this, " " + edittext.getText().toString(), Toast.LENGTH_SHORT).show();
                    FileOutputStream fileOutputStream;
                    try {
                        fileOutputStream = openFileOutput("topic_notes.txt", Context.MODE_APPEND);
                        fileOutputStream.write((edittext.getText().toString() + "332223").getBytes());
                        fileOutputStream.flush();
                        fileOutputStream = openFileOutput("desc_notes.txt", Context.MODE_APPEND);
                        fileOutputStream.write((edittext2.getText().toString() + "332223").getBytes());
                        fileOutputStream.flush();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    SharedPreferences sharedPreferences = getSharedPreferences("MyData", 0);

                    int no_of_notes = (sharedPreferences.getInt("no_of_notes", 0)) + 1;
                    sharedPreferences.edit().putInt("no_of_notes", no_of_notes).commit();
                    Intent intent = new Intent(this, Notes2.class);
                    startActivity(intent);
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.

        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

//    public void add()
//    {

//    }
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

                if(position==0) {
                    Fragment fragment = new Notes22();
                    if (is_intent) {

                        EditText editText1 = (EditText) findViewById(R.id.editTextN1);
                        EditText editText2 = (EditText) findViewById(R.id.editTextN2);
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
                        if (!(ss2.length == 1)) {

                            int y = ss2.length;
                            String ss33[] = new String[y - 1];
                            for (int t = 0; t < y - 1; t++) {

                                ss33[t] = ss2[t];
                            }
                            Bundle bundle = new Bundle();
                            bundle.putString("key1", ss33[poss]);
                            fragment.setArguments(bundle);
                        }


                    }
                    else
                    {

                        Bundle bundle = new Bundle();
                        bundle.putString("key1", "h11");
                        fragment.setArguments(bundle);
                    }

//                }catch(Exception e){}

                    return fragment;
            }
            if(position==1){
                Bundle bundle;
                if (is_intent) {
                    bundle = new Bundle();
                    bundle.putString("key2", poss + "");

                }
                else
                {
                    bundle = new Bundle();
                    bundle.putString("key2", "h11");

                }
                Fragment fragment = new Notes23();
                fragment.setArguments(bundle);

                return fragment;}

            return null;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "Theory";
                case 1:
                    return "Images";

            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_notes3, container, false);
            return rootView;
        }
    }

}