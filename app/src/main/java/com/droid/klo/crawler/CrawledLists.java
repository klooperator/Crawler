package com.droid.klo.crawler;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by prpa on 3/12/17.
 */

public class CrawledLists extends Fragment {

    //region globals
    private static String TAG = "CrawledList";
    public int bttn_count;
    //endregion

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(TAG,"[cl]...onCreateView");
        View view = inflater.inflate(R.layout.latest_list,container, false);
        //view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
		((MainActivity)getActivity()).dissableToolbarBack();
        bttn_count = 0;

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button bttn_add = (Button)getActivity().findViewById(R.id.bttn_add_new);
        bttn_add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addButton();
            }
        });
		

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void addButton(){
        Button bttn = new Button(getActivity());
        bttn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        bttn.setText("bttn"+bttn_count);
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
        bttn_count++;
    }

	@Override
	public void onResume()
	{
		Log.d("cl","onResume");
		
		super.onResume();
	}
	
	
}
//region tab init
       /* TabHost host = (TabHost)getActivity().findViewById(R.id.tabHost_lists);
        host.setup();

        for(int i=0; i<5;i++){
            TabHost.TabSpec spec = host.newTabSpec("Tab"+i);
            ListView list = new ListView(getActivity());
            spec.setContent((TabHost.TabContentFactory) list);
            spec.setIndicator("Tab"+i);
            host.addTab(spec);
        }
        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        ListView list = new ListView(getActivity());
        //spec.setContent(R.id.tab2);
        spec.setIndicator("Tab One");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        //spec.setContent(R.id.tab2);
        spec.setIndicator("Tab Two");
        host.addTab(spec);*/

//endregion
