package com.droid.klo.crawler;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.droid.klo.crawler.db.Dao;
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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_source,container, false);


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button bttn_save = (Button) getActivity().findViewById(R.id.button_save_source);
        bttn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name;
                String link;
                int top_val;
                int bot_val;
                Source s=null;

                TextView txt_name=(TextView) getActivity().findViewById(R.id.source_name);
                TextView txt_link=(TextView) getActivity().findViewById(R.id.source_link);
                TextView txt_top_value=(TextView) getActivity().findViewById(R.id.source_top_value);
                TextView txt_bottom_value=(TextView) getActivity().findViewById(R.id.source_bottom_value);


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
                    s = new Source(name,link,top_val,bot_val);
                    Dao dao=((MainActivity)getActivity()).getDao();
                    if(dao.isOpen()){
                        dao.insertSource(s);
                        getActivity().onBackPressed();
                    }
                    else{
                        dao.open();
                        dao.insertSource(s);
                        getActivity().onBackPressed();
                    }
                }


            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
