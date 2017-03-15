package com.droid.klo.crawler;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;

import com.droid.klo.crawler.db.Dao;


public class MainActivity extends AppCompatActivity {
	
	ActionBar actionBar;
	Dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
		actionBar=getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		dao = new Dao(this);
		dao.open();
		

        getFragmentManager().beginTransaction().replace(R.id.placeholder,new CrawledLists(),"cl").commit();


    }
	public void addBackButton(){
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if(item.getItemId() == android.R.id.home){
			onBackPressed();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void dissableToolbarBack(){
		//actionBar.setDefaultDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
		}
	public Dao getDao(){
		return this.dao;
	}
}
