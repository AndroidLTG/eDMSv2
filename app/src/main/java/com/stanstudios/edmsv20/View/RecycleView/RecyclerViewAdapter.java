package com.stanstudios.edmsv20.View.RecycleView;

/**
 * Created by DMSv4 on 12/14/2015.
 */
import java.util.ArrayList;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stanstudios.edmsv20.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RowViewHolder> {
    Context context;
    ArrayList<Products> itemsList;

    public RecyclerViewAdapter(Context context, ArrayList<Products> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    @Override
    public int getItemCount() {
        if (itemsList == null) {
            return 0;
        } else {
            return itemsList.size();
        }
    }

    @Override
    public RowViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.single_row, null);
        RowViewHolder viewHolder = new RowViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RowViewHolder rowViewHolder, int position) {
        Products items = itemsList.get(position);
        rowViewHolder.productName.setText(items.getProductName_());
        rowViewHolder.productNo.setText(items.getProductNo_());
    }
}