package com.droid.klo.crawler;

import android.app.Fragment;
import android.os.Bundle;
import android.widget.*;

/**
 * Created by prpa on 3/13/17.
 */

public class ListData extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String myValue = this.getArguments().getString("message");
		LinearLayout ll=new LinearLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
