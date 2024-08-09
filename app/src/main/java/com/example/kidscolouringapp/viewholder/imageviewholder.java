package com.example.kidscolouringapp.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidscolouringapp.Interface.ImageonClick;
import com.example.kidscolouringapp.R;

public class imageviewholder extends RecyclerView.ViewHolder {

    public ImageView imageView;

    private ImageonClick imageonClick;

    public void setImageonClick(ImageonClick imageonClick) {
        this.imageonClick = imageonClick;
    }


    public imageviewholder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image_outline);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageonClick.onClick(getAdapterPosition());

            }


        });

    }

}




