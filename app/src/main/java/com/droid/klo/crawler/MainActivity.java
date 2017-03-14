package com.droid.klo.crawler;

import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
		ActionBar actionBar=getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		

        getFragmentManager().beginTransaction().replace(R.id.placeholder,new CrawledLists(),"cl").commit();


    }


}
