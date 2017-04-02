package com.droid.klo.crawler;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by prpa on 4/1/17.
 */

public class SignleResult extends Fragment {

    //region variables
    private static final String TAG = "SignleResult";

    private String title;
    private String link;
    private String seller;
    private String content;
    private int price;
    private String phone;
    //endregion

    //region overrides

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.title = this.getArguments().getString("title");
        this.link = this.getArguments().getString("link");
        this.seller = this.getArguments().getString("seller");
        this.content = this.getArguments().getString("content");
        this.phone = this.getArguments().getString("phone");
        this.price = this.getArguments().getInt("price");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.singe_result,container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvTitle = (TextView)getActivity().findViewById(R.id.sr_title);
        tvTitle.setText(this.title);

        TextView tvContent = (TextView)getActivity().findViewById(R.id.sr_content);
        tvContent.setText(this.content);

        TextView tvPrice = (TextView)getActivity().findViewById(R.id.sr_price);
        tvPrice.setText(""+this.price);

        TextView tvLink = (TextView)getActivity().findViewById(R.id.sr_link);
        tvLink.setText(this.link);

        Button bPhone = (Button)getActivity().findViewById(R.id.sr_phone);
        bPhone.setText(this.phone);

        ToggleButton tbSeller = (ToggleButton)getActivity().findViewById(R.id.sr_seller);
        tbSeller.setText(this.seller);


    }

    //endregion
}
