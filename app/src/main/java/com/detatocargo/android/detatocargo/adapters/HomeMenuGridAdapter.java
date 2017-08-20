package com.detatocargo.android.detatocargo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.detatocargo.android.detatocargo.R;

/**
 * Created by Aldyaz on 7/5/2016.
 */
public class HomeMenuGridAdapter extends BaseAdapter {

    private Context context;
    private final int[] menuName;
    private final int[] menuImage;

    public HomeMenuGridAdapter(Context context, int[] menuName, int[] menuImage) {
        this.context = context;
        this.menuName = menuName;
        this.menuImage = menuImage;
    }

    @Override
    public int getCount() {
        return menuName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gridView;

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.custom_menu_grid, parent, false);

            TextView textViewMenu = (TextView) gridView.findViewById(R.id.home_menu_grid_text);
            ImageView imageViewMenu = (ImageView) gridView.findViewById(R.id.home_menu_grid_image);

            textViewMenu.setText(menuName[position]);
            imageViewMenu.setImageResource(menuImage[position]);
        } else {
            gridView = convertView;
        }

        return gridView;
    }

}
