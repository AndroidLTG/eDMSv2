package com.stanstudios.edmsv20.View.RecycleView;

/**
 * Created by DMSv4 on 12/14/2015.
 */
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.stanstudios.edmsv20.R;

public class RowViewHolder extends RecyclerView.ViewHolder {

    TextView productName,productNo;

    public RowViewHolder(View view) {
        super(view);
        this.productName = (TextView) view.findViewById(R.id.productName_);
        this.productNo = (TextView) view.findViewById(R.id.productNo_);
    }
}