package com.msu.footprints.fragments;

import java.util.List;

import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class SliderAdapter extends ss.com.bannerslider.adapters.SliderAdapter{

    List<String> list;
    int size;

    SliderAdapter(){
    }

    SliderAdapter(List<String> list, int size){
        this.list = list;
        this.size = size;
    }

    @Override
    public int getItemCount(){
        return size;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder imageSlideViewHolder){
        imageSlideViewHolder.bindImageSlide(list.get(position));
    }
}
