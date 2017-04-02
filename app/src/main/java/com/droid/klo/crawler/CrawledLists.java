package com.droid.klo.crawler;

import android.app.Fragment;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.droid.klo.crawler.contentProvider.DaoCP;
import com.droid.klo.crawler.db.Source;

import java.util.Iterator;
import java.util.List;

/**
 * Created by prpa on 3/12/17.
 */

public class CrawledLists extends Fragment {

    //region globals
    private static String TAG = "CrawledList";
    public int bttn_count;
    DaoCP dao;
    LinearLayout ll;
    //endregion

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(TAG,"onCreateView");
        View view = inflater.inflate(R.layout.source_list,container, false);
        //view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		((MainActivity)getActivity()).dissableToolbarBack();
        ll = (LinearLayout) getActivity().findViewById(R.id.layout_search_list);
        bttn_count = 0;
        dao = new DaoCP(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.v(TAG,"onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        Button bttn_add = (Button)getActivity().findViewById(R.id.bttn_add_new);
        bttn_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((MainActivity)getActivity()).addBackButton();
                getActivity().getFragmentManager().beginTransaction().replace(R.id.placeholder,new AddSourceFragment(),"add_source").addToBackStack("add_source").commit();

            }
        });


        List<Source> sources = dao.getSources();
        for(Iterator<Source> i = sources.iterator(); i.hasNext();){
            Source s = i.next();
            addButton(s.getName(), s.getId());
        }
		

    }

    @Override
    public void onStart() {
        Log.v(TAG,"onStart");
        super.onStart();
    }

    public void addButton(final String text, final long sourceID){
        Log.d(TAG, "addButton");

        RelativeLayout rl = new RelativeLayout(getActivity());
        rl.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        RelativeLayout.LayoutParams rlParams = (RelativeLayout.LayoutParams) rl.getLayoutParams();
        rlParams.setMargins(0,5,0,5);
        GradientDrawable border = new GradientDrawable();
        border.setColor(0xFFFFFFFF); //white background
        border.setStroke(1, 0xFF000000); //black border with full opacity
        rl.setBackground(border);
        rl.setLayoutParams(rlParams);

        TextView sName = new TextView(getActivity());
        sName.setText(text);
        sName.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        RelativeLayout.LayoutParams sNameParams = (RelativeLayout.LayoutParams) sName.getLayoutParams();
        sNameParams.addRule(RelativeLayout.ALIGN_PARENT_START);

        sName.setLayoutParams(sNameParams);
        sName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        sName.setId(View.generateViewId());


        TextView sCount = new TextView(getActivity());
        String notViewedCount = dao.getNotViewedResults(sourceID);
        if(notViewedCount == null || notViewedCount.contentEquals(""))notViewedCount="0";
        sCount.setText(notViewedCount);
        sCount.setId(View.generateViewId());
        sCount.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        RelativeLayout.LayoutParams sCountParams = (RelativeLayout.LayoutParams) sCount.getLayoutParams();
        sCountParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        sCountParams.addRule(RelativeLayout.ALIGN_BOTTOM, sName.getId());
        sCount.setLayoutParams(sCountParams);
        sCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);



        rl.addView(sName);
        rl.addView(sCount);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();

                b.putLong("source_id", sourceID);
                b.putString("source_name", text);
                ListData ld = new ListData();
                ld.setArguments(b);
                getActivity().getFragmentManager().beginTransaction().replace(R.id.placeholder, ld, "ld_" + text).addToBackStack("ld_" + text).commit();
                ((MainActivity)getActivity()).addBackButton();
            }
        });

        Log.i(TAG, "rl = = = "+rl.toString());
        if(ll == null)ll = (LinearLayout) getActivity().findViewById(R.id.layout_search_list);
        this.ll.addView(rl, 0);

        /*Button bttn = new Button(getActivity());
        bttn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        bttn.setText(text);
        bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b= new Bundle();
                b.putString("message", "bttn"+bttn_count);
                ListData ld = new ListData();
                ld.setArguments(b);
                getActivity().getFragmentManager().beginTransaction().replace(R.id.placeholder,ld,"ld"+bttn_count).addToBackStack("ld"+bttn_count).commit();
				((MainActivity)getActivity()).addBackButton();
            }
        });
        LinearLayout ll = (LinearLayout) getActivity().findViewById(R.id.layout_search_list);
        ll.addView(bttn, 0);
        bttn_count++;*/
    }

	@Override
	public void onResume()
	{
		Log.d(TAG,"onResume");
		
		super.onResume();
	}
	
	
}

