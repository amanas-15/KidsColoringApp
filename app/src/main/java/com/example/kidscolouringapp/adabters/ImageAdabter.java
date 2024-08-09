package com.example.kidscolouringapp.adabters;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kidscolouringapp.Interface.ImageonClick;
import com.example.kidscolouringapp.PaintActivity;
import com.example.kidscolouringapp.R;
import com.example.kidscolouringapp.viewholder.imageviewholder;

import java.util.ArrayList;
import java.util.List;

import common.Common;
import widget.PaintView;


public class ImageAdabter extends RecyclerView.Adapter<imageviewholder> {
    
    
    private Context context;
    private List<Integer> ListImages;
    
    public ImageAdabter(Context context){
        this.context=context;
        this.ListImages=getImages();
    }

    private List<Integer> getImages()

    {

        List<Integer> results = new ArrayList<>();



        results.add(R.drawable.batak);
        results.add(R.drawable.chair);
        results.add(R.drawable.flower1);
        results.add(R.drawable.leaf1);
        results.add(R.drawable.sun);
        results.add(R.drawable.tatto);
        results.add(R.drawable.toy);
        results.add(R.drawable.tree);
        results.add(R.drawable.umbrella);



        return results;
    }

    @NonNull
    @Override
    public imageviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_images,parent,false);
        return new imageviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull imageviewholder holder, int position) {

        holder.imageView.setImageResource(ListImages.get(position));

        holder.setImageonClick(new ImageonClick() {
            @Override
            public void onClick(int pos) {
                Common.PICTURE_SELECTED = ListImages.get(pos);
                context.startActivity(new Intent(context, PaintActivity.class));
            }
        });

    }


    @Override
    public int getItemCount() {
        return ListImages.size();
    }
}
