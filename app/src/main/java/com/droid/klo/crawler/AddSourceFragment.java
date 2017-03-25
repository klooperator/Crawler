package com.droid.klo.crawler;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.droid.klo.crawler.contentProvider.DaoCP;
import com.droid.klo.crawler.db.Source;

import static com.droid.klo.crawler.R.color.redError;

/**
 * Created by prpa on 3/15/17.
 */

public class AddSourceFragment extends Fragment {

    //region variables
    private static final String TAG = "AddSourceFragment";
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_source,container, false);
        Log.d(TAG, "onCreateView");


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
        Button bttn_save = (Button) getActivity().findViewById(R.id.button_save_source);
        bttn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name;
                String link;
                int top_val;
                int bot_val;
                int vauvau;
                Source s=null;

                TextView txt_name=(TextView) getActivity().findViewById(R.id.source_name);
                TextView txt_link=(TextView) getActivity().findViewById(R.id.source_link);
                TextView txt_top_value=(TextView) getActivity().findViewById(R.id.source_top_value);
                TextView txt_bottom_value=(TextView) getActivity().findViewById(R.id.source_bottom_value);
                ToggleButton vau_toggle= (ToggleButton) getActivity().findViewById((R.id.vauvau));


                if(vau_toggle.isChecked())vauvau=1;
                else vauvau=0;

                if((txt_name.getText().toString()).matches("")){
                    txt_name.setBackgroundResource(redError);
                    name=null;
                }else name=txt_name.getText().toString();

                if((txt_link.getText().toString()).matches("")){
                    txt_link.setBackgroundResource(redError);
                    link=null;
                }else link=txt_link.getText().toString();

                if((txt_top_value.getText().toString()).matches("")){
                    top_val=-1;
                }else top_val=Integer.parseInt(txt_top_value.getText().toString());

                if((txt_bottom_value.getText().toString()).matches("")){
                    bot_val=-1;
                }else bot_val=Integer.parseInt(txt_bottom_value.getText().toString());

                if(name==null || link==null)return;
                else{
                    s = new Source(name,link,top_val,bot_val,vauvau);
                    DaoCP dao = new DaoCP(getActivity());
                    dao.insertSource(s);
                    ((MainActivity)getActivity()).updateService();
                    getActivity().onBackPressed();

                }


            }
        });
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }
}
