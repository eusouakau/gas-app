package com.example.gas_app.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gas_app.R;

public class LineHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public ImageButton moreButton;
    public ImageButton deleteButton;

    public LineHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.imageView);
    }
}