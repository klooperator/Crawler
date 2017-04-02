package com.droid.klo.crawler;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.droid.klo.crawler.contentProvider.DaoCP;
import com.droid.klo.crawler.db.Result;

import java.util.List;

/**
 * Created by prpa on 3/13/17.
 */

public class ListData extends Fragment{

    //region variables
    private static final String TAG = "ListData";
    private List<Result> resaults;
    DaoCP dao;
    private String sourceName;
    private long sourceId;
    //endregion

    //region overrides

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        this.sourceName = this.getArguments().getString("source_name");
        this.sourceId = this.getArguments().getLong("source_id");


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.latest_list,container, false);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
        dao = new DaoCP(getActivity());
        this.resaults = dao.getResults(sourceId, 0, 20);

        ListView mListView = (ListView)getActivity().findViewById(R.id.result_list);
        if(mListView == null) Log.w(TAG, "reuslt_list === null");
        lvAdapter listAdapter = new lvAdapter(resaults);
        mListView.setAdapter(listAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Result r = resaults.get(position);

                Bundle b = new Bundle();
                b.putString("title", r.getTitle());
                b.putString("content", r.getContent());
                b.putString("link", r.getLink());
                b.putString("selle", r.getSeller());
                b.putString("phone", r.getPhone_number());
                b.putInt("price", r.getPrice());

                SignleResult sr = new SignleResult();
                sr.setArguments(b);
                getActivity().getFragmentManager().beginTransaction().replace(R.id.placeholder, sr, "sr_" + id).addToBackStack("sr_" + id).commit();
                ((MainActivity)getActivity()).addBackButton();
            }
        });
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



    //endregion

    //region listview adapter
    private class lvAdapter extends BaseAdapter{

        private List<Result> resultList;
        private LayoutInflater lInflater;

        public  lvAdapter(List<Result> resultList){
            Log.d(TAG, "lvAdapter");
            this.resultList=resultList;
            //this.lInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.lInflater = (LayoutInflater) getActivity().getLayoutInflater();
        }


        @Override
        public int getCount() {
            Log.d(TAG, "getCount");
            return resaults.size();
        }

        @Override
        public Object getItem(int position) {
            Log.d(TAG, "getItem");
            return resultList.get(position);
        }

        @Override
        public long getItemId(int position) {
            Log.d(TAG, "getItemId");
            return resultList.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.d(TAG, "getView");
            ViewHolder viewHolder;

            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = this.lInflater.inflate(R.layout.list_item, parent, false);
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_title);
                viewHolder.price = (TextView) convertView.findViewById(R.id.list_item_price);
                viewHolder.seller = (TextView) convertView.findViewById(R.id.list_item_seller);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.title.setText(this.resultList.get(position).getTitle());
            viewHolder.seller.setText(this.resultList.get(position).getSeller());
            viewHolder.price.setText(""+this.resultList.get(position).getPrice());

            return convertView;
        }
    }

    static class ViewHolder {

        TextView title;
        TextView price;
        TextView seller;

    }
    //endregion
}
