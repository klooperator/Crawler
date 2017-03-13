package com.droid.klo.crawler;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;

/**
 * Created by prpa on 3/12/17.
 */

public class CrawledLists extends Fragment {

    //region globals
    private static String TAG = "CrawledList";
    //endregion

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v(TAG,"[cl]...onCreateView");
        View view = inflater.inflate(R.layout.latest_list,container, false);
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //region tab init
        TabHost host = (TabHost)getActivity().findViewById(R.id.tabHost_lists);
        host.setup();

        for(int i=0; i<5;i++){
            TabHost.TabSpec spec = host.newTabSpec("Tab"+i);
            ListView list = new ListView(getActivity());
            spec.setContent((TabHost.TabContentFactory) list);
            spec.setIndicator("Tab"+i);
            host.addTab(spec);
        }
        /*//Tab 1
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
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
