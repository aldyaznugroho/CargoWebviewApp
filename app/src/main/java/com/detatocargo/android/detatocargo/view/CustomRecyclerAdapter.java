package com.detatocargo.android.detatocargo.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aldyaz on 7/19/2017.
 */

public class CustomRecyclerAdapter<T> extends RecyclerView.Adapter<CustomRecyclerAdapter.CustomViewHolder<T>> {
    private Context context;
    private int layoutResource;
    private List<T> itemList;
    private ViewHolderBinder<T> viewHolderBinder;

    public CustomRecyclerAdapter(Context context, int layoutResource, ViewHolderBinder<T> viewHolderBinder) {
        this.context = context;
        this.layoutResource = layoutResource;
        this.viewHolderBinder = viewHolderBinder;
        itemList = new ArrayList<>();
    }

    @Override
    public CustomViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(layoutResource, parent, false);
        return new CustomViewHolder<>(customView, viewHolderBinder);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder<T> holder, int position) {
        T item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void addAll(List<T> items) {
        itemList.addAll(items);
    }

    public void add(T item) {
        itemList.add(item);
    }

    public void cleanUp() {
        while (getItemCount() > 0) {
            removeItem(getItem(0));
        }
    }

    public void removeItem(T item) {
        int position = itemList.indexOf(item);
        if (position > -1) {
            itemList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public T getItem(int position) {
        return itemList.get(position);
    }

    public interface ViewHolderBinder<T> {
        void bind(CustomViewHolder<T> holder, T item);
    }

    public static class CustomViewHolder<T> extends RecyclerView.ViewHolder {
        private ViewHolderBinder<T> binder;

        public CustomViewHolder(View itemView, ViewHolderBinder<T> binder) {
            super(itemView);
            this.binder = binder;
        }

        public void bind(T item) {
            binder.bind(this, item);
        }
    }
}
